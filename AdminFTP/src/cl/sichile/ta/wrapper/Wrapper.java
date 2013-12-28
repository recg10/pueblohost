package cl.sichile.ta.wrapper;

import cl.sichile.ta.sftp.FileSFTP;

import com.jcraft.jsch.ChannelSftp.LsEntry;

public class Wrapper{
	
	public FileSFTP LsEntryTOFileSFTP( LsEntry entry ){
		FileSFTP fileSFTP = new FileSFTP();
		fileSFTP.setName(entry.getFilename());
		fileSFTP.setAtributos(entry.getAttrs().toString());
		fileSFTP.setImage(false);
		return fileSFTP;
	}

}
