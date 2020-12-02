package Client;



import java.net.MalformedURLException;


import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Scanner;

import Interface.Chat;
import Interface.Chat2;

public class Client extends UnicastRemoteObject implements Chat2{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Scanner sc1 = new Scanner(System.in);
	private String pseudo;
	
	//Chat Serveur;
	
	public Client(String pseudo) throws RemoteException {
		super(0);
		this.pseudo = pseudo;
		// TODO Auto-generated constructor stub
	}

	
	public String entree() {
		return sc1.nextLine();
	}
	
	public void afficheMessage(String message) throws RemoteException {
		System.out.println("->"+message);	
	}
	
	public String setPseudo() throws RemoteException{
		return pseudo;
	}

	
	public static void main(String args[]) throws Exception {
		
		System.out.println("Bienvenus dans le chat, entrez quit si vous voulez partir ;)");
		try {
			Registry registry = LocateRegistry.getRegistry();
			Chat stub = (Chat) Naming.lookup("//localhost/RmiServer");
			

	        Scanner sc1 = new Scanner(System.in);
	        System.out.println("Entre ton pseudo :");
	        String pseudo = sc1.nextLine();
			
			Chat2 chatClient=new Client(pseudo);  
			
			stub.addUser(chatClient);

	        
	        System.out.println("Tu peux commencer à envoyer des messages ;)");
	        String message = sc1.nextLine();
	        
	        while(!message.equals("quit")) {
	        	if(!message.equals("")) {
		        	stub.envoieDeMessage(message, pseudo);
		        	message = sc1.nextLine();
	        	}
	        }
	        sc1.close();
	        stub.removeUser(chatClient);//supprime le client
	        System.out.println("Au revoir !");
	        System.exit(0);//éteint le client
	        
			
			

	        
		}catch(RemoteException e) {
			//e.printStackTrace();
			System.out.println("le serveur n'est plus accessible");
			System.exit(0);
		}catch(NotBoundException e) {
			e.printStackTrace();
		}      
    }
}

