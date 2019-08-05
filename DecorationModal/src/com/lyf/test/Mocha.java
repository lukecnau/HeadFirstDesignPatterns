package com.lyf.test;

public class Mocha extends BeverageDeco {
	protected Mocha(Beverage v) {
		super(v);
	}

	@Override
	public String getDescription() {
		return b.getDescription() + ", Mocha";
	}

	@Override
	public double cost() {
		return b.cost() + 0.2;
	}

}
