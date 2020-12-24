package gui.account;

import java.util.EventObject;

public class AccountEvent extends EventObject {

	private String username;
	private String password;
	private boolean role;
	
	public AccountEvent(Object source) {
		super(source);
	}

	public AccountEvent(Object source, String username, String password, boolean role) {
		super(source);
		this.username = username;
		this.password = password;
		this.role = role;
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

}
