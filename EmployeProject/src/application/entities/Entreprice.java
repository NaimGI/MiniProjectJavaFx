package application.entities;

import java.util.HashMap;

public class Entreprice {
	private Integer idEn;
	

	public Entreprice(Integer idEn, String nomEntreprise) {
		super();
		this.idEn = idEn;
		this.nomEntreprise = nomEntreprise;
	}

	public Integer getIdEn() {
		return idEn;
	}

	public void setIdEn(Integer idEn) {
		this.idEn = idEn;
	}

	private String nomEntreprise;
	private HashMap<Integer, Salaire> salaries;

	public Entreprice(String nomEntreprise) {
		super();
		this.nomEntreprise = nomEntreprise;
	}

	public String getNomEntreprise() {
		return nomEntreprise;
	}

	public void setNomEntreprise(String nomEntreprise) {
		this.nomEntreprise = nomEntreprise;
	}

	public HashMap<Integer, Salaire> getSalaries() {
		return salaries;
	}

	public void setSalaries(HashMap<Integer, Salaire> salaries) {
		this.salaries = salaries;
	}

}
