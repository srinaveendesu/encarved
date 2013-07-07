// TCPClient.java
// A client program implementing TCP socket
import java.net.*; 
import java.io.*; 
import java.lang.*;

public class FileTransferCli { 
  public static void main (String args[]) 
  { 
 try{ 
   int serverPort = 6880; 
   ServerSocket listenSocket = new ServerSocket(serverPort); 
   
  
  
   while(true) { 
    Socket clientSocket = listenSocket.accept();
    InetAddress ia=InetAddress.getLocalHost();
    String as=ia.getHostAddress();
    
    System.out.println("----------------------Inet Address : "+ia);
    System.out.println("----------------------IP Address : "+as);
    Connection c = new Connection(clientSocket); 
   } 
 } 
 catch(IOException e) {
  System.out.println("Listen :"+e.getMessage());} 
  }
}

class Connection extends Thread { 
 DataInputStream input; 
 DataOutputStream output; 
 Socket clientSocket; 
 String fname=new String();
 public Connection (Socket aClientSocket) { 
  try { 
     clientSocket = aClientSocket; 
     input = new DataInputStream( clientSocket.getInputStream()); 
     output =new DataOutputStream( clientSocket.getOutputStream()); 
     this.start(); 
  } 
   catch(IOException e) {
   System.out.println("Connection:"+e.getMessage());
   } 
   } 

   public void run() { 
  try { // an echo server 
    //  String data = input.readUTF();
   System.out.println("Enter the file required :");
    BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    fname=br.readLine();
    
     //Step 1 send length
    output.writeInt(fname.length());
    output.writeBytes(fname); 
    InetAddress ia=InetAddress.getLocalHost();
    String as=ia.getHostAddress();
    output.writeInt(as.length());
    output.writeBytes(as); 
    
     int nnn=input.readInt();
     byte[] digs=new byte[nnn];
     for(int r=0;r<nnn;r++)
       digs[r]=input.readByte();
     String jjj=new String(digs);
     System.out.println("Message from SERVER -------:::"+jjj);
     int nnn1=input.readInt();
     byte[] digs1=new byte[nnn];
     for(int r1=0;r1<nnn1;r1++)
       digs1[r1]=input.readByte();
     String jjj1=new String(digs1);
     System.out.println("Message from SERVER -------:::"+jjj1);
     
     
     //Step 1 read length
     int nb2=input.readInt();
     byte[] dig=new byte[nb2];
     for(int z=0;z<nb2;z++)
       dig[z]=input.readByte();
     String hsd=new String(dig);
     FileWriter out = new FileWriter(hsd);
     BufferedWriter bufWriter = new BufferedWriter(out);
     int nb = input.readInt();
     System.out.println("Read Length"+ nb);
     byte[] digit = new byte[nb];
     //Step 2 read byte
      System.out.println("Writing.......");
     for(int i = 0; i < nb; i++)
    digit[i] = input.readByte();
     
      String st = new String(digit);
     bufWriter.append(st);
      bufWriter.close();
    System.out.println ("receive from : " + 
    clientSocket.getInetAddress() + ":" +
    clientSocket.getPort() + " message - File Received is :"+st);
     
     //Step 1 send length
     output.writeInt(st.length());
     //Step 2 send length
     output.writeBytes(st); // UTF is a string encoding
    //  output.writeUTF(data); 
   } 
   catch(EOFException e) {
   System.out.println("EOF:"+e.getMessage()); } 
   catch(IOException e) {
   System.out.println("IO:"+e.getMessage());}  
   
   finally { 
     try { 
      clientSocket.close();
     }
     catch (IOException e){/*close failed*/}
   }
  }
}
