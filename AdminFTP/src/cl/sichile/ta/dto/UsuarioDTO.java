package cl.bancoestado.workflow.dto;

import cl.bancoestado.workflow.dto.interfaces.DTO;

public class UsuarioDTO implements DTO {
										/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//tabla: WMB_USR
										//	Llaves primarias *	
	private String id;				//*USR_COD		char(8)
	private String nombre;			//USR_NOM		char(30)
	private String apePat;			//USR_APE_PAT	char(20)
	private String apeMat;			//USR_APE_MAT	char(20)
	private String email;			//USR_EML		char(50)
	private String fono;			//USR_TEL		char(12)
	private String password;		//USR_PWD		char(8)
	private String jefe;			//USR_COD_JEF	char(8)
	private String nombreJefe;		//USR_NOM_JEF	char(30)
	private String mailJefe;		//USR_EML_JEF	char(50)
	private int estilo;
	
	
	public int getEstilo() {
		return estilo;
	}
	public void setEstilo(int estilo) {
		this.estilo = estilo;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApePat() {
		return apePat;
	}
	public void setApePat(String apePat) {
		this.apePat = apePat;
	}
	public String getApeMat() {
		return apeMat;
	}
	public void setApeMat(String apeMat) {
		this.apeMat = apeMat;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFono() {
		return fono;
	}
	public void setFono(String fono) {
		this.fono = fono;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getJefe() {
		return jefe;
	}
	public void setJefe(String jefe) {
		this.jefe = jefe;
	}
	public String getNombreJefe() {
		return nombreJefe;
	}
	public void setNombreJefe(String nombreJefe) {
		this.nombreJefe = nombreJefe;
	}
	public String getMailJefe() {
		return mailJefe;
	}
	public void setMailJefe(String mailJefe) {
		this.mailJefe = mailJefe;
	}
}
