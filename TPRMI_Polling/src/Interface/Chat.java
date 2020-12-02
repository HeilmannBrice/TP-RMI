package Interface;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface Chat extends Remote{

	
	public ArrayList<String> envoieDeMessage(String message, String nomUtilisateur) throws RemoteException;
	
	//public String returnMessage()throws RemoteException;
	
	public ArrayList<String> returnMessageList() throws RemoteException;
	
	public ArrayList<String> returnUser() throws RemoteException;
}
