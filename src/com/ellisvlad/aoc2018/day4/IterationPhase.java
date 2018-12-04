package com.ellisvlad.aoc2018.day4;

import java.util.regex.Pattern;

enum IterationPhase {

	FIND_SHIFTS(Pattern.compile("\\[(.*)\\] Guard #(\\d+) begins shift")),
	FIND_SLEEP_START(Pattern.compile("\\[(.*)\\] falls asleep")),
	FIND_SLEEP_END(Pattern.compile("\\[(.*)\\] wakes up"));

	private final Pattern pattern;
	
	private IterationPhase(Pattern pattern) {
		this.pattern = pattern;
	}

	public Pattern getPattern() {
		return pattern;
	}

};