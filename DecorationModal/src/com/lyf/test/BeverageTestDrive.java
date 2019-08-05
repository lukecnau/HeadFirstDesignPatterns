package com.lyf.test;

public class BeverageTestDrive {

	public static void main(String[] args) {
		Beverage b = new Espresso();
		Beverage m1 = new Mocha(b);
		Beverage m2 = new Mocha(m1);
		Beverage mi1 = new Milk(m1);
		Beverage mi2 = new Milk(b);
		
		System.out.printf("[%s] costs %.2f\n", b.getDescription(), b.cost());		
		System.out.printf("[%s] costs %.2f\n", m1.getDescription(), m1.cost());
		System.out.printf("[%s] costs %.2f\n", m2.getDescription(), m2.cost());
		System.out.printf("[%s] costs %.2f\n", mi1.getDescription(), mi1.cost());
		System.out.printf("[%s] costs %.2f\n", mi2.getDescription(), mi2.cost());
	}

}
