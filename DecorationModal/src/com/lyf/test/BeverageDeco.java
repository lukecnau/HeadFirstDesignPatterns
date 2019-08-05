package com.lyf.test;

public abstract class BeverageDeco extends Beverage {
	Beverage b;
	
	private BeverageDeco() {}
	
	protected BeverageDeco(Beverage v) {
		b = v;
	}
	
	@Override
	public abstract String getDescription();
}
