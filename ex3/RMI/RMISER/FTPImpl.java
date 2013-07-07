import java.rmi.*;
import java.rmi.server.*;
import java.io.*;
public class FTPImpl extends UnicastRemoteObject implements FTP
{
	public FTPImpl()throws RemoteException
	{
		super();
	}
	public String getFileContents(String fname)throws RemoteException
	{
		try
		{
			File f=new File(fname);
			if(!f.exists())
			{
				return "File not found";	
			}
			FileInputStream fin=new FileInputStream(f);
			byte b[]=new byte[(int)f.length()];
			fin.read(b);
			return new String(b);
		}	
		catch(Exception e)
		{
			System.out.println(e);
			return "File not found";
		}
	}
	public String listDirectory(String dirname)throws RemoteException
	{
		File f=new File(dirname);
		if(!f.exists())
		{
			return "Directory not found";	
		}
		String list[]=f.list();
		String dirlist=new String();
		for(int i=0;i<list.length;++i)
	 		dirlist=dirlist+list[i]+"\n";
		return dirlist;
	}
	public boolean delete(String fname)throws RemoteException
	{
		File f=new File(fname);
		if(!f.exists())
		{
			return false;	
		}
		boolean r=f.delete();
		return r;
	}
}