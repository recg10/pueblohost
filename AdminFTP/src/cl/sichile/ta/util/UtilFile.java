package cl.sichile.ta.util;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class UtilFile{ 
     
    public void copyDirectory(File srcDir, File dstDir) throws IOException { 
        if (srcDir.isDirectory()) { 
            if (!dstDir.exists()) { 
                dstDir.mkdir(); 
            }              
            String[] children = srcDir.list(); 
            for (int i=0; i<children.length; i++) { 
                copyDirectory(new File(srcDir, children[i]), 
                    new File(dstDir, children[i])); 
            } 
        } else { 
            copy(srcDir, dstDir); 
        } 
    } 
     

    public void copy(File src, File dst) throws IOException { 
        InputStream in = new FileInputStream(src); 
        OutputStream out = new FileOutputStream(dst);         
        byte[] buf = new byte[1024]; 
        int len; 
        while ((len = in.read(buf)) > 0) { 
            out.write(buf, 0, len); 
        } 
        in.close(); 
        out.close(); 
    } 
     

    
    public static boolean borrarDirectorio(File file){
        if ( file.isDirectory()) {
              if (!file.delete()){
                    System.out.println("No puede borrar directorio");
                    return false;
              }
        }
        return true;
  }
  
    public static boolean borrarArchivos(File file){
        File[] archivos = file.listFiles(); 
        if ( archivos.length>0 ) {
          for (int i=0; i<archivos.length; i++){
            File fileTemp = archivos[i];
            if (!fileTemp.delete()){
                 System.out.println("No se pudo eliminar archivo-->"+fileTemp.getName());
                 return false;
            }
          }
        }else{
          System.out.println("No existen arxhivos a borrar");
          return false;
        }
        return true;
    }
    
    public static boolean borraArchivo(String archivo){
    	System.out.println("Procesando el directorio -->"+archivo);
		File file = new File(archivo);    		
        if (!file.delete()){
             System.out.println("No se pudo eliminar archivo-->"+file.getName());
             return false;
        }
          return true;
    }
    
    public static boolean checkFile(String rutaArchivo){
    	System.out.println("Procesando el directorio -->"+rutaArchivo);
		File file = new File(rutaArchivo);
		if (file.exists()) return true;
		return false;       
    }
}