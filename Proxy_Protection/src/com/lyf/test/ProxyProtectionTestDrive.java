package com.lyf.test;

public class ProxyProtectionTestDrive {

	public static void main(String[] args) {
		I i = new I();
		
		asProgrammer job1 = new asProgrammer(i);
		Programmer p = (Programmer) job1.getJob();
		p.Coding();
		Typer t = (Typer)p;
		t.Typing(230);
		
		asTyper job2 = new asTyper(i);
		t = (Typer) job2.getJob();
		t.Typing(100);
		p = (Programmer) t;
		p.Coding();
		
	}

}
