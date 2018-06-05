package cliente;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
    private static JFrame frame = null;
    private static JLabel labelField = null;
    private static JTextField textField = null;
    private static JLabel labelField2 = null;
    private static JComboBox comboBox;
    private static String str3;
    private static boolean aux;
    private static JButton submit;

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

                instancia.setStrbuf(new StringBuffer(instancia.getStr3() + instancia.getStr2()));

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

    public boolean getAux() {
        return aux;
    }

    public void setAux(boolean aux) {
        GameClientTCP.aux = aux;
    }

    public String getStr3() {
        return str3;
    }

    public void setStr3(String str3) {
        GameClientTCP.str3 = str3;
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
        //frame = new JFrame("ClientSide");
        Container contentPane = frame.getContentPane();
        contentPane.remove(comboBox);
        contentPane.remove(submit);
        textField = new JTextField();
        labelField.setText("Insira um comando válido!");
        contentPane.add(labelField, BorderLayout.NORTH);
        textField.addKeyListener(listener);
        contentPane.add(textField, BorderLayout.SOUTH);
        contentPane.add(labelField, BorderLayout.NORTH);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(300, 200);
        frame.isVisible();
        frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    }

    public static void setupCharacter() {
        frame = new JFrame("ClientSide");
        Container contentPane = frame.getContentPane();
        instancia.setAux(true);
        System.out.println(instancia.getAux());
        labelField = new JLabel("Escolha um personagem: ");
        contentPane.add(labelField, BorderLayout.NORTH);
        String[] options = {"Pacman", "Ghost","Ghost1"};
        comboBox = new JComboBox(options);
//        comboBox.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                
//            }
//        });
        contentPane.add(comboBox, BorderLayout.CENTER);
        submit = new JButton("Selecionar Personagem.");
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                instancia.setStr3((String) comboBox.getSelectedItem());
                comboBox.setEditable(false);
                submit.setEnabled(false);
                instancia.setAux(false);
            }
        });
        contentPane.add(submit, BorderLayout.SOUTH);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(300, 200);
        frame.isVisible();
        frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        setupCharacter();
        System.out.println(instancia.getAux());
        while (instancia.getAux()) {

            labelField.setText("Escolha um personagem: ");
        }
        setupLayout(setupKeyBoard());
        try {
            setupClientTCP();
        } catch (IOException e) {
            System.out.println("Conexão com servidor falhou.");
            labelField.setText("Conexão com servidor falhou.");
            textField.setEditable(false);
        }
    }
}
