package cliente;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author user
 */
public class GameClientTCP {

    private static GameClientTCP instancia = new GameClientTCP();
    private static String str2;
    private static StringBuffer strbuf;
    private static PrintStream toServer = null;
    private static Socket s = null;
    private static JFrame frame= null;
    private static JLabel labelField = null;
    private static JTextField textField = null;
    private static KeyListener setupKeyBoard() {
        KeyListener listener = new KeyListener() {

            @Override
            public void keyPressed(KeyEvent event) {
                printEventInfo("Key Pressed", event);
            }

            @Override
            public void keyReleased(KeyEvent event) {
                printEventInfo("Key Released", event);

            }

            @Override
            public void keyTyped(KeyEvent event) {
                printEventInfo("Key Typed", event);
            }

            private void printEventInfo(String str, KeyEvent e) {

                int code = e.getKeyCode();
                instancia.setStr2(KeyEvent.getKeyText(code));

                instancia.setStrbuf(new StringBuffer(instancia.getStr2()));

                new Thread() {

                    @Override
                    public void run() {

                        toServer.println(instancia.getStrbuf());

                        toServer.flush();

                    }
                }.start();

            }
        };
        return listener;
    }

    public static void setInstancia(GameClientTCP instancia) {
        GameClientTCP.instancia = instancia;
    }

    public String getStr2() {
        return str2;
    }

    public void setStr2(String str2) {
        GameClientTCP.str2 = str2;
    }

    public StringBuffer getStrbuf() {
        return strbuf;
    }

    public void setStrbuf(StringBuffer strbuf) {
        GameClientTCP.strbuf = strbuf;
    }

    public static void setupClientTCP() throws IOException {
        int read;
        PrintStream toUser = System.out;
        BufferedReader fromServer;
        s = new Socket(InetAddress.getLocalHost().getHostAddress(), 2017);
        char[] buffer = new char[1024];
        fromServer = new BufferedReader(new InputStreamReader(s.getInputStream()));
        toServer = new PrintStream(s.getOutputStream());
        while (true) {
            while ((read = fromServer.read(buffer)) != -1) {
                toUser.print(String.valueOf(buffer, 0, read));
            }
            toUser.flush();
        }
    }

    public static void setupLayout(KeyListener listener) {
        frame = new JFrame("ClientSide");
        Container contentPane = frame.getContentPane();
        textField = new JTextField();
        labelField = new JLabel("Insira um comando válido!");
        textField.addKeyListener(listener);
        contentPane.add(textField, BorderLayout.SOUTH);
        contentPane.add(labelField,BorderLayout.NORTH);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(220,64);
        frame.isVisible();
        frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        setupLayout(setupKeyBoard());
        try {
            setupClientTCP();
        } catch (Exception e) {
            System.out.println("Conexão com servidor falhou.");
            labelField.setText("Conexão com servidor falhou.");
            textField.setEditable(false);
        }
    }
}
