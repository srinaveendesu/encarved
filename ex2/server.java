import java.io.*;
import java.net.*;
import java.awt.event.*;
import java.awt.*;
public class server extends Frame implements ActionListener
{
DatagramPacket p;
DatagramSocket s;
String filename;
TextArea t1,t2;
Button b1,b2;
boolean flag=false;
byte buffer[];
byte buffe[];
public static void main(String args[])
{
server s=new server("Server");
s.design();
}
server(String arg)
{
	super(arg);
	this.setVisible(true);
	this.setSize(400,400);
}

public void design()
{   
	buffer=new byte[10024];
	t1=new TextArea(2,10);
	t2=new TextArea(10,15);
	b1=new Button("send");
	b2=new Button("Dispose");
    	setLayout(new FlowLayout());
	Label l1=new Label("File name");
	add(l1);
	add(t1);
	add(b1);
	add(b2);
	Label l2=new Label("Content and Message");
	add(l2);
	add(t2);
	b1.addActionListener(this);
	b2.addActionListener(this);
try{
	s=new DatagramSocket(2001);
	p=new DatagramPacket(buffer,buffer.length);
	s.receive(p);
	filename=new String(p.getData());
	t1.setText(filename);
	FileInputStream fin=new FileInputStream(filename);
	}
	catch(IOException e)
	{
	t2.setText("File doesnt exist");
	}
	catch(Exception e)
	{
		t2.setText("Exception caught"+e);
	}
}
public void actionPerformed(ActionEvent ae)
{
 if(ae.getSource()==b1)
 {
  try{			
      byte a;
      buffe=new byte[10024];
      int i=0;
      filename=t1.getText();
      FileInputStream fin=new FileInputStream(filename);
      while((a=(byte)fin.read())!=-1)
      {
	buffe[i++]=a;	
      }
      buffe[i]=a;
      s.send(new DatagramPacket(buffe,i,InetAddress.getLocalHost(),2002));
 	t2.setText("sent");
	s.receive(p);
	filename=new String(p.getData(),0,p.getLength());
	t1.setText(filename);
     }
     catch(Exception e)
         {}		
		
  }
 if(ae.getSource()==b2)
 {
  s.close();
  this.dispose();}
 }
}
