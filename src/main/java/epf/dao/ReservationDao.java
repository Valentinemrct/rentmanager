package epf.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import epf.exception.DaoException;
import epf.model.Reservation;
import epf.model.Vehicle;
import epf.persistence.ConnectionManager;

@Repository
@Scope("singleton")
public class ReservationDao {
	private static ReservationDao instance = null;

	private ReservationDao() {
	}

	private static final String CREATE_RESERVATION_QUERY = "INSERT INTO Reservation(client_id, vehicle_id, debut, fin) VALUES(?, ?, ?, ?);";
	private static final String DELETE_RESERVATION_QUERY = "DELETE FROM Reservation WHERE id=?;";
	private static final String FIND_RESERVATIONS_BY_CLIENT_QUERY = "SELECT id, vehicle_id, debut, fin FROM Reservation WHERE client_id=?;";
	private static final String FIND_RESERVATIONS_BY_VEHICLE_QUERY = "SELECT id, client_id, debut, fin FROM Reservation WHERE vehicle_id=?;";
	private static final String FIND_RESERVATIONS_QUERY = "SELECT id, client_id, vehicle_id, debut, fin FROM Reservation;";
	private static final String COUNT_RESERVATIONS = "SELECT COUNT(*) FROM Reservation;";
	private static final String EDIT_RESERVATION_QUERY = "UPDATE Reservation SET client_id = ?, vehicle_id = ?, debut = ?, fin = ?  WHERE id=?;";

	public int count() throws DaoException {
		int n = 0;

		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(COUNT_RESERVATIONS);
			// pstmt.setInt(1,id);
			ResultSet rs = pstmt.executeQuery();
			rs.next();

			n = rs.getInt(1);
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();

		}

		return n;
	}

	public long create(Reservation reservation) throws DaoException {
		long ret = 0;
		try {

			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(CREATE_RESERVATION_QUERY);

			LocalDate fin = reservation.getFin();
			LocalDate debut = reservation.getDebut();

			pstmt.setInt(1, reservation.getClient_id());
			pstmt.setInt(2, reservation.getVehicle_id());
			pstmt.setDate(3, Date.valueOf(debut));
			pstmt.setDate(4, Date.valueOf(fin));

			ret = pstmt.executeUpdate();

			return ret;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}

	}
	
	public long edit(Reservation reservation) throws DaoException {
		long ret = 0;

		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(EDIT_RESERVATION_QUERY);
			
			LocalDate fin = reservation.getFin();
			LocalDate debut = reservation.getDebut();

			pstmt.setInt(1, reservation.getClient_id());
			pstmt.setInt(2, reservation.getVehicle_id());
			pstmt.setDate(3, Date.valueOf(debut));
			pstmt.setDate(4, Date.valueOf(fin));
			pstmt.setInt(5, reservation.getId());


			ret = pstmt.executeUpdate();

			return ret;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
	}

	public long delete(Reservation reservation) throws DaoException {
		long ret = 0;

		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(DELETE_RESERVATION_QUERY);

			pstmt.setInt(1, reservation.getId());
			ret = pstmt.executeLargeUpdate();

		} catch (SQLException e) {
			throw new DaoException();
		}
		return ret;

	}

	public List<Reservation> findResaByClientId(long clientId) throws DaoException {
		return null;

	}

	public List<Reservation> findResaByVehicleId(long vehicleId) throws DaoException {
		return null;

	}

	public List<Reservation> findAll() throws DaoException {
		List<Reservation> listReservations = new ArrayList<>();

		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(FIND_RESERVATIONS_QUERY);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				int reservationId = rs.getInt("id");
				int clientId = rs.getInt("client_id");
				int reservationVehicle = rs.getInt("vehicle_id");
				LocalDate debut = rs.getDate("debut").toLocalDate();
				LocalDate fin = rs.getDate("fin").toLocalDate();

				Reservation reservation = new Reservation(reservationId, clientId, reservationVehicle, debut, fin);

				listReservations.add(reservation);

			}
			return listReservations;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}

	}
}
