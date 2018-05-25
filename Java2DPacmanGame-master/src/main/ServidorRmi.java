/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.rmi.Naming;

/**
 *
 * @author user
 */
public class ServidorRmi {

    public ServidorRmi() {
        try {
            GameServe g = new GameImpl();
            Naming.rebind("rmi://localhost:1099/PacmanService", g);
            System.out.println("Conexão realizada com sucesso!");
        } catch (Exception e) {
            System.out.println("Trouble: " + e);
        }
    }

}
