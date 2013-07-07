import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.rmi.*;
public class FTPClient extends JFrame implements ActionListener
{
	JTextArea ta;
	JTextField tf;
	JButton  get,list,delete,exit;
	JScrollPane sp;
	JPanel p1,p2;
	FTP f;
	FTPClient()
	{
		setSize(800,550);
		p1=new JPanel();
		p1.setBackground(Color.gray);
		p2=new JPanel();
		p2.setBackground(Color.cyan);
		p1.setLayout(new FlowLayout());
		p2.setLayout(new FlowLayout());
		ta=new JTextArea();
		sp=new JScrollPane(ta);
		tf=new JTextField(15);		
		Container c=getContentPane();
		c.add("Center",sp);
		JPanel z=new JPanel();
		c.add("South",z);
		z.setLayout(new GridLayout(2,1));
		z.add(p1);
		z.add(p2);
		p1.add(new JLabel("Enter ur Request ;"));
		p1.add(tf);
		get=new JButton("GET");
		list=new JButton("List");
		delete=new JButton("Delete");
		exit=new JButton("Exit");
		get.addActionListener(this);
		list.addActionListener(this);
		delete.addActionListener(this);
		exit.addActionListener(this);
		p2.add(get);
		p2.add(list);
		p2.add(delete);
		p2.add(exit);		
		setVisible(true);
		try
		{
			System.setProperty("java.security.policy","all.policy");
			System.setSecurityManager(new RMISecurityManager());
			System.setProperty("java.rmi.server.codebase","http://localhost:8080/examples/");
			f=(FTP)Naming.lookup("rmi://localhost:1099//FTP");
			ta.append("Remote Object Found");
		}
		catch(Exception e)
		{
			ta.append("Remote Object not found");
			ta.append("\n"+e.getMessage());
		}
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		try	
		{
			String obj=ae.getActionCommand();
			if(obj.equalsIgnoreCase("get"))
			{
				String fname=tf.getText();
				String content=f.getFileContents(fname);
				ta.append("\n"+content);
			}
			else if(obj.equalsIgnoreCase("list"))
			{
				String dirname=tf.getText();
				String list=f.listDirectory(dirname);
				ta.append("\n Files List : \n");
				ta.append(list);
			}	
			else if(obj.equalsIgnoreCase("delete"))
			{
				boolean v=f.delete(tf.getText());
				if(v)
				{
					ta.append("\n File/Directory deleted");
				}
				else
				{
					ta.append("\n File/Directory not deleted");
				}
			}
			else if(obj.equalsIgnoreCase("exit"))
			{
				System.exit(0);
			}
		}
		catch(Exception e)
		{
			ta.append("\n"+e.getMessage());
		}
	}
	public static void main(String args[])
	{
		FTPClient ftpc=new FTPClient();
	}
	
}