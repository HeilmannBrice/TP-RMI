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


public class Serveur extends UnicastRemoteObject implements Chat{
	private static final long serialVersionUID = 1L;

	private  static ArrayList<String> listeMessage = new ArrayList<String>();
	private  static ArrayList<String> users = new ArrayList<String>();
	
	String message1 = "";
	
	public Serveur() throws RemoteException{
	super(0);
	}
	
	

	//envoie les messages du client vers le serveur, et les ajoute à la liste des messages
	public ArrayList<String> envoieDeMessage(String message, String nomUtilisateur) throws RemoteException{
		message1 = message;
		this.listeMessage.add(message);
		this.users.add(nomUtilisateur);
		return listeMessage;
		}



	public ArrayList<String> returnMessageList() throws RemoteException{
		return listeMessage;
	}
	

	public ArrayList<String> returnUser() throws RemoteException{
		return users;
	}
	
	
	
	 public static void main(String args[]) throws Exception {
	        try { 
	        	LocateRegistry.createRegistry(1099);
	
	        } catch (RemoteException e) {
	        	e.printStackTrace();
	        }
	        Serveur chatServeur = new Serveur();
	        Naming.rebind("//localhost/RmiServer", chatServeur);
	        System.out.println("Serveur pret!");

	        }



	    }


