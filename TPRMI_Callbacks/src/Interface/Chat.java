package Interface;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import Client.Client;

public interface Chat extends Remote{

	
	public void envoieDeMessage(String message, String pseudo) throws RemoteException;
	
	public void returnMessage(String message, String pseudo)throws RemoteException;
	
	public void addUser(Chat2 chatClient) throws RemoteException;
	
	public void removeUser(Chat2 client) throws RemoteException;
	

}


