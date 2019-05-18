package com.zxelec.cpug.vl.util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.zxelec.cpug.vl.entity.MessageMetaDataType;
import com.zxelec.cpug.vl.entity.VissMessage;


/**
 * viss message convert util.
 *  详见 《高并发消息总线接口规范.docx》
 *
 */
public class VissMessageConvertUtils {

		public static final String DEAFAULT_CHRSET = "UTF-8";

		private static final Logger LOGGER = LogManager.getLogger(VissMessageConvertUtils.class);
		
		
		/**
		 * viss byte message to pojo.
		 *
		 * @param message
		 *            byte[] message
		 * @param clzss
		 *            转换class
		 * @param <T>
		 *            class
		 * @return pojo
		 * @throws UnsupportedEncodingException
		 *             转换异常.
		 */
		public static <T> VissMessage<T> resolveVissMessage(byte[] message, Class<T> clzss)
				throws UnsupportedEncodingException {
			int msgType = validate(message);
			if (msgType < 0) {
				throw new IllegalArgumentException("Reject wrong message");
			}
			VissMessage<T> vissMessage = new VissMessage<T>();
			int offset = 0;
			byte[] btStartCode = new byte[4];
			System.arraycopy(message, offset, btStartCode, 0, btStartCode.length);
			offset += btStartCode.length;
			String startCode = new String(btStartCode, DEAFAULT_CHRSET);
			vissMessage.setStartCode(startCode);

			// head json length
			int headLength = byte2Int(message, offset, msgType);
			offset += 4;

			if (headLength > 0) {
				// 获取MessageMetaDataType
				byte[] typeLength = new byte[headLength];
				System.arraycopy(message, offset, typeLength, 0, typeLength.length);
				offset += typeLength.length;
				String typeStr = new String(typeLength, DEAFAULT_CHRSET);
				MessageMetaDataType type = JSONObject.parseObject(typeStr, MessageMetaDataType.class);
				vissMessage.setType(type);
			}

			// 获取body
			int bodyLength = byte2Int(message, offset, msgType);
			offset += 4;
			if (bodyLength > 0) {
				byte[] byBody = new byte[bodyLength];
				System.arraycopy(message, offset, byBody, 0, byBody.length);
				offset += byBody.length;
				String bodyStr = new String(byBody, DEAFAULT_CHRSET);
				try {
					T body = JSONObject.parseObject(bodyStr, clzss);
					vissMessage.setBody(body);
				} catch (RuntimeException e) {
					e.printStackTrace();
					LOGGER.error("解析VISS 消息异常"+e.getMessage());
					LOGGER.error("viss message body : 【" + bodyStr + "】");
				}
			}

			// 获取binary data
			int binaryDatasLength = byte2Int(message, offset, msgType);
			offset += 4;
			if (binaryDatasLength > 0) {
				List<byte[]> binaryData = new ArrayList<byte[]>();
				int fileLengthTotal = 0;
				while (binaryDatasLength > fileLengthTotal) {
					int fileLength = byte2Int(message, offset, msgType);
					offset += 4;
					byte[] btFile = new byte[fileLength];
					System.arraycopy(message, offset, btFile, 0, btFile.length);
					offset += btFile.length;
					fileLengthTotal += 4;
					fileLengthTotal += btFile.length;
					binaryData.add(btFile);
				}
				vissMessage.setBinaryData(binaryData);
			}
			return vissMessage;
		}

		/**
		 * body to viss byte[].
		 *
		 * @param sender
		 *            发送者标识（如CPSS的设备编号）
		 * @param source
		 *            数据来源
		 * @param body
		 *            body内容
		 * @param <T>
		 *            body类型
		 * @return byte[]
		 * @throws UnsupportedEncodingException
		 *             转换异常
		 */
		public static <T> byte[] vissMessage2Byte(String sender, String source, T body)
				throws UnsupportedEncodingException {
			VissMessage<T> vissMessage = new VissMessage<T>();
			MessageMetaDataType type = new MessageMetaDataType();
			type.setSender(sender);
			type.setSendTime(new Date());
			type.setSouce(source);
			vissMessage.setType(type);
			vissMessage.setBody(body);
			return vissMessage2Byte(vissMessage);
		}

