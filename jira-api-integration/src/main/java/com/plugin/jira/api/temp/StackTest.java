package com.plugin.jira.api.temp;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class StackTest {

	String name;
	String xyz;

	public static void main(String[] args) {
		 actionPerformed();
//		mergeObject(new StackTest(), null);
	}

	public static void actionPerformed() {
 
		ArrayList<String> allQuestions = new ArrayList<String>();
		File file = new File("D:/me/test.txt");
		int numberOfRandomQuestions = 10;
		try {
			// Read line by line from the file
			Scanner scan = new Scanner(file);

			while (scan.hasNextLine()) {
				String line = scan.nextLine();
				allQuestions.add(line);
			}
			scan.close();

		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		}

		for (int i = 0; i < numberOfRandomQuestions; i++) {
			Random randNum = new Random();

			int randQuestionIndex = randNum.nextInt(numberOfRandomQuestions);
			System.out.println();
			String randomQuestion = allQuestions.get(randQuestionIndex);
			JOptionPane.showMessageDialog(null, randomQuestion.replace("/", "\n"));
		}
	}

	static boolean isObjectClassMethod(String methodName) {
		Method[] methods = Object.class.getMethods();
		for (Method method : methods) {
			if (method.getName().equals(methodName))
				return true;
		}
		return false;
	}

	/*
	 * static boolean isObjectClassField(String fieldName) { Method[] methods = Object.class.getMethods(); for (Method method : methods) { if (method.getName().equals(methodName)) return true; } return false; }
	 */
	/*
	 * static Object mergeObject(Object Obj1, Object Obj2) {
	 * 
	 * Method[] methods = Obj1.getClass().getMethods(); System.out.println(methods.length); for (Method method : methods) { if (!isObjectClassMethod(method.getName())) { if (!"main".equals(method.getName())) { System.out.println(method.getName()); } } } return null; }
	 */

	static Object mergeObject(Object Obj1, Object Obj2) throws InstantiationException, IllegalAccessException, ClassNotFoundException {

		System.out.println(Obj1.getClass().getName());
		Object obj = Class.forName(Obj1.getClass().getName()).newInstance();
		Field[] fields = Obj1.getClass().getDeclaredFields();
		System.out.println(fields.length);
		for (Field field : fields) {
			
		}
		return null;
	}
}
