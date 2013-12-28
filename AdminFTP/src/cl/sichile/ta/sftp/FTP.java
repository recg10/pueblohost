package cl.sichile.ta.sftp;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

public class FTP {
	
	private FTPClient ftp = new FTPClient();
	
	private static final String sFTP = "ftp.riesal.cl";//"209.239.112.106";
	private static final String sUser = "riesalcl";//"rcastro";
	private static final String sPassword = "Os698Vkx9u";//"4gf322";
//	private static final Integer port = 21;
	
	 public static void main (String[] args){
		 FTP ftp = new FTP();		
		 ftp.listDirectorySFTP();
		 
	 }
	
	private  void openConectSFTP (){
		
		try {
			ftp.connect(sFTP);
			boolean login = ftp.login(sUser,sPassword);
			
			
			} catch (IOException ioe) {
				System.out.println(ioe);
			}
		
	}
	
	private void closeConectionSFTP(){
		try {
			ftp.logout();
			ftp.disconnect();
		} catch (Exception e) {
			System.out.println(e);
		}
	 
	}
	
	public FTPFile[] listDirectorySFTP(){	
		this.openConectSFTP();
		FTPFile[] files = null;
		try {		
		files = ftp.listFiles("/public_ftp/incoming/");
	    for (int i=0; i < files.length; i++) {
	        if (files[i].getName().contains(".csv")) {

	            String remoteFile1 = files[i].getName();
	            File downloadFile1 = new File("/public_ftp/Laboratorio03/"+files[i].getName());
	            OutputStream outputStream1 = new BufferedOutputStream(new FileOutputStream(downloadFile1));
	            ftp.retrieveFile(remoteFile1, outputStream1);
	            outputStream1.close();                  

	        }
	    }
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		this.closeConectionSFTP();
		
//		Wrapper wrapper = new Wrapper();
//		Vector<FileSFTP> veFileSFTPs = new Vector<FileSFTP>();
//		while (ite.hasNext()) {
//			ChannelSftp.LsEntry lsEntry = (ChannelSftp.LsEntry) ite.next();
//			FileSFTP fileSFTP = wrapper.LsEntryTOFileSFTP(lsEntry);
//			veFileSFTPs.add(fileSFTP);
//		}
		return files;
	}

}
