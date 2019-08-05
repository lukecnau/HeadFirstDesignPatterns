package com.lyf.test;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class ServerTestDrive {

//	ServerTestDrive() {
//		System.setProperty("java.rmi.server.hostname","192.168.1.2");
//	}

	public static void main(String[] args) {
		
		DateMachine dm = new DateMachine();
		
		System.out.println("Ok! DateMachine service started!");
		try {
			RemoteCallInterface service = new DateMachineProxy(dm);

//				Registry r = LocateRegistry.createRegistry(8999);
//				r.rebind("Today", service);

			// same way
			LocateRegistry.createRegistry(8999);
			Naming.rebind("//localhost:8999/Today", service);

			System.out.println("Ok! DateMachine proxy bound!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
