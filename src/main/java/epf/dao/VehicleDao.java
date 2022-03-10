package epf.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import epf.exception.DaoException;
import epf.model.Client;
import epf.model.Vehicle;
import epf.persistence.ConnectionManager;

@Repository
@Scope("singleton")
public class VehicleDao {
	private static VehicleDao instance = null;

	private VehicleDao() {

	}

	private static final String CREATE_VEHICLE_QUERY = "INSERT INTO Vehicle(constructeur, nb_places) VALUES(?, ?);";
	private static final String DELETE_VEHICLE_QUERY = "DELETE FROM Vehicle WHERE id=?;";
	private static final String FIND_VEHICLE_QUERY = "SELECT id, constructeur, nb_places FROM Vehicle WHERE id=?;";
	private static final String FIND_VEHICLES_QUERY = "SELECT id, constructeur, nb_places FROM Vehicle;";
	private static final String COUNT_VEHICLES = "SELECT COUNT(*) AS count FROM Vehicle;";

	public int count() throws DaoException {
		int n = 0;

		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(COUNT_VEHICLES);
			// pstmt.setInt(1,id);
			ResultSet rs = pstmt.executeQuery();
			rs.next();

			n = rs.getInt("count");
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();

		}

		return n;
	}
	

	
	public long create(Vehicle vehicle) throws DaoException {
		long ret = 0;
		try {
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(CREATE_VEHICLE_QUERY);

			pstmt.setString(1, vehicle.getConstructeur());
			pstmt.setInt(2, vehicle.getNb_places());

			ret = pstmt.executeUpdate(); 
			return ret;
		} catch (SQLException e) {
			
			// TODO Auto-generated catch block
			 e.printStackTrace();
			 throw new DaoException();
		}
	
	}




	public long delete(Vehicle vehicle) throws DaoException {
			long ret = 0;

			try {
				Connection conn = ConnectionManager.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(DELETE_VEHICLE_QUERY);

				pstmt.setInt(1, vehicle.getId());
				ret = pstmt.executeLargeUpdate();

			} catch (SQLException e) {
				throw new DaoException();
			}
			return ret;

		}


	

	public Optional<Vehicle> findById(long id) throws DaoException {
		return null;
	}

	public List<Vehicle> findAll() throws DaoException {// notifie à la classe appelante que ceci jette une exception
		List<Vehicle> listVehicles = new ArrayList<>();

		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(FIND_VEHICLES_QUERY);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				int vehicleId = rs.getInt("id");
				String vehicleConstructeur = rs.getString("constructeur");
				int vehiclePlace = rs.getInt("nb_places");

				Vehicle vehicle = new Vehicle(vehicleId, vehicleConstructeur, vehiclePlace);

				listVehicles.add(vehicle);

			}
			return listVehicles;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

}
