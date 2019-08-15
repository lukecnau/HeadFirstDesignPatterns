package com.lyf.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class proxyProgrammer implements InvocationHandler {
	private iHuman p;

	public proxyProgrammer(iHuman v) {
		this.p = v;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		try {
			if (method.getName().equals("Coding")) {
				return method.invoke(p, args);
			} else if (method.getName().startsWith("do")) {
				return method.invoke(p, args);
			} else {
				System.out.println("No! " + method.getName() + " is out of my duty!");
				throw new IllegalAccessException();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
