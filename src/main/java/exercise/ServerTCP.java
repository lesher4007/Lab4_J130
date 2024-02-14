package exercise;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerTCP {

    public void start(){
        System.out.println("Server starts.");
        try(ServerSocket serverSocket = new ServerSocket(9000)){
            while (true) {
                Socket socket = serverSocket.accept();
                new Thread(()->handleSocket(socket)).start();
            }
        }catch (IOException ioe){}
    }

    private void handleSocket(Socket s){
        java.util.Date date = new java.util.Date();
        try(Socket socket = s){
            try(BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

                String message = reader.readLine();
                System.out.println("Сообщение: "+message+"\nдата: "+date+", адрес клиента: "+socket.getLocalSocketAddress());
                writer.write(String.valueOf(date));
                writer.flush();
            }
        }catch (IOException ioe){}
    }
}
