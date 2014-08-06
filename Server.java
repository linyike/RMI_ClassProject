package myrmi.server;

import java.io.*;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Server implements Authentication {

    public Server() {
    }

    public int login(String un, String pw) {
        int result;
        result = identify(un, pw);
        return result;
    }

    public void remote2(String un, String pw) {//Create Account
        writeFile(un + " " + pw + "\n", "\\serverData\\account.dat", true);
    }

    public void remote3(String un, String npw) {//Change Password
        String accounts = "";
        BufferedReader br = null;
        try {
            String sCurrentLine;
            String filePath = new File("").getAbsolutePath();
            filePath += "\\serverData\\account.dat";
            System.out.println(filePath);
            br = new BufferedReader(new FileReader(filePath));
            while ((sCurrentLine = br.readLine()) != null) {
                String[] up = sCurrentLine.split(" ");
                if (up[0].equals(un)) {
                    accounts += un + " " + npw + "\n";
                } else {
                    accounts += sCurrentLine + "\n";
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        writeFile(accounts, "\\serverData\\account.dat", false);
    }

    public void remote4(String un, String ms) {//Store Message
        writeFile(un + "##" + ms + "\n", "\\serverData\\message.dat", true);
    }

    public String remote5(String un) {//Display Messages
        int mn = 1;
        String messages = "";
        BufferedReader br = null;
        try {
            String sCurrentLine;
            String filePath = new File("").getAbsolutePath();
            filePath += "\\serverData\\message.dat";
            System.out.println(filePath);
            br = new BufferedReader(new FileReader(filePath));
            while ((sCurrentLine = br.readLine()) != null) {
                System.out.println(sCurrentLine);
                String[] up = sCurrentLine.split("##");
                System.out.println(up[1]);
                if (un.equals("admin")) {
                    if (up.length > 0) {
                        messages += mn + ". " + up[0] + ": " + up[1] + "\n";
                        mn++;
                    }
                } else if (up[0].equals(un)) {
                    if (up.length > 0) {
                        messages += mn + ". " + up[1] + "\n";
                        mn++;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return messages;
    }

    public void remote22(int dcn) {
        int num = 1;
        String accounts = "";
        BufferedReader br = null;
        try {
            String sCurrentLine;
            String filePath = new File("").getAbsolutePath();
            filePath += "\\serverData\\account.dat";
            System.out.println(filePath);
            br = new BufferedReader(new FileReader(filePath));
            while ((sCurrentLine = br.readLine()) != null) {
                if (num != dcn) {
                    accounts += sCurrentLine + "\n";
                }
                num++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        writeFile(accounts, "\\serverData\\account.dat", false);
    }

    public String remote221() {
        String accounts = "Account List:\n";
        int num=1;
        BufferedReader br = null;
        try {
            String sCurrentLine;
            String filePath = new File("").getAbsolutePath();
            filePath += "\\serverData\\account.dat";
            System.out.println(filePath);
            br = new BufferedReader(new FileReader(filePath));
            while ((sCurrentLine = br.readLine()) != null) {
                accounts += num+". "+sCurrentLine + "\n";
                num++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return accounts;
    }
    public void remote6(int dcn) {//Delete message
        int num = 1;
        String accounts = "";
        BufferedReader br = null;
        try {
            String sCurrentLine;
            String filePath = new File("").getAbsolutePath();
            filePath += "\\serverData\\message.dat";
            System.out.println(filePath);
            br = new BufferedReader(new FileReader(filePath));
            while ((sCurrentLine = br.readLine()) != null) {
                if (num != dcn) {
                    accounts += sCurrentLine + "\n";
                }
                num++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        writeFile(accounts, "\\serverData\\message.dat", false);
    }

    public String remote61() {//List Message
        String accounts = "Message List:\n";
        int num=1;
        BufferedReader br = null;
        try {
            String sCurrentLine;
            String filePath = new File("").getAbsolutePath();
            filePath += "\\serverData\\message.dat";
            System.out.println(filePath);
            br = new BufferedReader(new FileReader(filePath));
            while ((sCurrentLine = br.readLine()) != null) {
                sCurrentLine=sCurrentLine.replace("#", " ");
                accounts += num+". "+sCurrentLine + "\n";
                num++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return accounts;
    }
    public void writeFile(String word, String file, boolean append) {
        BufferedWriter bw = null;
        String filePath = new File("").getAbsolutePath();
        filePath += file;
        try {
            FileWriter fileWritter = new FileWriter(filePath, append);
            bw = new BufferedWriter(fileWritter);
            bw.write(word);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public int identify(String un, String pw) {
        BufferedReader br = null;

        try {
            String sCurrentLine;
            String filePath = new File("").getAbsolutePath();
            filePath += "\\serverData\\account.dat";
            System.out.println(filePath);
            br = new BufferedReader(new FileReader(filePath));
            while ((sCurrentLine = br.readLine()) != null) {
                String[] up = sCurrentLine.split(" ");
                if (up[0].equals(un)) {
                    if (up.length > 0) {
                        if (up[1].equals(pw)) {
                            if (up[0].equals("admin")) {
                                return 2;
                            } else {
                                return 1;
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return 0;
    }

    public static void main(String args[]) {

        try {
            Server obj = new Server();
            Authentication stub = (Authentication) UnicastRemoteObject.exportObject(obj, 0);

            // Bind the remote object's stub in the registry
            Registry registry = LocateRegistry.getRegistry();
            registry.bind("Authentication", stub);

            System.err.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
