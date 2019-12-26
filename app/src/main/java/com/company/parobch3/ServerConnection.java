package com.company.parobch3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerConnection {
	
	private Socket clientSocket;
    private ServerSocket server;
    private BufferedReader in;
    private BufferedWriter out;

	public ServerConnection(ServerSocket socket){	
        try {
        	this.server = socket;
    		System.out.println("������ �������!");
			clientSocket = server.accept();
			System.out.println("���������� �����������!");
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	        out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void toDoDataAlgorithm() {
		String word, res;
		try {
			word = in.readLine();
			System.out.println("Length of taken string: " + word.length());
			FindAmountLetter findLet = new FindAmountLetter(3, 'k', word);
			res = String.valueOf(findLet.retAmount());
			out.write(res + "\n");
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
