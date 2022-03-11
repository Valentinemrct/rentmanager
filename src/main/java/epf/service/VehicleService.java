package epf.service;

import java.util.List;

import org.springframework.stereotype.Service;

import epf.dao.VehicleDao;
import epf.exception.DaoException;
import epf.exception.ServiceException;
import epf.model.Client;
import epf.model.Vehicle;
@Service
public class VehicleService {

	private VehicleDao vehicleDao;
	
	private VehicleService(VehicleDao vehicleDao) {
		this.vehicleDao = vehicleDao;
	}

	
	public int count() throws ServiceException {
			try {
				return this.vehicleDao.count();
				
			} catch (DaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new ServiceException();	
			}
		//return 0;
	}
	
	
	public long create(Vehicle vehicle) throws ServiceException {
		try {
			return this.vehicleDao.create(vehicle);

		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException();
		}
		// TODO: créer un véhicule
		
		
	}
	
	public long edit(Vehicle vehicle) throws ServiceException {
		long ret = 0; 
		try {
			
			 ret = this.vehicleDao.edit(vehicle);
			

		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException();
		}
		// TODO: créer un client
		return ret; 
	}
	
	
	public long delete(Vehicle vehicle) throws ServiceException {
		try {
			return this.vehicleDao.delete(vehicle);
		}catch(DaoException e) {
			e.printStackTrace();
			throw new ServiceException();
			
			
			
		}
	}

	public Vehicle findById(long id) throws ServiceException {
		return null;
		// TODO: récupérer un véhicule par son id
		
	}

	public List<Vehicle> findAll() throws ServiceException {
		try {
			return this.vehicleDao.findAll();
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException();
		}
		
	
		// TODO: récupérer tous les véhicules
		
	}
	
}
