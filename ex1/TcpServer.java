import java.io.*;
import java.net.*;

class TcpServer1 extends Thread
{
	ServerSocket ss;
	Socket soc;

	TcpServer1(ServerSocket ss)
	{
		this.ss=ss;
		System.out.println("Thread entered");
		start();
	}
	
	public void run()
	{
		while(true)
		{
			try
			{
				soc=ss.accept();
				System.out.println("Client Connected Connected");
				System.out.println("Thread started");
				
				byte i;

				InputStream fin;
				InputStreamReader input = new InputStreamReader(soc.getInputStream());
				if(input==null)
					System.out.println("not created");
				
				BufferedReader in = new BufferedReader(input);
				if(in==null)
					System.out.println("not created");
				OutputStream fout = soc.getOutputStream();
				
				byte b[] = new byte[200];
				String str=in.readLine();
				System.out.println("\nFile To Be Sent: "+str);
				if(in.equals("q"))
					System.exit(0);
				
				while(str!=null)
				{
					fin = new FileInputStream(str);
					System.out.println("\nContent Available (In Bytes): "+fin.available());
					try
					{
						do
						{
							i = (byte)fin.read();
							fout.write(i);
							if(i!=-1)
								System.out.print((char)i);
						}while(i!=-1);
					}
					catch(FileNotFoundException fe)
					{
						System.out.println("Errr"+fe.getMessage());
					}
					str=in.readLine();
				}
			}
			catch(Exception e)
			{
				System.out.println("Errr:"+e.getMessage());
				System.exit(0);
			}
		}
	}
 }

class TcpServer
{
	public static void main(String args[]) throws IOException
	{
		ServerSocket ss = new ServerSocket(2222);
		ss.setSoTimeout(500000);
		new TcpServer1(ss);
	}
}		