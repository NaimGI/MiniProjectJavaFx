package application;

import java.awt.TextField;
import java.net.URL;
import java.util.ResourceBundle;

import application.entities.Entreprice;
import application.functions.EnFunction;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class EntController implements Initializable {

	@FXML
    private TextField Entrenom;
    @FXML
    private Button AddBtn;
    @FXML
    private TextField idEntreprice;

    @FXML
    private Button UpdBtn;

    @FXML
    private Button PreBtn;

    @FXML
    private TableView<Entreprice> table;

    @FXML
    private TableColumn<Entreprice, Integer> idE;

    @FXML
    private TableColumn<Entreprice, Integer> NomE;

    @FXML
    private Button deleBtn;
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		idE.setCellValueFactory(new PropertyValueFactory<>("idEn"));
		NomE.setCellValueFactory(new PropertyValueFactory<>("nomEntreprise"));
		EnFunction f=new EnFunction();
		table.getItems().addAll(f.listerEntreprcie());
		
		
	}

    @FXML
    void AddEn(ActionEvent event) {
  Entreprice E = new Entreprice(Integer.parseInt(idEntreprice.getText()),Entrenom.getText());
  EnFunction En =new EnFunction();
En.createEntreprice(E);
table.getItems().add(En.getEntreprice(E.getIdEn()));
    }

    @FXML
    void Delete(ActionEvent event) {

    }

    @FXML
    void Update(ActionEvent event) {

    }

    @FXML
    void prepare(ActionEvent event) {

    }
	

	

}
