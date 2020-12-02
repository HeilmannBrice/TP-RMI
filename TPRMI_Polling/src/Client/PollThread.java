package Client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.rmi.CORBA.Stub;

import Interface.Chat;

public class PollThread extends Thread{
	
	private Chat stub;
	private String nomUtilisateur;
	private Client chatClient;
	
	public PollThread(Chat stub, String nomUtilisateur, Client chatClient) {
		super();
		this.stub = stub;
		this.nomUtilisateur = nomUtilisateur;
		this.chatClient = chatClient;
	}

	int compteur =0;


	
	public void run(){
		try {
			for(String message : stub.returnMessageList()) {
				chatClient.returnMessageList().add(message);
			}
		}catch(RemoteException e) {
			e.printStackTrace();
		}

		while (true) 
			try {
				if(chatClient.returnMessageList().size()<stub.returnMessageList().size()) {
					for (int i=chatClient.returnMessageList().size(); i<stub.returnMessageList().size(); i++) {
						if(!stub.returnUser().get(i).equals(nomUtilisateur)) {
							String message=stub.returnMessageList().get(i);
							compteur++;
							System.out.println("->"+message+" message numéro : "+compteur);	
							chatClient.returnMessageList().add(message);
						}
						else {
							String message=stub.returnMessageList().get(i);
							compteur++;
							chatClient.returnMessageList().add(message);
						}


					}
			}

			}catch(RemoteException e) {
				//e.printStackTrace();
				System.out.println("Le serveur n'est plus accessible");
				System.exit(0);
			}
			
			} 

	}
	


