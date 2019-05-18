package com.zxelec.cpug.vl.entity.rest;

public class VissCpbsPage {
	private int total;
	private boolean maybeMore;
	private int nextOffset;

	public void setTotal(int total) {
		this.total = total;
	}

	public int getTotal() {
		return total;
	}

	public void setMaybeMore(boolean maybeMore) {
		this.maybeMore = maybeMore;
	}

	public boolean getMaybeMore() {
		return maybeMore;
	}

	public void setNextOffset(int nextOffset) {
		this.nextOffset = nextOffset;
	}

	public int getNextOffset() {
		return nextOffset;
	}
}
