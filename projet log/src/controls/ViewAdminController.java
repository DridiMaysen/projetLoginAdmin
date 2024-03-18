package controls;

import java.io.IOException;

import application.Main;
import application.SessionManager;
import application.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ViewAdminController {
	private Main main;
	

    public void setMain(Main main) {
		this.main = main;
	}

	public ViewAdminController() {
		super();
	}

	public ViewAdminController(Main main) {
		super();
		this.main = main;
	}

	@FXML
    private Button btnshow;

    @FXML
    void show(ActionEvent event) throws IOException {
    	Main startprofile=new Main();
    	setMain(startprofile);
    	SessionManager sm= SessionManager.getInstance();
    	User current = sm.getCurrentUser();
    	boolean okclick=main.showProfile(current);
    	if(okclick)
    		System.out.println("test fini");
    	

    }

}
