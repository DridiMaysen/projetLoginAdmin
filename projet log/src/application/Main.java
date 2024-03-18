package application;
	
import java.io.IOException;

import controls.EditProfileController;
import controls.ProfileController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;



public class Main extends Application {
	static Stage primaryStage;
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage=primaryStage;
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/Login.fxml"));
			
			Scene scene = new Scene(fxmlLoader.load());
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void changersc() throws IOException {
		FXMLLoader fxmlLoaderLoginAdmin = new FXMLLoader(Main.class.getResource("/view/ViewAdmin.fxml"));
		Scene sc = new Scene (fxmlLoaderLoginAdmin.load());
		primaryStage.setScene(sc);
		primaryStage.show();
	}
	public boolean showProfile(User U) throws IOException {
		FXMLLoader fxmlLoaderProfile = new FXMLLoader(Main.class.getResource("/view/Profile.fxml"));
		AnchorPane profile=fxmlLoaderProfile.load();
		Stage s=new Stage();
		s.setTitle("View Profile");
		s.initModality(Modality.WINDOW_MODAL);
		s.initOwner(primaryStage);
		Scene sc =new Scene (profile);
		s.setScene(sc);
		ProfileController pf=fxmlLoaderProfile.getController();
		pf.setStage(s);
		pf.setUser(U);
		pf.setMain(this);
		s.show();
		return pf.isOkClick();
	}
	public static void main1(String[] args) {
		launch(args);
	}

public boolean showProfileEdit(User U) throws IOException {
	FXMLLoader fxmlLoaderEditProfile = new FXMLLoader(Main.class.getResource("/view/EditProfile.fxml"));
	AnchorPane edprofile=fxmlLoaderEditProfile.load();
	Stage s=new Stage();
	s.setTitle("Edit Profile");
	s.initModality(Modality.WINDOW_MODAL);
	s.initOwner(primaryStage);
	Scene sc =new Scene (edprofile);
	s.setScene(sc);
	EditProfileController pf=fxmlLoaderEditProfile.getController();
	pf.setStage(s);
	pf.setUser(U);
	pf.setMain(this);
	s.show();
	return pf.isOkClick();
}
public static void main(String[] args) {
	launch(args);
}
}
