package model;

public class My_order {
	int id;
	String client_name;
	String product_name;
	int product_quantity;
	
	public My_order(String client_name, String product_name, int product_quantity) {
		this.client_name = client_name;
		this.product_name = product_name;
		this.product_quantity = product_quantity;
	}
	
	public My_order(int id, String client_name, String product_name, int product_quantity) {
		this.id = id;
		this.client_name = client_name;
		this.product_name = product_name;
		this.product_quantity = product_quantity;
	}

	public String getId() {
		Integer temp = this.id;
		return temp.toString();
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getClient_name() {
		return client_name;
	}

	public void setClient_name(String client_name) {
		this.client_name = client_name;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public String getProduct_quantity() {
		Integer temp = this.product_quantity;
		return temp.toString();
	}

	public void setProduct_quantity(int product_quantity) {
		this.product_quantity = product_quantity;
	}
}