		/**
		 * body to viss byte[].
		 * 
		 * @param sender
		 *            发送者标识（如CPSS的设备编号）
		 * @param source
		 *            数据来源
		 * @param body
		 *            body内容
		 * @param bin
		 *            二进制文件
		 * @return 二进制
		 * @throws UnsupportedEncodingException
		 *             转换异常.
		 */
		public static <T> byte[] vissMessage2Byte(String sender, String source, T body, List<byte[]> bin)
				throws UnsupportedEncodingException {
			VissMessage<T> vissMessage = new VissMessage<T>();
			MessageMetaDataType type = new MessageMetaDataType();
			type.setSender(sender);
			type.setSendTime(new Date());
			type.setSouce(source);
			vissMessage.setType(type);
			vissMessage.setBody(body);
			vissMessage.setBinaryData(bin);
			return vissMessage2Byte(vissMessage);
		}

		/**
		 * attach viss message to byte[].
		 *
		 * @param vissMessage
		 *            viss message
		 * @return message
		 * @throws UnsupportedEncodingException
		 *             转换异常
		 */
		public static byte[] vissMessage2Byte(VissMessage<?> vissMessage) throws UnsupportedEncodingException {
			MessageMetaDataType type = vissMessage.getType();
			if (type == null) {
				throw new IllegalArgumentException("VissMessage.type can not be null");
			}
			LOGGER.debug("发送订阅到CPSS :" + JSONObject.toJSONString(vissMessage));
			int length = 0;
			byte[] startCode = vissMessage.getStartCode().getBytes(DEAFAULT_CHRSET);
			// start code length
			length += startCode.length;
			// head
			String typeJsonStr = toJSONString(type);
			byte[] messageMetaDataTypeByte = typeJsonStr.getBytes(DEAFAULT_CHRSET);
			byte[] headJsonLength = int2Byte(typeJsonStr.length());
			length += headJsonLength.length;
			length += messageMetaDataTypeByte.length;
			// body
			String bodyJsonStr = toJSONString(vissMessage.getBody());
			byte[] bodyByte = bodyJsonStr.getBytes(DEAFAULT_CHRSET);
			byte[] bodyLengthByte = int2Byte(bodyJsonStr.length());
			length += bodyLengthByte.length;
			length += bodyByte.length;
			// file body
			int fileTotalLengt = 0;
			if (vissMessage.getBinaryData() != null && vissMessage.getBinaryData().size() > 0) {
				for (byte[] bs : vissMessage.getBinaryData()) {
					length += 4;
					length += bs.length;
					fileTotalLengt += 4;
					fileTotalLengt += bs.length;
				}
			}
			byte[] binaryDatasByte = int2Byte(fileTotalLengt);
			length += binaryDatasByte.length;

			byte[] result = new byte[length];
			int l = 0;
			// startCode
			copyArray(l, startCode, result);
			l += startCode.length;
			// head_json_length
			copyArray(l, headJsonLength, result);
			l += headJsonLength.length;
			// head_json_byte
			copyArray(l, messageMetaDataTypeByte, result);
			l += messageMetaDataTypeByte.length;
			// pack_body : json_length
			copyArray(l, bodyLengthByte, result);
			l += bodyLengthByte.length;
			// pack_body : pack_body
			copyArray(l, bodyByte, result);
			l += bodyByte.length;
			// binary_datas_length 为后续所有二进制数据的总字节数。（0表示无二进制数据）
			copyArray(l, binaryDatasByte, result);
			l += binaryDatasByte.length;
			// file body
			if (vissMessage.getBinaryData() != null && vissMessage.getBinaryData().size() > 0) {
				for (byte[] bs : vissMessage.getBinaryData()) {
					int bsLength = bs.length;
					byte[] bsLengthByte = int2Byte(bsLength);
					copyArray(l, bsLengthByte, result);
					l += bsLengthByte.length;
					copyArray(l, bs, result);
					l += bs.length;
				}
			}
			return result;

		}

