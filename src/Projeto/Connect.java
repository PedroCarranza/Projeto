package Projeto;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Connect extends Thread implements Runnable {

    int opt;
    Socket cli;
    String servIP;
    ServerSocket serv;
    Projeto pr;
    boolean running, connec = false;

    public Connect(int o,Projeto p) {
        opt = o;
        pr = p;
    }

    @Override
    public void run() {
        running = true;
        DataInputStream dIn = null;
        DataOutputStream dOut = null;
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
                dIn = new DataInputStream(tmp.getInputStream());
                dOut = new DataOutputStream(tmp.getOutputStream());
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
                    dIn = new DataInputStream(cli.getInputStream());
                    dOut = new DataOutputStream(cli.getOutputStream());
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
                dOut.writeByte(1);
                dOut.writeInt(pr.tela.p1.px);
                dOut.writeInt(pr.tela.p1.py);
                dOut.writeBoolean(pr.lis.up);
                dOut.writeBoolean(pr.lis.down);
                dOut.writeBoolean(pr.lis.left);
                dOut.writeBoolean(pr.lis.right);
                dOut.writeBoolean(pr.lis.tiro);
                //String in = br.readLine();
                if(dIn.readByte() == 1){
                    pr.tela.p2.px = dIn.readInt();
                    pr.tela.p2.py = dIn.readInt();
                    pr.tela.p2.up = dIn.readBoolean();
                    pr.tela.p2.down = dIn.readBoolean();
                    pr.tela.p2.left = dIn.readBoolean();
                    pr.tela.p2.right = dIn.readBoolean();
                    pr.tela.p2.tiro = dIn.readBoolean();
                }
                
            } catch (IOException ex) {
                Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void close() {
        try {
            serv.close();
            cli.close();
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
