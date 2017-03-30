package com.contrastsecurity;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FileFinder {

	public static String fileFind(String path, String filename) {

		String matchingFile = null;
		Stream<Path> files;
		try {
			files = Files.find(Paths.get(path), Integer.MAX_VALUE, (filePath, fileAttr) -> fileAttr.isRegularFile())
					.filter(n -> !n.toString().contains("conf_bk")).filter(n -> !n.toString().contains("tomcat")).filter(n -> n.toString().contains(filename));

			for (Path s : (Iterable<Path>) () -> files.iterator()) {
				if (s.toString().contains(filename) && s.toString().endsWith("properties"))
					matchingFile = s.toString();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return matchingFile;

	}

}
