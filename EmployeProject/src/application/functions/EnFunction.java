package application.functions;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import application.connection.connexion;
import application.entities.Entreprice;
import application.entities.Salaire;
import application.entities.Vendeur;

public class EnFunction {

	public EnFunction() {
		// TODO Auto-generated constructor stub
	}
	public List<Entreprice> listerEntreprcie() {
		List<Entreprice> emps = new ArrayList<>();
		try {
			String reqE = "SELECT idEntrepise ,nom from entreprise";
			Statement stmt = connexion.getConx().createStatement();
			ResultSet rsE = stmt.executeQuery(reqE);

			while (rsE.next()) {
				emps.add(new Entreprice(rsE.getInt(1),rsE.getString(2)));
			}
			return emps;

		} catch (SQLException ex) {
			System.out.println("Erreur SQL" + ex);
		}
		return null;
	}
	public boolean createEntreprice(Entreprice e) {
		try {
			Statement stmt = connexion.getConx().createStatement();
			String reqEntreprice = "Insert into entreprice values(" + e.getIdEn() + ",'" + e.getNomEntreprise() + "',');";
		} catch (SQLException ex) {
			System.out.println("Erreur SQL" + ex);
		}
		return false;
	}
	public Entreprice getEntreprice(int id) {
		try {
			String reqRole = "Select idEntreprise,nom from salarie where idEntreprise = " + id;
			Statement stmt = connexion.getConx().createStatement();
			ResultSet rs = stmt.executeQuery(reqRole);
			return new Entreprice(rs.getInt(1),rs.getString(2));
		} catch (SQLException ex) {
			System.out.println("SQL ERROR" + ex);
		}
		return null;
	}
	public static void main(String Args[]) {
	
		
		

	}
}
