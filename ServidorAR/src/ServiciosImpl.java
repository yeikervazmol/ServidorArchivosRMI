/**
* @author Karen Troiano		09-10855
* @author Yeiker Vazquez	09-10882
* @grupo  15
*
* Archivo: AutenticacionImpl.java
*
* Descripcion: Implementacion de la interfaz para el programa 
* principal del servidor de archivos (Servicios.java).
*/

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;


public class ServiciosImpl 
	extends	java.rmi.server.UnicastRemoteObject 
	implements Servicios {
	
	private class ArchivoDueno {
		
		private String archivo;
		private String dueno;
		
		private ArchivoDueno(){
			this.archivo = "";
			this.dueno = "";
		}
		
		public String getArchivo() {
			return archivo;
		}

		public void setArchivo(String archivo) {
			this.archivo = archivo;
		}

		public String getDueno() {
			return dueno;
		}

		public void setDueno(String dueno) {
			this.dueno = dueno;
		}
		
	} 
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static String claveServidor = "09108550910882"; 
	public static String[] logs = new String[20];
	private static int indice;
	public static Autenticacion a;
	public List<ArchivoDueno> listaDuenos = new ArrayList<ArchivoDueno>();
	
	// Implementations must have an explicit
	//constructor in order to declare the
	// RemoteException exception
	public ServiciosImpl(String host, String puerto)
	throws java.rmi.RemoteException {
		super();
		
		try {
			a = (Autenticacion)
					Naming.lookup("rmi://" + host + ":" + puerto + "/Autenticacion");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public Boolean iniciarSesion(String nombre, String clave, int i)
	throws java.rmi.RemoteException {
		try {
			
			String timeStamp = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
			if (!(a.autenticarUsuario(nombre, clave))) { 
				return false;
			}
			if (i == 0) {
				if ( indice >= 20) {
					indice = 0;
				}
			
				logs[indice] = "";
				logs[indice] = "(" + timeStamp + ") " + "Inicio de sesion por: " + nombre;
				indice++;
			}
			

		} catch (Exception e){
			System.out.println("Fallos conectandose al servidor de autenticacion.");
			System.exit(0);
		}
		
		return true;
	}
	
	public Boolean cerrarSesion(String nombre, String clave)
	throws java.rmi.RemoteException {
		try {
			if (!(a.autenticarUsuario(nombre, clave))) { 
				return false;
			}
		} catch (Exception e){
			System.out.println("Fallos conectandose al servidor de autenticacion.");
			System.exit(0);
		}
		
		String timeStamp = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
		
		if ( indice >= 20) {
			indice = 0;
		}
		
		logs[indice] = "";
		logs[indice] = "(" + timeStamp + ") " + "Cierre de sesion por: " + nombre;
		indice++;
	
		return true;
	}
	
	public String listarArchivosEnServidor(String nombre, String clave, String nombreArchivo)
	throws java.rmi.RemoteException {
		try {
			if (!(a.autenticarUsuario(nombre, clave))) { 
				return "false";
			}
		} catch (Exception e){
			System.out.println("Fallos conectandose al servidor de autenticacion.");
			System.exit(0);
		}
		
		String path = "."; 
		String respuesta = "";
		
		String files;
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles(); 
 
		for (int i = 0; i < listOfFiles.length; i++){
 
			if (	listOfFiles[i].isFile()){
				files = listOfFiles[i].getName();
				if	( !(files.equals("Servicios.java")
						|| files.equals("Servicios.class")
						|| files.equals("s_rmifs.java")
						|| files.equals("s_rmifs.class")
						|| files.equals("ServiciosImpl.java")
						|| files.equals("ServiciosImpl.class")
						|| files.equals("Autenticacion.java")
						|| files.equals("Autenticacion.class")
						|| files.equals("ServiciosImpl$1.class")
						|| files.equals("ServiciosImpl$ArchivoDueno.class")
						)
					){
					if (nombreArchivo == null) {
						respuesta += "\t" + files + "\n";
					} else {
						if (files.equals(nombreArchivo)) {
							return "true";
						} 
					}

				}
			}
		}
		
		if (nombreArchivo == null) {
			if ( indice >= 20) {
				indice = 0;
			}
			String timeStamp = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
			logs[indice] = "";
			logs[indice] = "(" + timeStamp + ") " + "Listado de archivos por: " + nombre;
			indice++;
			
			return respuesta;
		} else {
			return "no";
		}
		
	}
	
	public String subirArchivo(String nombre, String clave, String nombreArchivo, byte[] datosArchivo)
	throws java.rmi.RemoteException {
		
		try {
			if (!(a.autenticarUsuario(nombre, clave))) { 
				return "false";
			}
		} catch (Exception e){
			System.out.println("Fallos conectandose al servidor de autenticacion.");
			System.exit(0);
		}
		
		try {
			ArchivoDueno nuevo = new ArchivoDueno();
			nuevo.setArchivo(nombreArchivo);
			nuevo.setDueno(nombre);
			listaDuenos.add(nuevo);
	        File archivo = new File(nombreArchivo);
	        BufferedOutputStream salida = new
	           BufferedOutputStream(new FileOutputStream(archivo.getName()));
	         
	        salida.write(datosArchivo,0,datosArchivo.length);
	        salida.flush();
	         salida.close();
	         
	         
	         
	    } catch(Exception e) {
	         System.err.println("FileServer exception: "+ e.getMessage());
	         e.printStackTrace();
	    }
		
		String timeStamp = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
		
		if ( indice >= 20) {
			indice = 0;
		}
		
		logs[indice] = "";
		logs[indice] = "(" + timeStamp + ") " + "Subida del archivo: " + nombreArchivo + " por: " + nombre;
		indice++;
	
		return nombreArchivo + " ha sido subido con exito.\n";
	}
	
	public byte[] bajarArchivo(String nombre, String clave, String nombreArchivo)
	throws java.rmi.RemoteException {
		
		try {
			if (!(a.autenticarUsuario(nombre, clave))) { 
				return null;
			}
		} catch (Exception e){
			System.out.println("Fallos conectandose al servidor de autenticacion.");
			System.exit(0);
		}
		
		try {
			
			File archivo = new File(nombreArchivo);
			byte buffer[] = new byte[(int)archivo.length()];
		
			BufferedInputStream entrada = new 
					BufferedInputStream(new FileInputStream(nombreArchivo));	         
	    	
				entrada.read(buffer,0,buffer.length);
				entrada.close();
			
	         
	 		String timeStamp = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
	 		
	 		if ( indice >= 20) {
	 			indice = 0;
	 		}
	 		
	 		logs[indice] = "";
	 		logs[indice] = "(" + timeStamp + ") " + "Descarga del archivo: " + nombreArchivo + "por: " + nombre;
	 		indice++;
	 					
			return(buffer);
	         
	      } catch(FileNotFoundException e){
	    	  	return null;
	      } catch (IOException e) {
	    	  	return null;
		} 
		
		
	}
			
	public String borrarArchivo(String nombre, String clave, String nombreArchivo)
	throws java.rmi.RemoteException{
		
		try {
			if (!(a.autenticarUsuario(nombre, clave))) { 
				return "false";
			}
		} catch (Exception e){
			System.out.println("Fallos conectandose al servidor de autenticacion.");
			System.exit(0);
		}
		

		Boolean permiso = false;
		Iterator<ArchivoDueno> iteradorDuenos = listaDuenos.iterator();
		while(iteradorDuenos.hasNext()){
			 ArchivoDueno elemento = (ArchivoDueno)iteradorDuenos.next();
			 if (elemento.getArchivo().equals(nombreArchivo)){
				 if (elemento.getDueno().endsWith(nombre)){
					 permiso = true;
					 listaDuenos.remove(elemento);
					 break;
				 }
			 }
	         
	    }
		
		String timeStamp = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
		
		if ( indice >= 20) {
			indice = 0;
		}
		
		logs[indice] = "";
		logs[indice] = "(" + timeStamp + ") " + "Borrado el archivo: " + nombreArchivo + " por: " + nombre;
		indice++;
		if (permiso){
		   	File archivo = new File("./" + nombreArchivo);
 
			if(archivo.delete()){
				return nombreArchivo + " ha sido eliminado.\n";
			} else {
				return nombreArchivo + "no se pudo eliminar.\n";
			}
		}
	   	
	   	return "Usted no es el dueno del archivo " + nombreArchivo + ","
	   			+ "\n o puede que dicho archivo no exista."
	   			+ "\nPor lo tanto, no es posible eliminarlo en el servidor.\n";
	   	
	}
	
	
	
	public Boolean mostrarInformacion(String nombre, String clave)
			throws java.rmi.RemoteException{
		
		try {
			if (!(a.autenticarUsuario(nombre, clave))) { 
				return false;
			}
		} catch (Exception e){
			System.out.println("Fallos conectandose al servidor de autenticacion.");
			System.exit(0);
		}
		
		
		String timeStamp = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
		
		if ( indice >= 20) {
			indice = 0;
		}
		
		logs[indice] = "";
		logs[indice] = "(" + timeStamp + ") " + nombre + " solicito los comandos disponibles";
		indice++;
		return true;
		
	}
	
	public Boolean mostrarArchivosLocales(String nombre, String clave)
			throws java.rmi.RemoteException{
		
		try {
			if (!(a.autenticarUsuario(nombre, clave))) { 
				return false;
			}
		} catch (Exception e){
			System.out.println("Fallos conectandose al servidor de autenticacion.");
			System.exit(0);
		}
		
		
		String timeStamp = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
		
		if ( indice >= 20) {
			indice = 0;
		}
		
		logs[indice] = "";
		logs[indice] =  "(" + timeStamp + ") " + nombre + " solicito listar sus archivos locales";
		indice++;
		return true;
	}
	
	
	public void inicializarLog()
		throws java.rmi.RemoteException {
			indice = 0;
			for (int i = 0; i < 20 ; i++){
				logs[i] = "";
			}
	}

	
	public String entregarLog(String clave)
		throws java.rmi.RemoteException {
			String Log;
			if (clave.equals(claveServidor)) {
				Log = "";
				int j = 0;

				for (int i = indice; i < 20; i++) {
					if (!logs[i].equals("")) {
						j++;
						if (j < 10){
							Log += "\n\t" + (j) + ".-  " + logs[i] + ".";
						} else {
							Log += "\n\t" + (j) + ".- " + logs[i] + ".";
						}
					}
				}
				
				for (int i = 0; i < indice; i++) {
					if (!logs[i].equals("")) {
						j++;
						if (j < 10){
							Log += "\n\t" + (j) + ".-  " + logs[i] + ".";
						} else {
							Log += "\n\t" + (j) + ".- " + logs[i] + ".";
						}
					}
				}

				return Log;
			} else {
				return "Acceso denegado.\n";
			}
				
	}

}
