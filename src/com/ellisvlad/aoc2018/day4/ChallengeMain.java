package com.ellisvlad.aoc2018.day4;

import java.io.BufferedReader;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.ellisvlad.aoc2018.DayChallenge;

public class ChallengeMain extends DayChallenge {

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

	private TreeMap<Date, Integer> dutyStarts = new TreeMap<>();
	private TreeMap<Date, Integer> sleepStarts = new TreeMap<>();
	private Map<Integer, Long> sleepTimes = new HashMap<>();
	private Map<Integer, int[]> guardAsleepMins = new HashMap<>();

	@Override
	public void runChallenge(BufferedReader input) throws Exception {
		Set<String> lines = input.lines().collect(Collectors.toCollection(HashSet::new));
		
		for (IterationPhase phase : IterationPhase.values()) {
			Pattern regex = phase.getPattern();
			
			for (String line : new HashSet<>(lines)) {
				Matcher match = regex.matcher(line);
	
				if (match.matches()) {
					lines.remove(line);

					Date timestamp = dateFormat.parse(match.group(1));
					int guardId;
					Entry<Date, Integer> sleepStart;
					
					switch (phase) {
					case FIND_SHIFTS:
						guardId = Integer.parseInt(match.group(2));

						dutyStarts.put(timestamp, guardId);
						guardAsleepMins.put(guardId, new int[60]);
					break;
					case FIND_SLEEP_START:
						guardId = dutyStarts.floorEntry(timestamp).getValue();

						sleepStarts.put(timestamp, guardId);
					break;
					case FIND_SLEEP_END:
						sleepStart = sleepStarts.floorEntry(timestamp);
						guardId = sleepStart.getValue();

						sleepTimes.merge(
							guardId,
							(timestamp.getTime() - sleepStart.getKey().getTime()) / 1000 / 60,
							Long::sum);
						
						int[] sleepMins = guardAsleepMins.get(guardId);
						for (int min = sleepStart.getKey().getMinutes(); min != timestamp.getMinutes();) {
							sleepMins[min]++;
							
							min++;
							if (min == 60) min = 0;
						}
					break;
					}
				}
			}
		}

		if (!lines.isEmpty()) {
			throw new RuntimeException("A line was not formatted correctly! \"" + lines.iterator().next() + "\"");
		}

		Entry<Integer, Long> sleepiestGuard =
				Collections.max(sleepTimes.entrySet(), Comparator.comparingLong(Map.Entry<Integer, Long>::getValue));
		long secs = sleepiestGuard.getValue();
		System.out.println(String.format("Guard #%d slept for %dh %02dm", + sleepiestGuard.getKey(), secs / 60, secs % 60));
		
		Map<Integer, Pair<Integer, Integer>> mostSleptGuards = new HashMap<>();
		for (Entry<Integer, int[]> guardSleepMins : guardAsleepMins.entrySet()) {
			int[] sleepMins = guardSleepMins.getValue();

			int maxMins = 0, maxVal = 0;
			for (int i = 0; i < sleepMins.length; i++) {
				if (sleepMins[i] > maxVal) {
					maxVal = sleepMins[i];
					maxMins = i;
				}
			}
			
			mostSleptGuards.put(guardSleepMins.getKey(), new Pair<Integer, Integer>(maxVal, maxMins));
		}
		
		Pair<Integer, Integer> mostSlept = mostSleptGuards.get(sleepiestGuard.getKey());
		System.out.println(String.format("They slept %d times at %02d mins past the hour", mostSlept.getLeft(), mostSlept.getRight()));

		printPartOutput(1, "" + mostSlept.getRight() * sleepiestGuard.getKey());
		
		Entry<Integer, Pair<Integer, Integer>> mostSleptGuard =
				Collections.max(mostSleptGuards.entrySet(), (e1, e2) -> e1.getValue().getLeft() - e2.getValue().getLeft());
		
		System.out.println(String.format(
				"Guard #%d was asleep %d times at %02d mins past the hour",
				mostSleptGuard.getKey(),
				mostSleptGuard.getValue().getLeft(),
				mostSleptGuard.getValue().getRight()));
		System.out.println();

		printPartOutput(2, "" + mostSleptGuard.getKey() * mostSleptGuard.getValue().getRight());
	}
	
	@Override
	protected int getPartCount() {
		return 2;
	}

}