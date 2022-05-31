package application.functions;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import application.connection.connexion;
import application.entities.Employee;
import application.entities.Salaire;
import application.entities.Vendeur;






public class functions {
	public void updateVendeur(Vendeur v) {
		try {
			String req = "update salarie set matricule=" + v.getMatricule() + ",nom='" + v.getNom() + "', email='"
					+ v.getEmail() + "', anneRecruit=" + v.getAnneRecruit() + " where matricule=" + v.getMatricule();
			Statement stmt = connexion.getConx().createStatement();
			if (stmt.executeUpdate(req) == 1) {
				System.out.println("Salarie Updated");
			}
			String req1 = "update vendeur set  Matricule=" + v.getMatricule() + ", Pourcentage=" + v.getPourcentage()
					+ ",Vente=" + v.getVente() + " where Matricule=" + v.getMatricule();

			if (stmt.executeUpdate(req1) == 1) {
				System.out.println("Vendeur Updated");
			}
		} catch (SQLException ex) {
			System.out.println("SQL ERROR" + ex);
		}
	}
	public Employee EmployeDetails(int empId) {

		try {
			String req = "SELECT s.matricule, nom, email, anneRecruit,(salaire + HSupp * PHSupp) as salaireTot,  HSupp, PHSupp, categorie  from employee e, salarie s where e.Matricule = s.matricule and e.Matricule ="
					+ empId;
			Statement stmt = connexion.getConx().createStatement();
			ResultSet rs = stmt.executeQuery(req);

			if (rs.next())
				return new Employee(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(8), rs.getDouble(4),
						rs.getDouble(5), rs.getDouble(6), rs.getDouble(7));

		} catch (SQLException ex) {
			System.out.println("Erreur SQL " + ex);
		}
		return null;
	}
	public Vendeur VendeurDetails(int vdrId) {
		try {
			String req = "SELECT s.matricule, nom, email, anneRecruit,(salaire + Vente * Pourcentage) as salaireTot, Vente, Pourcentage, categorie from vendeur v, salarie s where v.Matricule = s.matricule and  v.Matricule ="
					+ vdrId;

			Statement stmt = connexion.getConx().createStatement();
			ResultSet rs = stmt.executeQuery(req);

			if (rs.next()) {
				return new Vendeur(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(8), rs.getDouble(4),
						rs.getDouble(5), rs.getDouble(6), rs.getDouble(7));
			}

		} catch (SQLException ex) {
			System.out.println("Erreur SQL " + ex);
		}
		return null;
	}
	
	public void updateEmploye(Employee e) {
		try {
			System.out.println(e.getEmail());
			String req = "update salarie set matricule=" + e.getMatricule() + ",nom='" + e.getNom() + "', email='"
					+ e.getEmail() + "', anneRecruit=" + e.getAnneRecruit() + " where matricule=" + e.getMatricule();
			Statement stmt = connexion.getConx().createStatement();
			if (stmt.executeUpdate(req) == 1) {
				System.out.println("Salarie Updated");
			}
			String req1 = "update employee set  Matricule=" + e.getMatricule() + ", PHSupp=" + e.getPHsupp() + ",HSupp="
					+ e.getHSupp() + " where Matricule=" + e.getMatricule();

			if (stmt.executeUpdate(req1) == 1) {
				System.out.println("Employe Updated");
			}
		} catch (SQLException ex) {
			System.out.println("SQL ERROR" + ex);
		}

	}
	public boolean createVendeur(Vendeur V) {
		try {
			Statement stmt = connexion.getConx().createStatement();
			if (V.getAnneRecruit() < 2005)
				V.setSalaire(400);
			else
				V.setSalaire(280);
			String reqSalarie = "Insert into salarie values(" + V.getMatricule() + ",'" + V.getNom() + "','"
					+ V.getEmail() + "'," + V.getAnneRecruit() + "," + V.getSalaire() + ",'Vendeur');";
			if (stmt.executeUpdate(reqSalarie) == 1) {
				String reqEmployee = "Insert into Vendeur values (" + V.getMatricule() + "," + V.getVente() + ","
						+ V.getPourcentage() + ");";
				if (stmt.executeUpdate(reqEmployee) == 1) {
					return true;
				}
			}

		} catch (SQLException ex) {
			System.out.println("Erreur SQL" + ex);
		}
		return false;
	}
	
	public boolean deleteSalarie(int empId) {
		try {
			String req = "Delete from salarie where matricule =" + empId;
			Statement stmt = connexion.getConx().createStatement();
			if (stmt.executeUpdate(req) == 1) {
				return true;
			}
		} catch (SQLException ex) {
			System.out.println("SQL EXCEPTION");
		}
		return false;
	}
	
