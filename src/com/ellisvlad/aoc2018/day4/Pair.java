package com.ellisvlad.aoc2018.day4;

public class Pair<L, R> {

	private final L l;
	private final R r;
	
	public Pair(L l, R r) {
		this.l = l;
		this.r = r;
	}

	public L getLeft() {
		return l;
	}

	public R getRight() {
		return r;
	}
	
	public String toString() {
		return l + ":" + r;
	}

}
