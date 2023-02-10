package station.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class FileService implements IFileSerivce{

	public String readFromInputStream(String urlInput) throws IOException {
		File initialFile = new File(urlInput);
		InputStream targetStream = new FileInputStream(initialFile);
		StringBuilder resultStringBuilder = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new InputStreamReader(targetStream))) {
			String line;
			while ((line = br.readLine()) != null) {
				resultStringBuilder.append(line).append("\n");
			}
		}
		return resultStringBuilder.toString();
	}

	public void writeFileOutPut(String json, String urlOutPut) throws IOException {
		OutputStream os = null;
		try {
			os = new FileOutputStream(new File(urlOutPut));
			os.write(json.getBytes(), 0, json.length());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
