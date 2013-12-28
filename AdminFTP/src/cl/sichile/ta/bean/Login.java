package cl.sichile.ta.bean;

import java.util.List;

import cl.sichile.ta.dto.UsuarioDTO;
import cl.sichile.ta.facade.FacadeGeneral;
import cl.sichile.ta.util.FacesUtils;

public class Login {
	
	private String nombre="";
	private String password="";
	private UsuarioDTO oUsuarioDTO;
	private String s="";
	
	public UsuarioDTO getoUsuarioDTO() {
		return oUsuarioDTO;
	}

	public void setoUsuarioDTO(UsuarioDTO oUsuarioDTO) {
		this.oUsuarioDTO = oUsuarioDTO;
	}

	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String validaLogin(){
		String sPaginaDestino="";
		String inputpass=this.getNombre();
		String dbpass=this.getPassword();
		
		UsuarioDTO logueando = new UsuarioDTO();
		logueando.setId(inputpass);
		logueando.setPassword(dbpass);
		try {
			List lUsuarios = FacadeGeneral.getInstance().listar(logueando);
			if(lUsuarios !=null && lUsuarios.size()>0 )	{
				sPaginaDestino ="exitoLogin";
				this.setoUsuarioDTO((UsuarioDTO)lUsuarios.get(0));
			}
			else{
				FacesUtils.addErrorMessage("Clave invalida!!");		        
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return sPaginaDestino;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}


	

}
