package com.zxelec.cpug.vl.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Encrypt {
	public static String encrypt(String str) {
		MessageDigest md = null;
		String out = null;
		try {
			md = MessageDigest.getInstance("MD5");
			byte[] digest = md.digest(str.getBytes("UTF-8"));
			out = byte2hex(digest);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return out;
	}

	private static String byte2hex(byte[] b) {
		StringBuffer hs = new StringBuffer();
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1) {
				hs.append("0"+stmp);
				//hs = hs + "0" + stmp;
			} else {
				hs.append(stmp);
			}
		}
		return hs.toString();
	}

}
