package main;

import br.ol.pacman.PacmanGame;
import br.ol.pacman.infra.Display;
import br.ol.pacman.infra.Game;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import br.ol.pacman.PacmanActor;
import br.ol.pacman.PacmanGame;
import br.ol.pacman.infra.Game;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Main class.
 * 
 * @author Leonardo Ono (ono.leo@gmail.com)
 */
public class Main {
    
     static int PORT_NUMBER = 2017;

    private static String mensagem1;
    private static String mensagem2;
    private static String mensagem3;
    private static String mensagem4;
    private static String mensagem5;
    private static String mensagem6;
    private static String mensagem7;
    private static String mensagem8;
    private static String mensagem9;

   
    public String getMensagem1() {
        return this.mensagem1;
    }

    public void setMensagem1(String mensagem1) {
        Main.mensagem1 = mensagem1;
    }

    public String getMensagem5() {
        return this.mensagem5;
    }

    public void setMensagem5(String mensagem5) {
        Main.mensagem5 = mensagem5;
    }

    public String getMensagem6() {
        return this.mensagem6;
    }

    public void setMensagem6(String mensagem6) {
        Main.mensagem6 = mensagem6;
    }

    public String getMensagem7() {
        return this.mensagem7;
    }

    public void setMensagem7(String mensagem7) {
        Main.mensagem7 = mensagem7;
    }

    public String getMensagem8() {
        return this.mensagem8;
    }

    public void setMensagem8(String mensagem8) {
        Main.mensagem8 = mensagem8;
    }
    
    

    public String getMensagem3() {
        return this.mensagem3;
    }

    public void setMensagem3(String mensagem3) {
        Main.mensagem3 = mensagem3;
    }

    public String getMensagem4() {
        return mensagem4;
    }

    public void setMensagem4(String mensagem4) {
        Main.mensagem4 = mensagem4;
    }

    public static String getMensagem9() {
        return mensagem9;
    }

    public static void setMensagem9(String mensagem9) {
        Main.mensagem9 = mensagem9;
    }
    
    
    

 
    
    private static final Main instancia = new Main();
    
    public static Main getInstancia() {
        return instancia;
    }
    
    public String getMensagem() {
        return mensagem1;
    }
    
    public String getMensagem2() {
        return mensagem2;
    }
    
    public void setMensagem(String mensagem1){
        this.mensagem1 = mensagem1;
    }
    
    
    public void setMensagem2(String mensagem2){
        this.mensagem2 = mensagem2;
    }
    
    
    

