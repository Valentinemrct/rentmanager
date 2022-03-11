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
import epf.service.ClientService;
import epf.service.ReservationService;
import epf.service.VehicleService;

@WebServlet("/vehicles/edit")
public class VehicleEditServlet extends HttpServlet {

	@Autowired
	private ClientService clientService;
	@Autowired
	private VehicleService vehicleService;
	@Autowired
	private ReservationService reservationService;
	
	private static final long serialVersionID = 1L;

	@Override
	public void init() throws ServletException {
		super.init();
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/views/vehicles/edit.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String vehicleConstructeur = request.getParameter("constructeur");

		int vehicleId = Integer.parseInt(request.getParameter("id"));
		int vehiclePlaces = Integer.parseInt(request.getParameter("nb_places"));
		

//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//		LocalDate clientNaissance = LocalDate.parse(clientNaissanceString, formatter);

		try {
	
	
			Vehicle vehicle = new Vehicle(vehicleId, vehicleConstructeur, vehiclePlaces);
			
			request.setAttribute("modification vehicle", this.vehicleService.edit(vehicle));
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		this.doGet(request, response);
	}

}