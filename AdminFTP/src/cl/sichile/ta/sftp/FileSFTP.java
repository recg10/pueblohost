package cl.sichile.ta.sftp;

import java.io.Serializable;

public class FileSFTP implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String atributos;
	private boolean image;
	
	public FileSFTP (){
		super();
	}
	
	public FileSFTP (String name, String atributos, boolean image){
		this.name = name;
		this.atributos = atributos;
		this.image = image;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAtributos() {
		return atributos;
	}
	public void setAtributos(String atributos) {
		this.atributos = atributos;
	}
	public boolean isImage() {
		return image;
	}
	public void setImage(boolean image) {
		this.image = image;
	}
	
}
