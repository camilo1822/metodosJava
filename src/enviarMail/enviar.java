package enviarMail;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.DataBufferInt;
import java.awt.image.ImageObserver;
import java.awt.image.WritableRaster;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.imageio.ImageIO;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.ServletContext;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

public class enviar{

	public static void main(String[] args) throws IOException, SQLException, JavaLayerException, ParseException
    {
		//enviarMail();
		//crearArchivo();
		//pegarDB();
		//sonidos();
		//File file1=new File("C:\\comparar_nequi\\punto.jpg");
		//File file2=new File("C:\\comparar_nequi\\punto2.jpg");
		//File file1=new File("C:\\Users\\juan.arboleda\\Pictures\\81440.jpg");
		//File file2=new File("C:\\Users\\juan.arboleda\\Pictures\\dos.png");
		/*if(IsPngEquals(file1, file2)){
			System.out.println("igual");
		}else{
			System.out.println("diferente");
		}*/
		enviarMailVarios();
		//enviarPdf();
		//quitarEsoacios();
    }
	
	public static void enviarMail(){
		 try
	        {
	            Properties props = new Properties();
	            props.put("mail.smtp.host", "smtp.gmail.com");
	            props.setProperty("mail.smtp.starttls.enable", "true");
	            props.setProperty("mail.smtp.port", "587");
	            props.setProperty("mail.smtp.user", "chuidiang@gmail.com");
	            props.setProperty("mail.smtp.auth", "true");

	            Session session = Session.getDefaultInstance(props, null);
	            BodyPart texto = new MimeBodyPart();
	            String url="http://www.google.com";
	            texto.setText("aqui \n "+url);

	            BodyPart adjunto = new MimeBodyPart();
	            adjunto.setDataHandler(
	                new DataHandler(new FileDataSource("C:/Users/juan.arboleda/Pictures/39268.png")));
	            adjunto.setFileName("39268.png");

	            MimeMultipart multiParte = new MimeMultipart();
	            multiParte.addBodyPart(texto);
	            multiParte.addBodyPart(adjunto);

	            MimeMessage message = new MimeMessage(session);
	            message.setFrom(new InternetAddress("juan.arboleda@pragma.com.co"));
	            message.addRecipient(
	                Message.RecipientType.TO,
	                new InternetAddress("juan.arboleda@pragma.com.co"));
	            message.setSubject("Pruebas");
	            message.setContent(multiParte);

	            Transport t = session.getTransport("smtp");
	            t.connect("automat.sqa@gmail.com", "Sq@2017auto");
	            t.sendMessage(message, message.getAllRecipients());
	            t.close();
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }
	}
	
	public static void enviarMailVarios(){
		 try
	        {
			 	String[] destinatarios = new String[3];
			 	destinatarios[0]="juan.arboleda@pragma.com.co";
			 	destinatarios[1]="juanc.arboleda@udea.edu.com";
			 	destinatarios[2]="christian.fontalvo@sqasa.com";
	            Properties props = new Properties();
	            props.put("mail.smtp.host", "smtp.gmail.com");
	            props.setProperty("mail.smtp.starttls.enable", "true");
	            props.setProperty("mail.smtp.port", "587");
	            props.setProperty("mail.smtp.user", "chuidiang@gmail.com");
	            props.setProperty("mail.smtp.auth", "true");

	            Session session = Session.getDefaultInstance(props, null);
	            BodyPart texto = new MimeBodyPart();
	            texto.setText("Texto del mensaje");

	            BodyPart adjunto = new MimeBodyPart();
	            adjunto.setDataHandler(
	                new DataHandler(new FileDataSource("C:/Users/juan.arboleda/Pictures/39268.png")));
	            adjunto.setFileName("39268.png");

	            MimeMultipart multiParte = new MimeMultipart();
	            multiParte.addBodyPart(texto);
	            multiParte.addBodyPart(adjunto);

	            MimeMessage message = new MimeMessage(session);
	            //message.setFrom(new InternetAddress("juan.arboleda@pragma.com.co"));
	            Address[] destinos = new Address[destinatarios.length];
	            for(int i=0;i<destinos.length;i++){
	                destinos[i]=new InternetAddress(destinatarios[i]);
	            }
	            message.addRecipients(Message.RecipientType.TO,destinos);
	            message.setSubject("Pruebas");
	            message.setContent(multiParte);

	            Transport t = session.getTransport("smtp");
	            t.connect("automat.sqa@gmail.com", "Sq@2017auto");
	            t.sendMessage(message, message.getAllRecipients());
	            t.close();
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }
	}
	
