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
import epf.model.Reservation;
import epf.service.ClientService;
import epf.service.ReservationService;
import epf.service.VehicleService;

@WebServlet("/rents")
public class ReservationServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Autowired
	ClientService clientService;
	VehicleService vehicleService;
	@Autowired
	private ReservationService reservationService;
	private Reservation reservation;

	@Override
	public void init() throws ServletException {
		super.init();
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			request.setAttribute("listReservations", reservationService.findAll()); // magasin où on rajoute des
			request.getRequestDispatcher("/WEB-INF/views/rents/list.jsp").forward(request, response); // requête
			// attributs à une
			System.out.println(reservationService.findAll()); // http//

		} catch (ServiceException e) { // si la méthode lance une exception on l'attrappe//
			e.printStackTrace();// lignes liées à l'erreur//
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			try {
				int id = Integer.parseInt(request.getParameter("idReservation"));
			for (int i = 0; i < reservationService.findAll().size(); i++) {
					if (reservationService.findAll().get(i).getId() == id) {
						reservation = reservationService.findAll().get(i);
					}
				}

				request.setAttribute("deleteReservation", reservationService.delete(reservation));

				response.sendRedirect("/rentmanager/rents");

			} catch (ServiceException e) {
				e.printStackTrace();

			}}
	
}
