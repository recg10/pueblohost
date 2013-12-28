package cl.sichile.ta.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class Property {

	private static Property instance = null;
    private static Properties  propPaths = new Properties();
    private static String PATH_LOCAL = "D:\\ta\\conf\\ta.properties";
    private static String PATH_SERVER = System.getenv("CATALINA_HOME") +  "\\conf\\pco\\pco.properties";
    
    private static Logger log = Logger.getLogger(Property.class);
    
	private Property() {
		System.out.println("PATH_LOCAL : " + PATH_LOCAL);
		System.out.println("PATH_SERVER: " + PATH_SERVER);
	    try {
	    	InputStream io = new FileInputStream(PATH_LOCAL);
			propPaths.load(io);
		} catch (IOException eLocal) {
		    try {
		    	InputStream io = new FileInputStream(PATH_SERVER);
				propPaths.load(io);
			} catch (IOException eServer) {
				eServer.printStackTrace();
				try{	
					//Nuevo directorio tomcat 7
					String pathFijo = "C:\\Program Files\\Apache Software Foundation\\Tomcat 6\\conf\\pco\\pco.properties";
					InputStream io = new FileInputStream(pathFijo);
					propPaths.load(io);
					System.out.println("PATH_FIJO: " + pathFijo);
				}catch (Exception e){
						System.out.println("ERRROR EN DURO: ");
						e.printStackTrace();}
			}
		}
		//System.out.println(propPaths.get("DE"));
	}
	
	public static Property getInstance() {
		if (instance == null) 
			instance = new Property();
		return instance;
	}
	
	private String getKey(String key) {
		return propPaths.getProperty(key);
	}

	public String getSmtpHost(){
		return getKey("smtp.host");
	}
	
	public String getAddressSender(){
		return getKey("address.sender");
	}
	
	public String getPasswordSender(){
		return getKey("password.sender");
	}
	
	public String getTituloMail(){
		return getKey("titulo.mail");
	}
		
	public String getRutaServer(){
		return getKey("ruta.server");
	}
		
	public String getRutaLocal () {
		return getKey("ruta.local");
	}
	
	public String getMailAutentication(){
		return getKey("mail.autentication");
	}
	
	public String getSimulacionEstado(){
		return getKey("simulacion.estado");
	}
	
//	public String getLocalHost(){
//		return getKey("local.host");
//	}
//	
//	public String getServerHost(){
//		return getKey("server.host");
//	}
	
}
