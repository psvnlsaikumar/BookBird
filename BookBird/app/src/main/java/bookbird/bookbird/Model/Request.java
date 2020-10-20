package bookbird.bookbird.Model;

import java.util.List;

/**
 * Created by Sai on 18/09/2017.
 */

public class Request {
	private String phone;
	private String name;
	private String address;
	private String total;
	private String status;
	private List<Order> books;
	
	
	public Request() {
	}
	

	
	public Request(String phone, String name, String address, String total, List<Order> books) {
		this.phone = phone;
		this.name = name;
		this.address = address;
		this.total = total;
		this.books = books;
		this.status = "0"; // Default is 0 , 0 when placed order,1:shipping,2:shipped
		
		
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getTotal() {
		return total;
	}
	
	public void setTotal(String total) {
		this.total = total;
	}
	
	public List<Order> getBooks() {
		return books;
	}
	
	public void setBooks(List<Order> books) {
		this.books = books;
	}
}