	public boolean createEmployee(Employee E) {
		try {
			Statement stmt = connexion.getConx().createStatement();
			if (E.getAnneRecruit() < 2005.0)
				E.setSalaire(400);
			else
				E.setSalaire(280);
			System.out.println(E.getMatricule() );
			String reqSalarie = "Insert into salarie(matricule,nom,email,anneRecruit,salaire,categorie) values(" + E.getMatricule() + ",'" + E.getNom() + "','"
					+ E.getEmail() + "'," + E.getAnneRecruit() + "," + E.getSalaire() + ",'Employee');";
			if (stmt.executeUpdate(reqSalarie) == 1) {
				String reqEmployee = "Insert into employee values (" + E.getMatricule() + "," + E.getHSupp() + ","
						+ E.getPHsupp() + ");";
				if (stmt.executeUpdate(reqEmployee) == 1) {
					return true;
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return false;
	}
	public Salaire getMaxVente() {
		try {
			String req = "Select s.matricule, nom, email, categorie, anneRecruit,(salaire + Vente * Pourcentage) from salarie s, vendeur v where s.matricule = v.Matricule ORDER by (salaire + Vente * Pourcentage) desc;";
			Statement stmt = connexion.getConx().createStatement();
			ResultSet rsv = stmt.executeQuery(req);
			rsv.next();
				return new Salaire(rsv.getInt(1), rsv.getString(2), rsv.getString(3), rsv.getString(4),
						rsv.getDouble(5), rsv.getDouble(6));
		}catch(SQLException ex) {
			System.out.println(ex);
		}
		return null;
	}
	public List<Salaire> listerEmployee() {
		List<Salaire> emps = new ArrayList<>();
		try {
			String reqE = "SELECT s.matricule, nom, email, categorie, anneRecruit, (salaire + HSupp * PHSupp) as salaireTot  from salarie s, employee e where s.matricule = e.Matricule;";
			Statement stmt = connexion.getConx().createStatement();
			ResultSet rsE = stmt.executeQuery(reqE);

			while (rsE.next()) {
				emps.add(new Salaire(rsE.getInt(1), rsE.getString(2), rsE.getString(3), rsE.getString(4),
						rsE.getDouble(5), rsE.getDouble(6)));
			}
			return emps;

		} catch (SQLException ex) {
			System.out.println("Erreur SQL" + ex);
		}
		return null;
	}
	public List<Salaire> listerAnniceir() {
		List<Salaire> emps = new ArrayList<>();
		try {
			String req = "Select matricule, nom, email, categorie, anneRecruit ,salaire from salarie Order by anneRecruit ASC;";
			Statement stmt = connexion.getConx().createStatement();
			ResultSet rsA = stmt.executeQuery(req);
			while (rsA.next()) {
				emps.add(new Salaire(rsA.getInt(1), rsA.getString(2), rsA.getString(3), rsA.getString(4),rsA.getDouble(5),rsA.getDouble(6)));
			}
			return emps;
		}catch(SQLException ex) {
			System.out.println(ex);
		}
		return null;
	}
	public List<Salaire> getSalariesMinMax(double min, double max) {

		List<Salaire> salaries = new ArrayList<>();
		functions AS = new functions();
		List<Salaire> v = AS.listerEmployee();
		List<Salaire> b = AS.listerVendeur();
		for (Salaire x : v) {
			salaries.add(x);
		}

		for (Salaire x : b) {
			salaries.add(x);
		}
		 
		Iterator<Salaire> it = salaries.iterator();
		
		while (it.hasNext()) {
			Salaire s = it.next();
			if(s.getSalaire()> max || s.getSalaire() < min) {
				it.remove();
			}
		}
		
		return salaries;

	}
	public List<Salaire> listerFaible() {
		List<Salaire> emps = new ArrayList<>();
		try {
			String req = "Select matricule, nom, email, categorie, anneRecruit ,salaire from salarie Order by salaire ASC;";
			Statement stmt = connexion.getConx().createStatement();
			ResultSet rsA = stmt.executeQuery(req);
			while (rsA.next()) {
				emps.add(new Salaire(rsA.getInt(1), rsA.getString(2), rsA.getString(3), rsA.getString(4),rsA.getDouble(5),rsA.getDouble(6)));
			}
			return emps;
		}catch(SQLException ex) {
			System.out.println(ex);
		}
		return null;
	}
	
	public List<Salaire> listerVendeur() {
		List<Salaire> vdrs = new ArrayList<>();
		try {
			String reqV = "SELECT s.matricule, nom, email, anneRecruit, (salaire + vente * pourcentage) as salaireTot, categorie from salarie s, vendeur v where s.matricule = v.Matricule;";
			Statement stmt = connexion.getConx().createStatement();
			ResultSet rsE = stmt.executeQuery(reqV);
			while (rsE.next()) {
				vdrs.add(new Salaire(rsE.getInt(1), rsE.getString(2), rsE.getString(3), rsE.getString(6),
						rsE.getDouble(4), rsE.getDouble(5)));
			}
			return vdrs;
		} catch (SQLException ex) {
			System.out.println("Erreur SQL" + ex);
		}
		return null;

	}
	public Salaire getSalarie(int id) {
		try {
			String reqRole = "Select categorie from salarie where matricule = " + id;
			Statement stmt = connexion.getConx().createStatement();
			ResultSet rs = stmt.executeQuery(reqRole);
			if (rs.next()) {
				if (rs.getString(1).equals("Vendeur")) {
					String req = "Select s.matricule, nom, email, categorie, anneRecruit,(salaire + Vente * Pourcentage) from salarie s, vendeur v where s.matricule = v.Matricule and s.matricule = "
							+ id;
					ResultSet rsv = stmt.executeQuery(req);
					if (rsv.next())
						return new Salaire(rsv.getInt(1), rsv.getString(2), rsv.getString(3), rsv.getString(4),
								rsv.getDouble(5), rsv.getDouble(6));
				} else {
					String req = "Select s.matricule, nom, email, categorie, anneRecruit,(salaire + HSupp * PHSupp) from salarie s, employee v where s.matricule = v.Matricule and s.matricule = "
							+ id;
					ResultSet rsv = stmt.executeQuery(req);
					if (rsv.next())
						return new Salaire(rsv.getInt(1), rsv.getString(2), rsv.getString(3), rsv.getString(4),
								rsv.getDouble(5), rsv.getDouble(6));
				}
			}
		} catch (SQLException ex) {
			System.out.println("SQL ERROR" + ex);
		}
		return null;
	}
	public static void main(String Args[]) {
		functions f = new functions();
		
		

	}
}
