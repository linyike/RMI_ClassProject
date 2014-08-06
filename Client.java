package myrmi.client;

import java.io.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import myrmi.server.*;

public class Client {

    private Client() {
    }

    public static void main(String[] args) {
        String userName = null;
        String passWord = null;
        //  prompt the user to enter their username
        System.out.print("Enter your username: ");
        //  open up standard input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //  read the username from the command-line; need to use try/catch with the
        //  readLine() method
        try {
            userName = br.readLine();
        } catch (IOException ioe) {
            System.out.println("IO error trying to read your name!");
            System.exit(1);
        }
        //  prompt the user to enter their password
        System.out.print("Enter your password: ");
        //  open up standard input
        try {
            passWord = br.readLine();
        } catch (IOException ioe) {
            System.out.println("IO error trying to read your name!");
            System.exit(1);
        }

        String host = (args.length < 1) ? null : args[0];
        try {
            Registry registry = LocateRegistry.getRegistry(host);
            Authentication stub = (Authentication) registry.lookup("Authentication");
            int respLogin = stub.login(userName, passWord);
            if (respLogin == 0) {
                System.out.println("INVALID CREDENTIALS");
                System.exit(1);
            } else if (respLogin == 1) {
                System.out.println("welcome User");
                while (true) {
                    operation(userName, br, stub);
                }
            } else if (respLogin == 2) {
                System.out.println("welcome Administrator");
                while (true) {
                    operation(userName, br, stub);
                }
            }

        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }

    public static void operation(String un, BufferedReader br, Authentication stub) {
        int type;
        if (un.equals("admin")) {
            type = 2;
        } else {
            type = 1;
        }
        System.out.println("\nOperation Options:");
        if (type == 1) {
            System.out.println(
                    "\t(1) store a message on server\n"
                    + "\t(2) change password\n"
                    + "\t(3) display all the past message of current user"
            );
        } else if (type == 2) {
            System.out.println(
                    "\t(1) create or delete an account  \n"
                    + "\t(2) change password  \n"
                    + "\t(3) store a message on the server \n"
                    + "\t(4) display all the past message on the server \n"
                    + "\t(5) delete any message on the server"
            );
        }
        System.out.println("\t(0) to exit program. ");
        String opNs = "";
        opNs = readFromCMD(br, "Please select option number:");
        int opN = -1;
        opN = Integer.parseInt(opNs);
        if (opN == 0) {
            System.out.println("***EXIT***");
            System.exit(1);
        } else {
            System.out.println("***PROCESSING***");
        }

        try {
            if (type == 1) {//Regular User
                if (opN == 1) {
                    String message = "";
                    message = readFromCMD(br, "Please enter the message:\n");
                    stub.remote4(un, message);
                } else if (opN == 2) {
                    String npw = "";
                    npw = readFromCMD(br, "Please enter new password:");
                    stub.remote3(un, npw);
                } else if (opN == 3) {
                    String msgs = "";
                    msgs = stub.remote5(un);
                    System.out.println(msgs);
                }
            } else if (type == 2) {//Admin
                if (opN == 1) {
                    String cun = "", cpw = "";
                    String cds = "";
                    cds = readFromCMD(br, "1. Create account or 2. Delete account:\n");
                    int cd = -1;
                    cd = Integer.parseInt(cds);
                    if (cd == 1) {
                        cun = readFromCMD(br, "create an account with Username:");
                        cpw = readFromCMD(br, "Password:");
                        stub.remote2(cun, cpw);
                    } else if (cd == 2) {
                        String actns = "";
                        actns = stub.remote221();
                        System.out.println(actns);
                        String dcns = "";
                        dcns = readFromCMD(br, "Please enter the # of account you want to delete:\n");
                        stub.remote22(Integer.parseInt(dcns));
                        actns = stub.remote221();
                        System.out.println(actns);
                    }
                } else if (opN == 2) {
                    String cun = "";
                    String npw = "";
                    cun = readFromCMD(br, "Please enter the usename:");
                    npw = readFromCMD(br, "and the new password:");
                    stub.remote3(cun, npw);
                } else if (opN == 3) {
                    String message = "";
                    message = readFromCMD(br, "Please enter the message:\n");
                    stub.remote4(un, message);
                } else if (opN == 4) {
                    String msgs = "";
                    msgs = stub.remote5(un);
                    System.out.println(msgs);
                } else if (opN == 5) {
                    String actns = "";
                    actns = stub.remote61();
                    System.out.println(actns);
                    String dcns = "";
                    dcns = readFromCMD(br, "Please enter the # of message you want to delete:\n");
                    stub.remote6(Integer.parseInt(dcns));
                    actns = stub.remote61();
                    System.out.println(actns);
                }
            }
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }

    public static String readFromCMD(BufferedReader br, String hint) {
        String result = "";
        System.out.print(hint);
        try {
            result = br.readLine();
        } catch (IOException ioe) {
            System.out.println("IO error trying to read your name!");
            System.exit(1);
        }
        return result;
    }
}
