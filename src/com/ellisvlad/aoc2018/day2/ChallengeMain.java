package com.ellisvlad.aoc2018.day2;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.ellisvlad.aoc2018.DayChallenge;

public class ChallengeMain extends DayChallenge {

	@Override
	public void runChallenge(BufferedReader input) throws Exception {
		int doubleCnt = 0;
		int tripleCnt = 0;
		List<byte[]> linesDiff = new ArrayList<>();

		String line;
		while ((line = input.readLine()) != null) {
			Map<Byte, Integer> repeatedChars = new HashMap<>();

			for (byte ch : line.getBytes()) {
				repeatedChars.merge(ch, 1, Integer::sum);
			}

			if (removeDuplicate(repeatedChars, 3)) tripleCnt++;
			if (removeDuplicate(repeatedChars, 2)) doubleCnt++;
			
			linesDiff.add(line.getBytes());
		}
		
		printPartOutput(1, "" + tripleCnt * doubleCnt);
		
		////////////////////////////

		for (int i = 0; i < linesDiff.size(); i++) {
			for (int j = i; j < linesDiff.size(); j++) {
				byte[] s1 = linesDiff.get(i);
				byte[] s2 = linesDiff.get(j);
				StringBuilder common = new StringBuilder();
				int diffCnt = 0;
				
				for (int k = 0; k < s1.length; k++) {
					if (s1[k] == s2[k]) {
						common.append((char) s1[k]);
					} else {
						diffCnt++;
					}
				}
				
				if (diffCnt == 1) {
					printPartOutput(2, common.toString());
				}
			}
		}
	}
	
	private boolean removeDuplicate(Map<Byte, Integer> repeatedChars, int duplicateCnt) {
		Iterator<Entry<Byte, Integer>> repCharItr = repeatedChars.entrySet().iterator();
		
		while (repCharItr.hasNext()) {
			Entry<Byte, Integer> repChar = repCharItr.next();

			if (repChar.getValue() == duplicateCnt) {
				repCharItr.remove();
				return true;
			}
		}
		
		return false;
	}

	@Override
	protected int getPartCount() {
		return 2;
	}

}
