package com.lyf.test;

import java.lang.reflect.Proxy;

public class ProxyDynamicTestDrive {
	private static iHuman getProgrammerProxy(Person i) {
		return (iHuman) Proxy.newProxyInstance(i.getClass().getClassLoader(), i.getClass().getInterfaces(), new proxyProgrammer(i));
	}
	
	private static iHuman getTyperProxy(Person i) {
		return (iHuman) Proxy.newProxyInstance(i.getClass().getClassLoader(), i.getClass().getInterfaces(), new proxyTyper(i));
	}
	
	public static void main(String[] args) {
		Person i = new Person();
		
		iHuman h = getProgrammerProxy(i);
		
		((iProgrammer)h).Coding();
		((iTyper)h).Typing(230);
		
		System.out.println();
		
		h = getTyperProxy(i);
		((iProgrammer)h).Coding();
		((iTyper)h).Typing(230);		
	}

}
