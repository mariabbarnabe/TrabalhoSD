package main;


import java.awt.event.KeyEvent;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class GameImpl extends UnicastRemoteObject  implements GameServe{
    public int desiredDirection;
    public GameImpl () throws RemoteException{
        super ();
    }

    @Override
    public void top() throws RemoteException {
      //if (Teclado.keyPressed[KeyEvent.VK_UP]){
          System.out.println("Client apertou  up");
      //}
    }

    @Override
    public boolean solicitarConexao(int n) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
