package com.ellisvlad.aoc2018.day3;

import java.io.BufferedReader;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ellisvlad.aoc2018.DayChallenge;

public class ChallengeMain extends DayChallenge {

	@Override
	public void runChallenge(BufferedReader input) throws Exception {
		int[][] fabric = new int[1000][1000];
		int[][] fabricIds = new int[1000][1000];
		Set<Integer> uniqueIds = new HashSet<>();
		
		Pattern regex = Pattern.compile("#(\\d+) @ (\\d+),(\\d+): (\\d+)x(\\d+)");
		
		String line;
		while ((line = input.readLine()) != null) {
			Matcher match = regex.matcher(line);
			
			if (!match.matches()) {
				throw new RuntimeException("A line was not formatted correctly! \"" + line + "\"");
			}

			int i = Integer.parseInt(match.group(1));
			int x = Integer.parseInt(match.group(2));
			int y = Integer.parseInt(match.group(3));
			int w = Integer.parseInt(match.group(4));
			int h = Integer.parseInt(match.group(5));
			
			uniqueIds.add(i);

			for (int w1 = 0; w1 < w; w1++) {
				for (int h1 = 0; h1 < h; h1++) {
					if (fabricIds[y + h1][x + w1] != 0) {
						uniqueIds.remove(i);
						uniqueIds.remove(fabricIds[y + h1][x + w1]);
					}

					fabric[y + h1][x + w1]++;
					fabricIds[y + h1][x + w1] = i;
				}
			}
		}

		int duplicates = 0;
		for (int x = 0; x != 1000; x++) {
			for (int y = 0; y != 1000; y++) {
				if (fabric[y][x] > 1) duplicates++;
			}
		}

		printPartOutput(1, "" + duplicates);
		
		if (uniqueIds.size() != 1) {
			throw new RuntimeException("Did not find one unique fabric area");
		}
		printPartOutput(2, "" + uniqueIds.iterator().next());
	}
	
	@Override
	protected int getPartCount() {
		return 2;
	}

}