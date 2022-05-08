package main;

import java.util.Scanner;

public class Driver {

// this driver is legacy and the main method is now run from GUI.java

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RPS driver = new RPS();
		Scanner sc = new Scanner(System.in);
		String input = "";
		System.out.println("Type a string containing only the letters R, P, and S (case insensitive) or type 'quit'");
		while (true)
		{
			input=sc.nextLine();
			if (input.equals("quit")) break;
			driver.play(input);
		}
	}
}
