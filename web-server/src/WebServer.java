import java.net.*;
import java.io.*;

public class WebServer {

    public static void main(String[] args) throws IOException {

        //define socket, initialize server socket
        Socket s = null;
        ServerSocket ss = null;
        try {
            ss = new ServerSocket(8080);
            System.out.println("Server online");
        } catch(IOException e) {
            e.printStackTrace();
            System.out.println("Server error");
        }

        //loop to establish client connections
        while(true) {
            try {
                s = ss.accept();
                System.out.println("A new client is connected : " + s);
                PrintWriter out = new PrintWriter(s.getOutputStream(), true);;
                BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
                ServerThread t = new ServerThread(s, in, out);
                new Thread(t).start();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Connection Error");
            }
            finally {
                if (ss != null) {
                    try {
                        System.out.println("closing server");
                        ss.close();
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }





    /*
    private ServerSocket server;
    private PrintWriter out;
    private BufferedReader in;

    public void initServer(int port) throws IOException{
        try {
            server = new ServerSocket(port);
            System.out.println("Server online");
        } catch (IOException ignored) {
            System.err.println("Unable to create server");
            System.exit(-1);
        }
    }

    public Socket acceptClient() throws IOException{
        Socket client = server.accept();
        out = new PrintWriter(client.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        return client;
    }

    public void sendMessage(String msg) {
        out.println(msg);
    }

    public String recMessage() throws IOException{
        return in.readLine();
    }

    public void closeClientConnection(Socket c) throws IOException{
        in.close();
        out.close();
        c.close();
    }

    public static void main(String[] args) throws IOException{

        WebServer myServer = new WebServer();
        myServer.initServer(8080);
        Socket currClient = myServer.acceptClient();
        System.out.println(myServer.recMessage());
        myServer.sendMessage("hello from server!");
        myServer.closeClientConnection(currClient);





        Socket client = server.accept();
        PrintWriter out = new PrintWriter(client.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));

        String greeting = in.readLine();
        System.out.println(greeting);

        out.println("Message from server: Hello, client!");

        client.close();
        server.close();
    } */
}

class ServerThread implements Runnable {
    private Socket s;
    private BufferedReader in;
    private PrintWriter out;

    public ServerThread(Socket _socket, BufferedReader _in, PrintWriter _out)  {
        s = _socket;
        in = _in;
        out = _out;
    }

    @Override
    public void run() {

        while (true) {
            try {
                System.out.println("message from client: " + in.readLine());
                out.println("hello");
            } catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                try {
                    if (out != null) {
                        out.close();
                    }
                    if (in != null) {
                        in.close();
                        s.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
