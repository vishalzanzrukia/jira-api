package com.plugin.jira.api.temp;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

/**
 * @author Vishal.Zanzrukia
 * 
 */
public class ATMDemo {

	/**
	 * declaring private member for itself
	 */
	private static ATMDemo object = null;

	/**
	 * maximum amount allowed per transaction
	 */
	public static final Integer MAXIMUM_AMOUT_PER_TRANSACTION = 50000;

	/**
	 * maximum hundred allowed
	 */
	public static final Integer MAXIMUM_HUNDRED_RUPEES_PER_TRANSACTION = 5;

	/**
	 * maximum five hundred allowed
	 */
	public static final Integer MAXIMUM_FIVE_HUNDRED_RUPEES_PER_TRANSACTION = 5;

	/**
	 * declaring constant to hold static current balance in ATM.
	 */
	private static final Map<Rupee, Long> AVAILABLE_BALANCE;

	/**
	 * making it singleton
	 */
	private ATMDemo() {

	}

	static {
		/**
		 * Initializing the current balance
		 * */
		AVAILABLE_BALANCE = new HashMap<ATMDemo.Rupee, Long>();
		AVAILABLE_BALANCE.put(Rupee.THOUSAND, 2L);
		AVAILABLE_BALANCE.put(Rupee.FIVE_HUNDRED, 0L);
		AVAILABLE_BALANCE.put(Rupee.HUNDRED, 2L);
	}

	enum Rupee {
		HUNDRED(100), THOUSAND(1000), FIVE_HUNDRED(500);

		private int value;

		private Rupee(int value) {
			this.value = value;
		}

		public int getValue() {
			return this.value;
		}

		@Override
		public String toString() {
			return this.getValue() + "";
		}
	}

	/**
	 * input value
	 */
	private Integer inputValue;

	/**
	 * return value
	 */
	private Map<Rupee, Integer> returnValue = new HashMap<Rupee, Integer>();

	/**
	 * provide instance
	 * 
	 * @return
	 */
	public static ATMDemo getInstance() {
		if (object == null) {
			object = new ATMDemo();
		}
		return object;
	}

	/**
	 * take input from rupees.
	 * 
	 * @return
	 */
	private void takeInputFromUser() {
		Scanner in = new Scanner(System.in);
		System.out.println("Please enter a ruppes : ");
		try {
			Integer inputValue = in.nextInt();
			in.close();
			this.inputValue = inputValue;
		} catch (InputMismatchException e) {
			System.out.println("The number you have entered  is not valid..Please try again!");
			System.exit(1);
		}
	}

	/**
	 * validate the input
	 * 
	 * @param inputValue
	 * @return
	 */
	private void isValidInput() {
		if (getInputValue() == null || getInputValue() % 100 != 0) {
			System.out.println("The number you have entered  is not valid..Please try again!");
			System.exit(1);
		} else if (getInputValue().intValue() > MAXIMUM_AMOUT_PER_TRANSACTION.intValue()) {
			System.out.println("Maximum amount allowed : " + MAXIMUM_AMOUT_PER_TRANSACTION + " per transaction. Please try again!");
			System.exit(1);
		}
	}

	/**
	 * process amount
	 */
	private void processAmout() {

		if (getInputValue().intValue() <= Rupee.FIVE_HUNDRED.getValue()) {
			int requiredNum = getInputValue().intValue() / Rupee.HUNDRED.getValue();
			if (isAvailable(Rupee.HUNDRED, requiredNum)) {
				returnValue.put(Rupee.HUNDRED, requiredNum);
				return;
			} else {
				if (getInputValue().intValue() == Rupee.FIVE_HUNDRED.getValue()) {
					if (isAvailable(Rupee.FIVE_HUNDRED, 1)) {
						returnValue.put(Rupee.FIVE_HUNDRED, 1);
						return;
					} else {
						System.out.println("Balance not avaliable..!");
						System.exit(1);
					}
				} else {
					System.out.println("Balance not avaliable..!");
					System.exit(1);
				}
			}
		}

		if (getInputValue().intValue() <= Rupee.THOUSAND.getValue()) {
			int totalRequiredNum = getInputValue().intValue() / Rupee.HUNDRED.getValue();
			int thresoldRequiredNum = totalRequiredNum <= MAXIMUM_HUNDRED_RUPEES_PER_TRANSACTION ? totalRequiredNum : MAXIMUM_HUNDRED_RUPEES_PER_TRANSACTION;
			if (isAvailable(Rupee.HUNDRED, thresoldRequiredNum)) {
				returnValue.put(Rupee.HUNDRED, thresoldRequiredNum);
			}
			else{
				
			}
		}
	}
	
//	private 

	/**
	 * @param ruppe
	 * @param number
	 * @return
	 */
	private boolean isAvailable(Rupee ruppe, Integer number) {
		if (AVAILABLE_BALANCE.get(ruppe).longValue() >= number) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 */
	private void printFinalOutput() {
		System.out.println(":: Please collect your money :: ");
		for (Map.Entry<Rupee, Integer> entry : returnValue.entrySet()) {
			System.out.println(entry.getKey() + "::" + entry.getValue());
		}
	}

	/**
	 * starting point of workflow
	 */
	private void startWorkFlow() {
		/**
		 * taking input
		 * */
		takeInputFromUser();
		/**
		 * validating input
		 * */
		isValidInput();
		/**
		 * processing amount
		 * */
		processAmout();

		/**
		 * print final output
		 * */
		printFinalOutput();
	}

	/**
	 * main
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		getInstance().startWorkFlow();
	}

	/**
	 * @return the inputValue
	 */
	public Integer getInputValue() {
		return inputValue;
	}

	/**
	 * @return the returnValue
	 */
	public Map<Rupee, Integer> getReturnValue() {
		return returnValue;
	}

	/**
	 * @param returnValue the returnValue to set
	 */
	public void setReturnValue(Map<Rupee, Integer> returnValue) {
		this.returnValue = returnValue;
	}
}
