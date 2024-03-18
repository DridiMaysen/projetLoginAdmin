package application;

public class SessionManager {
	private static SessionManager instance;
	private User CurrentUser;
	public SessionManager() {
		super();
	}
	public SessionManager(User currentUser) {
		super();
		CurrentUser = currentUser;
	}
	public static SessionManager getInstance(){
		if (instance ==null )
			instance= new SessionManager();
		return instance ;
		
	}
	public void startSession(User user) {
		CurrentUser=user;
	}
	public void endSession() {
		CurrentUser=null;
	}
	public User getCurrentUser() {
		return CurrentUser;
	}
	public void setCurrentUser(User currentUser) {
		CurrentUser = currentUser;
	}
	public boolean isLogg() {
		return CurrentUser !=null;
	}
	

}
