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

@WebServlet("/cars")
public class VehicleServlet extends HttpServlet {

	private static final long serialVersionID = 1L;

	@Autowired
	ClientService clientService;
	ReservationService reservationService;
	@Autowired
	private VehicleService vehicleService;
	private Vehicle vehicle;

	@Override
	public void init() throws ServletException {
		super.init();
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// affichage du formulaire

		// protected void doPost(HttpServletRequest request, HttpServletResponse
		// response) throws ServletException, IOException { traitement du formulaire
		// (appel à la méthode de sauvegarde)

		try 
		{
			request.setAttribute("listVehicles", vehicleService.findAll()); // on rajoute des attributs à une requête
			// http

		} catch (ServiceException e) {// jette une exception s'il y en a une
			e.printStackTrace();// lignes liées à l'erreur

			}this.getServletContext().getRequestDispatcher("/WEB-INF/views/vehicles/list.jsp").forward(request,response);

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			try {
				int id = Integer.parseInt(request.getParameter("idVehicle"));
			for (int i = 0; i < vehicleService.findAll().size(); i++) {
					if (vehicleService.findAll().get(i).getId() == id) {
						vehicle = vehicleService.findAll().get(i);
					}
				}

				request.setAttribute("deleteReservation", vehicleService.delete(vehicle));

				response.sendRedirect("/rentmanager/vehicles");

			} catch (ServiceException e) {
				e.printStackTrace();

			}
//		}
	}}
	
	
	
	

