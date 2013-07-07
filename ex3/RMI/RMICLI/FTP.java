import java.rmi.*;
public interface FTP extends Remote
{
	public String getFileContents(String fname) throws RemoteException;
	public String listDirectory(String dirname)throws RemoteException;
	public boolean delete(String name)throws RemoteException;
}