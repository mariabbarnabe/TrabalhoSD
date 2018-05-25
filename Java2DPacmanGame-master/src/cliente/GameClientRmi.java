package cliente;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author laser
 */
import main.GameServe;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;

public class GameClientRmi {

    public static void main(String[] args) {
        try {
            GameServe g = (GameServe) Naming.lookup("rmi://localhost:1099/PacmanService");
            g.top();
        } catch (Exception e) {
            System.out.println("Trouble: " + e);
        }
    }
}
