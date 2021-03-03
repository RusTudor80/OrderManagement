package bussines;

import java.sql.SQLException;

import com.itextpdf.text.pdf.PdfPTable;

import data.ClientData;
import model.Client;

public class ClientBussines {
	private ClientData dao;
	
	public ClientBussines() { dao = new ClientData(); }
	
	public void insertClient(Client client) {
		try {
			dao.insertClient(client);
		} catch (SQLException e) {
			System.out.println("Eroare, clientul nu a fost inserat.");
			return;
		}

		System.out.println("Clientul a fost inserat cu succes.");
	}

	
	public void deleteClient(String name, String adress) {
		try {
			dao.deleteClient(name, adress);
		} catch (SQLException e) {
			System.out.println("Eroare, clientul nu a fost sters.");
			return;
		}

		System.out.println("Clientul a fost sters cu succes.");
	}
	
	
	public PdfPTable selectClients() {
		return dao.selectClients();
	}
}