	public static void enviarMailVariosA(String a){
		 try
	        {
			 	String[] destinatarios = new String[3];
			 	destinatarios[0]="juan.arboleda@pragma.com.co";
			 	destinatarios[1]="juanc.arboleda@udea.edu.com";
			 	destinatarios[2]="christian.fontalvo@sqasa.com";
	            Properties props = new Properties();
	            props.put("mail.smtp.host", "smtp.gmail.com");
	            props.setProperty("mail.smtp.starttls.enable", "true");
	            props.setProperty("mail.smtp.port", "587");
	            props.setProperty("mail.smtp.user", "chuidiang@gmail.com");
	            props.setProperty("mail.smtp.auth", "true");

	            Session session = Session.getDefaultInstance(props, null);
	            BodyPart texto = new MimeBodyPart();
	            texto.setText("Texto del mensaje");

	            BodyPart adjunto = new MimeBodyPart();
	            adjunto.setDataHandler(
	                new DataHandler(new FileDataSource(a)));
	            adjunto.setFileName("39268.png");

	            MimeMultipart multiParte = new MimeMultipart();
	            multiParte.addBodyPart(texto);
	            multiParte.addBodyPart(adjunto);

	            MimeMessage message = new MimeMessage(session);
	            //message.setFrom(new InternetAddress("juan.arboleda@pragma.com.co"));
	            Address[] destinos = new Address[destinatarios.length];
	            for(int i=0;i<destinos.length;i++){
	                destinos[i]=new InternetAddress(destinatarios[i]);
	            }
	            message.addRecipients(Message.RecipientType.TO,destinos);
	            message.setSubject("Pruebas");
	            message.setContent(multiParte);

	            Transport t = session.getTransport("smtp");
	            t.connect("automat.sqa@gmail.com", "Sq@2017auto");
	            t.sendMessage(message, message.getAllRecipients());
	            t.close();
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }
	}
	
	public static void crearArchivo() throws IOException{
		String saludo="Hola";
		File archivo=new File("C:/Users/juan.arboleda/Pictures/texto.txt");
		FileWriter escribir=new FileWriter(archivo,true);
		escribir.write(saludo);
		escribir.close();
	}
	
	public static void pegarDB() throws SQLException{
		Connection con = null;
		try {
			System.out.println("getCon");
			con = DriverManager.getConnection("jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS_LIST=(ADDRESS=(PROTOCOL=TCP)(HOST=localhost)(PORT=1521)))(CONNECT_DATA=(SERVER=DEDICATED)(SID=xe)))","camilo","root");		
			Statement stmt = con.createStatement();
	//	    ResultSet rset = 
		             // stmt.executeQuery("INSERT INTO PRUEBA_NEQUI (INTENTOS, OTRA) values (95,14)");
		    
		    ResultSet rset = 
		              stmt.executeQuery("update PRUEBA_NEQUI set INTENTOS=88");
			while (rset.next())
		         System.out.println (rset.getString(1));   // Print col 1
		    stmt.close();
		} catch (SQLException ex) {
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		} 

	}
	
	public static void sonidos() throws FileNotFoundException, JavaLayerException{
		Player apl = new Player(new FileInputStream("C:/Users/juan.arboleda/Downloads/grillos01_mp3.mp3"));
	      apl.play();     
	}
	
