package epf.service;

import java.util.List;

import org.springframework.stereotype.Service;

import epf.dao.ReservationDao;
import epf.exception.DaoException;
import epf.exception.ServiceException;
import epf.model.Client;
import epf.model.Reservation;
import epf.persistence.ConnectionManager;

@Service
public class ReservationService {

	private ReservationDao reservationDao;

	private ReservationService(ReservationDao reservationDao) {
		this.reservationDao = reservationDao;
	}

	public int count() throws ServiceException {
		try {
			return this.reservationDao.count();

		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServiceException();
		}
		//return 0;
	}

	public long create(Reservation reservation) throws ServiceException {
		try {
			return this.reservationDao.create(reservation);

		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException();
		}
		// TODO: créer une réservation
	}
	
	
	public long delete(Reservation reservation) throws ServiceException {
		try {
			return this.reservationDao.delete(reservation);
		}catch(DaoException e) {
			e.printStackTrace();
			throw new ServiceException();
			
			
			
		}
	}
	
	
	

	public Reservation findById(long id) throws ServiceException {
		return null;
		// TODO: récupérer une réservation par son id

	}

	public List<Reservation> findAll() throws ServiceException {
		try {
			return this.reservationDao.findAll();
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException();
		}

		// TODO: récupérer toutes les réservations !!

	}

}
