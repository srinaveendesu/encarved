import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
public class client extends Frame implements ActionListener {
	DatagramPacket p;
	DatagramSocket s;
	String filename;
	TextArea t1,t2;
	Button b1,b2;
	byte buffer[];
	String buf;
public static void main(String args[])
{
	client c=new client("client");
	c.design();
}
client(String title)
{
	super(title);
	this.setVisible(true);
	this.setSize(400, 450);
}
public void design()
{
    buffer=new byte[10024];
    t1=new TextArea(2,10);
    t2=new TextArea(10,15);
    b1=new Button("Request");
    b2=new Button("Dispose");
    setLayout(new FlowLayout());
    Label l1=new Label("File name(quit for close)");
    add(l1);   add(t1);   add(b1);   add(b2);
    Label l2=new Label("Message");
    add(l2);   add(t2);
    b1.addActionListener(this);
    b2.addActionListener(this);
    try{	 
	s=new DatagramSocket(2002);
	p=new DatagramPacket(buffer,buffer.length);
       }
	catch(IOException e)
	{
	t2.setText("File does not exist");	
	}
	catch(Exception e){
		t2.setText("Exception caught");	
	}
}
public void actionPerformed(ActionEvent ae)
{
 if(ae.getSource()==b1)
 {
   try{
       buffer=t1.getText().getBytes();
       t2.setText(buffer.toString());
	s.send(new DatagramPacket(buffer,buffer.length,
                 InetAddress.getLocalHost(),2001));
	buffer=new byte[1006];
	p=new DatagramPacket(buffer,1000);
	if(t1.getText().equals("quit"))
        {
	 s.close();
	 this.dispose();
	 System.exit(0);
        }
	t2.setText("request sent");
	s.receive(p);
	String data=new String(p.getData(),0,p.getLength());
	t2.setText(data);		
       }
       catch(Exception e)
       {
	t2.setText("Exception caught"+e);
       }
  }
  if(ae.getSource()==b2)
  {
    this.dispose();
  }
}
}