	/*public static boolean IsPngEquals(File pngFile, File file) throws IOException {	
		String Str_Ruta_Evidencias = "C:\\evidencias_nequi\\";
		
		BufferedImage imageA = ImageIO.read(pngFile);
	    BufferedImage imageB = ImageIO.read(file);
	    //checkIdentical(imageA,imageB);
	    System.out.println(imageA);
	    System.out.println(imageB);
	    
	    BufferedImage recorte = ((BufferedImage) imageA).getSubimage(0,100,imageA.getWidth(),imageA.getHeight()-100);
	    System.out.println(recorte);
	    File img1 = new File("C:\\comparar_nequi\\punto.jpg");
	    ImageIO.write(recorte, "jpg", img1);
	    BufferedImage recorte2 = ((BufferedImage) imageB).getSubimage(0,100,imageB.getWidth(),imageB.getHeight()-100);
	    File img2 = new File("C:\\comparar_nequi\\punto2.jpg");
	    ImageIO.write(recorte2, "jpg", img2);
	    File file1=new File("C:\\comparar_nequi\\punto.jpg");
		File file2=new File("C:\\comparar_nequi\\punto2.jpg");
		BufferedImage imageC = ImageIO.read(file1);
	    BufferedImage imageD = ImageIO.read(file2);
 
//	    DataBufferByte dataBufferA = (DataBufferByte)recorte.getRaster().getDataBuffer();
//	    DataBufferByte dataBufferB = (DataBufferByte)recorte2.getRaster().getDataBuffer();
	    DataBufferInt dataBufferA = (DataBufferInt) imageC.getRaster().getDataBuffer();
	    DataBufferInt dataBufferB = (DataBufferInt) imageD.getRaster().getDataBuffer();
	    //WritableRaster raster = imageC.getRaster();
	    //DataBufferByte data   = (DataBufferByte) raster.getDataBuffer();

	    //System.out.println("a    "+data);
	   // System.out.println(dataBufferB.getData());
	    System.out.println(dataBufferA.getNumBanks());
	    System.out.println(dataBufferB.getNumBanks());
	    
	    
	    
	    
	    
	    BufferedImage bi=ImageIO.read(new File("C:\\comparar_nequi\\punto.jpg"));
	    int[] pixel;

	    for (int y = 0; y < bi.getHeight(); y++) {
	        for (int x = 0; x < bi.getWidth(); x++) {
	            pixel = bi.getRaster().getPixel(x, y, new int[100]);
	            Color c = new Color(pixel);
	            System.out.println(pixel[0] + " - " + pixel[1] + " - " + pixel[2] + " - " + (bi.getWidth() * y + x));
	        }
	    }
	    
	    
	    
	    
	    
	    
	    
 
	    if (dataBufferA.getNumBanks() != dataBufferB.getNumBanks()) {
	    	System.out.println(dataBufferA.getNumBanks());
	    	System.out.println(dataBufferB.getNumBanks());
	        return false;
	    }
 
	    for (int bank = 0; bank < dataBufferA.getNumBanks(); bank++) {
	    	System.out.println(dataBufferA.getData(bank));
        	System.out.println(dataBufferB.getData(bank));
	        if (!Arrays.equals(dataBufferA.getData(bank), dataBufferB.getData(bank))) {
	        	System.out.println(dataBufferA.getData(bank));
	        	System.out.println(dataBufferB.getData(bank));
	            return false;
	        }
	    }
 
	    return true;
	}*/
	
	public static void enviarPdf(){
		try{ 
	            System.out.println("Generating PDF...");
	           
	            JasperReport jasperReport = 
	            JasperCompileManager.compileReport("C:/Users/juan.arboleda/Pictures/hellojasper.jrxml");      
	            System.out.println("Gasd..");
	            String titulo = "Prueba";
	            String generado = "C:/Users/juan.arboleda/Pictures/oe.pdf";
	            Map<String, Object> parameters = new HashMap<String, Object>();
	            JasperPrint jasperPrint = 
	  	            	JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
	  	                    JasperExportManager.exportReportToPdfFile(
	  	                            jasperPrint, generado);
	                              
	            System.out.println("HelloJasper.pdf has been generated!");
	            //String ao=
	            enviarMailVariosA(generado);
	        }
	        catch (JRException e){
	            e.printStackTrace();
	        }
	}
	
	public static void quitarEsoacios(){
		String uno = " Meta Vacaciones";
		System.out.println(uno);
		String dos = uno.replaceAll(" ","");
		System.out.println(dos);
	}
	
}
