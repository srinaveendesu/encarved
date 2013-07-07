// TCPServer.java
// A server program implementing TCP socket
import java.net.*; 
import java.io.*; 
import java.lang.*;
import java.io.File.*;
public class FileTransferSer { 
 public static void main (String args[]) 
 {// arguments supply message and hostname of destination  
  Socket s = null; 
  String fname=new String();
  String IPADDRESS=new String();
System.out.println("server start listening... ... ...");
     char h;
      String ip = "localhost";
  try{ 
    int serverPort=6880;
    s = new Socket(ip, serverPort); 
    DataInputStream input = new DataInputStream( s.getInputStream()); 
    DataOutputStream output = new DataOutputStream( s.getOutputStream()); 
    int nb2=input.readInt();
     byte[] dig=new byte[nb2];
     for(int z=0;z<nb2;z++)
       dig[z]=input.readByte();
     fname=new String(dig);
     int nb9=input.readInt();
     byte[] dig9=new byte[nb9];
     for(int z9=0;z9<nb9;z9++)
       dig9[z9]=input.readByte();
     IPADDRESS=new String(dig9);
    int perm=0;
    FileReader in = new FileReader(fname);
    /*File ff2=new File("C:'\'Users'\'dell'\'Desktop'\'firewall'\'"+fname);
    File ff1=new File("C:'\'Users'\'dell'\'Desktop'\'firewall'\'pavan.txt");
    boolean boo=ff1.setReadable(false);
   
   if(boo)
   {
     System.out.println("Success");
   }
   else
   {
     System.out.println("Failure");
   }*/
    if(fname.equals("pavan.txt"))
    {
      perm=1;
    }
           
    
    /*InetAddress iii=s.getInetAddress();
    System.out.println("Inet :"+iii);
    String aaa=new String();
    String hhh=new String();
    byte[] ipadd=new byte[15];
   ipadd=iii.getAddress();
    System.out.println("AAA----"+ipadd);
     aaa=iii.getHostAddress();
     System.out.println("-------");
      System.out.println("IP :"+aaa);
       System.out.println("-----------");
       String vfe=new String(ipadd);
       System.out.println(vfe);*/
     System.out.println("Firewall checking IP address of the client.....");
     System.out.println("IPAddress of client is :"+IPADDRESS);
    String hhh=new String(); 
    hhh=IPADDRESS.substring(0,3);
     int dss=Integer.parseInt(hhh);
      System.out.println("dsss :"+dss);
     if(dss>0 && dss<127)
     {
       System.out.println("Client belongs to Class A Denied.......");
        String sast=new String("Access Denied to the Client---class A--......");
        output.writeInt(sast.length());
    output.writeBytes(sast);
    System.exit(0);
     }
     else if(dss>126 && dss<192)
     {
       System.out.println("Client belongs to Class B Granted.......");
        String sast=new String("Access Granted to the Client---class B--......");
        output.writeInt(sast.length());
    output.writeBytes(sast);
     }
     else
     {
        System.out.println("Access Granted class C.......");
        String sas=new String("Access Granted By The Server----class C......");
        output.writeInt(sas.length());
    output.writeBytes(sas);
        
     }
     System.out.println("Firewall Checking whether the files are authorized or not.......");
    
    if(perm==0)
    {
      System.out.println("Access authorized.............");
            String sast=new String("Access Granted to the file......");
        output.writeInt(sast.length());
    output.writeBytes(sast);
    }
    else if(perm==1)
    {
      System.out.println("Access Denied to the file............");
      String sas=new String("Access Denied to the file......");
        output.writeInt(sas.length());
    output.writeBytes(sas);
       
                        System.exit(0);
     
     
    }
    else
    {
      System.out.println("File not found......");
    }
    
    String dat=new String();
     String data=new String();
     int c=in.read();
     while(c!=-1)
     {
       h=(char)c;
      data=data+h;
      
       c=in.read();
     }
    
     //Step 1 send length
    output.writeInt(fname.length());
    output.writeBytes(fname);
     System.out.println("Length"+ data.length());
     output.writeInt(data.length());
     //Step 2 send length
     System.out.println("Writing.......");
     output.writeBytes(data); // UTF is a string encoding
     
     //Step 1 read length
     int nb = input.readInt();
     byte[] digit = new byte[nb];
     //Step 2 read byte
     for(int i = 0; i < nb; i++)
    digit[i] = input.readByte();
    
     String st = new String(digit);
    System.out.println("Received: "+ st); 
  }
  catch (UnknownHostException e){ 
   System.out.println("Sock:"+e.getMessage());}
  catch (EOFException e){
   System.out.println("EOF:"+e.getMessage()); }
  catch (IOException e){
   System.out.println("IO:"+e.getMessage());} 
  catch(Exception e){
    System.out.println("IO:"+e.getMessage());} 
  finally {
     if(s!=null) 
      try {s.close();
      } 
      catch (IOException e) {/*close failed*/}
 }
  }
}

