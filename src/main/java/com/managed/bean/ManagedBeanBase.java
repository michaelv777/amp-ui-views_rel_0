/**
 * 
 */
package com.managed.bean;

import java.io.InputStream;
import java.io.StringWriter;
import java.util.HashMap;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.commons.io.IOUtils;

import com.managed.bean.extensions.CommonTypes.NodeLevel;

/**
 * @author MVEKSLER
 *
 */
public class ManagedBeanBase 
{
	protected boolean lcRes = true;
	
	//---class variables-------------------------------------------
	protected HashMap<String, String> cResources = null;

	protected String cSourceName = "";
	
	protected NodeLevel cNodeLevel = NodeLevel.NONE;
	//---getters/setters-------------------------------------------
	
	
	/**
	 * @return the cResources
	 */
	public HashMap<String, String> getcResources() {
		return cResources;
	}

	
	

	public NodeLevel getcNodeLevel() {
		return cNodeLevel;
	}




	public void setcNodeLevel(NodeLevel cNodeLevel) {
		this.cNodeLevel = cNodeLevel;
	}




	public String getcSourceName() {
		return cSourceName;
	}

	public void setcSourceName(String cSourceName) {
		this.cSourceName = cSourceName;
	}

	/**
	 * @param cResources the cResources to set
	 */
	public void setcResources(HashMap<String, String> cResources) {
		this.cResources = cResources;
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
	/**
	 * 
	 */
	//---class methods---------------------------------------------
	public ManagedBeanBase() 
	{
		try
		{
			//this.init();
		}
		catch( Exception e)
		{
			
		}
	}

	@SuppressWarnings("unused")
	@PostConstruct
	private void init()  
	{
		boolean cRes = true;
    	
    	try
    	{
    		this.cResources = new HashMap<String, String>();
    	}
    	catch( Exception e )
    	{
    		FacesMessage msg = new FacesMessage(
  	        		FacesMessage.SEVERITY_ERROR, "init", 
  	        		e.getMessage());
  				
			FacesContext.getCurrentInstance().
				addMessage("loadResources", msg);
			
			
    	}
	}
	//--------------------------------------------------------------
	protected boolean loadResources()
	{
		boolean cRes = true;
    	
    	try
    	{
    		if ( null == this.cResources )
    		{
    			this.cResources = new HashMap<String, String>();
    		}
    		
    		/*---option 1
    		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
    		InputStream input = externalContext.getResourceAsStream("/WEB-INF/resources/config/FrameworkSettingsJar.xml");
			*/
   
    		//---option 2
    		ClassLoader classLoader = getClass().getClassLoader();
    		
    		if ( cRes )
    		{
	    		InputStream fSettingsJar = classLoader.getResourceAsStream("/WEB-INF/resources/config/FrameworkSettingsJar.xml");
	    		if ( null == fSettingsJar )
	    		{
	    			FacesMessage msg = new FacesMessage(
	      	        		FacesMessage.SEVERITY_ERROR, "loadResources", 
	      	        		"Error on load FrameworkSettingsJar.xml!");
	      				
	    			FacesContext.getCurrentInstance().
	    				addMessage("loadResources", msg);
	    			
	    			//cRes = false;
	    		}
	    		else
	    		{
	    			StringWriter writer = new StringWriter();
	    			
	    			IOUtils.copy(fSettingsJar, writer, "UTF-8");
	    			
	    			String theString = writer.toString();
	    			
	    			IOUtils.closeQuietly(fSettingsJar);
	    			
	    			this.cResources.put("FrameworkSettingsJar.xml", theString);
	    		}
    		}
    		//---
    		if ( cRes )
    		{
    			InputStream fSettingsSQL = classLoader.getResourceAsStream("/WEB-INF/resources/config/FrameworkSQL.xml");
	    		if ( null == fSettingsSQL )
	    		{
	    			FacesMessage msg = new FacesMessage(
	      	        		FacesMessage.SEVERITY_ERROR, "loadResources", 
	      	        		"Error on load FrameworkSQL.xml!");
	      				
	    			FacesContext.getCurrentInstance().
	    				addMessage("loadResources", msg);
	    			
	    			//cRes = false;
	    		}
	    		else
	    		{
	    			StringWriter writer = new StringWriter();
	    			
	    			IOUtils.copy(fSettingsSQL, writer, "UTF-8");
	    			
	    			String theString = writer.toString();
	    			
	    			IOUtils.closeQuietly(fSettingsSQL);
	    			
	    			this.cResources.put("FrameworkSQL.xml", theString);
	    		}
    		}
    		//---
    		if ( cRes )
    		{
    			InputStream fSettingsHibernate = 
    					classLoader.getResourceAsStream("/WEB-INF/resources/config/hibernate.cfg.xml");
    			
	    		if ( null == fSettingsHibernate )
	    		{
	    			FacesMessage msg = new FacesMessage(
	      	        		FacesMessage.SEVERITY_ERROR, "loadResources", 
	      	        		"Error on load hibernate.cfg.xml!");
	      				
	    			FacesContext.getCurrentInstance().
	    				addMessage("loadResources", msg);
	    			
	    			//cRes = false;
	    		}
	    		else
	    		{
	    			StringWriter writer = new StringWriter();
	    			
	    			IOUtils.copy(fSettingsHibernate, writer, "UTF-8");
	    			
	    			String theString = writer.toString();
	    			
	    			IOUtils.closeQuietly(fSettingsHibernate);
	    			
	    			this.cResources.put("hibernate.cfg.xml", theString);
	    		}
    		}
    		//---
    		if ( cRes )
    		{
    			InputStream fSettingsLog4J = classLoader.getResourceAsStream("/WEB-INF/resources/config/log4j.properties");
	    		if ( null == fSettingsLog4J )
	    		{
	    			FacesMessage msg = new FacesMessage(
	      	        		FacesMessage.SEVERITY_ERROR, "loadResources", 
	      	        		"Error on load log4j.properties!");
	      				
	    			FacesContext.getCurrentInstance().
	    				addMessage("loadResources", msg);
	    			
	    			//cRes = false;
	    		}
	    		else
	    		{
	    			StringWriter writer = new StringWriter();
	    			
	    			IOUtils.copy(fSettingsLog4J, writer, "UTF-8");
	    			
	    			String theString = writer.toString();
	    			
	    			IOUtils.closeQuietly(fSettingsLog4J);
	    			
	    			this.cResources.put("log4j.properties", theString);
	    		}
    		}
    		return cRes;
    	}
    	catch( Exception e )
    	{
    		FacesMessage msg = new FacesMessage(
  	        		FacesMessage.SEVERITY_ERROR, "loadResources", 
  	        		e.getMessage());
  				
			FacesContext.getCurrentInstance().
				addMessage("loadResources", msg);
			
			return ( cRes = false );
    	}
	}
	
	// ------------------------------------------------------------------
	protected void addMessage(String mType, String summary, String detail) {
		FacesMessage message = null;
	
		if (mType != null) {
			if (mType.equalsIgnoreCase("INFO")) {
				message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
			} else if (mType.equalsIgnoreCase("ERROR")) {
				message = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail);
			} else {
				message = new FacesMessage(FacesMessage.SEVERITY_WARN, summary, detail);
			}
		}
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
}
