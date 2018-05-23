package main;

public interface GameServe extends java.rmi.Remote {
    
     public void top() throws java.rmi.RemoteException;	
     public boolean solicitarConexao(int n) throws java.rmi.RemoteException;
     
}
