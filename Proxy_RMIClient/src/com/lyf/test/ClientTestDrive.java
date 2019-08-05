package com.lyf.test;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class ClientTestDrive {

	public static void main(String[] args) {
		try {
			RemoteCallInterface r = (RemoteCallInterface) Naming.lookup("rmi://192.168.1.2:8999/Today");
			
			String day = r.getToday();
			
			System.out.printf("I got a message, today is %s\n", day);
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
	}

}
