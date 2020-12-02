package Client;

import java.rmi.RemoteException;

import Interface.Chat;

public class SendThread extends Thread{

	private Chat stub;
	private String nomUtilisateur;
	private Client chatClient;

	
	public SendThread(Chat stub, String nomUtilisateur, Client chatClient) {
		super();
		this.stub = stub;
		this.nomUtilisateur = nomUtilisateur;
		this.chatClient = chatClient;
	}
	
	public void run(){
		while(true) {
			try {
				String message = this.chatClient.entree();
				if(!message.equals("quit")) {
					stub.envoieDeMessage(message, nomUtilisateur);
				}
				else {
					break;
				}
				
			}catch(RemoteException e) {
				//e.printStackTrace();
				System.out.println("Le serveur n'est plus accessible");
				System.exit(0);
			}
		}
		System.out.println("Au revoir !");
		System.exit(0);
	}
	

}