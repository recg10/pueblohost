/**
 * 
 */
package cl.sichile.ta.demon;

import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;

import cl.sichile.ta.util.UtilFile;

/**
 * @author Rodrigo
 *
 */
public class EjecucionDemonio {
	private static Logger log = Logger.getLogger(EjecucionDemonio.class);
	/**
	 * @param args
	 */
	
	
	public static void main(String arg[])
	{	
		TimerTask timerTask = new TimerTask() 
	     { 
			int cont=0;
	         public void run()
	         
	         {
	        	 String ruta="C:\\ta\\file\\";
	        	 String file="PCO.log";
	        	 cont = cont+1;
	        	 log.debug("Ejecucion-->"+cont);
	        	 log.debug("Verificar existencia de archivo en directorio...");
	        	 if (!UtilFile.checkFile(ruta+file)){
	        		 log.error("No existe archivo-->"+ruta+file);
	        		 return;
	        	 }
	        	 log.debug("Verificar TAMAÑO archivo en directorio...");
	        	 
	        	 log.debug("Leer archivo y detectar errores desde la ultima linea procesada");
	        	 
	        	 log.debug("finalizar proceso!!!");
	        	
	        	
	        	
	        	
	        	
	        	
	    
	         }
			
	     };
	      // Aquí se pone en marcha el timer cada segundo. 
	     Timer timer = new Timer(); 
	     // Dentro de 0 milisegundos avísame cada 1000 milisegundos 
	     timer.scheduleAtFixedRate(timerTask, 0, 60000); 
	}//FIN DEL MAIN

}
