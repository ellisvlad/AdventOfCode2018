package com.ellisvlad.aoc2018.day1;

import java.io.BufferedReader;
import java.util.HashSet;
import java.util.Set;

import com.ellisvlad.aoc2018.DayChallenge;

public class ChallengeMain extends DayChallenge {

	@Override
	public void runChallenge(BufferedReader input) throws Exception {
		input.mark(10240);

		Set<Integer> seenVals = new HashSet<>();
		seenVals.add(0);

		int sum = 0;
		
		for (int reps = 0; reps < 1000; reps++) {
			String line;
			while ((line = input.readLine()) != null) {
				sum += Integer.parseInt(line);

				if (!seenVals.add(sum)) {
					printPartOutput(2, String.valueOf(sum));
				}
			}

			printPartOutput(1, String.valueOf(sum));

			input.reset();
		}
		
		throw new RuntimeException("Looped input 1000 times and there was no repeated frequency!");
	}

	@Override
	protected int getPartCount() {
		return 2;
	}

}
