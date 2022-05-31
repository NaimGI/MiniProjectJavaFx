package application.entities;

public class Vendeur extends Salaire {
	private double Vente;
	private double Pourcentage;



	public Vendeur(int matricule, String nom, String email, String cat, double anneRecruit, double salaire,
			double vente, double pourcentage) {
		super(matricule, nom, email, cat, anneRecruit, salaire);
		Vente = vente;
		Pourcentage = pourcentage;
	}

	public double getVente() {
		return Vente;
	}

	public void setVente(double vente) {
		Vente = vente;
	}

	public double getPourcentage() {
		return Pourcentage;
	}

	public void setPourcentage(double pourcentage) {
		Pourcentage = pourcentage;
	}

	@Override
	public String toString() {
		return "Vendeur Vente=\n" + Vente + ", Pourcentage=" + Pourcentage + ",\n Matricule()=" + getMatricule()
				+ ", \n Nom()=" + getNom() + ",\n Email()=" + getEmail() + ",\n AnneRecruit()=" + getAnneRecruit()
				+ ",\n Salaire()=" + getSalaire() ;
	}

	
	
}
