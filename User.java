
public class User {
	public User() {
		
	}

	private User_Type type;
	private String userName;
	private String passWord;
	private String firstName;
	private String lastName;
	
	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	User(String username, String password, String firstname, String lastname, User_Type user_type){
		userName = username;
		passWord = password;
		firstName = firstname;
		lastName = lastname;
		type = user_type;
	}
}
