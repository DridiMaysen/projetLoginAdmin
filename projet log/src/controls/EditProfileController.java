package controls;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import application.Main;
import application.SessionManager;
import application.User;
import dao.SingletonConnection;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditProfileController {

    @FXML
    private Label lbluser;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPass;
    
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
					txtName.setText(user.getUserName());
					txtPass.setText(rs.getString("pass"));
					txtEmail.setText(rs.getString("email"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	 @FXML
	    public void Cancel() {
	    	stage.close();
	    }
	    @FXML
	    public void ok() {
	    	if(isValide()) {
	    		String rq="update admin set userName=?,pass=?,email=? where admin_id=?;";
	    		PreparedStatement ps;
	    		try {
					ps= SingletonConnection.getCon().prepareStatement(rq);
					SessionManager sm= SessionManager.getInstance();
					User U =sm.getCurrentUser();
					ps.setString(1, txtName.getText());
					ps.setString(2, txtPass.getText());
					ps.setString(3, txtEmail.getText());
					ps.setInt(4, EditProfileController.getId(U.getUserName()));
					int rowupdate=ps.executeUpdate();
					if (rowupdate<0) {
						Alert alert =new Alert (Alert .AlertType.INFORMATION);
			    		alert.initOwner(stage);
			    		alert.setTitle("Success fields");
			    		alert.setHeaderText("Info modified successfully");
			    		alert.show();
						
						
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    		
	    	}
	    	
	    }
	    private boolean isValide() {
	    	String msg="";
	    	if (txtName.getText()==null) {
	    		msg+="no valid userName";
	    	}
	    	if (txtPass.getText()==null) {
	    		msg+="no valid Password";
	    	}
	    	if (msg.length()==0) return true;
	    	else {
	    		Alert alert =new Alert (Alert .AlertType.ERROR);
	    		alert.initOwner(stage);
	    		alert.setTitle("Invalid fields");
	    		alert.setHeaderText("Pls correct invalid fields");
	    		alert.show();
	    		return false ;
	    	}

	    }
	    public static int getId(String u) {
	    	String rq= "select admin_id from admin wher userName=?;";
	    	int id=0;
	    	ResultSet rs;
	    	PreparedStatement ps;
	    	try {
				ps= SingletonConnection.getCon().prepareStatement(rq);
				ps.setString(1, u);
				rs= ps.executeQuery();
				while (rs.next()) {
					id=rs.getInt("admin_id");
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}return id;
	    }
	    
	    

}
