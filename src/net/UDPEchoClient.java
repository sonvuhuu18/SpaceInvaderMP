package net;


import java.net.*;

import core.Game;
import input.KeyboardManager;

import java.awt.event.KeyEvent;
import java.io.*;


public class UDPEchoClient {
	private int port;
	private Game game;
	public static class UDPEchoReader extends Thread {
		public UDPEchoReader(DatagramSocket socket) {
			datagramSocket = socket;
			active = true;
		}
		public void run() {
			byte[] buffer = new byte[1024];
			DatagramPacket incoming = new DatagramPacket(buffer, buffer.length);
			String receivedString;
			while(active) {
				try {
					// listen for incoming datagram packet
					datagramSocket.receive(incoming);
					// print out received string
					receivedString = new String(incoming.getData(), 0, incoming.getLength());
					System.out.println("From Server: "+receivedString);
				}
				catch(IOException e) {
					System.out.println(e);
					active = false;
				}
			}
		}
		public void keyPressed(KeyEvent e){

		}
		public void keyReleased(KeyEvent e){

		}
		public boolean active;
		public DatagramSocket datagramSocket;
	 }
	public UDPEchoClient(Game game, InetAddress address, int port){
		this.port = port;
		this.game = game;
		DatagramSocket datagramSocket = null;
	}
//	 public static void main(String[] args) {
//		 InetAddress address = null;
//		 int port = 8000;
//		 DatagramSocket datagramSocket = null;
//		 BufferedReader keyboardReader = null;
//		 // Create a Datagram Socket...
//		 try {
//			 address = InetAddress.getByName("127.0.0.1");
//			 datagramSocket = new DatagramSocket();
//			 keyboardReader = new BufferedReader(new InputStreamReader(System.in));
//		 }
//		 catch (IOException e) {
//			 System.out.println(e);
//			 System.exit(1);
//		 }
//		 // Start the listening thread...
//		 UDPEchoReader reader = new UDPEchoReader(datagramSocket);
//		 reader.setDaemon(true);
//		 reader.start();
//		 System.out.println("Ready to send your messages...");
//		 try {
//			 String input;
//			 while (true) {
//				 // read input from the keyboard
//				 input = keyboardReader.readLine();
//				 // send datagram packet to the server
//				 DatagramPacket datagramPacket = new DatagramPacket(input.getBytes(), input.length(), address, port);
//				 datagramSocket.send(datagramPacket);
//			 }
//		 }
//		 catch(IOException e) {
//			 System.out.println(e);
//		 }
//	 }
}
