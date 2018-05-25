/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public class ServidorTCP {

    private static int PORT_NUMBER = 2017;
    private static final ServidorTCP instancia = new ServidorTCP();
    private ServerSocket ss = null;
    public static ServidorTCP getInstancia() {
        return instancia;
    }

    private static String mensagem1;
    private static String mensagem2;
    private static String mensagem3;
    private static String mensagem4;
    private static String mensagem5;
    private static String mensagem6;
    private static String mensagem7;
    private static String mensagem8;

    public String getMensagem() {
        return mensagem1;
    }

    public String getMensagem2() {
        return mensagem2;
    }

    public void setMensagem(String mensagem1) {
        this.mensagem1 = mensagem1;
    }

    public void setMensagem2(String mensagem2) {
        this.mensagem2 = mensagem2;
    }

    public String getMensagem1() {
        return this.mensagem1;
    }

    public void setMensagem1(String mensagem1) {
        ServidorTCP.mensagem1 = mensagem1;
    }

    public String getMensagem5() {
        return this.mensagem5;
    }

    public void setMensagem5(String mensagem5) {
        ServidorTCP.mensagem5 = mensagem5;
    }

    public String getMensagem6() {
        return this.mensagem6;
    }

    public void setMensagem6(String mensagem6) {
        ServidorTCP.mensagem6 = mensagem6;
    }

    public String getMensagem7() {
        return this.mensagem7;
    }

    public void setMensagem7(String mensagem7) {
        ServidorTCP.mensagem7 = mensagem7;
    }

    public String getMensagem8() {
        return this.mensagem8;
    }

    public void setMensagem8(String mensagem8) {
        ServidorTCP.mensagem8 = mensagem8;
    }

    public String getMensagem3() {
        return this.mensagem3;
    }

    public void setMensagem3(String mensagem3) {
        ServidorTCP.mensagem3 = mensagem3;
    }

    public String getMensagem4() {
        return mensagem4;
    }

    public void setMensagem4(String mensagem4) {
        ServidorTCP.mensagem4 = mensagem4;
    }

    public ServidorTCP() {
        try {
            ss = new ServerSocket(PORT_NUMBER);
            while (true) {
                System.out.println("Server waiting @" + ss.getInetAddress());
                Socket s = ss.accept();
                System.out.println("connection from:" + s.getInetAddress());
                new ServidorTCP.Worker(s).start();
            }
        } catch (IOException e) {
            System.out.println(e);
        } finally {
            try {
                ss.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    static class Worker extends Thread {

        final static ArrayList<PrintStream> os = new ArrayList(10);
        Socket clientSocket;
        BufferedReader fromClient;

        public Worker(Socket clientSocket) throws IOException {
            this.clientSocket = clientSocket;
            PrintStream toClient = new PrintStream(new BufferedOutputStream(this.clientSocket.getOutputStream()));
            toClient.println("connected to server");
            os.add(toClient);
            fromClient = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));

        }
        @Override
        public void run() {

            while (true) {
                try {
                    String message = fromClient.readLine();

                    System.out.println(message);

                    if ((message.equals("Esquerda"))) {
                        instancia.setMensagem(message);
                        instancia.setMensagem2(null);
                        instancia.setMensagem3(null);
                        instancia.setMensagem4(null);
                        instancia.setMensagem5(null);
                        instancia.setMensagem6(null);
                        instancia.setMensagem7(null);
                        instancia.setMensagem8(null);

                        System.out.println(mensagem1);
                    }

                    if ((message.equals("Direita"))) {
                        instancia.setMensagem2(message);
                        instancia.setMensagem(null);
                        instancia.setMensagem3(null);
                        instancia.setMensagem4(null);
                        instancia.setMensagem5(null);
                        instancia.setMensagem6(null);
                        instancia.setMensagem7(null);
                        instancia.setMensagem8(null);

                        System.out.println(mensagem2);
                    }

                    if ((message.equals("Acima"))) {
                        instancia.setMensagem3(message);
                        instancia.setMensagem(null);
                        instancia.setMensagem2(null);
                        instancia.setMensagem4(null);
                        instancia.setMensagem5(null);
                        instancia.setMensagem6(null);
                        instancia.setMensagem7(null);
                        instancia.setMensagem8(null);

                        System.out.println(mensagem3);
                    }

                    if ((message.equals("Abaixo"))) {
                        instancia.setMensagem4(message);
                        instancia.setMensagem(null);
                        instancia.setMensagem3(null);
                        instancia.setMensagem2(null);
                        instancia.setMensagem5(null);
                        instancia.setMensagem6(null);
                        instancia.setMensagem7(null);
                        instancia.setMensagem8(null);

                        System.out.println(mensagem4);
                    }

                    if ((message.equals("A"))) {
                        instancia.setMensagem5(message);
                        instancia.setMensagem(null);
                        instancia.setMensagem3(null);
                        instancia.setMensagem2(null);
                        instancia.setMensagem4(null);
                        instancia.setMensagem6(null);
                        instancia.setMensagem7(null);
                        instancia.setMensagem8(null);

                        System.out.println(mensagem5);
                    }
                    if ((message.equals("D"))) {
                        instancia.setMensagem6(message);
                        instancia.setMensagem(null);
                        instancia.setMensagem3(null);
                        instancia.setMensagem2(null);
                        instancia.setMensagem4(null);
                        instancia.setMensagem5(null);
                        instancia.setMensagem7(null);
                        instancia.setMensagem8(null);

                        System.out.println(mensagem6);
                    }
                    if ((message.equals("W"))) {
                        instancia.setMensagem7(message);
                        instancia.setMensagem(null);
                        instancia.setMensagem3(null);
                        instancia.setMensagem2(null);
                        instancia.setMensagem4(null);
                        instancia.setMensagem6(null);
                        instancia.setMensagem5(null);
                        instancia.setMensagem8(null);

                        System.out.println(mensagem4);
                    }
                    if ((message.equals("S"))) {
                        instancia.setMensagem8(message);
                        instancia.setMensagem(null);
                        instancia.setMensagem3(null);
                        instancia.setMensagem2(null);
                        instancia.setMensagem4(null);
                        instancia.setMensagem6(null);
                        instancia.setMensagem7(null);
                        instancia.setMensagem5(null);

                        System.out.println(mensagem4);
                    }
                    synchronized (os) {

                        for (PrintStream toClient : os) {
                            toClient.println(message);
                            toClient.flush();
                        }

                    }

                } catch (IOException ex) {
                    //user discnnected
                    try {
                        clientSocket.close();
                    } catch (IOException ex1) {

                    }
                }

            }
        }

    }
}
