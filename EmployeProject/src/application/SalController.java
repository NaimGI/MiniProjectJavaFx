package application;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.entities.Employee;
import application.entities.Salaire;

import application.entities.Vendeur;
import application.functions.functions;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class SalController implements Initializable  {
	 @FXML
	    private Button btnChek;

    @FXML
    private AnchorPane btnListeTout;

    @FXML
    private TextField Nom;

    @FXML
    private TextField email;

    @FXML
    private TextField recut;

    @FXML
    private TextField salaire;

    @FXML
    private TextField mat;

    @FXML
    private RadioButton btnE;

    @FXML
    private RadioButton BtnV;

    @FXML
    private Button btnadd;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableView<Salaire> table;

    @FXML
    private TableColumn<Salaire, Integer> matColmn;

    @FXML
    private TableColumn<Salaire, Integer> nameColmn;

    @FXML
    private TableColumn<Salaire, Integer> emailColmn;

    @FXML
    private TableColumn<Salaire, Integer> recColmn;

    @FXML
    private TableColumn<Salaire, Integer> salColmn;
    @FXML
    private TableColumn<Salaire, Integer> roleCol;
    @FXML
    private Button BtnLister;
    @FXML
    private Button btnMax;

    @FXML
    private Button btnMix;

    @FXML
    private Button BTNDet;

    @FXML
    private Button BtnExport;

    @FXML
    private Button BtnListeAn;

    @FXML
    private TextField first;

    @FXML
    private TextField last;
    @FXML
    private TextArea textArea;

    @FXML
    private Button BtnBetwwen;
    @FXML
    private TextField Hsupp;
    @FXML
    private RadioButton RadioEm;

    @FXML
    private RadioButton RadioVen;
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	matColmn.setCellValueFactory(new PropertyValueFactory<>("Matricule"));
    	nameColmn.setCellValueFactory(new PropertyValueFactory<>("Nom"));
    	emailColmn.setCellValueFactory(new PropertyValueFactory<>("Email"));
    	roleCol.setCellValueFactory(new PropertyValueFactory<>("Cat"));
    	recColmn.setCellValueFactory(new PropertyValueFactory<>("AnneRecruit"));
    	salColmn.setCellValueFactory(new PropertyValueFactory<>("Salaire"));
		functions f= new functions();
		table.getItems().addAll(f.listerEmployee());
		table.getItems().addAll(f.listerVendeur());
	}
    @FXML
    void Add(ActionEvent event) throws NumberFormatException, IOException {
    	double vente = 0;
		double PhSupp=0;
		File inputFile = new File("C:\\Users\\NAIM\\eclipse-workspace\\EmployeProject\\bin\\application\\vente.txt");
		FileReader fin = new FileReader(inputFile);
		BufferedReader br =new BufferedReader(fin); 
		String s;
		while((s=br.readLine())!=null) {
			String [] T=s.split(" ");
		 vente=Double.parseDouble(T[0]);
		PhSupp =Double.parseDouble(T[1]);}
		fin.close();
		
		System.out.println(vente);
		System.out.println(PhSupp);
    	if(btnE.isSelected()) {
    		
			    		Employee emp = new Employee(Integer.parseInt(mat.getText()), Nom.getText(), email.getText(),"E",Double.parseDouble(recut.getText()), 0.1,Double.parseDouble(Hsupp.getText()),PhSupp);
    		System.out.println(emp.toString());
    		functions AS = new functions();
    		boolean result = AS.createEmployee(emp);
    		table.getItems().add(AS.getSalarie(emp.getMatricule()));
    		mat.setText("");
    		Nom.setText("");
    		email.setText("");
    		recut.setText("");
    		Hsupp.setText("");
    		
    	} else if(BtnV.isSelected()) {
    		Vendeur vdr = new Vendeur(Integer.parseInt(mat.getText()), Nom.getText(), email.getText(),"V",Double.parseDouble(recut.getText()), 0.1,vente, 0.2);
    		System.out.println(vdr.toString());
    		functions AS = new functions();
    		boolean result = AS.createVendeur(vdr);
    		table.getItems().add(AS.getSalarie(vdr.getMatricule()));
    		mat.setText("");
    		Nom.setText("");
    		email.setText("");
    		recut.setText("");
    		Hsupp.setText("");
    	}
    }

    @FXML
    void Delete(ActionEvent event) {
    	functions AS = new functions();
    	
    	AS.deleteSalarie(table.getSelectionModel().getSelectedItem().getMatricule());
    	
    	table.getItems().remove(table.getSelectionModel().getSelectedItem());
    }

    @FXML
    void Details(ActionEvent event) {
    	functions AS = new functions();
    	int id = table.getSelectionModel().getSelectedItem().getMatricule();
    	String role = table.getSelectionModel().getSelectedItem().getCat();
    	if(role.equals("Vendeur")) {
    		Vendeur vd = AS.VendeurDetails(id);
    		System.out.println(vd.toString());
    		textArea.setText(vd.toString());
    		
    	}else {
    		Employee emp=AS.EmployeDetails(id);
    		textArea.setText(emp.toString());
    	}
    }

    @FXML
    void Export(ActionEvent event) {
    	File expFile = new File("C:\\Users\\NAIM\\eclipse-workspace\\EmployeProject\\bin\\application\\export.txt");
		try {
			FileWriter expFileReader=new FileWriter(expFile);
			expFileReader.write(table.getItems().toString());
			expFileReader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void ListAnn(ActionEvent event) {
    	functions AS = new functions();
		table.getItems().clear();
		table.getItems().addAll(AS.listerAnniceir());
    }

    @FXML
    void ListerBetw(ActionEvent event) {
    	functions AS = new functions();
    	List<Salaire> salaries = AS.getSalariesMinMax(Double.parseDouble(first.getText()), Double.parseDouble(last.getText()));
    	table.getItems().clear();
    	table.getItems().addAll(salaries);
    }

    @FXML
    void ListerCat(ActionEvent event) {
    	System.out.println(RadioEm.isSelected());
if(RadioEm.isSelected()) {
	
	functions f =new functions();
	table.getItems().clear();
	table.getItems().addAll(f.listerEmployee());
}else if(RadioVen.isSelected()) {
	functions f =new functions();
	table.getItems().clear();
	table.getItems().addAll(f.listerVendeur());
}else {
	System.out.println("Please Check a button");
}
    }

    @FXML
    void ListerMax(ActionEvent event) {
    	
    		functions AS = new functions();
    		table.getItems().clear();
    		table.getItems().add(AS.getMaxVente());
    }

    @FXML
    void ListerMin(ActionEvent event) {
    	functions AS = new functions();
		table.getItems().clear();
		table.getItems().addAll(AS.listerFaible());
    }

    @FXML
    void Quite(ActionEvent event) {

    }
    @FXML
    void ModifCkeck(ActionEvent event) {
    	functions AS = new functions();
    	int id = table.getSelectionModel().getSelectedItem().getMatricule();
    	String role = table.getSelectionModel().getSelectedItem().getCat();
    	if(role.equals("Vendeur")) {
    		Vendeur vd = AS.VendeurDetails(id);
    		mat.setText(Integer.toString( vd.getMatricule()));
    		email.setText(vd.getEmail());
    		Nom.setText(vd.getNom());
    		recut.setText(Double.toString(vd.getAnneRecruit()));
    		//tfTaux_Pourcentage.setText(Double.toString(vd.getPourcentage()));
    		Hsupp.setText(Double.toString(vd.getVente()));
    		btnE.setSelected(true);
    	}else {
    		Employee emp=AS.EmployeDetails(id);
    		mat.setText(Integer.toString( emp.getMatricule()));
    		email.setText(emp.getEmail());
    		Nom.setText(emp.getNom());
    		recut.setText(Double.toString(emp.getAnneRecruit()));
    		//tfTaux_Pourcentage.setText(Double.toString(emp.getPHsupp()));
    		Hsupp.setText(Double.toString(emp.getHSupp()));
    		BtnV.setSelected(true);
    	}
    }

    @FXML
    void update(ActionEvent event) {
    	functions AS = new functions();
    	
    	if(btnE.isSelected()) {
    		Employee emp = new Employee(Integer.parseInt(mat.getText()), Nom.getText(), email.getText(),"E",Double.parseDouble(recut.getText()), 0.1,Double.parseDouble(Hsupp.getText()),0.2);
    		AS.updateEmploye(emp);
    		table.getItems().remove(table.getSelectionModel().getSelectedItem());
    		table.getItems().add(AS.getSalarie(emp.getMatricule()));
    	} else if(BtnV.isSelected()) {
    		Vendeur vdr = new Vendeur(Integer.parseInt(mat.getText()), Nom.getText(), email.getText(),"E",Double.parseDouble(recut.getText()), 0.1,Double.parseDouble(Hsupp.getText()), 0.2);
    		table.getItems().remove(table.getSelectionModel().getSelectedItem());
    		AS.updateVendeur(vdr);
    		table.getItems().add(AS.getSalarie(vdr.getMatricule()));
    	}
}
}