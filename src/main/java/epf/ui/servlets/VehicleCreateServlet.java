package epf.ui.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import epf.exception.ServiceException;
import epf.model.Vehicle;
import epf.service.VehicleService;

@WebServlet("/createVehicle")
public class VehicleCreateServlet extends HttpServlet {

	@Autowired
	private VehicleService vehicleService;
	private static final long serialVersionID = 1L;
	
	private static int nbVehicle = 0;
	
	Vehicle vehicle = new Vehicle();

	@Override
	public void init() throws ServletException {
		super.init();
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/views/vehicles/create.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String constructeur = request.getParameter("constructeur");
		String nb_places = request.getParameter("nb_places");

		int nb_places1 = Integer.parseInt(nb_places);
		
		try {
			nbVehicle = vehicleService.count();
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		vehicle.setId(nbVehicle +1 );
		
		vehicle.setConstructeur(constructeur);
		vehicle.setNb_places(nb_places1);

		try {

			request.setAttribute("CreateVehicle", this.vehicleService.create(vehicle));

		} catch (ServiceException e) {
			e.printStackTrace();

		}
		doGet(request, response);
	}

}