    public static void main(String[] args) throws IOException {
        
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                Game game = new PacmanGame();
                Display view = new Display(game);
                JFrame frame = new JFrame();
                frame.setTitle("Pacman");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.getContentPane().add(view);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                view.requestFocus();
                view.start();
            }

        });
        ServerSocket ss = null;
        try {
            ss = new ServerSocket(PORT_NUMBER);
            while (true){
                System.out.println("Server waiting @" + ss.getInetAddress());
                Socket s = ss.accept();
                System.out.println("connection from:" + s.getInetAddress());
                new Main.Worker(s).start();
            }
        }catch(IOException e ){
            System.out.println(e);
        }
        finally{
            try {
                ss.close();
            }
            catch (Exception e){
                System.out.println(e);
            }
        }
        /*
        while (true) {
            try (ServerSocket ss = new ServerSocket(PORT_NUMBER)){
                System.out.println("Server waiting @" + ss.getInetAddress());
                Socket s = ss.accept();
                System.out.println("connection from:" + s.getInetAddress());
                
                new Main.Worker(s).start();
            } 
        }
        */
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
                    
                    if( (message.equals("PacmanEsquerda"))){
                        instancia.setMensagem(message);
                        instancia.setMensagem2(null);
                        instancia.setMensagem3(null);
                        instancia.setMensagem4(null);
                        instancia.setMensagem5(null);
                        instancia.setMensagem6(null);
                        instancia.setMensagem7(null);
                        instancia.setMensagem8(null);
                        instancia.setMensagem9(null);
                        
                        System.out.println(mensagem1);
                    }
                    
                    if((message.equals("PacmanDireita"))){
                        instancia.setMensagem2(message);
                        instancia.setMensagem(null);
                        instancia.setMensagem3(null);
                        instancia.setMensagem4(null);
                        instancia.setMensagem5(null);
                        instancia.setMensagem6(null);
                        instancia.setMensagem7(null);
                        instancia.setMensagem8(null);
                        instancia.setMensagem9(null);
                        
                        System.out.println(mensagem2);
                    }
                    
                    if((message.equals("PacmanAcima"))){
                        instancia.setMensagem3(message);
                        instancia.setMensagem(null);
                        instancia.setMensagem2(null);
                        instancia.setMensagem4(null);
                        instancia.setMensagem5(null);
                        instancia.setMensagem6(null);
                        instancia.setMensagem7(null);
                        instancia.setMensagem8(null);
                        instancia.setMensagem9(null);
                        
                        System.out.println(mensagem3);
                    }
                    
                    if((message.equals("PacmanAbaixo"))){
                        instancia.setMensagem4(message);
                        instancia.setMensagem(null);
                        instancia.setMensagem3(null);
                        instancia.setMensagem2(null);
                        instancia.setMensagem5(null);
                        instancia.setMensagem6(null);
                        instancia.setMensagem7(null);
                        instancia.setMensagem8(null);
                        instancia.setMensagem9(null);
                        
                        System.out.println(mensagem4);
                    }
                    
                    if((message.equals("GhostEsquerda"))){
                        instancia.setMensagem5(message);
                        instancia.setMensagem(null);
                        instancia.setMensagem3(null);
                        instancia.setMensagem2(null);
                        instancia.setMensagem4(null);
                        instancia.setMensagem6(null);
                        instancia.setMensagem7(null);
                        instancia.setMensagem8(null);
                        instancia.setMensagem9(null);
                        
                        System.out.println(mensagem5);
                    }
                    if((message.equals("GhostDireita"))){
                        instancia.setMensagem6(message);
                        instancia.setMensagem(null);
                        instancia.setMensagem3(null);
                        instancia.setMensagem2(null);
                        instancia.setMensagem4(null);
                        instancia.setMensagem5(null);
                        instancia.setMensagem7(null);
                        instancia.setMensagem8(null);
                        instancia.setMensagem9(null);
                        
                        System.out.println(mensagem6);
                    }
                    if((message.equals("GhostAcima"))){
                        instancia.setMensagem7(message);
                        instancia.setMensagem(null);
                        instancia.setMensagem3(null);
                        instancia.setMensagem2(null);
                        instancia.setMensagem4(null);
                        instancia.setMensagem6(null);
                        instancia.setMensagem5(null);
                        instancia.setMensagem8(null);
                        instancia.setMensagem9(null);
                        
                        System.out.println(mensagem4);
                    }
                    if((message.equals("GhostAbaixo"))){
                        instancia.setMensagem8(message);
                        instancia.setMensagem(null);
                        instancia.setMensagem3(null);
                        instancia.setMensagem2(null);
                        instancia.setMensagem4(null);
                        instancia.setMensagem6(null);
                        instancia.setMensagem7(null);
                        instancia.setMensagem5(null);
                        instancia.setMensagem9(null);
                        
                        System.out.println(mensagem4);
                    }
                    if("PacmanEspaço".equals(message)||"GhostEspaço".equals(message)){
                        instancia.setMensagem9(message);
                        instancia.setMensagem(null);
                        instancia.setMensagem3(null);
                        instancia.setMensagem2(null);
                        instancia.setMensagem4(null);
                        instancia.setMensagem6(null);
                        instancia.setMensagem7(null);
                        instancia.setMensagem5(null);
                        instancia.setMensagem8(null);
                        
                        System.out.println(mensagem9);
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