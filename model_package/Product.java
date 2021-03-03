package model;

public class Product {
	String name;
	int quantity;
	double price;
	
	public Product(String name, int quantity, double price) {
		this.name = name;
		this.quantity = quantity;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getQuantity() {
		Integer temp = this.quantity;
		return temp.toString();
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getPrice() {
		Double temp = this.price;
		return temp.toString();
	}

	public void setPrice(int price) {
		this.price = price;
	}
}
