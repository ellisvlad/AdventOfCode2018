package com.ellisvlad.aoc2018;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	private static int SOLVED_DAYS = 1;

	public static void main(String[] args) {
		int pickedDay = 0;
		BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

		if (args.length > 0) {
			try {
				pickedDay = Integer.parseInt(args[0]);
			} catch (NumberFormatException e) {
				System.err.println("Day input should be an integer!");
			}
		}

		while (pickedDay < 1 || pickedDay > SOLVED_DAYS) {
			if (pickedDay != 0) {
				System.err.println("The picked day was out of range of which day's I've solved!");
			}

			System.out.println("Pick a day between 1 and " + SOLVED_DAYS + "!");
			System.out.print("Pick a day [1-" +  SOLVED_DAYS + "]: ");

			try {
				pickedDay = Integer.parseInt(console.readLine());
			} catch (NumberFormatException e) {
				System.err.println("Day input should be an integer!");
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(1);
			}
		}

		System.out.println("");
		System.out.println("==========================================");
		System.out.println("Running day " + pickedDay);
		System.out.println("==========================================");
		System.out.println();
		
		
	}

}
