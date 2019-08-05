package com.lyf.test;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class DateMachineProxy extends UnicastRemoteObject implements RemoteCallInterface {
	DateMachine dm;
	
	protected DateMachineProxy() throws RemoteException {
		super();
		dm = new DateMachine();
	}

	protected DateMachineProxy(DateMachine v) throws RemoteException {
		super();
		dm = v;
	}
	
	@Override
	public String getToday() throws RemoteException {
		return dm.getDate();
	}

	
}
