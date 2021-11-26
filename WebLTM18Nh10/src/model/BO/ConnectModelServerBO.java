package model.BO;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class ConnectModelServerBO {
	private int port = 9900;
	private DatagramSocket clientSocket;
    private InetAddress address;
    private byte[] buf;
    private DatagramPacket packet;
    
    public static void main(String[] args) {
    	ConnectModelServerBO connectServer = new ConnectModelServerBO(9900);
    	connectServer.connectToServer();
    	connectServer.sendMessageToServer("ahihi");
    }
    
	public ConnectModelServerBO(int port) {
		this.port  = port;
		try {
			this.address = InetAddress.getByName("localhost");
			System.out.println("Connected to server");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
	
	// ket noi den server python
	public void connectToServer() {
		try {
			clientSocket = new DatagramSocket();
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}
	
	
	// gui thong diep den server
	public void sendMessageToServer(String path_video) {
		System.out.println("sending mess...");
		try {
			buf = path_video.getBytes();
			DatagramPacket packet = new DatagramPacket(buf, buf.length, address, port);
			clientSocket.send(packet);
			System.out.println("success!!!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// nhan thong diepp tu server
	public String recieveMessageFromServer() {
		String received = null;
		try {
			packet = new DatagramPacket(buf, buf.length);
			clientSocket.receive(packet);
	        received = new String(packet.getData(), 0, packet.getLength());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return received;
	}
}
