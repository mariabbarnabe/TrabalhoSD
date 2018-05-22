package main;


import java.awt.event.KeyEvent;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class GameImpl extends UnicastRemoteObject  implements GameServe{
    public int desiredDirection;
    public GameImpl () throws RemoteException{
        super ();
    }

    
    public void top() throws RemoteException {
      //if (Teclado.keyPressed[KeyEvent.VK_UP]){
          System.out.println("Client apertou  up");
      //}
    }
    
    
}
