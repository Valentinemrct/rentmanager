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
import epf.model.Client;
import epf.service.ClientService;

@WebServlet("/users/create")
public class UserCreateServlet extends HttpServlet {

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
		this.getServletContext().getRequestDispatcher("/WEB-INF/views/users/create.jsp").forward(request, response);

// affichage

//	try {
		// ClientService.getInstance().create(client);

		// }catch (ServiceException e) {
		// e.printStackTrace();
		// }
		// response.sendRedirect("/rentmanager/users");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String clientLastName = request.getParameter("nom");
		String clientFirstName = request.getParameter("prenom");
		String clientEmail = request.getParameter("email");
		String clientNaissanceString = request.getParameter("naissance");

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate clientNaissance = LocalDate.parse(clientNaissanceString, formatter);
		
		System.out.println("clientlastname " + clientLastName);
	

		try {
			
			Client client = new Client(clientLastName, clientFirstName, clientEmail, clientNaissance);
			clientService.create(client);
			response.sendRedirect("/rentmanager/users");
		
		} catch (ServiceException e) {
			e.printStackTrace();

		}
	}

}
