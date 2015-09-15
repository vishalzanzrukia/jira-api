package com.plugin.jira.api.temp;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.sax.SAXSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.w3c.dom.Document;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

class Test {
	
	protected static final NumberFormat PRICE_FORMAT = new DecimalFormat("#####0.00");
	
	/*MyObj myObj = new MyObj();

	public synchronized void test() {
		// doing my other stuff

		// accessing myObj
	}*/
	
	
	
	public static void main(List<? extends Object> objects){
		for(Object obj : objects){
//			System.out.println(obj instanceof Long);
//			System.out.println(obj instanceof String);
			Long l = null;
			if(obj instanceof Long){
				l = (Long)obj;
			}
			if(obj instanceof String){
				l = Long.valueOf((String)obj);
			}
			System.out.println("result : "+l);
		}
	}
	
	public static void subStringTest(){
		System.out.println("vishal".substring(1));	
		System.out.println("vishal".substring(0,0));	
		char[] returnValue = Arrays.copyOf(new char[]{'h','e','l','l','o'}, 7);
		for(char c : returnValue){
			System.out.println("c : "+c);
		}
		
		char[] input =  new char[]{'h','e','l','l','o'};
		char[] output = new char[]{'t','h','i','s'};
		
		System.arraycopy(input, 1, output, 0, 10);
		
		for(char c : output){
			System.out.println("c : "+c);
		}
	}
	
	public static void testOutOfMemoryError(){
		List<String> list = new ArrayList<String>();
		while(true){
			list.add(new String("Vishal"));
		}
	}
	
	public static void testStackOverFlow(int a, int b){
		testStackOverFlow(a++,b++);
	}
	
	
	
	public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
		
//		System.out.println(new String(Base64.encodeBase64(("claire@shoprunner.com.shoprunner"+ ":" + "KEuJg1a8j2k").getBytes())));
//		testOutOfMemoryError();
//		System.out.println(PRICE_FORMAT.format(2099.86768D));
	}
}
public class TestUtil {
	



	public static final String padWith(String toPad, char padCharacter, int desiredLength, boolean padBeforeString, boolean truncateIfTooLong) {

		// if the string to pad is null create a new string, it will be filled with padding characters
		if (toPad == null)
			toPad = "";

		// StringBuilder will hold the initial value and the padding we need to add
		StringBuilder sb = new StringBuilder(desiredLength);
		sb.append(toPad);

		// work out how much padding is needed
		int padLength = toPad.length() >= desiredLength ? 0 : desiredLength - toPad.length();

		// do we need to add padding characters?
		if (padLength > 0) {
			// build up the padding characters
			char[] padding = new char[padLength];
			Arrays.fill(padding, padCharacter);

			if (padBeforeString) {
				// insert padding characters at the beginning of the string
				sb.insert(0, padding);
			} else {
				// insert padding characters at the end of the string
				sb.append(padding);
			}
		} else {
			// we can optionally truncate the string to the correct length
			if (truncateIfTooLong) {
				if (padBeforeString) {
					// remove characters at end of string
					return sb.substring(0, desiredLength);
				} else {
					// remove characters at beginning of string
					return sb.substring(sb.length() - desiredLength);
				}
			}
		}

		return sb.toString();
	}

	public static final double roundToTwoDp(double value) {
		BigDecimal bd = new BigDecimal(Double.toString(value));
		return bd.setScale(2, RoundingMode.HALF_UP).doubleValue();
	}

	public static Double getTotalCostWithoutCredits(double dblTotal, double dblShippingCost) {

		return roundToTwoDp(dblTotal + dblShippingCost);
	}

	public static final int multiplyToInt(float value, int multiplicand) {

		BigDecimal bd = new BigDecimal(Double.toString(value));
		bd = bd.setScale(2, RoundingMode.HALF_UP);
		bd = bd.multiply(new BigDecimal(multiplicand));
		return bd.intValue();
	}

	public static final float truncateToTwoDp(float value) {

		return multiplyToInt(value, 100) / 100f;
		// return ((int)(value*100))/100f; // Introduces rounding errors
	}

	public static Double testFloatSubtraction(Double value1, Double value2) {
		return new BigDecimal(value1.toString()).subtract(new BigDecimal(value2.toString())).doubleValue();
	}

	public static void main(String[] args) {
		// System.out.println(padWith(Float.toString(getTotalCostWithoutCredits(0.9999, 0.7788).floatValue()), '0', 4, true, true));
		// System.out.println(truncateToTwoDp(10.0000009f));
		// System.out.println(testFloatSubtraction(3.48d, 3.47d));
		// System.out.println("hello this is test".replaceAll("\r\n", ""));

		// System.out.println();
	}
}

class Demo {
	public static void main(String[] args) throws Exception {
		
		String xmlFileLocation = "src/validate/blog/input.xml";
		SAXSource source = new SAXSource(new InputSource(xmlFileLocation));

		SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Schema schema = sf.newSchema(new File("src/validate/blog/customer.xsd"));
		Validator validator = schema.newValidator();
		validator.setErrorHandler(new MyErrorHandler());

		validator.validate(source);
		System.out.println("DONE");
	}

	private static class MyErrorHandler implements ErrorHandler {

		public void error(SAXParseException arg0) throws SAXException {
			System.out.println("ERROR");
			arg0.printStackTrace(System.out);
		}

		public void fatalError(SAXParseException arg0) throws SAXException {
			System.out.println("FATAL ERROR");
			arg0.printStackTrace(System.out);
		}

		public void warning(SAXParseException arg0) throws SAXException {
			System.out.println("WARNING ERROR");
			arg0.printStackTrace(System.out);
		}

	}
}