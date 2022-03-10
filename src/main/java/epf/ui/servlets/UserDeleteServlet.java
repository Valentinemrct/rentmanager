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
import epf.model.Client;
import epf.service.ClientService;

@WebServlet("/users/delete")

public class UserDeleteServlet extends HttpServlet {
	private Client client;

	@Autowired
	private ClientService clientService;
	private static final long serialVersionID = 1L;

	@Override
	public void init() throws ServletException {
		super.init();
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/users/delete.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		

		try {
			int id = Integer.parseInt(request.getParameter("idUser"));
			for (int i = 0; i < clientService.findAll().size(); i++) {
				if (clientService.findAll().get(i).getId() == id) {
					client = clientService.findAll().get(i);
				}
			}

			request.setAttribute("idclient", clientService.delete(client));

			response.sendRedirect("/rentmanager/users");

		} catch (ServiceException e) {
			e.printStackTrace();

		}

	}
}
