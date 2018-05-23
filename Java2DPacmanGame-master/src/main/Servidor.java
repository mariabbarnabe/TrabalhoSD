package main;

import br.ol.pacman.PacmanGame;
import br.ol.pacman.infra.Display;
import br.ol.pacman.infra.Game;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.rmi.*;

public class Servidor {
    public Servidor() {
        try {
            GameServe g = new GameImpl();
            Naming.rebind("rmi://localhost:1099/PacmanService", g);
            System.out.println("Conexão realizada com sucesso!");
        } catch (Exception e) {
            System.out.println("Trouble: " + e);
        }
    }
    public static void main(String[] args) {
        ///Lembrar de rodar o rmiregistry no caminho classes
        ///Servidor s = new Servidor();
        
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
    }
    
}
