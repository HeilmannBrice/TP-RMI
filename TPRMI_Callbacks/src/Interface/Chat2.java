package Interface;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Chat2 extends Remote{

	public void afficheMessage(String message) throws RemoteException;
	public String setPseudo() throws RemoteException;
}
