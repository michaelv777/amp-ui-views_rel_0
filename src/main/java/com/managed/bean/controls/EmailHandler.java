/**
 * 
 */
package com.managed.bean.controls;

import java.io.Serializable;
import java.security.Security;
import java.util.Date;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


/**
 * @author MVEKSLER
 *
 */
@ManagedBean(name = "emailHandlerBean")
@ViewScoped
public class EmailHandler implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	protected boolean lcRes = true;
	
	protected String email = "";
	
	protected String subject = "";
	
	protected String message = "";
	
	//---getters/setters-------------------------------------------
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try 
		{

			/*
			new EmailHandler().SendOutlook("michael.veksler", 
			  "Amdocs10", 
			  "michael.veksler@amdocs.com", 
			  "michael.veksler@amdocs.com", 
			  "test", 
			  "test");
			*/
			
			
			new EmailHandler().SendOutlookTLS(
					  "ntnet\\mveksler", 
					  "Amdocs10", 
					  "michael.veksler@amdocs.com",
					  "michael.veksler@amdocs.com", 
					  "michael.veksler@amdocs.com", 
					  "test", 
					  "test");
			
			
		/*	
		new EmailHandler().SendOutlookNoAuth(
					  "ntnet\\mveksler", 
					  "Amdocs10", 
					  "michael.veksler@amdocs.com", 
					  "michael.veksler@amdocs.com", 
					  "test", 
					  "test");
		*/	
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}


	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}


	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}


	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}


	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}


	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}


	/**
	 * @return the lcRes
	 */
	public boolean isLcRes() {
		return lcRes;
	}


	/**
	 * @param lcRes the lcRes to set
	 */
	public void setLcRes(boolean lcRes) {
		this.lcRes = lcRes;
	}
	
	//---class methods---------------------------------------------
	public EmailHandler() 
	{
		try
		{
			this.init();
		}
		catch( Exception e)
		{
			
		}
	}
	//--------------------------------------------------------------
	@PostConstruct
    public void init() 
	{
		boolean cRes = true;
		
		String cMethodName = "";
		
		try
		{
			StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
	        StackTraceElement ste = stacktrace[1];
	        cMethodName = ste.getMethodName();
			
			this.setLcRes(cRes);
		}
		catch( Exception e)
		{
			FacesMessage msg = new FacesMessage(
	        		FacesMessage.SEVERITY_ERROR, cMethodName, 
	        		e.getMessage());
				
			FacesContext.getCurrentInstance().
					addMessage(null, msg);
			
			System.out.println("M.V. Custom::" + cMethodName + "::" + e.getMessage());
			
			this.setLcRes(cRes = false);
		}
    }
	//------------------------------------------------------------------
	
	//---------------------------
	/*
	public  void SendGoogle(final String username, 
						   final String password, 
						   String recipientEmail, 
						   String ccEmail, 
						   String title, 
						   String message) throws AddressException, MessagingException 
	{
		String cMethodName = "";
		
		try
		{
			StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
	        StackTraceElement ste = stacktrace[1];
	        cMethodName = ste.getMethodName();
	        
	        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
	        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
	
	        // Get a Properties object
	        Properties props = System.getProperties();
	        props.setProperty("mail.smtps.host", "smtp.gmail.com");
	        props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
	        props.setProperty("mail.smtp.socketFactory.fallback", "false");
	        props.setProperty("mail.smtp.port", "465");
	        props.setProperty("mail.smtp.socketFactory.port", "465");
	        props.setProperty("mail.smtps.auth", "true");
	
	        
//	        If set to false, the QUIT command is sent and the connection is immediately closed. If set 
//	        to true (the default), causes the transport to wait for the response to the QUIT command.
//	
//	        ref :   http://java.sun.com/products/javamail/javadocs/com/sun/mail/smtp/package-summary.html
//	                http://forum.java.sun.com/thread.jspa?threadID=5205249
//	                smtpsend.java - demo program from javamail
	        
	        props.put("mail.smtps.quitwait", "false");
	
	        Session session = Session.getInstance(props, null);
	
	        // -- Create a new message --
	        final MimeMessage msg = new MimeMessage(session);
	
	        // -- Set the FROM and TO fields --
	        msg.setFrom(new InternetAddress(username + "@gmail.com"));
	        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail, false));
	
	        if (ccEmail.length() > 0) {
	            msg.setRecipients(Message.RecipientType.CC, InternetAddress.parse(ccEmail, false));
	        }
	
	        msg.setSubject(title);
	        msg.setText(message, "utf-8");
	        msg.setSentDate(new Date());
	
	        SMTPTransport t = (SMTPTransport)session.getTransport("smtps");
	
	        t.connect("smtp.gmail.com", username, password);
	        t.sendMessage(msg, msg.getAllRecipients());      
	        t.close();
		}
		catch( Exception e)
		{
			FacesMessage msg = new FacesMessage(
	        		FacesMessage.SEVERITY_ERROR, cMethodName, 
	        		e.getMessage());
				
			FacesContext.getCurrentInstance().
					addMessage(null, msg);
			
			System.out.println("M.V. Custom::" + cMethodName + "::" + e.getMessage());
		}
    }
    */
	//---------------------------
	
	public  void SendOutlookSSL(final String username, 
							    final String password, 
								String fromEmail,
								String recipientEmail, 
								String ccEmail, 
								String title, 
								String messageText) throws AddressException, MessagingException 
	{
		String cMethodName = "";
		
		try
		{
			StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
	        StackTraceElement ste = stacktrace[1];
	        cMethodName = ste.getMethodName();
	        
	        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
	        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
	
	        // Get a Properties object
	        Properties props = System.getProperties();
	        props.setProperty("mail.smtps.host", "ilmail.corp.amdocs.com");
	        props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
	        props.setProperty("mail.smtp.socketFactory.fallback", "false");
	        props.setProperty("mail.smtp.port", "465");
	        props.setProperty("mail.smtp.socketFactory.port", "465");
	        props.setProperty("mail.smtps.auth", "true");
	        props.put("mail.transport.protocol", "smtps");
	        props.put("mail.debug", "true");
	        /*
	        If set to false, the QUIT command is sent and the connection is immediately closed. If set 
	        to true (the default), causes the transport to wait for the response to the QUIT command.
	
	        ref :   http://java.sun.com/products/javamail/javadocs/com/sun/mail/smtp/package-summary.html
	                http://forum.java.sun.com/thread.jspa?threadID=5205249
	                smtpsend.java - demo program from javamail
	        */
	        props.put("mail.smtps.quitwait", "false");
	
	        Session session = Session.getDefaultInstance(props,
	    			new javax.mail.Authenticator() {
	    				protected PasswordAuthentication getPasswordAuthentication() {
	    					return new PasswordAuthentication(username, password);
	    				}
	    			});

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(fromEmail));
			
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(recipientEmail));
			
			message.setSubject(title);
			message.setText(messageText);

			Transport.send(message);

			System.out.println("Done");
		}
		catch( Exception e)
		{
			FacesMessage msg = new FacesMessage(
	        		FacesMessage.SEVERITY_ERROR, cMethodName, 
	        		e.getMessage());
				
			FacesContext.getCurrentInstance().
					addMessage(null, msg);
			
			System.out.println("M.V. Custom::" + cMethodName + "::" + e.getMessage());
		}
    }
	
	//---------------------------
	public boolean SendOutlookTLS(final String username, 
							    final String password, 
							    String fromEmail,
							    String recipientEmail, 
							    String ccEmail, 
							    String title, 
							    String cMessage) throws AddressException, MessagingException 
	{
		String cMethodName = "";
		
		boolean cRes = true;
		
		try
		{
			StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
	        StackTraceElement ste = stacktrace[1];
	        cMethodName = ste.getMethodName();
	        
	        Properties props = new Properties();
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.smtp.starttls.enable", "true");
	        props.put("mail.smtp.host", "ilmail.corp.amdocs.com");
	        props.put("mail.smtp.port", "587");
	        props.put("mail.debug", "true");
	        
	        Session session = Session.getInstance(props,
	          new javax.mail.Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(username, password);
	            }
	          });

	        //Message message = new MimeMessage(session);
	        MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            
            message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(recipientEmail));
            
            message.setSubject(title);
            //message.setText(cMessage);
            message.setContent(cMessage, "text/html");

            message.setSentDate(new Date());
            
            Transport.send(message);

            System.out.println("Done");

            return cRes;
        } 
        catch (MessagingException e) 
        {
        	/*
        	FacesMessage msg = new FacesMessage(
	        		FacesMessage.SEVERITY_ERROR, cMethodName, 
	        		e.getMessage());
				
			FacesContext.getCurrentInstance().
					addMessage(null, msg);
			*/
			System.out.println("M.V. Custom::" + cMethodName + "::" + e.getMessage());
        	
        	return ( cRes = false );
        }
		catch (Exception e) 
        {
			FacesMessage msg = new FacesMessage(
	        		FacesMessage.SEVERITY_ERROR, cMethodName, 
	        		e.getMessage());
				
			FacesContext.getCurrentInstance().
					addMessage(null, msg);
			
			System.out.println("M.V. Custom::" + cMethodName + "::" + e.getMessage());
			
			return ( cRes = false );
        }
    }
	//---------------------------
	/*
	public  void SendOutlookNoAuth(final String username, 
								   final String password, 
								   String recipientEmail, 
								   String ccEmail, 
								   String title, 
								   String cmessage) throws AddressException, MessagingException 
	{
		String cMethodName = "";
		
		try
		{
			StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
	        StackTraceElement ste = stacktrace[1];
	        cMethodName = ste.getMethodName();
			
			Properties props = new Properties();

			props.put("mail.smtp.auth", "false");
			props.put("mail.smtp.host", "ilmail.corp.amdocs.com");
			props.put("mail.debug", "true");
			
			MailSSLSocketFactory sf = new MailSSLSocketFactory();
			sf.setTrustAllHosts(true); 
			
			props.put("mail.smpt.ssl.trust", "ilmail.corp.amdocs.com");
			props.put("mail.smpt.ssl.socketFactory", sf);
			props.put("mail.smtp.starttls.enable", "true");
			
			Session session = Session.getInstance(props);
			
			Message message = new MimeMessage(session);
											
			message.setFrom(new InternetAddress(username));
			InternetAddress[] address = {new InternetAddress(recipientEmail)};
            message.setRecipients(Message.RecipientType.TO, address);
			
			message.setSubject(title);
			message.setText(cmessage);
 
			Transport.send(message);
 
			System.out.println("Done");
		}
		catch( Exception e)
		{
			FacesMessage msg = new FacesMessage(
	        		FacesMessage.SEVERITY_ERROR, cMethodName, 
	        		e.getMessage());
				
			FacesContext.getCurrentInstance().
					addMessage(null, msg);
			
			System.out.println("M.V. Custom::" + cMethodName + "::" + e.getMessage());
		}
    }
	*/
	//--------------------------------------------------------------	
	public void sendEmail() 
	{        
		String cMethodName = "";
		
		boolean cRes = true;
		
        try
        {
        	StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
	        StackTraceElement ste = stacktrace[1];
	        cMethodName = ste.getMethodName();
	        
	        /* --Handle After
	        boolean isSaved = this.getcAPIService().saveContact(
	        		this.getcSelectedSite(), this.getcContactNew());
	        */
	      
	        
	        cRes = SendOutlookTLS("ntnet\\mveksler", 
						    	   "Amdocs10", 
						    	   "michael.veksler@amdocs.com",
						    	   this.email, 
						    	   this.email,
						    	   this.subject, 
						    	   this.message);
	        
	        if ( cRes )
	        {
	        	FacesMessage msg = new FacesMessage(
		        		FacesMessage.SEVERITY_INFO, cMethodName, 
		        		"Email to " + this.email + " was sent sucessfully!");
					
				FacesContext.getCurrentInstance().
						addMessage(null, msg);
	        }
	        else
	        {
	        	FacesMessage msg = new FacesMessage(
		        		FacesMessage.SEVERITY_INFO, cMethodName, 
		        		"It was error to send Email to " + this.email);
					
				FacesContext.getCurrentInstance().
						addMessage(null, msg);
	        }
	        
	        this.setLcRes(cRes);
        }
        catch( Exception e)
        {
        	FacesMessage msg = new FacesMessage(
	        		FacesMessage.SEVERITY_ERROR, cMethodName, 
	        		e.getMessage());
				
			FacesContext.getCurrentInstance().
					addMessage(null, msg);
			
			System.out.println("M.V. Custom::" + cMethodName + "::" + e.getMessage());
        }
        finally
        {
        	
        }
    }
}
