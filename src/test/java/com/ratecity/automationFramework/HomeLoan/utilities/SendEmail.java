package com.ratecity.automationFramework.HomeLoan.utilities;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;



public class SendEmail {
	public static void SendMail() {
		String messageBody=null;
		Properties props = new Properties();
		props.put("mail.smtp.host","smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		Session session = Session.getDefaultInstance(props,
				new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("testRateCity@gmail.com","Qwerty@123#");
			}
		});

		try {
			MimeMessage message = new MimeMessage(session);  
			message.setFrom(new InternetAddress("testRateCity@gmail.com"));//change accordingly  
			InternetAddress[] iAdressArray_to = InternetAddress.parse("Deepak.gupta@tothenew.com");
			//InternetAddress[] iAdressArray_cc = InternetAddress.parse(cc);
			message.setRecipients(Message.RecipientType.TO,iAdressArray_to);  
			//message.setRecipients(Message.RecipientType.CC,iAdressArray_cc); 
			DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
			Date dateobj = new Date();
			// System.out.println(df.format(dateobj));
			message.setSubject("QA Report ("+df.format(dateobj)+")");  
			messageBody=GenerateHTML();
			BodyPart messageBodyPart1 = new MimeBodyPart();  
			messageBodyPart1.setContent(messageBody,"text/html");
//		    messageBodyPart1.setText("For More INFO..PFA attached file herewith");
			MimeBodyPart imagePart = new MimeBodyPart();
			imagePart.attachFile(GenerateChart.getGraphPath());
			imagePart.setDisposition(MimeBodyPart.INLINE);
		    MimeBodyPart messageBodyPart2 = new MimeBodyPart();  
		    String filename = System.getProperty("user.dir")+File.separator+"Reports"+File.separator+"TestReport"+".html";//cha.htmlnge accordingly  
		    DataSource source = new FileDataSource(filename);  
		    messageBodyPart2.setDataHandler(new DataHandler(source));  
		    messageBodyPart2.setFileName("TestReport");  
		  /*  MimeBodyPart messageBodyPart3 = new MimeBodyPart();  
		    String allureReport = System.getProperty("user.dir")+File.separator+"target"+File.separator+"site"+File.separator+"allure-maven-plugin"+File.separator+"index"+".html";
		    DataSource source1 = new FileDataSource(allureReport);  
		    messageBodyPart3.setDataHandler(new DataHandler(source1));  
		    messageBodyPart3.setFileName("AllureReport"); */
		    Multipart multipart = new MimeMultipart();  
		    multipart.addBodyPart(messageBodyPart1);  
		    multipart.addBodyPart(imagePart);
		    multipart.addBodyPart(messageBodyPart2);  
		   // multipart.addBodyPart(messageBodyPart3);  
		    message.setContent(multipart );  
			Transport.send(message);


		} catch (MessagingException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String GenerateHTML(){
		String messageBody=null;
		messageBody = "<html>"+
		"<style>"+
		"#AlignLeft {"+
		 "float: left;"+
		"margin: 0 1.5%;"+
		 "width: 63%;"+
		"}"+
		"#AlignRight {"+
		  "float: right;"+
		  "margin: 0 1.9%;"+
		 " width: 30%;"+
		"}"+
		"#header-content {"+
		   " position: absolute;"+
		    "bottom: 0;"+
		    "left: 0;"+
		  "}"+
		"</style>"+
		"<body>"+
		"<p>Hi,<br></p><p><b>Below are the summary of the Test Result status of today.</b><br><br></p>"+
		"<div id='AlignLeft'>"+
					"<table class='tg' border='2' cellpadding='15' CELLSPACING='2'>"+
					  "<tr>"+
					   "<th bgcolor='#FF5733'><b>Total Test Cases</b></th>"+
					   "<th bgcolor='#FF5733'>Passed</th>"+
					   "<th bgcolor='#FF5733'>Fail       </th>"+
					   "<th bgcolor='#FF5733'>Skipped</th>"+
					   "</tr>"+ConvertSummaryIntoHtmlTableFormat(BaseClass.total.size(),BaseClass.pass.size(),BaseClass.fail.size(),BaseClass.skip.size())+"</table></div>"+
		"<div id='AlignRight'>"+
					 /*  "<table>"+
					   "<tr>Summary Graph</tr>"+
					   ConvertSummaryIntoGraph()+
					   "</table>"+*/
		"</div>"+
		"<div id='header-content'><table>"+
					  "<tr></tr>"+
					   "<tr></tr>"+
					   "<tr></tr>"+
					   "<tr></tr>"+
					   "<tr></tr>"+
					   "<tr></tr>"+
					   "<tr><b><i>For detailed info.....PFA file herewith</i></b></tr>"+
					   "</table>"+
		"</div>"+
		"</body>"+
		"</html>";
		return messageBody;
	}
	
	public static StringBuilder ConvertSummaryIntoHtmlTableFormat(int counter, int PassCount, int FailCount, int SkipCount){
		StringBuilder body = new StringBuilder();
		body.append("<tr>");
		body.append("<td class='tg-yw4l' style='text-align:center'>"+BaseClass.total.size()+"</td>");
		body.append("<td class='tg-yw4l' style='text-align:center'>"+BaseClass.pass.size()+"</td>");
		body.append("<td class='tg-yw4l' style='text-align:center'>"+BaseClass.fail.size()+"</td>");
		body.append("<td class='tg-yw4l' style='text-align:center'>"+BaseClass.skip.size()+"</td>");
		body.append("</tr>");

		return body;
		}
	
	public static StringBuilder ConvertSummaryIntoGraph(){
		StringBuilder body = new StringBuilder();
		body.append("<tr>");
		body.append("<td>"+"<img src="+GenerateChart.getGraphPath()+"/>"+"</td>");
		body.append("</tr>");
		return body;
	}
}

