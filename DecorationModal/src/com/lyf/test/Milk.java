package com.lyf.test;

public class Milk extends BeverageDeco {
	protected Milk(Beverage v) {
		super(v);
	}

	@Override
	public String getDescription() {
		return b.getDescription() + ", Milk";
	}

	@Override
	public double cost() {
		return b.cost() + 0.1;
	}

}