		/**
		 * integer to byte[].
		 *
		 * @param n
		 *            integer
		 * @return byte
		 */
		public static byte[] int2Byte(int n) {
			byte[] bytes = new byte[4];
	        bytes[3] = (byte) (n & 0xFF);
	        bytes[2] = (byte) (n >> 8 & 0xFF);
	        bytes[1] = (byte) (n >> 16 & 0xFF);
	        bytes[0] = (byte) (n >> 24 & 0xFF);
			return bytes;
		}

		/**
		 * byte[] to integer (大小端) .
		 *
		 * @param b
		 *            byte[]
		 * @param offset
		 *            offset
		 * @param type
		 *            type
		 * @return integer.
		 */
		public static int byte2Int(byte[] b, int offset, int type) {
			if (type == 0) {
				return (((b[offset] & 0xFF) << 24) | ((b[offset + 1] & 0xFF) << 16) | ((b[offset + 2] & 0xFF) << 8)
						| ((b[offset + 3] & 0xFF)));
			} else {
				return (((b[offset + 3] & 0xFF) << 24) | ((b[offset + 2] & 0xFF) << 16) | ((b[offset + 1] & 0xFF) << 8)
						| ((b[offset + 0] & 0xFF)));
			}

		}

		private static void copyArray(int length, byte[] source, byte[] dest) {
			System.arraycopy(source, 0, dest, length, source.length);
		}

		/**
		 * 验证消息完整性.
		 *
		 * @param message
		 *            byte数组
		 * @return int整数 0：消息Big-Endian 1：Little-Endian -1：消息不完整.
		 */
		private static int validate(byte[] message) {
			try {
				if (message == null || message.length < 13) {
					return -1;
				}
				if (validateWithEndian(message, 0)) {
					return 0;
				} else if (validateWithEndian(message, 1)) {
					return 1;
				} else {
					return -1;
				}
			} catch (Exception e) {
				return -1;
			}
		}

		private static boolean validateWithEndian(byte[] message, int type) {
			try {
				final int lenTotal = message.length;
				int offset = 0;
				offset += 4;
				int headLen = byte2Int(message, offset, type);
				offset += 4 + headLen;
				int jsonLen = byte2Int(message, offset, type);
				offset += 4 + jsonLen;
				int imagesLen = byte2Int(message, offset, type);
				offset += 4;
				int imageLenSum = 0;

				while (message.length > (offset + 4)) {
					int imgLen = byte2Int(message, offset, type);
					offset += 4 + imgLen;
					imageLenSum += 4 + imgLen;
				}

				return (imagesLen == imageLenSum) && (lenTotal == (4 + jsonLen + 4 + imagesLen + 8 + headLen));
			} catch (RuntimeException e) {
				LOGGER.error(e.getMessage());
				return false;
			}
		}
		
		/**
		 * 转json 首字母大写.
		 * 
		 * @param object
		 *            object
		 * @return json string
		 */
		public static String toJSONString(Object object) {
			JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(object));
			Iterator<Map.Entry<String, Object>> iterator = jsonObject.entrySet().iterator();
			JSONObject res = new JSONObject();
			while (iterator.hasNext()) {
				Map.Entry<String, Object> next = iterator.next();
				Object value = next.getValue();
				String key = next.getKey();
				String newKey = captureName(key);
				res.put(newKey, value);
			}
			return JSONObject.toJSONString(res);
		}

		/**
		 * 首字母大写.
		 * 
		 * @param str
		 *            字符串
		 * @return 字符串
		 */
		public static String captureName(String str) {
			char[] cs = str.toCharArray();
			cs[0] -= 32;
			return String.valueOf(cs);
		}
}
