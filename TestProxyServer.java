import java.io.*;  
import java.net.*;  

// Source: javatpoint

public class TestProxyServer {  
  public static void main(String[] args) throws IOException {  
    try {  
      String host = "Proxy Server";  
      int remoteport = 1025;  
      int localport = 1026;   
        
      System.out.println("Starting proxy for " + host + ":" + remoteport  
          + " on port " + localport);  
      
      runServer(host, remoteport, localport); 
    }   
    catch (Exception e)   
    {  
      System.err.println(e); 
    }  
  }  
  
  public static void runServer(String host, int remoteport, int localport)  
      throws IOException {  
   
    ServerSocket s = new ServerSocket(localport);  
    final byte[] request = new byte[1024];  
    byte[] reply = new byte[4096];  
    while (true) {  
      Socket client = null, server = null;  
      try {  
        
        client = s.accept();  
        final InputStream streamFromClient = client.getInputStream();  
        final OutputStream streamToClient = client.getOutputStream();  
  
        try {  
          server = new Socket(host, remoteport);  
        } catch (IOException e) {  
          PrintWriter out = new PrintWriter(streamToClient);  
          out.print("Proxy server cannot connect to " + host + ":"  
              + remoteport + ":\n" + e + "\n");  
          out.flush();  
          client.close();  
          continue;  
        }  

        final InputStream streamFromServer = server.getInputStream();  
        final OutputStream streamToServer = server.getOutputStream();  
  
        Thread t = new Thread() {  
          public void run() {  
            int bytesRead;  
            try {  
              while ((bytesRead = streamFromClient.read(request)) != -1) {  
                streamToServer.write(request, 0, bytesRead);  
                streamToServer.flush();  
              }  
            } catch (IOException e) {  
            }  
   
            try {  
              streamToServer.close();  
            } catch (IOException e) {  
            }  
          }  
        };  
  
        t.start();  

        int bytesRead;  
        try {  
          while ((bytesRead = streamFromServer.read(reply)) != -1) {  
            streamToClient.write(reply, 0, bytesRead);  
            streamToClient.flush();  
          }  
        } catch (IOException e) {  
        }  

        streamToClient.close();  
      } catch (IOException e) {  
        System.err.println(e);  
      } finally {  
        try {  
          if (server != null)  
            server.close();  
          if (client != null)  
            client.close();  
        } catch (IOException e) {  
        }  
      }  
      s.close();
    }  
  }  
}  