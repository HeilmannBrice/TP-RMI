package Client;



import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Interface.Chat;


public class Client {
	
	
	private  ArrayList<String> listeMessage = new ArrayList<String>();
	private Scanner sc1 = new Scanner(System.in);
	
	
	

	public ArrayList<String> returnMessageList() throws RemoteException{
		return listeMessage;
	}
	
	public String entree() {
		return sc1.nextLine();
	}

	
	public static void main(String args[]) throws Exception {
		
		try {
			Registry registry = LocateRegistry.getRegistry();
			Chat stub = (Chat) Naming.lookup("//localhost/RmiServer");

			
			Client chatClient=new Client();  
	        Scanner sc1 = new Scanner(System.in);
	        
			System.out.println("veuillez rentrer votre nom d'utlisateur : ");
		    String nomUtilisateur = sc1.nextLine();
			System.out.println("ok");
			
			new SendThread(stub, nomUtilisateur, chatClient).start();
	        new PollThread(stub, nomUtilisateur, chatClient).start();

	        
		}catch(RemoteException e) {
			//e.printStackTrace();
			System.out.println("Le serveur n'est plus accessible");
			System.exit(0);
		}catch(NotBoundException e) {
			e.printStackTrace();
		}      
    }
}

