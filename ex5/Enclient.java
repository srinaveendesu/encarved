import java.io.*;
import java.lang.*;
import java.net.*;
import java.awt.*;
import  java.awt.event.*;
public class Enclient extends Frame implements ActionListener
{
DatagramPacket p;
DatagramSocket s,st;
TextField t1,t2,t3;
Label l1,l2,l3;
Button b1,b2;
byte buffer[],buff[];
String s1=" ";
String s2=" " ;
String s5=" ";
int i,j,k;
char x;
public static void main(String args[])
{
Enclient c=new Enclient("Client");
c.setVisible(true);
c.setSize(700,500);
c.init();
}
Enclient(String arg)
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
l1=new Label("Encrypted Text from Server");
t1=new TextField(30);
l2=new Label("Decrytion key");
t2=new TextField(30);
l3=new Label("Original Plain Text");
t3=new TextField(30);
b1=new Button("Decrypt");
b2=new Button("Quit");
add(l1);  add(t1);
add(l2);  add(t3);
add(l3);  add(t2); 
add(b2);
add(b1);
b1.addActionListener(this);
b2.addActionListener(this);
try{
s=new DatagramSocket(2003);
buffer=new byte[1006];
p=new DatagramPacket(buffer,1000);
s.receive(p);
s1=new String(p.getData(),0,p.getLength());
st=new DatagramSocket(2004);
p=new DatagramPacket(buff,1000);
st.receive(p);
s5=new String(p.getData(),0,p.getLength());
t1.setText(s1);
t3.setText(s5);
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
s1=t1.getText();
for(int i=0;i<s1.length();i++)
{
x=s1.charAt(i);
j=(x-k);
x=(char)j;
s2=s2+x;
}
t2.setText(s2);
}
catch(Exception e)
{
System.out.println("Exception caught..." +e);
}
}
if(ae.getSource()==b2)
{
this.dispose();
}
}
}

