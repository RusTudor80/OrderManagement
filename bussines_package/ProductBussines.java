package bussines;

import java.sql.SQLException;

import com.itextpdf.text.pdf.PdfPTable;

import data.ProductData;
import model.Product;

public class ProductBussines {
	private ProductData dao;
	
	public ProductBussines() { dao = new ProductData(); }
	
	
	public void insertProduct(Product product) {
		try {
			dao.insertProduct(product);
		} catch (SQLException e) {
			System.out.println("Eroare, produsul nu a fost inserat.");
			return;
		}

		System.out.println("Produsul a fost inserat cu succes.");
	}

	public void deleteProduct(String name) {
		try {
			dao.deleteProduct(name);
		} catch (SQLException e) {
			System.out.println("Eroare, produsul nu a fost sters.");
			return;
		}

		System.out.println("Produsul a fost sters cu succes.");
	}
	
	public PdfPTable selectProducts() {
		return dao.selectProducts();
	}
}
