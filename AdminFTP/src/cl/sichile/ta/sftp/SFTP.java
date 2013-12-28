package cl.sichile.ta.sftp;

import java.util.Iterator;
import java.util.Vector;

import cl.sichile.ta.util.Property;
import cl.sichile.ta.wrapper.Wrapper;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.ChannelSftp.LsEntry;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

public class SFTP {
	private static final String user = "visitas";
    private static final String host = "200.14.165.184";
	private static final String pass = "visita1";
	private static final Integer port = 22;
	
//	private static final String rutaServer = "/home/WebSphere/AppServer/logs/server1/logs_app_banco/";
//	private static final String rutaLocal = "D:/monitoreo/";
	private ChannelSftp sftp = null;
	private Session session = null;
	
	private ChannelSftp openConectSFTP(){	 
		    
		 System.out.println("------------------- INICIO----------------");		        
		 try {
			 	
			 System.out.println("------------------- PASEEEEEEEEE----------");		     
			 JSch jsch = new JSch();
		        session = jsch.getSession(user, host, port);
		        MyUserInfo ui = new MyUserInfo();
		        ui.setPassword(pass);
		        session.setUserInfo(ui);
		        session.setPassword(pass);
		        session.connect();
		        sftp = (ChannelSftp)session.openChannel("sftp");
		        sftp.connect();
//		        sftp.cd("/home/WebSphere/AppServer/logs/server1/logs_app_banco");
//		        System.out.println("conectado-->"+sftp.pwd());
//		        System.out.println("Copiar archivo");
//		        sftp.get(rutaServer+archivo, rutaLocal+archivo);
//		        System.out.println("FIN COPIA archivo");		        
//		        
//		        System.out.println("FIN listo archivos...");
//		        System.out.println("Cierro conexxiones");
//		 
		        System.out.println("----------------- FIN");
		} catch ( JSchException jSE){
			System.out.println(jSE.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
			sftp.exit();
	        sftp.disconnect();
	        session.disconnect();
		}		       
		return sftp;
	}
	
	public Vector listDirectorySFTP(){	
		this.openConectSFTP();
		Vector<LsEntry> v = null;
		try {
			v = sftp.ls(Property.getInstance().getRutaServer());			
		} catch (SftpException e) {
			System.out.println(e);
		}        
		this.closeConectionSFTP();
		Iterator<LsEntry> ite = v.iterator();
		Wrapper wrapper = new Wrapper();
		Vector<FileSFTP> veFileSFTPs = new Vector<FileSFTP>();
		while (ite.hasNext()) {
			ChannelSftp.LsEntry lsEntry = (ChannelSftp.LsEntry) ite.next();
			FileSFTP fileSFTP = wrapper.LsEntryTOFileSFTP(lsEntry);
			veFileSFTPs.add(fileSFTP);
		}
		return veFileSFTPs;
	}
	
	public boolean updateFle(String file){
		try {
			ChannelSftp sftp = this.openConectSFTP();
			sftp.get(Property.getInstance().getRutaServer()+file, Property.getInstance().getRutaLocal()+file);
			this.closeConectionSFTP();
		} catch (Exception e) {
			return false;
		}		
		return true;
	}
	
	private boolean closeConectionSFTP (){
		try {
			sftp.exit();
	        sftp.disconnect();
	        session.disconnect();	
		} catch (Exception e) {
			return false;
		}
		return true;
	}

}
