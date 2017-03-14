package enviarMail;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
		File file1=new File("C:\\evidencias_nequi\\Screenshot_14_3_2017 02_33_56 033.jpg");
		File file2=new File("C:\\evidencias_nequi\\dos.jpg");
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
	    BufferedImage imageA = ImageIO.read(pngFile);
 
	    BufferedImage imageB = ImageIO.read(file);
 
	    DataBufferByte dataBufferA = (DataBufferByte)imageA.getRaster().getDataBuffer();
	    DataBufferByte dataBufferB = (DataBufferByte)imageB.getRaster().getDataBuffer();
 
	    if (dataBufferA.getNumBanks() != dataBufferB.getNumBanks()) {
	        return false;
	    }
 
	    for (int bank = 0; bank < dataBufferA.getNumBanks(); bank++) {
	        if (!Arrays.equals(dataBufferA.getData(bank), dataBufferB.getData(bank))) {
	            return false;
	        }
	    }
 
	    return true;
	}
}
