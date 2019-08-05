package com.lyf.test;

public class I implements Programmer, Typer {
	@Override
	public void doRunning() {
		System.out.println("I am running!");
	}

	@Override
	public void doSleeping() {
		System.out.println("I am sleeping!");
	}

	@Override
	public void doWalking() {
		System.out.println("I am walking!");
	}

	@Override
	public void doEating() {
		System.out.println("I am eating!");
	}

	@Override
	public void doExtruding() {
		System.out.println("I am extruding!");
	}

	@Override
	public void Coding() {
		System.out.println("I am coding!");
	}

	@Override
	public void Typing(int hours) {
		System.out.println("I am typing " + hours + " hours !");
	}
}
