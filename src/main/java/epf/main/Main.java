package epf.main;

import java.time.LocalDate;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import epf.configuration.AppConfiguration;
import epf.exception.ServiceException;
import epf.model.Client;
import epf.service.ClientService;

public class Main {

	public static void main(String[] args) throws ServiceException {

	Client valentine = new Client(1,"valentine", "mercent", "valentine.mercent@gamil.com", LocalDate.of(1990,1,1));
	
		System.out.println(valentine.toString());

	ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
		ClientService clientService = context.getBean(ClientService.class);
		System.out.println(clientService.findAll());
	int id;
	clientService.delete(valentine);
	
		System.out.println(clientService.findAll());
	}
}
