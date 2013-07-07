import java.io.*;
import java.net.*;
import java.applet.*;
import java.awt.*;
import java.awt.event.*;


public class TcpClient extends Frame implements ActionListener
{
	Button b_dwnload,b_connect,b_disconnect,b_quit;
	TextField tf;
	TextArea ta;
	Label fname;
	Socket socket;
	
	DataInputStream din;
	DataOutputStream dout;
	FileInputStream fin;
	FileOutputStream fout;
	
	public TcpClient()
	{
		
		
		setLayout(new BorderLayout());
		fname = new Label("Enter FileName :");
		tf = new TextField(8);
		ta = new TextArea(15,50);
		
		Panel p1 = new Panel();
		Panel p2 = new Panel();
		Panel p3 = new Panel();

		b_dwnload = new Button("DOWNLOAD");
		b_connect = new Button("CONNECT");
		b_disconnect = new Button("DISCONNECT");
		b_quit = new Button("QUIT");

		b_dwnload.addActionListener(this);
		b_connect.addActionListener(this);
		b_disconnect.addActionListener(this);
		b_quit.addActionListener(this);

		p1.add(fname);
		p1.add(tf);
		p1.add(b_dwnload);
	
		p2.add(ta);

		p3.add(b_connect);
		p3.add(b_disconnect);
		p3.add(b_quit);
		
		add(p1,BorderLayout.NORTH);
		add(p2,BorderLayout.CENTER);
		add(p3,BorderLayout.SOUTH);
		
	}

	public void start()
	{}

	public void actionPerformed(ActionEvent ae)
	{
		String str = ae.getActionCommand();

		if(str.equals("DOWNLOAD"))
		{
			download();
		}

		else if(str.equals("CONNECT"))
		{
		  try
		  {
			socket = new Socket("127.0.0.1",2222);
		   }
		   catch(Exception e)
		   {}
		}

		else if(str.equals("DISCONNECT"))
		{
		  try
		  {
			socket.close();
			System.out.println("DisConnected");
		   }
		   catch(Exception e)
		   {}
		}
		
		else
		{
		  try
		  {
			System.exit(0);
		   }
		   catch(Exception e)
		   {}
		}
	}

	public void download()
	{
		byte b;
		StringBuffer sb = new StringBuffer();

		try
		{
			System.out.println("\nDownload Started...");
			InputStream in = socket.getInputStream();
			PrintStream out = new PrintStream(new DataOutputStream(socket.getOutputStream()));
			
			String filename = tf.getText();

			out.println(filename);
			 
			while((b=(byte)in.read())!=-1)
			{
				if(b==-1)
					break;
				sb.append((char)b);
			}
			
			String fc = new String(sb);
			ta.setText(fc);
			System.out.println("\nDownload Completed...");
		}
		catch(Exception e)
		{}
	}

	public static void main(String args[])
	{
		Frame f = new TcpClient();
		f.setSize(300,400);
		f.setVisible(true);
	}
}	