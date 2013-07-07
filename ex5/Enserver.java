import java.io.*;
import java.lang.*;
import java.net.*;
import java.awt.*;
import  java.awt.event.*;
public class Enserver extends Frame implements ActionListener
{
DatagramSocket s,q;
DatagramPacket p;
TextField t1,t2,t3;
Label l1,l2,l3;
Button b1,b2,b3;
byte buffer[],buff[];
String s1=" ";
String s2=" " ,k1;
int i,j,k,z=0;
char x,y;
int m=0;
public static void main(String args[])
{
Enserver s=new Enserver("Server");
s.setVisible(true);
s.setSize(400,400);
s.init();
}
Enserver(String arg)
{
super(arg);
this .setVisible(true);
this.setSize(400,400);
}
public void init()
{
buffer=new byte[10024];
buff=new byte[10024];
setLayout(new FlowLayout());
l1=new Label("Enter Text");
t1=new TextField(30);
b1=new Button("Encrypt");
l2=new Label("Encrypted Text");
t2=new TextField(30);
l3=new Label("Encryption key");
t3=new TextField(30);
b2=new Button("Send");
b3=new Button("Quit");
add(l1);  add(t1);
add(l3); add(t3);
add(l2); add(t2);
add(b1);
add(b2);
add(b3);
b1.addActionListener(this);
b2.addActionListener(this);
b3.addActionListener(this);
try{
s=new DatagramSocket(2001);
q=new DatagramSocket(2002);
}
catch(Exception e)
{
System.out.println("Exception Caught..." +e);
}
}
public void actionPerformed(ActionEvent ae)
{
if(ae.getSource()==b1)
{
try
{
k=Integer.parseInt(t3.getText());
k1=t3.getText();
s1=t1.getText();
for(int i=0;i<s1.length();i++)
{
x=s1.charAt(i);
j=(x+k);
x=(char)j;
buffer[z++]=(byte)x;
s2=s2+x;
}
t2.setText(s2);
for(i=0;i<k1.length();i++)
{
y=k1.charAt(i);
buff[0+1]=(byte)y;
}
}
catch(Exception e)
{
System.out.println("Exception caught..." +e);
}
}
if(ae.getSource()==b2)
{
try{
byte bt1[]=s2.getBytes();
byte bt2[]=k1.getBytes();
s.send(new DatagramPacket(bt1,bt1.length,InetAddress.getLocalHost(),2003));
q.send(new DatagramPacket(bt2,bt2.length,InetAddress.getLocalHost(),2004));
}
catch(Exception e)
{
System.out.println("Exception Caught..");
}
}
if(ae.getSource()==b3)
{
this.dispose();
}
}
}

