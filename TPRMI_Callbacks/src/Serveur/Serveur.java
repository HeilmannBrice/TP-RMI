package Serveur;

import java.rmi.Naming;



import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Scanner;


import Interface.Chat;
import Interface.Chat2;


public class Serveur extends UnicastRemoteObject implements Chat{
	private static final long serialVersionUID = 1L;

	private  static ArrayList<Chat2> users = new ArrayList<Chat2>();
	
	String message1 = "";
	
	public Serveur() throws RemoteException{
	super(0);
	}
	
	//envoie les nouveaux messages aux clients
	public void envoieDeMessage(String message, String pseudo) throws RemoteException{
		returnMessage(message, pseudo);
		}

		
	//récupère les nouveaux messages 
	public void returnMessage(String message, String pseudo) throws RemoteException {
		for(Chat2 client : users) {
			try {
				if(!pseudo.equals(client.setPseudo())) {
					client.afficheMessage(message);
				}
				
		
		}catch(RemoteException e){
			e.printStackTrace();
			this.removeUser(client);
		}
		}

	}


	//ajoute un nouveau client
	public void addUser(Chat2 client) throws RemoteException{
		users.add(client);
	}
	
	//supprime un client
	public void removeUser(Chat2 client) throws RemoteException{
		users.remove(client);
	}
		

	
	 public static void main(String args[]) throws Exception {
	        try { 
	        	//création du registre
	        	LocateRegistry.createRegistry(1099);
	
	        } catch (RemoteException e) {
	        	e.printStackTrace();
	        }
	        Serveur chatServeur = new Serveur();
	        Naming.rebind("//localhost/RmiServer", chatServeur);
	        System.out.println("Serveur pret!");

	        }



	    }



