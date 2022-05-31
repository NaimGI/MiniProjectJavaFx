package application.entities;

public class Salaire {
	private int Matricule;
	private String Nom;
	private String Email;
	private String Cat;
	private double AnneRecruit;
	private double Salaire;
	
	public Salaire(int matricule, String nom, String email, String cat, double anneRecruit, double salaire) {
		super();
		Matricule = matricule;
		Nom = nom;
		Email = email;
		Cat = cat;
		AnneRecruit = anneRecruit;
		Salaire = salaire;
	}

	public Salaire(int matricule, String nom, String email, String cat, double anneRecruit) {
		super();
		Matricule = matricule;
		Nom = nom;
		Email = email;
		Cat = cat;
		AnneRecruit = anneRecruit;
		
	}

	public String getCat() {
		return Cat;
	}

	public void setCat(String cat) {
		Cat = cat;
	}



	public int getMatricule() {
		return Matricule;
	}

	public void setMatricule(int matricule) {
		Matricule = matricule;
	}

	public String getNom() {
		return Nom;
	}

	public void setNom(String nom) {
		Nom = nom;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public double getAnneRecruit() {
		return AnneRecruit;
	}

	public void setAnneRecruit(double anneRecruit) {
		AnneRecruit = anneRecruit;
	}

	public double getSalaire() {
		return Salaire;
	}

	public void setSalaire(double salaire) {
		Salaire = salaire;
	}

	@Override
	public String toString() {
		return "Salarie [Matricule=" + Matricule + ", Nom=" + Nom + ", Email=" + Email + ", Cat=" + Cat
				+ ", AnneRecruit=" + AnneRecruit + ", Salaire=" + Salaire + "]";
	}

}

