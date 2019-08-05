package com.lyf.test;

public abstract class Beverage {
	protected String desc = "Unknown Beverage";
	
	public abstract double cost();
	public String getDescription() { return desc; }
}
