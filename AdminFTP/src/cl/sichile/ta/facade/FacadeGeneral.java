package cl.bancoestado.wf.modelo.facade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import cl.bancoestado.wf.modelo.dao.ArtefactoDAO;
import cl.bancoestado.wf.modelo.dao.AsignacionProyectoDAO;
import cl.bancoestado.wf.modelo.dao.AsignacionProyectoHisDAO;
import cl.bancoestado.wf.modelo.dao.DocumentoDAO;
import cl.bancoestado.wf.modelo.dao.EtapaDeProcesoDAO;
import cl.bancoestado.wf.modelo.dao.FechaEtapaDAO;
import cl.bancoestado.wf.modelo.dao.FechaEtapaHisDAO;
import cl.bancoestado.wf.modelo.dao.IniciativaVigenteDAO;
import cl.bancoestado.wf.modelo.dao.NivelResponsabilidadDAO;
import cl.bancoestado.wf.modelo.dao.ProcesoDAO;
import cl.bancoestado.wf.modelo.dao.RelacionConfiguracionDocumentoDAO;
import cl.bancoestado.wf.modelo.dao.RelacionParaConfiguracionDAO;
import cl.bancoestado.wf.modelo.dao.RolDAO;
import cl.bancoestado.wf.modelo.dao.ServiciosDao;
import cl.bancoestado.wf.modelo.dao.SubprocesoDAO;
import cl.bancoestado.wf.modelo.dao.TailoringDAO;
import cl.bancoestado.wf.modelo.dao.TailoringHisDAO;
import cl.bancoestado.wf.modelo.dao.TipoIniciativaDAO;
import cl.bancoestado.wf.modelo.dao.UsuarioDAO;
import cl.bancoestado.workflow.dto.ArtefactoDTO;
import cl.bancoestado.workflow.dto.AsignacionProyectoDTO;
import cl.bancoestado.workflow.dto.DocumentoDTO;
import cl.bancoestado.workflow.dto.EtapaDeProcesoDTO;
import cl.bancoestado.workflow.dto.FechaEtapaDTO;
import cl.bancoestado.workflow.dto.HistAsignacionesProyectoDTO;
import cl.bancoestado.workflow.dto.HistFechaEtapaDTO;
import cl.bancoestado.workflow.dto.HistTailoringDTO;
import cl.bancoestado.workflow.dto.IniciativaVigenteDTO;
import cl.bancoestado.workflow.dto.NivelResponsabilidadDTO;
import cl.bancoestado.workflow.dto.ProcesoDTO;
import cl.bancoestado.workflow.dto.RelacionConfiguracionDocumentoDTO;
import cl.bancoestado.workflow.dto.RelacionParaConfiguracionDTO;
import cl.bancoestado.workflow.dto.RolDTO;
import cl.bancoestado.workflow.dto.SubprocesoDTO;
import cl.bancoestado.workflow.dto.TailoringDTO;
import cl.bancoestado.workflow.dto.TipoIniciativaDTO;
import cl.bancoestado.workflow.dto.UsuarioDTO;
import cl.bancoestado.workflow.dto.interfaces.DTO;


public class FacadeGeneral {
	
	private static FacadeGeneral _instance=null;
	private Map<String, ServiciosDao> servicesMap;
	
	private cl.bancoestado.wf.modelo.dao.ServiciosDao usuarioDAO;
	private cl.bancoestado.wf.modelo.dao.ServiciosDao iniciativaDAO;
	private cl.bancoestado.wf.modelo.dao.ServiciosDao asignacionDAO;
	private cl.bancoestado.wf.modelo.dao.ServiciosDao rolDAO;
	private cl.bancoestado.wf.modelo.dao.ServiciosDao tipoIniciativaDAO;
	private cl.bancoestado.wf.modelo.dao.ServiciosDao etapaDeProcesoDAO;
	private cl.bancoestado.wf.modelo.dao.ServiciosDao procesoDAO;
	private cl.bancoestado.wf.modelo.dao.ServiciosDao nivelResponsabilidadDAO;
	private cl.bancoestado.wf.modelo.dao.ServiciosDao artefactoDAO;
	private cl.bancoestado.wf.modelo.dao.ServiciosDao subprocesoDAO;
	private cl.bancoestado.wf.modelo.dao.ServiciosDao fechasDeEtapaDAO;
	private cl.bancoestado.wf.modelo.dao.ServiciosDao relConfiguracionDAO;
	private cl.bancoestado.wf.modelo.dao.ServiciosDao tailoringDAO;
	private cl.bancoestado.wf.modelo.dao.ServiciosDao relConfigDocumentoDAO;
	private cl.bancoestado.wf.modelo.dao.ServiciosDao DocumentoDAO;
	
	private cl.bancoestado.wf.modelo.dao.ServiciosDao fechaEtapaHisDAO;
	private cl.bancoestado.wf.modelo.dao.ServiciosDao asignacioProyectoHisDAO;
	private cl.bancoestado.wf.modelo.dao.ServiciosDao tailoringHisDAO;
	
