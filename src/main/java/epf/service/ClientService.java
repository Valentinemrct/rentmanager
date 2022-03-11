package epf.service;

import java.util.List;

import org.springframework.stereotype.Service;

import epf.dao.ClientDao;
import epf.exception.DaoException;
import epf.exception.ServiceException;
import epf.model.Client;

@Service
public class ClientService {

	private ClientDao clientDao;

	private ClientService(ClientDao clientDao) {
		this.clientDao = clientDao;

	}

	public int count() throws ServiceException {
		try {
			return this.clientDao.count();

		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		throw new ServiceException();
		}
		// return 0;
	}

	public long create(Client client) throws ServiceException {
		try {
		if( client.getAge() >= 18) {
			return this.clientDao.create(client);
		}
		else {
		return 0; 
		}

		} catch (DaoException e) {
			throw new ServiceException();
		}
		// TODO: créer un client
	}
	
	
	
	public long edit(Client client) throws ServiceException {
		long ret = 0; 
		try {
			
			 ret = this.clientDao.edit(client);
			

		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException();
		}
		// TODO: créer un client
		return ret; 
	}
	
	public long delete(Client client) throws ServiceException {
		try {
			return this.clientDao.delete(client);
		}catch(DaoException e) {
			e.printStackTrace();
			throw new ServiceException();
			
			
			
		}
	}

	public Client findById(int id) throws ServiceException {
		try {
			return this.clientDao.findById(id).get();
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException();
		}
	}

	public List<Client> findAll() throws ServiceException {
		try {
			return this.clientDao.findAll();
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServiceException();
		}

		// TODO: récupérer tous les clients

	}
	
	public void verificationage(Client client) throws ServiceException {
	
		if (!Client.isLegal(client)) {
			throw new ServiceException(); 
			
		}
	}

}
