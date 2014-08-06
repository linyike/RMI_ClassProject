package myrmi.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Authentication extends Remote {

    int login(String un, String pw) throws RemoteException;

    void remote2(String un, String pw) throws RemoteException;

    void remote22(int dcn) throws RemoteException;

    String remote221() throws RemoteException;

    void remote3(String un, String npw) throws RemoteException;

    void remote4(String un, String ms) throws RemoteException;

    String remote5(String un) throws RemoteException;

    void remote6(int dcn) throws RemoteException;

    String remote61() throws RemoteException;
}
