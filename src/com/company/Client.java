package com.company;
import java.net.*;
import java.io.*;

public class Client
{
    private Socket socket = null;
    private DataInputStream input = null;
    private DataOutputStream out = null;


    public Client(String address, int port)
    {
        try
        {
            socket = new Socket(address, port);
            System.out.println("Connected");

            input = new DataInputStream(System.in);

            out = new DataOutputStream(socket.getOutputStream());
        }
        catch(UnknownHostException u)
        {
            System.out.println(u);
        }
        catch(IOException i)
        {
            System.out.println(i);
        }

        String line = "";
        boolean done = false;
        int test;
        while (!done)
        {
            try
            {
                line = input.readLine();
                out.writeUTF(line);
                try{
                    test = Integer.parseInt(line);
                    if(isPrime(test)){
                        System.out.println(test + " is prime!");
                        done = true;
                    }
                    else {
                        System.out.println(test + " is not prime :(");
                    }
                } catch(NumberFormatException nfe){
                    System.out.println("Please enter a number");
                }
            }
            catch(IOException i)
            {
                System.out.println(i);
            }
        }
        try
        {
            input.close();
            out.close();
            socket.close();
        }
        catch(IOException i)
        {
            System.out.println(i);

        }
    }

    public static void main(String args[])
    {
        Client client = new Client("127.0.0.1", 7000);
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
