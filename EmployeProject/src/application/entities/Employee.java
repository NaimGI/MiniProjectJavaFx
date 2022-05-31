package application.entities;

public class Employee extends Salaire {
	private double HSupp;
	private double PHsupp;
	
	
	

	public Employee(int matricule, String nom, String email, String cat, double anneRecruit, double salaire,
			double hSupp, double pHsupp) {
		super(matricule, nom, email, cat, anneRecruit, salaire);
		HSupp = hSupp;
		PHsupp = pHsupp;
	}

	public double getHSupp() {
		return HSupp;
	}

	public void setHSupp(double hSupp) {
		HSupp = hSupp;
	}

	public double getPHsupp() {
		return PHsupp;
	}

	public void setPHsupp(double pHsupp) {
		PHsupp = pHsupp;
	}


	@Override
	public String toString() {
		return "Employee \n HSupp=" + HSupp + ",\n PHsupp=" + PHsupp  + ",toString()= \n" + super.toString() ;
	}

}
