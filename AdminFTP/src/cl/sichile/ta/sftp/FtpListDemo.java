package cl.sichile.ta.sftp;

import java.io.IOException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
//import org.apache.commons.io.FileUtils;


public class FtpListDemo {
    public static void main(String[] args) {
        FTPClient client = new FTPClient();
 
        try {
            client.connect("ftp.domain.com");
            client.login("admin", "secret");
 
            //
            // Obtain a list of filenames in the current working
            // directory. When no file found an empty array will
            // be returned.
            //
            String[] names = client.listNames();
            for (String name : names) {
                System.out.println("Name = " + name);
            }
 
            FTPFile[] ftpFiles = client.listFiles();
            for (FTPFile ftpFile : ftpFiles) {
                //
                // Check if FTPFile is a regular file
                //
                if (ftpFile.getType() == FTPFile.FILE_TYPE) {
                    System.out.println("FTPFile: " + ftpFile.getName() +"; "); //+ FileUtils.byteCountToDisplaySize(ftpFile.getSize()));
                }
            }
            client.logout();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                client.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}