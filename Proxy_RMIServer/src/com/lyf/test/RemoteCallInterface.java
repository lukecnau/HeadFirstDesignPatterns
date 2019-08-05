package com.lyf.test;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteCallInterface extends Remote {
	public String getToday() throws RemoteException;
}
