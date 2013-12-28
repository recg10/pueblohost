package cl.sichile.ta.log;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.PropertyConfigurator;

public class Log4jInit extends HttpServlet {

	private static final long serialVersionUID = 1L;

public
  void init() {	
	try {
		System.out.println("* Inicializando Log4J para TaskAdminBCH");		
		String prefix =  getServletContext().getRealPath("/");		
		System.out.println("resultado de prefix = " + prefix);
		String file = getInitParameter("log4j-init-file");
		System.out.println("resultado de file = " + file);
		System.out.println("se cargaron los datos de Properties");
		PropertyConfigurator.configure(prefix+file);
		System.out.println("* Log4J inicializado");
	} catch (Exception e) {
		e.printStackTrace();
	}
  }

  public
  void doGet(HttpServletRequest req, HttpServletResponse res) {
  }
}