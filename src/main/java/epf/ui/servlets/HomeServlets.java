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
import epf.service.ClientService;
import epf.service.ReservationService;
import epf.service.VehicleService;

@WebServlet("/home")
public class HomeServlets extends HttpServlet {
	@Autowired
	VehicleService vehicleService;
	@Autowired
	ClientService clientService;
	@Autowired
	ReservationService reservationService;
	
	@Override
	public void init() throws ServletException{
		super.init();
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


		try {
			request.setAttribute("listVehicles", this.vehicleService.count());
			request.setAttribute("listUsers", this.clientService.count());
			request.setAttribute("listReservations", this.reservationService.count());
			this.getServletContext().getRequestDispatcher("/WEB-INF/views/home.jsp").forward(request, response);


		} catch (ServiceException e) { // si la méthode lance une exception on l'attrappe//
			e.printStackTrace();// lignes liées à l'erreur//


		}
	}
}
