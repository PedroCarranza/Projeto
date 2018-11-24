package Projeto;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Enumeration;

public class Connect extends Thread implements Runnable {

    int opt;
    Socket cli;
    String servIP;
    ServerSocket serv;
    Projeto pr;
    boolean running, connec = false;

    public Connect(int o, Projeto p) {
        opt = o;
        pr = p;
    }

    @SuppressWarnings("")
    @Override
    public void run() {
        running = true;
        ObjectInputStream oIn = null;
        ObjectOutputStream oOut = null;
        InputStream in;
        if (opt == 0) {
            Thread discoveryThread = new Thread(DiscoveryThread.getInstance());
            discoveryThread.start();
            try {
                serv = new ServerSocket(25566);
                System.out.println("Servidor aberto");
                Socket tmp = serv.accept();
                System.out.println("Cliente conectado");
                connec = true;
                oOut = new ObjectOutputStream(tmp.getOutputStream());
                oIn = new ObjectInputStream(tmp.getInputStream());
            } catch (IOException e) {
                System.err.println("Não foi possível abrir o servidor");
            }
        }
        if (opt == 1) {
            receiveIP();
            try {
                cli = new Socket(servIP, 25566);
                if (cli.isConnected()) {
                    System.out.println("Conectado");
                    connec = true;
                    oOut = new ObjectOutputStream(cli.getOutputStream());
                    oIn = new ObjectInputStream(cli.getInputStream());
                    pr.tela.estadoTela = 10;
                }
            } catch (IOException ex) {
                System.err.println("Não foi possível conectar ao servidor");
                ex.printStackTrace();
            }
        }
        pr.tela.p2 = new Player2(pr);
        while (running) {

            try {
                Dados send = new Dados(pr);
                send.getData();
                oOut.writeObject(send);

                Dados recieve = (Dados) oIn.readObject();
                if (opt == 0) {
                    recieve.setData();
                } else if (opt == 1) {
                    recieve.setCliData();
                }

            } catch (SocketException ex) {
                System.err.println("Player disconectado");
                connec = false;
                pr.tela.p2 = null;
            } catch (IOException | ClassNotFoundException ex) {
                System.err.println("Deu ruim");
                ex.printStackTrace();
            }
        }
    }

    public void close() {
        try {
            serv.close();
            cli.close();
            connec = false;
            pr.tela.p2 = null;
        } catch (Exception e) {

        }
    }

    private void receiveIP() {
        // Find the server using UDP broadcast
        try {
            //Open a random port to send the package
            DatagramSocket c = new DatagramSocket();
            c.setBroadcast(true);

            byte[] sendData = "DISCOVER_FUIFSERVER_REQUEST".getBytes();

            //Try the 255.255.255.255 first
            try {
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getByName("255.255.255.255"), 8888);
                c.send(sendPacket);
                System.out.println(getClass().getName() + " > Request packet sent to: 255.255.255.255 (DEFAULT)");
            } catch (Exception e) {
            }

            // Broadcast the message over all the network interfaces
            Enumeration interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface networkInterface = (NetworkInterface) interfaces.nextElement();

                if (networkInterface.isLoopback() || !networkInterface.isUp()) {
                    continue; // Don't want to broadcast to the loopback interface
                }

                for (InterfaceAddress interfaceAddress : networkInterface.getInterfaceAddresses()) {
                    InetAddress broadcast = interfaceAddress.getBroadcast();
                    if (broadcast == null) {
                        continue;
                    }

                    // Send the broadcast package!
                    try {
                        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, broadcast, 8888);
                        c.send(sendPacket);
                    } catch (Exception e) {
                    }

                    System.out.println(getClass().getName() + " > Request packet sent to: " + broadcast.getHostAddress() + "; Interface: " + networkInterface.getDisplayName());
                }
            }

            System.out.println(getClass().getName() + " > Done looping over all network interfaces. Now waiting for a reply!");

            //Wait for a response
            byte[] recvBuf = new byte[15000];
            DatagramPacket receivePacket = new DatagramPacket(recvBuf, recvBuf.length);
            c.receive(receivePacket);

            //We have a response
            System.out.println(getClass().getName() + " > Broadcast response from server: " + receivePacket.getAddress().getHostAddress());

            //Check if the message is correct
            String message = new String(receivePacket.getData()).trim();
            if (message.equals("DISCOVER_FUIFSERVER_RESPONSE")) {
                //DO SOMETHING WITH THE SERVER'S IP (for example, store it in your controller)
                servIP = receivePacket.getAddress().getHostAddress();
            }

            //Close the port!
            c.close();
        } catch (IOException ex) {
            System.err.println("ADajksdohji");
        }
    }
}
