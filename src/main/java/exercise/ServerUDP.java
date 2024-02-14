package exercise;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ServerUDP {
    private byte[] buff = new byte[256];
    private DatagramSocket socket;
    java.util.Date date = new java.util.Date();

    public ServerUDP() throws IOException{

        this.socket = new DatagramSocket(9000);
    }

    public void start(){
        System.out.println("Server starts.");

        while (true) {
            try{
                DatagramPacket packetFromClient = new DatagramPacket(buff, buff.length);
                socket.receive(packetFromClient);
                byte[] answer =(String.valueOf(date)).getBytes();
                DatagramPacket packetToClient = new DatagramPacket(answer, answer.length, packetFromClient.getAddress(),packetFromClient.getPort());
                socket.send(packetToClient);
                String messageFromClient = new String(packetFromClient.getData(),0,packetFromClient.getLength());

                System.out.println("Сообщение: "+ messageFromClient+" \nдата: "+date+", адрес клиента: "+packetFromClient.getAddress()+packetFromClient.getPort());

                }catch (IOException ioe){}
        }
    }
}
