package com.zxelec.cpug.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import org.springframework.core.io.ClassPathResource;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
/**
 * 解析json文件
 * @author liu.yongquan
 *
 */
public class JsonUtils {
	public static String jsonRead(File file){
        Scanner scanner = null;
        StringBuilder buffer = new StringBuilder();
        try {
            scanner = new Scanner(file, "utf-8");
            while (scanner.hasNextLine()) {
                buffer.append(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
            if (scanner != null) {
                scanner.close();
            }
        }
        return buffer.toString();
    }
}
