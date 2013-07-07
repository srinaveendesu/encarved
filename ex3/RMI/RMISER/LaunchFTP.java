import java.rmi.*;
import java.rmi.server.*;
class LaunchFTP
{
	public static void main(String args[])
	{
		try
		{
			FTPImpl m=new FTPImpl();
			System.out.println("Remote Object Constructed");
			//UnicastRemoteObject.exportObject(m);
			Naming.bind("rmi://localhost:1099//FTP",m);
			System.out.println("Remote Object Binded. Ready for Service....");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}