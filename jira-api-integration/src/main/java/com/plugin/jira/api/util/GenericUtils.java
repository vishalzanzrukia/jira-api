package com.plugin.jira.api.util;

import java.io.File;

/**
 * @author vishal.zanzrukia
 * @since 26 May 2015
 * @version 1.0
 */
public class GenericUtils {


  /**
   * Validate the String
   * 
   * @param s
   * @return
   */
  public static boolean isStringValid(String s) {
    if (s == null || s.isEmpty()) {
      return false;
    }
    return true;
  }

  /**
   * Get last modified file from directory
   * 
   * @param dirPath folder path
   * @return File last modified file
   */
  public static File getLatestFilefromDir(String dirPath) {
    File dir = new File(dirPath);
    File[] files = dir.listFiles();
    if (files == null || files.length == 0) {
      return null;
    }

    File lastModifiedFile = files[0];
    for (int i = 1; i < files.length; i++) {
      if (lastModifiedFile.lastModified() < files[i].lastModified()) {
        lastModifiedFile = files[i];
      }
    }
    return lastModifiedFile;
  }
}
