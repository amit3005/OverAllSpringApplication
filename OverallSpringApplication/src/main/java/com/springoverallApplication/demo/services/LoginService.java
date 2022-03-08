package com.springoverallApplication.demo.services;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.springoverallApplication.demo.dao.AppCountryREPO;
import com.springoverallApplication.demo.dao.AppStateREPO;
import com.springoverallApplication.demo.dao.AppUserREPO;
import com.springoverallApplication.demo.model.AppCountry;
import com.springoverallApplication.demo.model.AppState;
import com.springoverallApplication.demo.model.AppUser;

@Service
public class LoginService {
	
	@Autowired
	private AppUserREPO appUserRepo;
	@Autowired
	private AppCountryREPO appCountryRepo;
	@Autowired
	private AppStateREPO appStateRepo;
	@Autowired
	private JavaMailSender javaMailSender;
		
	public void addAppUser(AppUser appUser) {
		appUserRepo.save(appUser);
	}
	
	public List<AppCountry> getAllCountry(){
		List<AppCountry> list = appCountryRepo.findAllByIsActive(1);
		return list;
	}
	
	/*
	 * public List<AppState> getAllStatesByCountryId(int countryId){ List<AppState>
	 * list = null; if(countryId != 0) { list = appStateRepo.findAllByAppCountry(new
	 * AppCountry(countryId)); } return list; }
	 */
	
	public List<Object[]> getAllStatesByCountryId(int countryId){
		List<Object[]> list = null;
		if(countryId != 0) {
			list = appStateRepo.stateData(countryId);
		}
		return list;
	}
	
	public List<AppState> getAllStates(){
		return appStateRepo.findAllByIsActive(1);
	}
	
	public boolean isValidEmailId(String emailId) {
		AppUser appUser = appUserRepo.findByAppUserEmailId(emailId);
		return appUser==null ? true : false;
	}
	
	public String sendEmail(String to,String otp) throws IOException{
		// email ID of Recipient.
	      String recipient = "amitdamariya2000@gmail.com";
	 
	      // email ID of  Sender.
	      String sender = "milan.balar2000@gmail.com";
	 
	      // using host as localhost
	      String host = "127.0.0.1";
	 
	      // Getting system properties
	      Properties properties = System.getProperties();
	 
	      // Setting up mail server
	      properties.setProperty("mail.smtp.host", host);
	 
	      // creating session object to get properties
	      Session session = Session.getDefaultInstance(properties);
	      
	  	try
		{
			// MimeMessage object.
			MimeMessage message = new MimeMessage(session);

			// Set From Field: adding senders email to from field.
			message.setFrom(new InternetAddress(sender));

			// Set To Field: adding recipient's email to from field.
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));

			// Set Subject: subject of the email
			message.setSubject("This is Subject");

			// set body of the email.
			System.out.print(otp);
			message.setText("YOur OTP Registration for Registration is : " + otp);

			/*
			 * Multipart multipart = new MimeMultipart(); MimeBodyPart minebodyPart = new
			 * MimeBodyPart(); minebodyPart.attachFile(new
			 * File("C:\\Users\\AMIT DAMARIYA\\Desktop\\Interview_Preparation.docx"));
			 * multipart.addBodyPart(minebodyPart);
			 */
			// Send email.
			//message.setContent(multipart);
			javaMailSender.send(message);
			System.out.println("Mail successfully sent");
		}
		catch (MessagingException mex)
		{
			mex.printStackTrace();
		}
	  	return to;
	}
	
	public static String generateOTP() 
    {  //int randomPin declared to store the otp
        //since we using Math.random() hence we have to type cast it int
        //because Math.random() returns decimal value
        int randomPin   =(int) (Math.random()*9000)+1000;
        String otp  = String.valueOf(randomPin);
        return otp; //returning value of otp
    }
}
