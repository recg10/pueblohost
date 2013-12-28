package cl.sichile.ta.bean;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import javax.faces.bean.CustomScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.log4j.Logger;

import cl.sichile.ta.constant.Constantes;
import cl.sichile.ta.sftp.FTP;
import cl.sichile.ta.sftp.FileSFTP;
import cl.sichile.ta.sftp.SFTP;
import cl.sichile.ta.util.Property;
import cl.sichile.ta.util.UtilFile;


@ManagedBean(name= ProcessFileBean.BEAN_NAME)
@CustomScoped(value = "#{window}")
public class ProcessFileBean  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String BEAN_NAME = "ProcessFileBean";
	private Logger log = Logger.getLogger(ProcessFileBean.class);
	private int progress;
	private Vector<FileSFTP> vList = null;
	private List vList2 = null;
	
	
	public ProcessFileBean() {
        super();
    }
	
	public int getProgress() {
		return progress;
	}

	public void setProgress(int progress) {
		this.progress = progress;
	}
	
	public void queryDocs(ActionEvent actionEvent) {
		if (Constantes.SIMULACION.equals(Property.getInstance().getSimulacionEstado())){
			vList = new Vector<FileSFTP>();
			vList.add(new FileSFTP("captaciones.log","12kb captaciones.log -cp-r",false));
			vList.add(new FileSFTP("captaciones.log.2012-05-28","12kb captaciones.log.2012-05-28 -cp-r",false));
			vList.add(new FileSFTP("captaciones.log.2012-05-27","12kb captaciones.log.2012-05-27 -cp-r",false));
			vList.add(new FileSFTP("captaciones.log.2012-05-29","12kb captaciones.log.2012-05-29 -cp-r",false));
		}else{
//			SFTP sFTPFile = new SFTP();
			 FTP ftp = new FTP();		
			 
			try {		
//				vList = sFTPFile.listDirectorySFTP();
				vList2 = Arrays.asList(ftp.listDirectorySFTP());
			} catch (Exception e) {
				log.error(e);
			}
		}
	}
	
	
	public List getvList2() {
		return vList2;
	}

	public void setvList2(List vList2) {
		this.vList2 = vList2;
	}

	public void monitoriar(ActionEvent actionEvent) {
		log.info("monitoriar-->Simulacion"+Property.getInstance().getSimulacionEstado());
		try {
			String[] index = (String[]) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterValuesMap().get("index");
	    	int indice = Integer.parseInt(index[0]);
	    	FileSFTP fileSFTP = (FileSFTP)getvList().get(indice);
	    	if (!Constantes.SIMULACION.equals(Property.getInstance().getSimulacionEstado())){
	    		this.readFile(fileSFTP.getName());
	    	}else{
	    		this.readFile("\\PCO.log");
	    	}
	    	fileSFTP.setImage(true);	    	
//		    	monitoreado = "Monitoreando este elemento";
		} catch (Exception e) {
			log.error(e);
		}    	
    return;
    }
	
	public void finalizaMonitoreo(ActionEvent actionEvent) {
		log.debug("finalizaMonitoreo-->Simulacion"+Property.getInstance().getSimulacionEstado());
		try {
			String[] index = (String[]) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterValuesMap().get("index");
	    	int indice = Integer.parseInt(index[0]);
	    	FileSFTP fileSFTP = (FileSFTP)getvList().get(indice);
	    	if (!Constantes.SIMULACION.equals(Property.getInstance().getSimulacionEstado())){
	    		this.readFile(fileSFTP.getName());
	    	}else{
	    		UtilFile.borraArchivo(Property.getInstance().getRutaLocal()+"\\PCO.log");
	    	}
	    	fileSFTP.setImage(false);	    	
//		    	monitoreado = "Monitoreando este elemento";
		} catch (Exception e) {
			log.error(e);
		}    	
    return;
    }
    
    public void openFile(ActionEvent actionEvent) {
    	
    	String[] index = (String[]) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterValuesMap().get("file");
    	String file = index[0];
    	if (!updateFile(file)){
    		log.error("No fue posible ACTUALIZAR archivo");
    		return;
    	}
//    	String file = "GenesisServices.log";
		String cmd="uedit32.exe "+Property.getInstance().getRutaLocal()+file;
		Runtime rt = Runtime.getRuntime();
        try {
			Process proc = rt.exec(cmd);
		} catch (IOException e) {
			log.error(e);
		}
        return;
    }
    
    private boolean updateFile(String file){
    	boolean bExito=false;
    	SFTP sFTPFile = new SFTP();
		//String file = "GenesisServices.log";
		try {			
			sFTPFile.updateFle(file);	
			bExito=true;
		} catch (Exception e) {
			log.error(e);
		}		
        return bExito;
    }
    
    private int readFile(String file){ 
    	if (!updateFile(file)){
    		log.error("No fue posible ACTUALIZAR archivo");
    		return 0;
    	}
		File f = new File(Property.getInstance().getRutaLocal()+file);
		InputStream aux = null;
		try {
			aux = new FileInputStream(f);
		} catch (FileNotFoundException e) {
			log.error(e);
		}					
		BufferedReader lector =	new BufferedReader(new InputStreamReader(aux));		
		StringBuffer salida=new StringBuffer();		
		String line;
		int errores=0;
		try {
			while ((line = lector.readLine()) != null)
			{
				salida.append(line+"\n");
				if (line.indexOf("ERROR")!=-1){
					errores = errores +1;
				}
			}
		} catch (IOException e) {
			log.error(e);
		}
		
		return errores;
    }
	

	public Vector<FileSFTP> getvList() {
		return vList;
	}

	public void setvList(Vector<FileSFTP> vList) {
		this.vList = vList;
	}
	
}
