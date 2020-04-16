package com.company;// A Java program for a Server
import java.net.*;
import java.io.*;

public class Server
{
    private Socket socket = null;
    private ServerSocket server = null;
    private DataInputStream in = null;

    public Server(int port)
    {

        try
        {
            server = new ServerSocket(port);
            System.out.println("Server started");

            System.out.println("Waiting for a client ...");

            socket = server.accept();
            System.out.println("Client accepted");


            in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));

            String line = "";
            int test;
            boolean done = false;

            while (!done)
            {
                try
                {
                    line = in.readUTF();
                    try{
                        test = Integer.parseInt(line);
                        if(isPrime(test)){
                            System.out.println(line + " is prime!");
                            done = true;
                        }else {
                            System.out.println(test + " is not prime :(");
                        }
                    }catch(NumberFormatException nfe){
                        System.out.println("This is not a number");
                    }
                    System.out.println(line);

                }
                catch(IOException i)
                {
                    System.out.println(i);
                }
            }
            System.out.println("Closing connection");

            // close connection
            socket.close();
            in.close();
        }
        catch(IOException i)
        {
            System.out.println(i);
        }
    }

    public static void main(String args[])
    {
        Server server = new Server(7000);
    }

    static boolean isPrime(int n)
    {
        if (n <= 1)
            return false;

        for (int i = 2; i < n; i++)
            if (n % i == 0)
                return false;

        return true;
    }
}
