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
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ProfileController {
	private User user;
	private Stage stage;
	private Main main;
	private boolean okclick=false;
	public boolean isOkClick() {
		return okclick;
	}
	

    public void setStage(Stage stage) {
		this.stage = stage;
	}


	public void setMain(Main main) {
		this.main = main;
    }

	@FXML
    private Label lblUser;

    @FXML
    private Label lblmail;

    @FXML
    private Label lblpass;

    @FXML
    private Label lbluser;

    @FXML
    void change(ActionEvent event) throws IOException {
    	Main start = new Main();
    	setMain(main);
    	SessionManager sm= SessionManager.getInstance();
    	User c =sm.getCurrentUser();
    	main.showProfileEdit(c);

    }

    @FXML
    void ok(ActionEvent event) {
    	okclick=true;
    	stage.close();
    }
    public void setUser(User U) {
    	SessionManager sm=SessionManager.getInstance();
    	this.user=sm.getCurrentUser();
    	String rq="select * from admin where userName=?;";
    	ResultSet rs;
    	PreparedStatement ps ;
    	try {
			ps= SingletonConnection.getCon().prepareStatement(rq);
			ps.setString(1, user.getUserName());
			rs= ps.executeQuery();
			while (rs.next()) {
				lbluser.setText("Hello"+ user.getUserName());
				lblUser.setText(user.getUserName());
				lblpass.setText(rs.getString("pass"));
				lblmail.setText(rs.getString("email"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    

}