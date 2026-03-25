package com.lynda.javatraining;

public class Main {

	private static String value = "one";
	
	public static void main(String[] args) {

		switch (value) {
		case "one":
			System.out.println("The value is one");
			break;

		default:
			System.out.println("The value isn't one");
			break;
		}
		
	}

}
