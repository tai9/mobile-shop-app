package gui.customer;

import java.util.EventObject;

import model.Customer;

public class CustomerFormEvent extends EventObject {
	private int id;
	private String name;
	private int birthYear;
	private boolean sex;
	private String phoneNumber;
	private String address;
	private String moreInfo;

	public CustomerFormEvent(Object source, int id, String name, int birthYear, boolean sex, String phoneNumber,
			String address, String moreInfo) {
		super(source);
		this.id = id;
		this.name = name;
		this.birthYear = birthYear;
		this.sex = sex;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.moreInfo = moreInfo;
	}

	public CustomerFormEvent(Object source, String name, int birthYear, boolean sex, String phoneNumber, String address,
			String moreInfo) {
		super(source);
		this.name = name;
		this.birthYear = birthYear;
		this.sex = sex;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.moreInfo = moreInfo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getBirthYear() {
		return birthYear;
	}

	public void setBirthYear(int birthYear) {
		this.birthYear = birthYear;
	}

	public boolean isSex() {
		return sex;
	}

	public void setSex(boolean sex) {
		this.sex = sex;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMoreInfo() {
		return moreInfo;
	}

	public void setMoreInfo(String moreInfo) {
		this.moreInfo = moreInfo;
	}
	
	public Customer getCustomer() {
		return new Customer(id, name, birthYear, sex, phoneNumber, address, moreInfo);
	}

	@Override
	public String toString() {
		return id + " " + name + " " + birthYear + " " + sex + " " + phoneNumber + " " + address + " " + moreInfo;
	}
}