	private FacadeGeneral () {
		
		try{
			usuarioDAO        		= new UsuarioDAO();
			iniciativaDAO     		= new IniciativaVigenteDAO();
			asignacionDAO     		= new AsignacionProyectoDAO();
			rolDAO            		= new RolDAO();	
			tipoIniciativaDAO 		= new TipoIniciativaDAO();
			etapaDeProcesoDAO		= new EtapaDeProcesoDAO();
			procesoDAO 		  		= new ProcesoDAO();
			nivelResponsabilidadDAO	= new NivelResponsabilidadDAO();
			artefactoDAO			= new ArtefactoDAO();
			subprocesoDAO			= new SubprocesoDAO();
			fechasDeEtapaDAO		= new FechaEtapaDAO();
			relConfiguracionDAO		= new RelacionParaConfiguracionDAO();
			tailoringDAO			= new TailoringDAO();
			relConfigDocumentoDAO   = new RelacionConfiguracionDocumentoDAO();
			DocumentoDAO			= new DocumentoDAO();
			
			fechaEtapaHisDAO		= new FechaEtapaHisDAO();
			asignacioProyectoHisDAO	= new AsignacionProyectoHisDAO();
			tailoringHisDAO			= new TailoringHisDAO();
			
			
			servicesMap = new HashMap<String, ServiciosDao>();
			servicesMap.put(UsuarioDTO.class.getName(), usuarioDAO);
			servicesMap.put(IniciativaVigenteDTO.class.getName(), iniciativaDAO);
			servicesMap.put(AsignacionProyectoDTO.class.getName(), asignacionDAO);
			servicesMap.put(RolDTO.class.getName(), rolDAO);
			servicesMap.put(TipoIniciativaDTO.class.getName(), tipoIniciativaDAO);
			servicesMap.put(EtapaDeProcesoDTO.class.getName(), etapaDeProcesoDAO);
			servicesMap.put(ProcesoDTO.class.getName(), procesoDAO);
			servicesMap.put(NivelResponsabilidadDTO.class.getName(), nivelResponsabilidadDAO);
			servicesMap.put(ArtefactoDTO.class.getName(), artefactoDAO);
			servicesMap.put(SubprocesoDTO.class.getName(), subprocesoDAO);
			servicesMap.put(FechaEtapaDTO.class.getName(), fechasDeEtapaDAO);
			servicesMap.put(RelacionParaConfiguracionDTO.class.getName(), relConfiguracionDAO);
			servicesMap.put(TailoringDTO.class.getName(), tailoringDAO);
			servicesMap.put(RelacionConfiguracionDocumentoDTO.class.getName(), relConfigDocumentoDAO);	
			servicesMap.put(DocumentoDTO.class.getName(), DocumentoDAO);
			
			servicesMap.put(HistFechaEtapaDTO.class.getName(), fechaEtapaHisDAO);
			servicesMap.put(HistAsignacionesProyectoDTO.class.getName(), asignacioProyectoHisDAO);
			servicesMap.put(HistTailoringDTO.class.getName(), tailoringHisDAO);
			
			
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	public static FacadeGeneral getInstance() {
		if (_instance == null) 
			_instance =  new FacadeGeneral();
		return _instance;
	}

	//-----------------------------------------------------------------------
	private boolean validateServiceExist(String serviceName){
		return servicesMap.containsKey(serviceName);
	}

	private ServiciosDao getInstance( String serviceName )throws Exception{

		Object obj = servicesMap.get(serviceName);
		ServiciosDao _service = ( ServiciosDao )obj;
		return _service;
	}
	//-----------------------------------------------------------------------------
	public ArrayList<?> listar(DTO dto) throws Exception {
		String serviceName = dto.getClass().getName();
		if( validateServiceExist(serviceName) ){
			ServiciosDao _service = getInstance(serviceName);
			return _service.listar(dto);		
		} else {
			throw new Exception("ERROR 400 - Service Not Found --> "+serviceName);
		}
	}
	//------------------------------------------------------------------
	public DTO agregar(DTO dto) throws Exception {
 		String serviceName = dto.getClass().getName();
		if( validateServiceExist(serviceName) ){
			ServiciosDao _service = getInstance(serviceName);
			return _service.agregar(dto);	
		}
		else{
			throw new Exception("ERROR 400 - Service Not Found --> "+serviceName);
		}
	}
	//----------------------------------------------------------
	public void agregarOModificar(DTO dto) throws Exception {
		String serviceName = dto.getClass().getName();
		if( validateServiceExist(serviceName) ){
			ServiciosDao _service = getInstance(serviceName);
			_service.agregarOmodificar(dto);
		}
		else{
			throw new Exception("ERROR 400 - Service Not Found --> "+serviceName);
		}
	}
	//--------------------------------------------------------
	public void eliminar(DTO dto) throws Exception {
		String serviceName = dto.getClass().getName();
		if( validateServiceExist(serviceName) ){
			ServiciosDao _service = getInstance(serviceName);
			_service.eliminar(dto);
		}
		else{
			throw new Exception("ERROR 400 - Service Not Found --> "+serviceName);
		}
	}
	//--------------------------------------------------------------------------
	public void modificar(DTO dto) throws Exception {
		String serviceName = dto.getClass().getName();
		if( validateServiceExist(serviceName) ){
			ServiciosDao _service = getInstance(serviceName);
			_service.modificar(dto);
		}
		else{
			throw new Exception("ERROR 400 - Service Not Found --> "+serviceName);
		}
	}
	//--------------------------------------------------------------------------
/*	public void pasarAHistorico(DTO dto, UsuarioDTO usuario) throws Exception {
		String serviceName = dto.getClass().getName();
		if( validateServiceExist(serviceName) ){
			ServiciosDao _service = getInstance(serviceName);
			_service.pasarAHistorico(dto, usuario);
		}
		else{
			throw new Exception("ERROR 400 - Service Not Found --> "+serviceName);
		}
	}*/
	
}
