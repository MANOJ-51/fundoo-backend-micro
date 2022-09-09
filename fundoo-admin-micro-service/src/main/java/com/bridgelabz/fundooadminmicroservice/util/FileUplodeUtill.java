package com.bridgelabz.fundooadminmicroservice.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.web.multipart.MultipartFile;

/**
 * Purpose:Creating method for file uplode utill
 * 
 * @author Manoj Version 1.0
 */
public class FileUplodeUtill {
	public static void saveFile(String fileName, MultipartFile multipartFile) throws IOException {
		Path uplodeDirectory = Paths.get("Files-uplode");

		try (InputStream inputStream = multipartFile.getInputStream()) {
			Path filePath = uplodeDirectory.resolve(fileName);
			Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException exception) {
			throw new IOException("Eroor saving uploded file: " + fileName, exception);
		}
	}

}
