package epf.model;

import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;

public class Client {

	private int id;
	private String nom;
	private String prenom;
	private String email;
	private LocalDate naissance;

	public Client(int id, String nom, String prenom, String email, LocalDate naissance) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.naissance = naissance;

	}

	public Client(String clientLastName, String clientFirstName, String clientEmail, LocalDate clientNaissance) {
		this.nom = clientLastName;
		this.prenom = clientFirstName;
		this.email = clientEmail;
		this.naissance = clientNaissance;
	}
		
		// TODO Auto-generated constructor stub
	public Client() {
	}
	

	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getNaissance() {
		return naissance;
	}

	public void setNaissance(LocalDate naissance) {
		this.naissance = naissance;
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", naissance="
				+ naissance + "]";
	}

	public int getAge() {
		// TODO Auto-generated method stub
		
		return Period.between(this.getNaissance(), LocalDate.now()).getYears();
		
	
	}
	 public static boolean isLegal(Client client) {
	       return
	          client.getAge() >= 18;
	 }

}

