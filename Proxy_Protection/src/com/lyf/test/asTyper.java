package com.lyf.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.lyf.test.asProgrammer.ProgrammerInvocationHandler;

public class asTyper {
	private Human p;
	
	public asTyper(Human v) {
		this.p = v;
	}
	
	class ProgrammerInvocationHandler implements InvocationHandler {
		private Human p;
		
		public ProgrammerInvocationHandler(Human v) {
			this.p = v;
		}
		
		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			try {
			if( method.getName().equals("Typing")) {
				return method.invoke(p, args);
			} else if( method.getName().startsWith("do") ) {
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
	
	public Human getJob() {
		return (Human) Proxy.newProxyInstance(p.getClass().getClassLoader(), p.getClass().getInterfaces(), new ProgrammerInvocationHandler(p));
	}
}
