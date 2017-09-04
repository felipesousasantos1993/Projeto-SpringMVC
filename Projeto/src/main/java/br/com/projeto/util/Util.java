package br.com.projeto.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.tomcat.util.http.fileupload.IOUtils;

import com.google.gson.Gson;

public abstract class Util {

	public static String convertObjectToJson(Object object) {
		return new Gson().toJson(object);
	}

	public static void writeFile(File file, String value) {
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(file, true);
			fileWriter.write(value + "\n\r");
		} catch (IOException e) {
		} finally {
			IOUtils.closeQuietly(fileWriter);
		}
	}
}
