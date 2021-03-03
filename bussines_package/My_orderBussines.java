package bussines;

import java.sql.SQLException;

import com.itextpdf.text.pdf.PdfPTable;

import data.My_orderData;
import model.My_order;

public class My_orderBussines {
	private My_orderData dao;
	
	public My_orderBussines() { dao = new My_orderData(); }
	
	
	public void insertOrder(My_order order) {
		try {
			dao.insertOrder(order);
		} catch (SQLException e) {
			System.out.println("Eroare, comanda nu a fost inserata.");
			return;
		}

		System.out.println("Comanda a fost inserata cu succes.");
	}
	
	public PdfPTable selectOrders() {
		return dao.selectOrders();
	}
}
