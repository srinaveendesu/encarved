import java.awt.*;
import java.awt.event.*;
import java.io.*;
public class wrkpopup extends Frame implements ActionListener
{
	Label l1,l2;
	Choice ch;
	TextField tf;
	TextArea ta;
	Button b1,b2;
	Panel p1,p2,p3;
	String str,str1,str2,str3;
	public wrkpopup(String title)
	{
		super(title);
		setLayout(new FlowLayout());
		p1=new Panel();
		l1=new Label("NET SEND");
		ch=new Choice();
		tf=new TextField(30);
		p1.add(l1);
		p1.add(ch);
		p1.add(tf);
		add(p1);
		b1=new Button("SEND");
		b2=new Button("QUIT");
		p2=new Panel();
		p2.add(b1);
		p2.add(b2);
		add(p2);
		l2=new Label("STATUS:");
		ta=new TextArea();
		p3=new Panel();
		p3.add(l2);
		p3.add(ta);
		add(p3);
		b1.addActionListener(this);
		b2.addActionListener(this);
		str=new String();
		str1=new String();
		str2=new String();
		try
		{
			str2="NET VIEW";
			Runtime r=Runtime.getRuntime();
			Process p=r.exec(str2);
			BufferedReader br=new BufferedReader(new InputStreamReader(p.getInputStream()));
			while(br!=null)
			{
				str1=br.readLine();
				System.out.println(str1);
				if(str1.startsWith("\\")==true)
				{
					str3=str1.trim();
					ch.add(str3);
				}
				else
				if(str1==null)
				break;
				else
				continue;
				}
			}
		catch(Exception e)
		{
			System.out.println("Error:"+e);
		}
	}
	public void actionPerformed(ActionEvent ae)
	{
			if(ae.getSource()==b1)
			popupwrk();
			else if(ae.getSource()==b2)
			quit();
	}
	public void quit()
	{
			System.exit(0);
			dispose();
			setVisible(false);
	}
	public void popupwrk()
	{
		try
		{
			str="net send"+" "+ch.getSelectedItem()+" "+tf.getText();
			System.out.println(str);
			Runtime r1=Runtime.getRuntime();
			Process p1=r1.exec(str);
			BufferedReader br1=new BufferedReader(new InputStreamReader(p1.getInputStream()));
			while(br1!=null)
			{
				str1=br1.readLine();
				ta.append(str1);
				ta.append("\n");
			}
		}
		catch(Exception e)
		{
			System.out.println("Error:"+e);
		}
	}
	public static void main(String srgs[])
	{
		wrkpopup w1=new wrkpopup("POP UP MSG..");
		w1.setSize(700,500);
		w1.setVisible(true);
	}
}
										 