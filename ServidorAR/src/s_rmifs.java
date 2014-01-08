import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
public class s_rmifs {
	public s_rmifs() {
		try {
			Servicios c = new ServiciosImpl();
			
			LocateRegistry.createRegistry(2000);
			
			Naming.rebind("rmi://localhost:2000/Servicios", c);
		}
		catch (Exception e) {
			System.out.println ("Trouble: " + e);
		}
	}
	
	public static void main(String args[]) {
		new s_rmifs();
	}
}