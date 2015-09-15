package com.plugin.jira.api.temp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

public class DeleteLog {

  final static String SERVER_FOLDER_ROOT_PATH = "C:\\usr\\local\\jboss402\\server";
  final static String SWAP_FILES_PATH = "C:\\opt\\ftp\\{serverName}\\reports\\";
  final static String FILLED_REPORT_PATH = "C:\\opt\\ftp\\%s\\reports\\filled_reports\\";


  final static boolean IS_WORK_FOLDER_DELETE = true;
  final static boolean IS_LOG_FOLDER_DELETE = true;
  final static boolean IS_TMP_FOLDER_DELETE = true;
  final static boolean IS_SWAP_FILE_DELETE = true;
  final static boolean IS_FILLED_REPORT_FILE_DELETE = true;


  private static List<File> ALL_CLIENT_FOLDERS;

  private static void delete(String path) {
    File folder = new File(path);
    if (folder.exists() && folder.isDirectory()) {
      try {
        deleteRecursive(folder);
        System.out.println("This folder has been deleted : " + path);
      } catch (Exception e) {
        System.err.println("Error while deleting the directory : " + path);
        System.err.println(e.getMessage());
        e.printStackTrace();
      }
    } else {
      System.err.println("This folder may not exist :: " + path);
    }
  }

  private static void deleteRecursive(File f) throws FileNotFoundException {
    if (f.exists() && f.isDirectory()) {
      for (File c : f.listFiles())
        deleteRecursive(c);
    }
    if (!f.delete()) {
      throw new FileNotFoundException("Failed to delete file: " + f);
    }
  }

  private static void deleteAll() {
    String workFolderPath;
    String tmpFolderPath;
    String logFolderPath;

    for (File serverFolder : getAllClientFolders()) {
      if (IS_WORK_FOLDER_DELETE) {
        workFolderPath = serverFolder.getPath() + "\\work";
        delete(workFolderPath);
      }
      if (IS_TMP_FOLDER_DELETE) {
        tmpFolderPath = serverFolder.getPath() + "\\tmp";
        delete(tmpFolderPath);
      }
      if (IS_LOG_FOLDER_DELETE) {
        logFolderPath = serverFolder.getPath() + "\\log";
        delete(logFolderPath);
      }
      if (IS_FILLED_REPORT_FILE_DELETE) {
        logFolderPath = serverFolder.getPath() + "\\log";
        delete(logFolderPath);
      }
    }

    for (File serverFolder : getAllClientFolders()) {

    }

  }

  private static List<File> getAllClientFolders() {
    if (ALL_CLIENT_FOLDERS == null) {
      setAllClientFolders();
    }
    return ALL_CLIENT_FOLDERS;
  }

  private static void setAllClientFolders() {
    File serversFolderRoot = new File(SERVER_FOLDER_ROOT_PATH);
    ALL_CLIENT_FOLDERS = Arrays.asList(serversFolderRoot.listFiles());
  }

  public static void main(String[] args) {
    deleteAll();
  }
}
