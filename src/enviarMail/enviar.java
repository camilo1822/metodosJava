package enviarMail;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.DataBufferInt;
import java.awt.image.ImageObserver;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;


import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.imageio.ImageIO;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class enviar{

	public static void main(String[] args) throws IOException, SQLException, JavaLayerException
    {
		//enviarMail();
		//crearArchivo();
		//pegarDB();
		//sonidos();
		File file1=new File("C:\\evidencias_nequi\\dos.jpg");
		File file2=new File("C:\\evidencias_nequi\\tres.jpg");
		//File file1=new File("C:\\Users\\juan.arboleda\\Pictures\\81440.jpg");
		//File file2=new File("C:\\Users\\juan.arboleda\\Pictures\\dos.png");
		if(IsPngEquals(file1, file2)){
			System.out.println("igual");
		}else{
			System.out.println("diferente");
		}
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
	            texto.setText("Texto del mensaje");

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
	
	public static boolean IsPngEquals(File pngFile, File file) throws IOException {	
		String Str_Ruta_Evidencias = "C:\\evidencias_nequi\\";
		
		BufferedImage imageA = ImageIO.read(pngFile);
	    BufferedImage imageB = ImageIO.read(file);
	    //checkIdentical(imageA,imageB);
	    System.out.println(imageA);
	    System.out.println(imageB);
	    
	    BufferedImage recorte = ((BufferedImage) imageA).getSubimage(0,100,imageA.getWidth(),imageA.getHeight()-100);
	    System.out.println(recorte);
	    File img1 = new File("C:\\evidencias_nequi\\punto.jpg");
	    ImageIO.write(recorte, "jpg", img1);
	    BufferedImage recorte2 = ((BufferedImage) imageB).getSubimage(0,100,imageB.getWidth(),imageB.getHeight()-100);
	    File img2 = new File("C:\\evidencias_nequi\\punto2.jpg");
	    ImageIO.write(recorte2, "jpg", img2);
	    File file1=new File("C:\\evidencias_nequi\\punto.jpg");
		File file2=new File("C:\\evidencias_nequi\\punto2.jpg");
		BufferedImage imageC = ImageIO.read(file1);
	    BufferedImage imageD = ImageIO.read(file2);
 
//	    DataBufferByte dataBufferA = (DataBufferByte)recorte.getRaster().getDataBuffer();
//	    DataBufferByte dataBufferB = (DataBufferByte)recorte2.getRaster().getDataBuffer();
	    DataBufferInt dataBufferA = (DataBufferInt) imageC.getRaster().getDataBuffer();
	    DataBufferInt dataBufferB = (DataBufferInt) imageD.getRaster().getDataBuffer();

	    System.out.println(dataBufferA);
	    System.out.println(dataBufferB);
	    System.out.println(dataBufferA.getNumBanks());
	    System.out.println(dataBufferB.getNumBanks());
 
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
	}
	

}
