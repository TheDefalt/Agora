import java.net.Socket;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.io.IOException;
import java.lang.NumberFormatException;
 
/*
Hello everyone!
My name is Defalt and I've been studying Java in my spare time.
This is my first actual tool built using Java, enjoy!
*/
 
public class portscan {
    public static boolean scanPort(String host, int port) {
        try {
            Socket scanSock = new Socket();
            scanSock.connect(new InetSocketAddress(host, port), 1000);
            scanSock.close();
            return true;
        } catch (UnknownHostException e) {
            System.out.println("\n[!] Failed to Reach Specified Target");
            System.exit(1);
        } catch (IOException e) {
            return false;
        }
        return false;
    }
 
    public static boolean checkNum(String num) {
        try {
            int checkedNum = Integer.parseInt(num);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
 
    public static void main(String[] args) {
            if (args.length < 3) {
                System.out.println("Usage: ./portscan.jar [TARGET] [START PORT] [STOP PORT]");
                System.exit(1);
            } else if (!checkNum(args[1]) || !checkNum(args[2])) {
                System.out.println("[!] Invalid Port Specification");
                System.exit(1);
            }
            System.out.println("[*] Beginning Scan...\n");
            int stopPort = Integer.parseInt(args[2]);
            String target = args[0];
            for (int i = Integer.parseInt(args[1]); i <= stopPort; i++) {
                if (scanPort(target, i)) {
                    System.out.println(String.format("Port %d: Open", i));
                }
            }
            System.out.println("\n[*] Scan Complete!");
            System.exit(0);
    }
}
