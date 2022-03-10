package epf.ui.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import epf.exception.ServiceException;
import epf.model.Reservation;
import epf.service.ClientService;
import epf.service.ReservationService;
import epf.service.VehicleService;

@WebServlet("/rents/create")
public class ReservationCreateServlet extends HttpServlet {

	private static String Vue_Formulaire = "/WEB-INF/views/rents/create.jsp";

	@Autowired
	ReservationService reservationService;
	@Autowired
	ClientService clientService;
	@Autowired
	VehicleService vehicleService;
	private static final long serialVersionID = 1L;

	@Override
	public void init() throws ServletException {
		super.init();
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setAttribute("listUsers", this.clientService.findAll());
			request.setAttribute("listVehicles", this.vehicleService.findAll());
			request.getRequestDispatcher("/WEB-INF/views/rents/create.jsp").forward(request, response);

		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

//		try {
//			request.setAttribute("listClients", this.clientService.findAll());
//			request.setAttribute("listVehicles", this.vehicleService.findAll());
//		} catch (ServiceException e) {
//			e.printStackTrace();
//		}
//		this.getServletContext().getRequestDispatcher(Vue_Formulaire).forward(request, response);
//	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Coucou");
		DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		String client_idString = request.getParameter("client_id");
		String vehicle_idString = request.getParameter("vehicle_id");
		String debutString = request.getParameter("debut");
		String finString = request.getParameter("fin");

		LocalDate debutString1 = LocalDate.parse(debutString, df);
		LocalDate finString1 = LocalDate.parse(finString, df);

		int client_id = Integer.parseInt(client_idString);
		int vehicle_id = Integer.parseInt(vehicle_idString);

		try {

			Reservation reservation = new Reservation(client_id, vehicle_id, debutString1, finString1);
			reservationService.create(reservation);

		} catch (ServiceException e) {
			e.printStackTrace();

		}
		response.sendRedirect("/rentmanager/rents");

	}
}
