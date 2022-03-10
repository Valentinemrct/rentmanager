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

@WebServlet("/rents/delete")
public class ReservationDeleteServlet extends HttpServlet {
	@Autowired
	ClientService clientService;
	VehicleService vehicleService; 
	@Autowired
	private Reservation reservation;
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
		request.getRequestDispatcher("/WEB-INF/views/rents/delete.jsp").forward(request, response);
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

			request.setAttribute("idRes", reservationService.delete(reservation));

			response.sendRedirect("/rentmanager/rents");

		} catch (ServiceException e) {
			e.printStackTrace();

		}

	}
}
