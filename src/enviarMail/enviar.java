package enviarMail;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xmlbeans.impl.util.Base64;

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
		//enviarMailVarios();
		//enviarPdf();
		//quitarEsoacios();
		modifyExcel();
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
			//generar excel
			JRXlsxExporter exporter = new JRXlsxExporter();
			exporter.setParameter(JRXlsExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRXlsExporterParameter.OUTPUT_FILE_NAME,
					"C:/Users/juan.arboleda/Downloads/documentos/oe2.xlsx");
			exporter.exportReport();
			//Fin excel
	                              
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
	
	/**
     * Recive pdf en base64 y lo envia
     * 
     * @param a
     */
    public static void enviarMailVariosPdfEncriiptado(String a) {
        try {
            String[] destinatarios = new String[1];
            destinatarios[0] = "juan.arboleda@pragma.com.co";

            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.setProperty("mail.smtp.starttls.enable", "true");
            props.setProperty("mail.smtp.port", "465");
            props.setProperty("mail.smtp.auth", "true");
            props.setProperty("mail.smtp.socketFactory.port", "465");
            props.setProperty("mail.smtp.socketFactory.class",
                    "javax.net.ssl.SSLSocketFactory");

            Session session = Session.getDefaultInstance(props, null);
            BodyPart adjunto = new MimeBodyPart();

            byte[] faceByte = a.getBytes();
            faceByte = Base64.decode(faceByte);
            // OutputStream out = new FileOutputStream(
            // "/home/cristian/Downloads/sublime_text_3/out.pdf");
            // out.write(faceByte);
            // out.close();

            DataSource ds;
            ds = new ByteArrayDataSource(faceByte, "application/pdf");

            adjunto.setDataHandler(new DataHandler(ds));
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            MimeMultipart multiParte = new MimeMultipart();
            multiParte.addBodyPart(adjunto);
            multiParte.addBodyPart(messageBodyPart);
            messageBodyPart.setText(
                    "<html> <head> <meta charset=\"utf-8\"> <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no\"> <title>NEQUI</title> </head><body> <!--[if mso]> <table width=\"768\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" align=\"center\"> <tr> <td> <![endif]--> <table width=\"100%\" align=\"center\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style=\"max-width:768px;\"> <tr> <td> <table width=\"100%\" align=\"center\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style=\"max-width:670px; border:solid 1px #dcdcdc;\"> <tr> <td style=\"text-align: center;\"> <img src=\"http://s3.amazonaws.com/mail-nequi-co/img/ico_bloqueo.png\" style=\" width:100%; max-width:100px; padding:20px 0px 0px; border: 0;\" alt=\"Bloqueo cuenta\"> </td> </tr> <tr> <td style=\"padding: 0px 10%\"> <table width=\"100%\" align=\"center\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" style=\"max-width:500px; font-family: Lucida Sans Unicode, Lucida Grande, sans-serif; font-size:16px; line-height: 1.3; color:#8e8e8e; text-align:center;\"> <tr> <td width=\"100%\" style=\"padding:20px 10% 15px;\"> <span style=\"font-size: 20px; font-weight: bold; color:#210049\">Tu seguridad es lo más</span> </td> </tr> <tr> <td width=\"100%\" style=\"padding:0px 0px 30px;\"> Hola #NOMBRE. Bloqueamos tu cuenta por una solicitud que parece que tu realizaste desde nuestra página web. </td> </tr> <tr> <td width=\"100%\" style=\"padding:30px 0px 30px; border-top: dashed 1px #dcdcdc; border-bottom: dashed 1px #dcdcdc \"> Si no fuiste tú, fue un error o tienes dudas, escríbenos a <span style=\"color:#ff2f73\"><a href=\"mailto:#NEQUIMAIL\" target=\"_blank\" style=\"text-decoration:none; color:#ff2f73\">#NEQUIMAIL</a></span> o llámanos al #NEQUIWPP. Estamos por aquí de lunes a viernes de 8 a.m. a 6 p.m. <br> </td> </tr><tr> <td width=\"100%\" style=\"padding:20px 10% 10px;\"> Recuerda seguirnos en Facebook e Instagram, siempre tenemos cosas nuevas.<br><br>¡Hasta pronto! </tr><tr> <td> <table align=\"center\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" style=\"padding:0px 100px 45px\"> <tr> <td><a href=\"https://www.facebook.com/appnequi\" target=\"_blank\"><img src=\"http://s3.amazonaws.com/mail-nequi-co/img/ico_facebook.png\" alt=\"facebook\" style=\"height: 40px; width: 40px; padding:0px 10px 0px\"/></a></td> <td><a href=\"https://www.instagram.com/nequi_\" target=\"_blank\"><img src=\"http://s3.amazonaws.com/mail-nequi-co/img/ico_instagram.png\" alt=\"instagram\" style=\"height: 40px; width: 40px; padding:0px 10px 0px\"/></a></td> </tr> </table> </td> </tr> <tr> <td width=\"100%\" style=\"padding:0px 0px 30px\"> ¿Necesitas ayuda?<br> <span style=\"color:#ff2f73\"><a href=\"http://www.nequi.co/ayuda\" target=\"_blank\" style=\"text-decoration:none; color:#2fcad7\">www.nequi.co/ayuda</a></span><br> </td> </tr> </table> </td> </tr> <tr> <td style=\"text-align: center; max-width:670px;\"><a href=\"http://www.nequi.com/\" target=\"_blank\"><img src=\"http://s3.amazonaws.com/mail-nequi-co/img/footer_nequi.png\" alt=\" NEQUI\" style=\"width:100%;\"></a></td> </tr> </table> </td> </tr> </table><table width=\"100%\" align=\"center\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" style=\"max-width:670px; font-family: Lucida Sans Unicode, Lucida Grande, sans-serif; font-size:11px; text-align: center; padding-top:15px\"> <tr style=\"color:#B0A4BD\"> <td>Copyright ©2016, All rights reserved</td> </tr> </table><!--[if mso]> </td> </tr> </table> <![endif]--></body> </html>",
                    "ISO-8859-1");
            messageBodyPart.setHeader("Content-Type",
                    "text/html; charset=\"ISO-8859-1\"");
            messageBodyPart.setHeader("Content-Transfer-Encoding",
                    "quoted-printable");

            MimeMessage message = new MimeMessage(session);
            Address[] destinos = new Address[destinatarios.length];
            for (int i = 0; i < destinos.length; i++) {
                destinos[i] = new InternetAddress(destinatarios[i]);
            }
            message.addRecipients(Message.RecipientType.TO, destinos);
            message.setSubject("Puerto seguro");
            message.setContent(multiParte);

            Transport t = session.getTransport("smtp");
            t.connect("automat.sqa@gmail.com", "Sq@2017auto");
            t.sendMessage(message, message.getAllRecipients());
            t.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void enviarPdfEncriptado() {
        String a = "JVBERi0xLjUKJeLjz9MKMyAwIG9iago8PC9UeXBlL1hPYmplY3QvQ29sb3JTcGFjZS9EZXZpY2VS R0IvU3VidHlwZS9JbWFnZS9CaXRzUGVyQ29tcG9uZW50IDgvV2lkdGggMTEwMi9MZW5ndGggMjc5 NzcvSGVpZ2h0IDI3OS9GaWx0ZXIvRENURGVjb2RlPj5zdHJlYW0K/9j/4AAQSkZJRgABAgAAZABk AAD/7AARRHVja3kAAQAEAAAAUAAA/+4ADkFkb2JlAGTAAAAAAf/bAIQAAgICAgICAgICAgMCAgID BAMCAgMEBQQEBAQEBQYFBQUFBQUGBgcHCAcHBgkJCgoJCQwMDAwMDAwMDAwMDAwMDAEDAwMFBAUJ BgYJDQsJCw0PDg4ODg8PDAwMDAwPDwwMDAwMDA8MDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwM /8AAEQgBFwROAwERAAIRAQMRAf/EAMwAAQABBQEBAQEAAAAAAAAAAAAHBQYICQoEAgMBAQEAAQUB AQAAAAAAAAAAAAAAAgEDBAUGBwgQAAEEAgECAwQFBggJCAkFAAABAgMEBQYREgchEwgxQSIUUWEy FQlCUiN1djhxYrS1FjYXN4GhcjOzJMWGV/CRsVOVtkgZ4YJDc9PU1VamktJjVCYRAQACAAMEBAsG BAYDAAMAAAABAhEDBCExEgVBUXEGYYGRobHBIjITMwfw0UJSYnLhgiMUkqKywkMV8VM0YyQW/9oA DAMBAAIRAxEAPwDf4AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAa6fXR2Gk2HFs7xatS8zL6/XSDdKkTeX2KE f+bto1qeLq/Ko9f+r4XlEjMrT5mHsy9w+kXfGNLm/wDV6i3sXnHKmfw3nfTsv+H9fhu1LmY+kAAA AAAAAAAAAAAAAAAAAP4BE+29rMbmPMu4Ty8VkV5c6BE4ryr9bU+wv1tTj6veZmTq5rstth5F3v8A pRpOZ8WfocMnO38OH9O89ke5PhrGHXXHax1yuHyWDtvpZSo+pYb4o16eDk9nUxyeDk+tFNnS9bxj Evm/m3JtZynPnI1eXNLx17pjrrO60eGJmFMJtYAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAADt0OHA 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 tHhlZvV/x28Ue7PhrGHXXpa8tq1DZNJysuF2jEz4nIR8q1kqfBIxF464pG8tkaqp9pqqh2Wn1OXq K8WXOMPBeZ8p1XLc6cnU0mlo690+GJ3THhhbRfa4AAAAAAAAAAAADfAeSvtsAAAAAAAAAAAAAAAA AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAFrbdpWsb1in4bacRDlabuViV 6cSwvVOOuGVvDmO+tq/w+BkabVZmntxZc4T9t7Wc15PpOaZM5OppF69HXHhrO+J7Gu3ux6Ydn0r5 nM6l5+16yzl8kbGc36jE5X9LGxP0jWp7XsT61a1PE7Pl3PsvUYVzPZt5p+7snyvAu9H031fLcc7S 45uT2e3WPDEe9H6q+OsQxaN+80AAAAAAAAAAABvgPJX22AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAxt7semzU+4PzOXwiR6ttcirI+5Cz/VbT1/8A 7ELeOFVfy2ePvcjjd8u55m6bCt/ap547J9Xoeed6Pp5o+bY5uThlZ89MR7Np/VXr/VG3r4muXd+3 22dvMq7E7TiZKEiq75S4nx1rLW/lwTJ8L08U5T2p7HIi+B22l1mVqa8WXOPpjth8/wDOeRazlGd8 LU0ms9E7628Nbbp9MdMRKyzJacAAAAAAAAAb4DyV9tgAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAFD2LWsDtuKsYXZMVXzGMsp+lq2G8oi+5zHJw5j k9zmqip7lLuTn3ybcVJwlhcw5dp9fkzk6ikXpPRPpjpifDGEw1+d2fSpm9d+aznb502xYRvMkuDd 8WQrt8VXy+ETz2p9Sdf1O8XHY8u7wUzcKZ3s26+ifu9DwnvR9Mc/R8WdoMczL38H/JXs/PH+bwTv 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 582iYwjGd89YaZ2Ow+m5nA3Y9ozWbwOjV7NTtlp1/wCT+Q16C2xInsrOgrRTzeXAny8K2JJFjhVW N+0rlXz5tE7IxnfPWKpuHauTYtmduOvb7sPbvYruIhwGdvYJMfK29jq009iuySLI07kbZa8lqZYZ WNRzfMei9SLwUpm8McMxExjjt/gLayHp7wLaOGx+n7ZsPb2vQ1etpOYfhpKssuUwFPzVr1rMt+ta e2WJ08yssRKyVqzSr1KrkVso1E4zNoiduPZIqOzdjsPl5Kb9X2jNdtmN1yvp2Yh135NG39fqLKtW i9blaysS1/PmSGaFWSMSWTh3KoraVz5jfETtx29YvjO9vdYz2iS9uZqb8frH3fXx2PgovWGWkyn0 LTkqyfErJKz4mPid48Oa1fEt1zJi3F0im6J27TT72fz2V2nKbzt+zsp18ztWXZThmdTx3nfJVIoM fXqwRxQrYmeiNj5V8sjlX4kRK5mZxRERGER0Cnzdntbk7fS9vI8hlK9JuWnz2KzscsKZGhlJMs/N w268iw+V1V7b0dGj43NVrUZI2RquR0vjTxcXi82ApLOx2Isa3s+Lzm0ZvO7PtuVo53Jdwpfk4MpD ksSsDsXPUZBWjqwpSWtGscaQqxyo5ZWyLJL11+POMTERERsw6Nu/yi7tD0GLSkz921n8ht2z7XdZ e2Xaso2tHYsvhgZWrxNipw14IooYY2tYyONE56nu6pHvc6GZmceGzCI6BIBbAAAAAAAAAAAAAAAA AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAp+Vy2MwWMv5nNZCvicRi4H2clkrcjYYIIY06nyS SPVGta1E5VVUrWs2nCN6kzhEz1bfI5/vVb67sNu+2WYtIjdsOKwyyVdd81XR0Ym8oj7D/Y6V8qtR VRvCI3pb1rx49xy/Lpocrhjbed89HZ4nlPM+62u70az42rtOTpqbMun/ACTHTaY3Um36sZiMK8Oz FrS23uHuG72ks7DmZrMcb+utQjXyq0Kp7FjiZw1FT85eXfSpPMzrZnvS7zknd3Q8mrEaTLitvzb7 z223+KMIjoh7MH3N2nDdEUlpMrUbwnkXOXuRP4svKP8A+dVT6jCzNLS/g7HsHIvqhznleFbZnxsu Pw5ntT4r+/2YzaI6mafpq9ZKdodwr5Zqz1MTkljg2rW5381bkCKvDmSonDJYuVWN7mpx4ovwuci6 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 eDK1XMJz6cEUrWMeKcOm3W03Ju7NeX5859s7Mzb8Hw6zeY9nLiceHZEY7YjGZ3qvv3bbWO5MWrw7 NFYkZqOfqbHivl5fK5tU0kYkc3gvXDKyVzJI18HtXhTDy82aY4dMYOnUGv2S0StT7sUG1bT6neWS 07boXzqqMZchfHNFT8E8ljpJprHCc/pppJOeXeEvj29n9O4fGB7IaNruf0XZaEV52V7fa4utYeSa wr2zwcKjLVxOlPOssSWdGyr4/wCsT+H6TwWz7WiY65x+326IFCy/ps7ZZvE75hr1XILU7ibDHsmc VlpWvjnbJ5stas5G/oq9h8lhZo/Hr+ZsJzxJ4SjU3iYnqjD7fbogX1mO3GLz1u5ZyuZzNyKxsGF2 SpjpLSOrUrWCdBJXjqRrGvlxSSV0fK3lepyuXlOS3GZMbojdMeUUvN9n9azN7O5ht/K4jP5jPUNm qZ+hNE2zjspjsezFwz00lhli4dVa6ORkzJGPa97XNVruCtc6YiI6MMPWPq72rr5LE4yrkd02W7sW EzL89g98lmp/elK8+vJTcsEbaaUWxrWmkhWH5Xyla5znMV6q8Rm4TsiMMMMOj7/OI3xHZC5czXea hteZzuQxm5XtWy2D3ae1S+81yOBbHZgtwRwQtrwOp2q8LmMWs2Jyt5dG9HP6rs5+EVwiNmOzt+/t F61eyGust5XJ5TP5/YcxnM7gNiy2WyE9ZJJbutujdR6Yq1aCCKP9E1HsijajvFfBznOW3OfO6IiI wmPKKpk+1VLIupXW7dstLYcRmclmcBtUdqvPdoJlepLFGFtutPXdU6HIxkMsL0ajWOT42NclIzcO iMMN3Z6x55+03VdpZyj3D2vE7bDjXYjL7fXfi5LeUp/My24o7kNnHTU/0Es8ixLDXjWNHuYzhjla V+NswwjDq27PPiKnrHavU9Qv61kMHHbgl1XX7euY5ks7pkkrXrVe5ZmnfIjpJZ5Jq6PdI53Kq5yr yq8kbZtrRMT0ziKbjezWr4fDdv8AC4u/l6MfbPULOlarfhstZaioWatOoszpEj8Z2MoxOY9EREdy vT7klOdMzMzhtnGft4wt9pYHZBuYw29bTrGYtYihhdhymPnozTZevjfMSvJcW/SttSdEmkRZoUjk d1eLvhj6EZ2zCYidvkHhy3Y/AZZ2x0n7LsNPUdyurkNx0KtNUTF5KeRI0s+a+So+5Ey15SefHBYj ZJy9VbzJJ1VjPmMNkYxun7bBNBYAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAB/9kKZW5kc3RyZWFtCmVuZG9iago1IDAgb2JqCjw8 L1R5cGUvWE9iamVjdC9Db2xvclNwYWNlL0RldmljZVJHQi9TdWJ0eXBlL0ltYWdlL0JpdHNQZXJD b21wb25lbnQgOC9XaWR0aCA1OC9MZW5ndGggOTMxMS9IZWlnaHQgNjUwL0ZpbHRlci9EQ1REZWNv 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 u3wjn1+7X8srxju22y5Tu6bu8zUuOr9ikruifZJPYF9ENhQTx6oX9+e/5Jr4herbb5VQ9X8cahqD iZFPVJTPSkp/mT6lPP8AChweFK6J5HQ9oGY23u3lTTq118MLpn4x+Tf3ET9hvNbfCJ6fc2XPJOgw GAwMNW/+d7D/AOM/6Vn/AEf/AH39Bn/W/wDT/V/J2Zu/Gef7Jz5Xx/ducwoYGGpv/Mdm/wDGf8tr /t3/AHn0T/r/AMn6v5s3fE8p6/K+P7/q3OYUMD//2QplbmRzdHJlYW0KZW5kb2JqCjYgMCBvYmoK PDwvVHlwZS9YT2JqZWN0L0NvbG9yU3BhY2UvRGV2aWNlUkdCL1N1YnR5cGUvSW1hZ2UvQml0c1Bl ckNvbXBvbmVudCA4L1dpZHRoIDY5L0xlbmd0aCA0NTYxL0hlaWdodCAxNTIvRmlsdGVyL0RDVERl Y29kZT4+c3RyZWFtCv/Y/+AAEEpGSUYAAQEBAGAAYAAA/9sAQwACAQECAQECAgICAgICAgMFAwMD AwMGBAQDBQcGBwcHBgcHCAkLCQgICggHBwoNCgoLDAwMDAcJDg8NDA4LDAwM/9sAQwECAgIDAwMG AwMGDAgHCAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwM /8AAEQgAmABFAwEiAAIRAQMRAf/EAB8AAAEFAQEBAQEBAAAAAAAAAAABAgMEBQYHCAkKC//EALUQ AAIBAwMCBAMFBQQEAAABfQECAwAEEQUSITFBBhNRYQcicRQygZGhCCNCscEVUtHwJDNicoIJChYX GBkaJSYnKCkqNDU2Nzg5OkNERUZHSElKU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6g4SFhoeIiYqS k5SVlpeYmZqio6Slpqeoqaqys7S1tre4ubrCw8TFxsfIycrS09TV1tfY2drh4uPk5ebn6Onq8fLz 9PX29/j5+v/EAB8BAAMBAQEBAQEBAQEAAAAAAAABAgMEBQYHCAkKC//EALURAAIBAgQEAwQHBQQE AAECdwABAgMRBAUhMQYSQVEHYXETIjKBCBRCkaGxwQkjM1LwFWJy0QoWJDThJfEXGBkaJicoKSo1 Njc4OTpDREVGR0hJSlNUVVZXWFlaY2RlZmdoaWpzdHV2d3h5eoKDhIWGh4iJipKTlJWWl5iZmqKj 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 1+cn/BvL5M+kftI3UQhUXPxV1F0AXDmPhkJyAcENxxxhuBX6Nyf6vv0PSvzg/wCCBM//AAi/j/8A aq8FyrcR3nh/4nXdwRPbGOQRTS3EcZLn72fsr4/E/wAdaR+FnVS/hT+Q7TtOl8Lf8HNt/JE8ccXi X4UCWdMMxl8uWJB7Kcwr+C475r9Ha/Oz9re5j+Dv/BwZ+zf4klnW1sfH3hXUvDE7NkLJNGtw8Q4B yzPJAo+gycCv0TY5NOprZk4h3UfQMZpyHAoQ4FI5yayMNtR+c0U1DgUUFDaKKKDMSTPl8HacdfSv zh/Yad/h/wD8F9P2rPDvkmNPEGi6XribECowVLc5HygH5ryTJz13feJ4KK0g9zqobS9D33/goT+w xr37Vvxi/Z+8aeGNU0jS9W+DnjWHW7g6gZQJ9PZomuo4tisDKwgRQGAGCfnXv9QR/dooqG+hhKbc Un0HUUUUiAooooA//9kKZW5kc3RyZWFtCmVuZG9iago3IDAgb2JqCjw8L1R5cGUvWE9iamVjdC9D b2xvclNwYWNlL0RldmljZVJHQi9TdWJ0eXBlL0ltYWdlL0JpdHNQZXJDb21wb25lbnQgOC9XaWR0 aCAxMjI0L0xlbmd0aCAxMjYyOS9IZWlnaHQgNzYvRmlsdGVyL0RDVERlY29kZT4+c3RyZWFtCv/Y /+AAEEpGSUYAAQIAAGQAZAAA/+wAEUR1Y2t5AAEABAAAAFAAAP/uAA5BZG9iZQBkwAAAAAH/2wCE AAICAgICAgICAgIDAgICAwQDAgIDBAUEBAQEBAUGBQUFBQUFBgYHBwgHBwYJCQoKCQkMDAwMDAwM DAwMDAwMDAwBAwMDBQQFCQYGCQ0LCQsNDw4ODg4PDwwMDAwMDw8MDAwMDAwPDAwMDAwMDAwMDAwM DAwMDAwMDAwMDAwMDAwMDP/AABEIAEwEyAMBEQACEQEDEQH/xACqAAEAAgIDAQEAAAAAAAAAAAAA BgcFCAMECQIBAQEAAwEBAQEAAAAAAAAAAAAAAQIDBAUHBhAAAAUCBAQDAgsGBgIDAAAAAAECAwQF BhESBxfSlFUIITETIhRBMpPTFZXVFlcYWFFhI7R2N3GBJDS2d0JSYnIzEQEAAAMIAQIEBAYDAQEA AAAAAQIDEVHRklMUBhchMRJBkRME8GFxUoGhscEiMuFCciNi/9oADAMBAAIRAxEAPwDzM3VvXqLX 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 7PJ/wMZkdjGbwyT6ODuPzmF2Pp0v8M0kmsz+msOny6/Td9GXe1uH8UCaIIvSZRqjGgOy4jGfXHSy E8/nufRv0madWMZpm5jEFLdmdDCMcsG55GMBn4HJILnt3F2XP8HW0KQN+q/cm7Yw27rotPu6rCrc ZQwLMlMPhSZCn4vnQ6vTVZuiOfQSm8rEja1u6wY3q357TNU8ZI1E1ii1Z43eBE2UFhxBKMpVd5VC 0Q7MpV3ayhWtuT/ee2EeAwjdAt2eRzJQwEIyqFFumGtf7+XpQIY+toWtz0A4aLVdN6nNryrrnhih 6te3DTGOLLZJWSWpyXKXwLMT5IuokY4jrGekDaxbmxgscqSNrU11As5QTvenBD14Srx3XGksrKsy R+eQpKu0GSn22AuLfSvMSOdY7nth9kQ/t+OuGRa9XlgcaIUfrRZ85OF3aZbGZhBlV9iXlYBkO6o1 e+ieh3xkZj+hCsDarFBVElsjp22+drmt4ScUd3mK+oPo4jvc7a4Jn1tkAiAVoskHmJRZmV9h+aKc 4D8M5Y9NRrg9yaQ7OHEWqoTcZYM/ycZuQvffZHea7e/Q1/8YFesLCmVuZHN0cmVhbQplbmRvYmoK MSAwIG9iago8PC9Hcm91cDw8L1R5cGUvR3JvdXAvQ1MvRGV2aWNlUkdCL1MvVHJhbnNwYXJlbmN5 Pj4vUGFyZW50IDkgMCBSL0NvbnRlbnRzIDggMCBSL1R5cGUvUGFnZS9UYWJzL1MvUmVzb3VyY2Vz PDwvWE9iamVjdDw8L2ltZzMgNyAwIFIvaW1nMiA2IDAgUi9pbWcxIDUgMCBSL2ltZzAgMyAwIFI+ Pi9Qcm9jU2V0IFsvUERGIC9UZXh0IC9JbWFnZUIgL0ltYWdlQyAvSW1hZ2VJXS9Db2xvclNwYWNl PDwvQ1MvRGV2aWNlUkdCPj4vRm9udDw8L0YxIDIgMCBSL0YyIDQgMCBSPj4+Pi9NZWRpYUJveFsw IDAgNTk1IDg0Ml0+PgplbmRvYmoKMTAgMCBvYmoKWzEgMCBSL1hZWiAwIDg1MiAwXQplbmRvYmoK MiAwIG9iago8PC9CYXNlRm9udC9IZWx2ZXRpY2EvVHlwZS9Gb250L0VuY29kaW5nL1dpbkFuc2lF bmNvZGluZy9TdWJ0eXBlL1R5cGUxPj4KZW5kb2JqCjQgMCBvYmoKPDwvQmFzZUZvbnQvSGVsdmV0 aWNhLUJvbGQvVHlwZS9Gb250L0VuY29kaW5nL1dpbkFuc2lFbmNvZGluZy9TdWJ0eXBlL1R5cGUx Pj4KZW5kb2JqCjkgMCBvYmoKPDwvSVRYVCgyLjEuNykvVHlwZS9QYWdlcy9Db3VudCAxL0tpZHNb MSAwIFJdPj4KZW5kb2JqCjExIDAgb2JqCjw8L05hbWVzWyhKUl9QQUdFX0FOQ0hPUl8wXzEpIDEw IDAgUl0+PgplbmRvYmoKMTIgMCBvYmoKPDwvRGVzdHMgMTEgMCBSPj4KZW5kb2JqCjEzIDAgb2Jq Cjw8L05hbWVzIDEyIDAgUi9UeXBlL0NhdGFsb2cvVmlld2VyUHJlZmVyZW5jZXM8PC9QcmludFNj YWxpbmcvQXBwRGVmYXVsdD4+L1BhZ2VzIDkgMCBSPj4KZW5kb2JqCjE0IDAgb2JqCjw8L0NyZWF0 b3IoSmFzcGVyUmVwb3J0cyBMaWJyYXJ5IHZlcnNpb24gNi4zLjEpL1Byb2R1Y2VyKGlUZXh0IDIu MS43IGJ5IDFUM1hUKS9Nb2REYXRlKEQ6MjAxNzA5MDQxMzU4MzYtMDUnMDAnKS9DcmVhdGlvbkRh dGUoRDoyMDE3MDkwNDEzNTgzNi0wNScwMCcpPj4KZW5kb2JqCnhyZWYKMCAxNQowMDAwMDAwMDAw IDY1NTM1IGYgCjAwMDAwNTYwODggMDAwMDAgbiAKMDAwMDA1NjQzMCAwMDAwMCBuIAowMDAwMDAw MDE1IDAwMDAwIG4gCjAwMDAwNTY1MTggMDAwMDAgbiAKMDAwMDAyODE0OCAwMDAwMCBuIAowMDAw MDM3NjEyIDAwMDAwIG4gCjAwMDAwNDIzMjYgMDAwMDAgbiAKMDAwMDA1NTExMCAwMDAwMCBuIAow MDAwMDU2NjExIDAwMDAwIG4gCjAwMDAwNTYzOTQgMDAwMDAgbiAKMDAwMDA1NjY3NCAwMDAwMCBu IAowMDAwMDU2NzMwIDAwMDAwIG4gCjAwMDAwNTY3NjQgMDAwMDAgbiAKMDAwMDA1Njg2OSAwMDAw MCBuIAp0cmFpbGVyCjw8L1Jvb3QgMTMgMCBSL0lEIFs8NzBhNWExNjgyYzQ2YWIyMjAyYTU0ZTQ4 OTJhMDU4OGQ+PDZhMjc4ZWU2YzE5MGFlNDU5ZmJmNGU5MTJjNGM1Nzg3Pl0vSW5mbyAxNCAwIFIv U2l6ZSAxNT4+CnN0YXJ0eHJlZgo1NzAzNwolJUVPRgo=";
        enviarMailVariosPdfEncriiptado(a);
    }
    
	public static void modifyExcel() throws IOException {
		// Read Excel document first
		FileInputStream input_document = new FileInputStream(
				new File("C:\\Users\\CAMILO\\eclipse-workspace\\curso\\managedocuments\\resources\\entrada\\asd.xlsx"));
		// convert it into a POI object
		XSSFWorkbook my_xlsx_workbook = new XSSFWorkbook(input_document);

		int numero_hojas = my_xlsx_workbook.getNumberOfSheets();
		for (int i = 0; i < numero_hojas; i++) {
			XSSFSheet hoja = my_xlsx_workbook.getSheetAt(i);
			int columnas = hoja.getLastRowNum();
			for (int j = 0; j < columnas; j++) {
				XSSFRow columna = hoja.getRow(j);
				Iterator<Cell> cellIterator = columna.cellIterator();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					org.apache.poi.ss.usermodel.CellType cellType = cell.getCellTypeEnum();
					String name = cellType.name();
					System.out.println(name);
					if (name == "STRING") {
						String value = cell.getStringCellValue();
						System.out.println(value);
						// si parametro es igual al nombre lo reemplazo por el valor
						if (value.contains("DATOS")) {
							cell.setCellValue("PUNK");
						}
					}

				}
			}
		}
		input_document.close();
		// Open FileOutputStream to write updates
		FileOutputStream output_file = new FileOutputStream(new File(
				"C:\\\\Users\\\\CAMILO\\\\eclipse-workspace\\\\curso\\\\managedocuments\\\\resources\\\\salida\\\\asd.xlsx"));
		// write changes
		my_xlsx_workbook.write(output_file);
		// close the stream
		output_file.close();

//		// Read excel sheet that needs to be updated
//		XSSFSheet my_worksheet = my_xlsx_workbook.getSheetAt(0);
//		// declare a Cell object
//		Cell cell = null;
//		// Access the cell first to update the value
//		cell = my_worksheet.getRow(2).getCell(1);
//		// Get current value and reduce 5 from it
//		cell.setCellValue(cell.getNumericCellValue() - 5);
//		// important to close InputStream
//		input_document.close();
//		// Open FileOutputStream to write updates
//		FileOutputStream output_file = new FileOutputStream(new File("C:\\simple.xlsx"));
//		// write changes
//		my_xlsx_workbook.write(output_file);
//		// close the stream
//		output_file.close();
	}
	
	/**
     * Metodo con ejemplo de manejo de fechas en java 8
     * 
     */
    public static void dates() {

        // Para año mes y dia
        LocalDate date = LocalDate.of(1989, 11, 11); // 1989-11-11
        System.out.println(date.getYear()); // 1989
        System.out.println(date.getMonth()); // NOVEMBER
        System.out.println(date.getDayOfMonth()); // 11
        System.out.println(date);
        // Actual
        LocalDate dateCurrent = LocalDate.now();
        System.out.println("actual: " + dateCurrent);
        System.out.println("");

        // Para horas minutos segundos y nanosegundos
        LocalTime time = LocalTime.of(5, 30, 45, 35); // 05:30:45:35
        System.out.println(time.getHour()); // 5
        System.out.println(time.getMinute()); // 30
        System.out.println(time.getSecond()); // 45
        System.out.println(time.getNano()); // 35
        System.out.println(time);
        // Actual
        LocalTime timeCurrent = LocalTime.now();
        System.out.println("actual: " + timeCurrent);
        System.out.println("");

        // Union de ambos
        LocalDateTime dateTime = LocalDateTime.of(1989, 11, 11, 5, 30, 45, 35); // 1989-11-11T05:30:45.000000035
        // o haciendo referencia a los dos anteriores
        LocalDate date1 = LocalDate.of(1989, 11, 11);
        LocalTime time1 = LocalTime.of(5, 30, 45, 35);
        LocalDateTime dateTime1 = LocalDateTime.of(date1, time1);
        System.out.println(dateTime);
        System.out.println(dateTime1);
        // Actual
        LocalDateTime localDateTimeCurrent = LocalDateTime.now();
        System.out.println("actual: " + localDateTimeCurrent);
        System.out.println("");

        // Diferencias entre tiempos
        LocalTime localTime1 = LocalTime.of(12, 25);
        LocalTime localTime2 = LocalTime.of(17, 35);
        Duration duration = Duration.between(localTime1, localTime2);
        System.out.println("Diferencia horas: " + duration);
        LocalDateTime localDateTime1 = LocalDateTime.of(2016, Month.JULY, 18,
                14, 13);
        LocalDateTime localDateTime2 = LocalDateTime.of(2016, Month.JULY, 20,
                12, 25);
        Duration duration1 = Duration.between(localDateTime1, localDateTime2);
        System.out.println("Diferencia horas: " + duration1);
        Duration oneDayDuration = Duration.of(1, ChronoUnit.DAYS);
        System.out.println("Diferencia un dia: " + oneDayDuration);
        System.out.println("");

        // Diferencias entre fechas
        LocalDate localDate1 = LocalDate.of(2016, Month.JULY, 18);
        LocalDate localDate2 = LocalDate.of(2016, Month.JULY, 20);
        Period period = Period.between(localDate1, localDate2);
        System.out.println("Diferencia dias " + period.getDays());
        System.out.println("");

        // Ejemplo de diferencias con ChronoUnit
        LocalDateTime oldDate = LocalDateTime.of(1982, Month.AUGUST, 31, 10, 20,
                55);
        LocalDateTime newDate = LocalDateTime.of(2016, Month.NOVEMBER, 9, 10,
                21, 56);

        System.out.println(oldDate);
        System.out.println(newDate);

        // count between dates
        long years = ChronoUnit.YEARS.between(oldDate, newDate);
        long months = ChronoUnit.MONTHS.between(oldDate, newDate);
        long weeks = ChronoUnit.WEEKS.between(oldDate, newDate);
        long days = ChronoUnit.DAYS.between(oldDate, newDate);
        long hours = ChronoUnit.HOURS.between(oldDate, newDate);
        long minutes = ChronoUnit.MINUTES.between(oldDate, newDate);
        long seconds = ChronoUnit.SECONDS.between(oldDate, newDate);
        long milis = ChronoUnit.MILLIS.between(oldDate, newDate);
        long nano = ChronoUnit.NANOS.between(oldDate, newDate);

        System.out.println("\n--- Total --- ");
        System.out.println(years + " years");
        System.out.println(months + " months");
        System.out.println(weeks + " weeks");
        System.out.println(days + " days");
        System.out.println(hours + " hours");
        System.out.println(minutes + " minutes");
        System.out.println(seconds + " seconds");
        System.out.println(milis + " milis");
        System.out.println(nano + " nano");
    }
	
}
