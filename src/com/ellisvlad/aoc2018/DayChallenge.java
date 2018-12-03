package com.ellisvlad.aoc2018;

import java.io.BufferedReader;

public abstract class DayChallenge {
	
	private int completedParts = 0;
	private String[] outputParts;

	public abstract void runChallenge(BufferedReader input) throws Exception;
	
	protected abstract int getPartCount();

	protected void printOutput(String output) {
		System.out.println("Answer for this day: " + output);
		System.exit(0);
	}

	protected void printPartOutput(int part, String output) {
		if (outputParts == null) {
			outputParts = new String[getPartCount()];
		}

		if (outputParts[part - 1] == null) {
			outputParts[part - 1] = output;

			if (++completedParts == outputParts.length) {
				for (int i = 0; i != outputParts.length; i++) {
					System.out.println("Part " + (i + 1) + " answer for this day: " + outputParts[i]);
				}

				System.exit(0);
			}
		}
	}

}
