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

@WebServlet("/vehicles/delete")
public class VehicleDeleteServlet extends HttpServlet {
	private Vehicle vehicle;

	@Autowired
	private VehicleService vehicleService;
	private static final long serialVersionID = 1L;

	@Override
	public void init() throws ServletException {
		super.init();
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/vehicles/delete.jsp").forward(request, response);
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

			request.setAttribute("idvehicle", vehicleService.delete(vehicle));

			response.sendRedirect("/rentmanager/cars");

		} catch (ServiceException e) {
			e.printStackTrace();

		}

	}	

}
