package com.plugin.jira.api.temp;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author vishal.zanzrukia
 * 
 */
public class TestDirectory {

	public static void testDirectory(String filePath) {

		// String TEST_FILE_PATH = "C:\\opt\\ftp\\ClairesNA\\catalogue\\in";
		File DIRECTORY = new File(filePath);
		Path PATH = Paths.get(filePath);

		/** test if exist */
		if (DIRECTORY.exists()) {
			System.out.println(DIRECTORY + " is exist");
		} else {
			System.out.println(DIRECTORY + " is not exist");
		}

		if (Files.exists(PATH, new LinkOption[] { LinkOption.NOFOLLOW_LINKS })) {
			System.out.println(PATH + " is exist with nio..!");
		} else {
			System.out.println(PATH + " is not exist with nio..!");
		}

		/** test is directory */
		if (DIRECTORY.isDirectory()) {
			System.out.println(DIRECTORY + " is directory");
		} else {
			System.out.println(DIRECTORY + " is not directory");
		}

		if (Files.isDirectory(PATH, new LinkOption[] { LinkOption.NOFOLLOW_LINKS })) {
			System.out.println(PATH + " is directory using nio..!");
		} else {
			System.out.println(PATH + " is not directory using nio..!");
		}

		/** test readable */
		if (DIRECTORY.canRead()) {
			System.out.println(DIRECTORY + " is readable");
		} else {
			System.out.println(DIRECTORY + " is not readable");
		}

		if (Files.isReadable(PATH)) {
			System.out.println(PATH + " is readable using nio..!");
		} else {
			System.out.println(PATH + " is not readable using nio..!");
		}

		/** test writable */
		if (DIRECTORY.canWrite()) {
			System.out.println(DIRECTORY + " is writable");
		} else {
			System.out.println(DIRECTORY + " is not writable");
		}
		if (Files.isWritable(PATH)) {
			System.out.println(PATH + " is writable using nio..!");
		} else {
			System.out.println(PATH + " is not writable using nio..!");
		}
	}

	public static void main(String[] args) {
		testDirectory(args[0]);
	}
}
