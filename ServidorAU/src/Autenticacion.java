
public interface Autenticacion extends java.rmi.Remote {
	
	public Boolean autenticarUsuario(String nombre, String clave)
			throws java.rmi.RemoteException;

}
