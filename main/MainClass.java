package main;

import com.itextpdf.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import bussines.*;
import data.*;
import model.*;

@SuppressWarnings("unused")
public class MainClass {
	protected static final Logger LOGGER = Logger.getLogger(MainClass.class.getName());

	public static void main(String[] args) {
		try {
			System.setIn(new FileInputStream("figure1.txt"));
		} catch (Exception e) {
			System.out.println("Eroare, nu s-a gasit fisierul.");
		}
		
		Scanner scanner = new Scanner(System.in);
		
		
		ClientBussines client_content = new ClientBussines();			
        ProductBussines product_content = new ProductBussines();		
        My_orderBussines  order_content = new My_orderBussines();	
		
		String buffer = "";
		int pdf_id = 1;		
        int order_id = 1;	

		while (scanner.hasNextLine()) {
			buffer = scanner.nextLine();
			System.out.println(buffer);
			
			try {
				if (buffer.contains("Insert client")) {
                    buffer = buffer.replaceAll("[A-Za-z ]*: ", "");
                    System.out.println(buffer);
                    String[] bufferSplit = buffer.split(", ");
                    if (bufferSplit.length != 2) {
                        continue;
                    }
                    client_content.insertClient(new Client(bufferSplit[0], bufferSplit[1]));
                } else if (buffer.contains("Insert product")) {
                    buffer = buffer.replaceAll("[A-Za-z ]*: ", "");
                    System.out.println(buffer);
                    String[] bufferSplit = buffer.split(", ");
                    if (bufferSplit.length != 3) {
                        continue;
                    }
                    product_content.insertProduct(new Product(bufferSplit[0], Integer.parseInt(bufferSplit[1]), Double.parseDouble(bufferSplit[2])));
                } else if (buffer.contains("Delete client")) {
                    buffer = buffer.replaceAll("[A-Za-z ]*: ", "");
                    System.out.println(buffer);
                    String[] bufferSplit = buffer.split(", ");
                    if (bufferSplit.length != 2) {
                    	continue;
                    }
                    client_content.deleteClient(bufferSplit[0], bufferSplit[1]);
                } else if (buffer.contains("Delete Product")) {
                    buffer = buffer.replaceAll("[A-Za-z ]*: ", "");
                    System.out.println(buffer);
                    product_content.deleteProduct(buffer);
                } else if (buffer.contains("Order: ")) {
                    buffer = buffer.replaceAll("[A-Za-z ]*: ", "");
                    System.out.println(buffer);
                    String[] bufferSplit = buffer.split(", ");

                    if (bufferSplit.length != 3) {
                        continue;
                    }

                    Document document = new Document();
                    PdfWriter.getInstance(document, new FileOutputStream("order" + order_id + ".pdf"));
                    
                    PdfPTable pdfTable = new PdfPTable(3);
                    pdfTable.addCell("client name");
                    pdfTable.addCell("product name");
                    pdfTable.addCell("product quantity");
                    pdfTable.addCell(bufferSplit[0]);
                    pdfTable.addCell(bufferSplit[1]);
                    pdfTable.addCell(bufferSplit[2]);
                    
                    order_id++;
                    
                    document.open();
                    document.add(pdfTable);
                    document.close();

                    order_content.insertOrder(new My_order(bufferSplit[0], bufferSplit[1], Integer.parseInt(bufferSplit[2])));
                } else if (buffer.contains("Report ")) {
                    buffer = buffer.replace("Report ", "");
                    
                    Document document = new Document();
                    PdfWriter.getInstance(document, new FileOutputStream("report" + pdf_id + ".pdf"));
                    
                    pdf_id++;
                    
                    document.open();
                    if (buffer.equals("client")) {
                        document.add(client_content.selectClients());
                    } else if (buffer.equals("product")) {
                        document.add(product_content.selectProducts());
                    } else if (buffer.equals("order")) {
                        document.add(order_content.selectOrders());
                    }
                    document.close();
                }
        } catch (Exception e) {
            LOGGER.log(Level.INFO, e.getMessage());
        }
		}
		scanner.close();
	}

	public static void createDatabase() {
		WarehouseData whDB = new WarehouseData();

		try {
			whDB.createDatabase();
		} catch (SQLException e) {
			System.out.println("Nu s-a putut crea baza de date.");
		}

		System.out.println("Baza de date a fost creata cu succes.");
	}
}
