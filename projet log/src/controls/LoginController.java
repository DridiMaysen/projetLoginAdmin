package controls;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import application.Main;
import application.SessionManager;
import application.User;
import dao.SingletonConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {
	String successStyle = String.format("-fx-border-color: #A9A9A9; -fx-border-width: 2; -fx-border-radius: 5;");
    String successMessage = String.format("-fx-text-fill: GREEN;");
    String errorMessage = String.format("-fx-text-fill: RED;");
    String errorStyle = String.format("-fx-border-color: RED; -fx-border-width: 2; -fx-border-radius: 5;");

    @FXML
    private Button btnSign;

    @FXML
    private ComboBox<String> cmbSelect;

    @FXML
    private  TextField txtName;

    @FXML
    private PasswordField txtPass;
    @FXML
    private Label lblmsg;

    @FXML
    void login(ActionEvent event) throws IOException {
    	if (txtName.getText().isBlank() || txtPass.getText().isBlank()) {
    		lblmsg.setText("the userName and the Password are required!");
    	    lblmsg.setStyle(errorMessage);
    	    if(txtName.getText().isBlank())
    	    	txtName.setStyle(errorStyle);
    	    else 
    	    	txtPass.setStyle(errorStyle);
    	}
    	else if (ConnectUser(txtName.getText(),txtPass.getText())){
    		User U= new User(txtName.getText());
    		SessionManager sm= SessionManager.getInstance();
    		sm.startSession(U);
    		Alert alert= new Alert (Alert.AlertType.INFORMATION);
    		alert.setTitle("Login SUCCESSEFUL");
    		alert.setHeaderText("Welcome");
    		alert.show();
    	Main l=new Main();
    	l.changersc();
    	} 
    	else {
		  Alert alert = new Alert(Alert.AlertType.ERROR);
		  alert.setTitle("Admin doesn't exist");
		  alert.setHeaderText("let's verify yourn inputs");
		  alert.show();
    	}
		

    }
    public static boolean ConnectUser(String Name, String Password) {
    	String rq="select * from admin where userName=? and pass=?;";
    	PreparedStatement ps;
    	ResultSet rs;
    	try {
			ps=SingletonConnection.getCon().prepareStatement(rq);
				ps.setString(1, Name);
				ps.setString(2, Password);
			    rs=ps.executeQuery();
				if (rs.next()) {
					return true;
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
    	
    }
    

}

