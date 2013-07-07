
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.io.*;
	
public class ping extends Frame implements ActionListener,ItemListener
{
Label l;
Choice ch1;
TextField t1;
List lst1;
Button b1,b2;
TextArea t;
Panel p1,p2,p3;
String i,str,str1;
int q[]=new int[12];

public ping(String title)
{
 super(title);
 setLayout(new FlowLayout());
 p1=new Panel();
 l=new Label("PING");
 lst1= new List(4,true);

 lst1.add("-t");
 lst1.add("-a");
 lst1.add("-n");
 lst1.add("-l");
 lst1.add("-f");
 lst1.add("-i");
 lst1.add("-v");
 lst1.add("-r");
 lst1.add("-s");
 lst1.add("-w");
 
 ch1=new Choice();
 ch1.add("172.16.2.133");
 ch1.add("172.16.5.11");
 ch1.add("172.16.5.12");
 ch1.add("172.16.5.13");
 ch1.add("172.16.5.14");
 
 System.out.println("selcted ip"+ch1.getSelectedItem());
 t1=new TextField("",25);
 p1.add(l);
 p1.add(lst1);
 p1.add(t1);
 p1.add(ch1);
 t1.setEnabled(false);
 lst1.addItemListener(this);
 p2=new Panel();
 b1=new Button("PING");
 p2.add(b1);
 b2=new Button("QUIT");
 p2.add(b2);
 p3=new Panel();
 t=new TextArea();
 p3.add(t);
 b1.addActionListener(this);
 b2.addActionListener(this);
 add(p1);
 add(p3);
 add(p2);
}

public void itemStateChanged(ItemEvent ie)
{
 t1.setText("");
 int a;
 a=lst1.getItemCount();
 for(int j=0;j<a;j++);
 i=lst1.getSelectedItem();
 if(i=="-n")
  t1.setEnabled(true);
 else if(i=="-l")
  t1.setEnabled(true);
 else if(i=="-i")
  t1.setEnabled(true);
 else if(i=="-v")
  t1.setEnabled(true);
 else if(i=="-r")
  t1.setEnabled(true);
 else if(i=="-s")
  t1.setEnabled(true);
 else if(i=="-w")
  t1.setEnabled(true);
 else
  t1.setEnabled(false);
}	

public void actionPerformed(ActionEvent ae)
{
 if(ae.getSource()==b1)
  wrkping();
 else if(ae.getSource()==b2)
 quit();
}

public void quit()
{
 System.exit(0);
 dispose();
 setVisible(false);
}

public void wrkping()
{
 try
 {

  str="ping"+" "+lst1.getSelectedItem()+" "+t1.getText()+" "+ch1.getSelectedItem();
  System.out.println(str);
  Runtime r=Runtime.getRuntime();
  Process p=r.exec(str);
  BufferedReader br=new BufferedReader(new InputStreamReader(p.getInputStream()));
  while(br!=null)
  {
   str1=br.readLine();
   t.append(str1);
   t.append("\n");
  }
 }
 catch(Exception e)
 {
  System.out.println("Error:"+e);
 }
}

public static void main(String srgs[])
{
 ping w1=new ping("Ping");
 w1.setSize(500,500);
 w1.setVisible(true);
}
}
