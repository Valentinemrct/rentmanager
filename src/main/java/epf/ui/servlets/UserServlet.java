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

@WebServlet("/users")
public class UserServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ClientService clientService;
	private Client client;

	@Override
	public void init() throws ServletException {
		super.init();
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		


	try

	{
		request.setAttribute("listUsers", clientService.findAll()); // magasin où on rajoute des attributs à une
																	// requête http//

	}catch(ServiceException e)
	{ // si la méthode lance une exception on l'attrappe//
		e.printStackTrace();// lignes liées à l'erreur//
	}this.getServletContext().getRequestDispatcher("/WEB-INF/views/users/list.jsp").forward(request,response);

}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	try {
		int id = Integer.parseInt(request.getParameter("idUser"));
		for (int i = 0; i < clientService.findAll().size(); i++)
		{
			if (clientService.findAll().get(i).getId()	== id){
				client = clientService.findAll().get(i);
				}
			}
		
		request.setAttribute("deleteClient", clientService.delete(client));
	
		response.sendRedirect("/rentmanager/users");
	
	} catch (ServiceException e) {
		e.printStackTrace();

	
	}
}}