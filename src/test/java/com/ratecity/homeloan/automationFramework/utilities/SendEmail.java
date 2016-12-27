package com.ratecity.homeloan.automationFramework.utilities;

import java.io.File;
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
	public static void SendMail(){
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
			message.setSubject("Daily QA Report ("+df.format(dateobj)+")");  
			messageBody="<p>Hi,<br></p>"+"<p>Below are the summary of the Test Result status of today.<br><br></p>"+
			"<table class='tg' border='2'style='border-collapse:collapse'>"+
			  "<tr>"+
			    "<th bgcolor='#FF5733'><b>Total Test Cases</b></th>"+
			    "<th bgcolor='#FF5733'>Passed</th>"+
			    "<th bgcolor='#FF5733'>Fail       </th>"+
			    "<th bgcolor='#FF5733'>Skipped</th>"+
			  "</tr>"+ConvertSummaryIntoHtmlTableFormat(BaseClass.total.size(),BaseClass.pass.size(),BaseClass.fail.size(),BaseClass.skip.size())+"</table>"+
			   "<table>"+
			   "<tr></tr>"+
			   "<tr></tr>"+
			   "<tr>For More Info.....PFA attached file herewith</tr>"+
			   "</table>";
			
			BodyPart messageBodyPart1 = new MimeBodyPart();  
			messageBodyPart1.setContent(messageBody,"text/html");
//		    messageBodyPart1.setText("For More INFO..PFA attached file herewith");
		    MimeBodyPart messageBodyPart2 = new MimeBodyPart();  
		    
		    String filename = System.getProperty("user.dir")+File.separator+"Reports"+File.separator+"TestReport"+".html";//cha.htmlnge accordingly  
		    DataSource source = new FileDataSource(filename);  
		    messageBodyPart2.setDataHandler(new DataHandler(source));  
		    messageBodyPart2.setFileName("TestReport");  
		    Multipart multipart = new MimeMultipart();  
		    multipart.addBodyPart(messageBodyPart1);  
		    multipart.addBodyPart(messageBodyPart2);  
		    message.setContent(multipart );  
		    
		 //   message.setContent(messageBody,"text/html");  
					
					
					
					
					
					
					
					/*"<style type='text/css'>"+
			".tg  {border-collapse:collapse;border-spacing:0;border-color:#aaa;}"+
			".tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#aaa;color:#333;background-color:#fff;}"+
			".tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#aaa;color:#fff;background-color:#f38630;}"+
			".tg .tg-9hbo{font-weight:bold;vertical-align:top}"+
			".tg .tg-yw4l{vertical-align:top}"+
			"</style>"+
			"<table class='tg'>"+
			  "<tr>"+
			    "<th class='tg-9hbo'>Total Test Cases</th>"+
			    "<th class='tg-9hbo'>Passed</th>"+
			    "<th class='tg-9hbo'>Fail       </th>"+
			    "<th class='tg-9hbo'>Skipped</th>"+
			  "</tr>"+ConvertSummaryIntoHtmlTableFormat(BaseClass.total.size(),BaseClass.pass.size(),BaseClass.fail.size(),BaseClass.skip.size())+"</table>";*/
					
					
					
					
					
					
					/*"<style type='text/css'>"+"<p>Hi,<br></p>"+"<p>Below are the QA status of today.<br><br></p>"+
					".tg  {border-collapse:collapse;border-spacing:0;border-color:#999;}"+
					".tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 20px;border-style:solid;border-width:2px;overflow:hidden;word-break:normal;border-color:#999;color:#444;background-color:#F7FDFA;}"+
					".tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 20px;border-style:solid;border-width:2px;overflow:hidden;word-break:normal;border-color:#999;color:#fff;background-color:#26ADE4;}"+
					".tg .tg-baqh{text-align:center;vertical-align:top}"+
					".tg .tg-hgcj{font-weight:bold;text-align:center}"+
					".tg .tg-amwm{font-weight:bold;text-align:center;vertical-align:top}"+
					".tg .tg-yw4l{vertical-align:top}"+
					"</style>"+
					"<table class='tg' border='2' style='border-collapse:collapse'>"+
					"<tr>"+
					"<th class='tg-031e' colspan='3'>Attention Required (API Performance)<br></th>"+
					"</tr>"+
					"<tr>"+
					"<th class='tg-hgcj' >Page Name<br></th>"+
					"<th class='tg-amwm' >API URL<br></th>"+
					"<th class='tg-amwm' >RT (sec)<br></th>"+
					//"<th class='tg-amwm' >Page Status<br></th>"+
					"</tr>"+ConvertDataIntoHtmlTableFormat(data)+"</table><br><br>"+"<style type='text/css'>"+
					".tg  {border-collapse:collapse;border-spacing:0;border-color:#999;}"+
					".tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px"+ "5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#999;color:#444;background-color:#F7FDFA;}"+
					".tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px"+ "5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#999;color:#fff;background-color:#26ADE4;}"+
					".tg .tg-9hbo{font-weight:bold;vertical-align:top}"+
					".tg .tg-yw4l{vertical-align:top}"+
					"</style>"+
					"<table class='tg' border='2'>"+
					"<tr>"+
					"<th class='tg-031e' colspan='2'>API Performance Automation<br></th>"+
					"<th class='tg-031e' colspan='3'>Account Used: Blaze Fast Fire'd Pizza ; Locations: 160</th>"+
					"</tr>"+
					"<tr>"+
					"<td class='tg-9hbo' style='text-align:center'>Total API Executed</td>"+
					"<td class='tg-9hbo' style='text-align:center'>Pass Count</td>"+
					"<td class='tg-9hbo' style='text-align:center'>Fail Count</td>"+
					"<td class='tg-9hbo' style='text-align:center'>P0 RT&gt;15</td>"+
					"<td class='tg-9hbo' style='text-align:center'>P1 RT&gt;10</td>"+
					"</tr>"+ConvertSummaryIntoHtmlTableFormat(counter,PassCount,FailCount,P0_Count, P1_Count)+"</table>";*/
			


			Transport.send(message);

			System.out.println("Done!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
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
}

