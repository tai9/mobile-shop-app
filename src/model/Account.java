package model;

public class Account {
	private String username;
	private String password;
	private boolean role;

	public Account() {
	}
	
	public Account(String username, String password, boolean role) {
		super();
		this.username = username;
		this.password = password;
		this.role = role;
	}

	public Account(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isRole() {
		return role;
	}

	public void setRole(boolean role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return username + " " + password + " "+ role;
	}
}
