/**
 * 
 */
package com.managed.bean.amazon;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.primefaces.event.TabChangeEvent;
import org.primefaces.event.TabCloseEvent;
import org.primefaces.model.menu.DefaultMenuColumn;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

import com.managed.bean.ManagedBeanBase;
import com.managed.bean.extensions.CommonTypes.NodeLevel;

import amp.amazon.webservices.rest.Item;
import amp.amazon.webservices.rest.Items;
import amp.business.aws.OperationsBeanAWS;
import amp.business.base.BusinessBeanBase;
import amp.common.api.impl.ToolkitConstants;
import amp.jpa.entities.Category;
import amp.jpa.entities.Node;
import amp.jpa.entities.Source;
import amp.jpaentities.mo.CategoryMO;
import amp.jpaentities.mo.NodeMO;


/**
 * @author MVEKSLER
 *
 */
@ManagedBean(name = "cAmazonDataBean")
@ViewScoped
public class AmazonDataBean extends ManagedBeanBase implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	//---class variables-------------------------------------------
	private BusinessBeanBase cOperationsBeanAWS = 
			new OperationsBeanAWS();
	
	//---categories Controls
	protected LinkedHashMap<String, CategoryMO> cCategoriesMap = 
			new LinkedHashMap<String, CategoryMO>();
	
	protected LinkedList<CategoryMO> cCategories = 
			new LinkedList<CategoryMO>();
	
	protected CategoryMO cSelectedCategory = 
			new CategoryMO();
	
	protected NodeMO cSelectedCategoryNode = 
			new NodeMO();
	
	protected NodeMO cSelectedCategorySubNode = 
			new NodeMO();
	
	//---items retrieved from the source 
	protected LinkedHashMap<Long, LinkedList<Item>> cSourceItemsMap = 
			new LinkedHashMap<Long, LinkedList<Item>>();
	
	//---top search controls
    protected List<SelectItem> cCategoriesItems = 
    		new LinkedList<SelectItem>();
    
	protected String cSelectedCategoryItem = "";
	
	protected String cCategorySearchInputText = "";
	
	//---UI Controls
	protected MenuModel cCategoriesMenuModel = new DefaultMenuModel();
	
	protected MenuModel cCategoriesSearchMenuModel = new DefaultMenuModel();
	
	protected MenuModel cCategoriesNodesMenuModel = new DefaultMenuModel();
	
	//---getters/setters-------------------------------------------
	
	
	public MenuModel getcCategoriesMenuModel() {
		return cCategoriesMenuModel;
	}

	public LinkedHashMap<Long, LinkedList<Item>> getcSourceItemsMap() {
		return cSourceItemsMap;
	}

	public void setcSourceItemsMap(LinkedHashMap<Long, LinkedList<Item>> cSourceItemsMap) {
		this.cSourceItemsMap = cSourceItemsMap;
	}

	public String getcSelectedCategoryItem() {
		return cSelectedCategoryItem;
	}

	public void setcSelectedCategoryItem(String cSelectedCategoryItem) {
		this.cSelectedCategoryItem = cSelectedCategoryItem;
	}

	public String getcCategorySearchInputText() {
		return cCategorySearchInputText;
	}

	public void setcCategorySearchInputText(String cCategorySearchInputText) {
		this.cCategorySearchInputText = cCategorySearchInputText;
	}

	public MenuModel getcCategoriesSearchMenuModel() {
		return cCategoriesSearchMenuModel;
	}

	public void setcCategoriesSearchMenuModel(MenuModel cCategoriesSearchMenuModel) {
		this.cCategoriesSearchMenuModel = cCategoriesSearchMenuModel;
	}

	public BusinessBeanBase getcOperationsBeanAWS() {
		return cOperationsBeanAWS;
	}

	public void setcOperationsBeanAWS(BusinessBeanBase cOperationsBeanAWS) {
		this.cOperationsBeanAWS = cOperationsBeanAWS;
	}

	public void setcCategoriesMenuModel(MenuModel cCategoriesMenuModel) {
		this.cCategoriesMenuModel = cCategoriesMenuModel;
	}
	
	public MenuModel getcCategoriesNodesMenuModel() {
		return cCategoriesNodesMenuModel;
	}

	public void setcCategoriesNodesMenuModel(MenuModel cCategoriesNodesMenuModel) {
		this.cCategoriesNodesMenuModel = cCategoriesNodesMenuModel;
	}
	
	public List<CategoryMO> getcCategories() {
		return cCategories;
	}

	public void setcCategories(LinkedList<CategoryMO> cCategories) {
		this.cCategories = cCategories;
	}
	
	public LinkedHashMap<String, CategoryMO> getcCategoriesMap() {
		return cCategoriesMap;
	}

	public void setcCategoriesMap(LinkedHashMap<String, CategoryMO> cCategoriesMap) {
		this.cCategoriesMap = cCategoriesMap;
	}

	public CategoryMO getcSelectedCategory() {
		return cSelectedCategory;
	}

	public void setcSelectedCategory(CategoryMO cSelectedCategory) {
		this.cSelectedCategory = cSelectedCategory;
	}

	public NodeMO getcSelectedCategoryNode() {
		return cSelectedCategoryNode;
	}

	public void setcSelectedCategoryNode(NodeMO cSelectedCategoryNode) {
		this.cSelectedCategoryNode = cSelectedCategoryNode;
	}
	
	public NodeMO getcSelectedCategorySubNode() {
		return cSelectedCategorySubNode;
	}

	public void setcSelectedCategorySubNode(NodeMO cSelectedCategorySubNode) {
		this.cSelectedCategorySubNode = cSelectedCategorySubNode;
	}

	public List<SelectItem> getcCategoriesItems() {
		return cCategoriesItems;
	}

	public void setcCategoriesItems(List<SelectItem> cCategoriesItems) {
		this.cCategoriesItems = cCategoriesItems;
	}

	//---class methods---------------------------------------------
	public AmazonDataBean() 
	{
		super();
		
		try
		{
			
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
	        
	        //---initialize data objects
	        this.cSourceName = "AmazonUS";
	        
	        //---initialize views objects
	        this.cCategoriesMenuModel = new DefaultMenuModel();
	        
	        this.cCategoriesSearchMenuModel = new DefaultMenuModel();
	    	
	    	this.cCategoriesNodesMenuModel = new DefaultMenuModel();
	    	
	        this.cOperationsBeanAWS = new OperationsBeanAWS(true);
	        
	        this.cCategoriesItems = new LinkedList<SelectItem>();
	        
	        this.cSourceItemsMap = new LinkedHashMap<Long, LinkedList<Item>>();
	        
	        //---check bean
	        if ( !this.cOperationsBeanAWS.isLcRes() )
	        {
				System.out.println("M.V. Custom::" + cMethodName + "::cOperationsBeanAWS is null!");
				
				this.setLcRes(cRes = false);
	        }
	        
	        //---get root departments
	        if ( cRes )
	        {
	        	cRes = this.getRootCategories();
	        }
	        //---get root categories nodes
	        if ( cRes )
	        {
	        	cRes = this.getRootCategoriesNodes();
	        }
	        //---build departments menu
	        if ( cRes )
	        {
	        	cRes = this.buildCategoriesMenuModel();
	        }
	        //---set default category sub nodes menu
	        if ( cRes )
	        {
	        	cRes = this.setDefaultCategory();
	        }
	        
	        //---build departments search menu
	        if ( cRes )
	        {
	        	cRes = this.setCategorySearchMenuModel();
	        }
	        //---build departments search one select
	        if ( cRes )
	        {
	        	cRes = this.buildCategoriesSearchList();
	        }
	       
			this.setLcRes(cRes);
		}
		catch( Exception e)
		{
			this.setLcRes(cRes = false);
			
			this.addMessage("ERROR", cMethodName, e.getMessage());
			
			System.out.println("M.V. Custom::" + cMethodName + "::" + e.getMessage());
			
			this.setLcRes(cRes = false);
		}
    }
	//--------
	/**
	 * @param cRes
	 */
	public boolean handleCategoryView(CategoryMO cCategory) 
	{
		boolean cRes = true;
		
		String cMethodName = "";
		
		try
		{
			StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
	        StackTraceElement ste = stacktrace[1];
	        cMethodName = ste.getMethodName();
	        
			//---
			if ( cRes )
			{
				cRes = this.getRootCategorySubNodes(cCategory);
			}
			//---
			if ( cRes )
			{
				cRes = this.getCategoryItemSearchList(cCategory, 1, true);
			}
			//---
			if ( cRes )
			{
				cRes = this.getCategoryItemSearchList(cCategory, 2, false);
			}
			//---
			if ( cRes )
			{
				cRes = this.setCategorySubNodesMenuModel(cCategory);
			}
			
			return cRes;
		}
		catch( Exception e)
		{
			this.setLcRes(cRes = false);
			
			this.addMessage("ERROR", cMethodName, e.getMessage());
			
			System.out.println("M.V. Custom::" + cMethodName + "::" + e.getMessage());
			
			return cRes;
		}
	}

	//--------
	/**
	 * @param cRes
	 */
	public boolean handleCategoryNodeView(CategoryMO cCategory, NodeMO cNode) 
	{
		boolean cRes = true;
		
		String cMethodName = "";
		
		try
		{
			StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
	        StackTraceElement ste = stacktrace[1];
	        cMethodName = ste.getMethodName();
			
			//---
			if ( cRes )
			{
				cRes = this.getCategoryNodeItemSearchList(cCategory, cNode, 1, true);
			}
			//---
			if ( cRes )
			{
				cRes = this.getCategoryNodeItemSearchList(cCategory, cNode, 2, false);
			}
			//---
			
			return cRes;
		}
		catch( Exception e)
		{
			this.setLcRes(cRes = false);
			
			this.addMessage("ERROR", cMethodName, e.getMessage());
			
			System.out.println("M.V. Custom::" + cMethodName + "::" + e.getMessage());
			
			return cRes;
		}
	}
		
	//--------
	/**
	 * @param cRes
	 */
	public boolean handleCategorySubNodeView(CategoryMO cCategory, NodeMO cNode) 
	{
		boolean cRes = true;
		
		String cMethodName = "";
		
		try
		{
			StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
	        StackTraceElement ste = stacktrace[1];
	        cMethodName = ste.getMethodName();
			
			//---
			if ( cRes )
			{
				cRes = this.getCategorySubNodeItemSearchList(cCategory, cNode, 1, true);
			}
			//---
			if ( cRes )
			{
				cRes = this.getCategorySubNodeItemSearchList(cCategory, cNode, 2, false);
			}
			//---
			
			return cRes;
		}
		catch( Exception e)
		{
			this.setLcRes(cRes = false);
			
			this.addMessage("ERROR", cMethodName, e.getMessage());
			
			System.out.println("M.V. Custom::" + cMethodName + "::" + e.getMessage());
			
			return cRes;
		}
	}
	//--------
	@SuppressWarnings("unchecked")
	public boolean getRootCategories()
	{
		boolean cRes = true;
		
		String  cMethodName = "";
		
		try
    	{
			StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
	        StackTraceElement ste = stacktrace[1];
	        cMethodName = ste.getMethodName();

	        //-----------------------------------------
	        this.cCategories = new LinkedList<CategoryMO>();
	        
	        Map<String, Object> cMethodParams = new TreeMap<String, Object>();
	        Map<String, Object> cMethodResults = new TreeMap<String, Object>();
	        
	        cMethodParams.put("p1", this.cSourceName);

	        //-----------------------------------------
	        if ( null == this.cOperationsBeanAWS )
	        {
	        	String cMesage = cMethodName + "::cOperationsBeanAWS not initialised!";
	        	
	        	FacesContext.getCurrentInstance().addMessage(null, 
	        			new FacesMessage(FacesMessage.SEVERITY_ERROR, cMethodName, cMesage));
	        	
	        	System.out.println(cMesage);
	        	
	        	this.setLcRes(cRes = false);
	        }
	        //-----------------------------------------
	        if ( cRes )
	        {
	        	cMethodResults = this.cOperationsBeanAWS.handleRequest(cMethodName, cMethodParams);
	        			
	        	if ( null == cMethodResults )
	        	{
	        		String cMesage = cMethodName + "::cOperationsBeanAWS not initialised!";
	        		
	        		FacesContext.getCurrentInstance().addMessage(null, 
	        				new FacesMessage(FacesMessage.SEVERITY_ERROR, cMethodName, cMesage));
	        		
	        		System.out.println(cMesage);
	        		
	        		this.setLcRes(cRes = false);
	        	}
	        }
	        //-----------------------------------------
	        if ( cRes )
	        {
	        	List<Category> cCategories =  (List<Category>)cMethodResults.get("r1");
	        	
	        	if ( null == cCategories )
	        	{
	        		String cMesage = cMethodName + "::cCategories list is null!";
	        		
	        		FacesContext.getCurrentInstance().addMessage(null, 
	        				new FacesMessage(FacesMessage.SEVERITY_ERROR, cMethodName, cMesage));
	        		
	        		System.out.println(cMesage);
	        		
	        		this.setLcRes(cRes = false);
	        	}
	        	else
	        	{
	        		for( Category cCategory: cCategories )
	        		{
	        			CategoryMO cCategoryMO = new CategoryMO(cCategory);
	        			
	        			this.cCategoriesMap.put(cCategoryMO.getRootbrowsenode(), cCategoryMO);
	        			
	        			this.cCategories.add(cCategoryMO);
	        		}
	        	}
	        }
	        //-----------------------------------------
	        if ( cRes )
	        {
	        	if ( (this.cCategories != null) && (this.cCategories.size() >= 1) )
	        	{
	        		this.setcSelectedCategory(this.cCategories.get(0));
	        	}
	        }
	        
	        this.setLcRes(cRes = true); return cRes;
    	}
    	catch( Exception e)
    	{
    		this.setLcRes(cRes = false);
			
			this.addMessage("ERROR", cMethodName, e.getMessage());
			
			System.out.println("M.V. Custom::" + cMethodName + "::" + e.getMessage());
    		
    		this.setLcRes(cRes = false); return cRes;
    	}
	}
	//------------------------------------------------------------------
	@SuppressWarnings("unchecked")
	public boolean getRootCategoriesNodes()
	{
		boolean cRes = true;
		
		String  cMethodName = "";
		
		try
    	{
			StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
	        StackTraceElement ste = stacktrace[1];
	        cMethodName = ste.getMethodName();

	        //-----------------------------------------
	        
	        Map<String, Object> cMethodParams = new TreeMap<String, Object>();
	        Map<String, Object> cMethodResults = new TreeMap<String, Object>();
	        
	        cMethodParams.put("p1", this.cSourceName);

	        //-----------------------------------------
	        if ( null == this.cOperationsBeanAWS )
	        {
	        	String cMesage = cMethodName + "::cOperationsBeanAWS not initialised!";
	        	
	        	FacesContext.getCurrentInstance().addMessage(null, 
	        			new FacesMessage(FacesMessage.SEVERITY_ERROR, cMethodName, cMesage));
	        	
	        	System.out.println(cMesage);
	        	
	        	this.setLcRes(cRes = false);
	        }
	        //-----------------------------------------
	        if ( cRes )
	        {
	        	cMethodResults = this.cOperationsBeanAWS.handleRequest(cMethodName, cMethodParams);
	        			
	        	if ( null == cMethodResults )
	        	{
	        		String cMesage = cMethodName + "::cOperationsBeanAWS not initialised!";
	        		
	        		FacesContext.getCurrentInstance().addMessage(null, 
	        				new FacesMessage(FacesMessage.SEVERITY_ERROR, cMethodName, cMesage));
	        		
	        		System.out.println(cMesage);
	        		
	        		this.setLcRes(cRes = false);
	        	}
	        }
	        //-----------------------------------------
	        if ( cRes )
	        {
	        	List<Node> cNodes =  (List<Node>)cMethodResults.get("r1");
	        	
	        	if ( null == cNodes )
	        	{
	        		String cMesage = cMethodName + "::Categories Nodes list is null!";
	        		
	        		FacesContext.getCurrentInstance().addMessage(null, 
	        				new FacesMessage(FacesMessage.SEVERITY_ERROR, cMethodName, cMesage));
	        		
	        		System.out.println(cMesage);
	        		
	        		this.setLcRes(cRes = false);
	        	}
	        	else
	        	{
	        		for( Node cNode : cNodes )
	        		{
	        			Node cParentNode = cNode.getNode();
	        			
	        			String cRootbrowsenode = cParentNode.getBrowsenodeid();//cNode.getNode2parentbrowsenode();
	        			
	        			if ( this.cCategoriesMap.containsKey(cRootbrowsenode))
	        			{
	        				CategoryMO cCategory = this.cCategoriesMap.get(cRootbrowsenode);
	        				
	        				NodeMO cNodeMO = new NodeMO();
	        				
	        				cNodeMO.setcNode(cNode);
	        				
	        				cCategory.getcCategoryNodes().add(cNodeMO);
	        			}
	        		}
	        	}
	        }
	        //-----------------------------------------
	     
	        
	        this.setLcRes(cRes = true); return cRes;
    	}
    	catch( Exception e)
    	{
    		this.setLcRes(cRes = false);
			
			this.addMessage("ERROR", cMethodName, e.getMessage());
			
			System.out.println("M.V. Custom::" + cMethodName + "::" + e.getMessage());
    		
    		this.setLcRes(cRes = false); return cRes;
    	}
	}
	
	//------------------------------------------------------------------
	public List<NodeMO> getRootCategoryNodes(String cRootBrowseNodeId)
	{
		boolean cRes = true;
		
		String  cMethodName = "";
		
		List<NodeMO> cCategoryNodes = new LinkedList<NodeMO>();
		
		try
		{
			StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
	        StackTraceElement ste = stacktrace[1];
	        cMethodName = ste.getMethodName();
	
	        //-----------------------------------------
	        if ( null == cRootBrowseNodeId)
	        {
	        	String cMesage = cMethodName + "::cRootBrowseNodeId not initialised!";
	        	
	        	FacesContext.getCurrentInstance().addMessage(null, 
	        			new FacesMessage(FacesMessage.SEVERITY_ERROR, cMethodName, cMesage));
	        	
	        	System.out.println(cMesage);
	        	
	        	this.setLcRes(cRes = false);
	        }
	        //-----------------------------------------
	        
	        if ( cRes )
	        {
				if ( this.cCategoriesMap.containsKey(cRootBrowseNodeId))
				{
					CategoryMO cCategory = this.cCategoriesMap.get(cRootBrowseNodeId);
					
					cCategoryNodes = cCategory.getcCategoryNodes();
				}
	        }
	        //-----------------------------------------
	     
	        
	        this.setLcRes(cRes = true); 
	        
	        return cCategoryNodes;
		}
		catch( Exception e)
		{
			this.setLcRes(cRes = false);
			
			this.addMessage("ERROR", cMethodName, e.getMessage());
			
			System.out.println("M.V. Custom::" + cMethodName + "::" + e.getMessage());
			
			return new LinkedList<NodeMO>();
		}
	}
	//------------------------------------------------------------------
	@SuppressWarnings("unchecked")
	public boolean getRootCategorySubNodes(CategoryMO cCategory)
	{
		boolean cRes = true;
		
		String  cMethodName = "";
		
		try
		{
			StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
	        StackTraceElement ste = stacktrace[1];
	        cMethodName = ste.getMethodName();
	
	        //-----------------------------------------
	        //CategoryMO cCategory = new CategoryMO();
	        
	        List<Node> cNodes = new LinkedList<Node>();
	        		
	        Map<String, Object> cMethodParams = new TreeMap<String, Object>();
	        Map<String, Object> cMethodResults = new TreeMap<String, Object>();
	        
	        
	        //-----------------------------------------
	        if ( null == cCategory )
	        {
	        	String cMesage = cMethodName + "::cCategory not initialised!";
	        	
	        	System.out.println(cMesage);
	        	
	        	this.setLcRes(cRes = false);
	        }
	        //-----------------------------------------
	        if ( null == this.cOperationsBeanAWS )
	        {
	        	String cMesage = cMethodName + "::cOperationsBeanAWS not initialised!";
	        	
	        	FacesContext.getCurrentInstance().addMessage(null, 
	        			new FacesMessage(FacesMessage.SEVERITY_ERROR, cMethodName, cMesage));
	        	
	        	System.out.println(cMesage);
	        	
	        	this.setLcRes(cRes = false);
	        }
	        //-----------------------------------------
	        
	        /*if ( cRes )
	        {
	    		String cRootbrowsenode = cCategory.getRootbrowsenode();
			
	    		if ( this.cCategoriesMap.containsKey(cRootbrowsenode))
				{
					cCategory = this.cCategoriesMap.get(cRootbrowsenode);
				}
				else
				{
					this.setLcRes(cRes = false);
				}
	        }*/
	        
	        //-----------------------------------------
	        if ( cRes )
	        {
	        	cMethodParams.put("p1", this.cSourceName);
	        	cMethodParams.put("p2", cCategory.getRootbrowsenode());
	        	
	        	cMethodResults = this.cOperationsBeanAWS.handleRequest(cMethodName, cMethodParams);
	        			
	        	if ( null == cMethodResults )
	        	{
	        		String cMesage = cMethodName + "::cOperationsBeanAWS not initialised!";
	        		
	        		FacesContext.getCurrentInstance().addMessage(null, 
	        				new FacesMessage(FacesMessage.SEVERITY_ERROR, cMethodName, cMesage));
	        		
	        		System.out.println(cMesage);
	        		
	        		this.setLcRes(cRes = false);
	        	}
	        }
	        //-----------------------------------------
	        if ( cRes )
	        {
	        	cNodes =  (List<Node>)cMethodResults.get("r1");
	        	
	        	if ( null == cNodes )
	        	{
	        		String cMesage = cMethodName + "::Categories Sub Nodes list is null!";
	        		
	        		FacesContext.getCurrentInstance().addMessage(null, 
	        				new FacesMessage(FacesMessage.SEVERITY_ERROR, cMethodName, cMesage));
	        		
	        		System.out.println(cMesage);
	        		
	        		this.setLcRes(cRes = false);
	        	}
	        }
	        //-----------------------------------------
	        for( NodeMO cCatNode : cCategory.getcCategoryNodes())
			{
	        	cCatNode.getcSubNodes().clear();
			}
	        //-----------------------------------------
			if ( cRes )
			{
	    		for( Node cNode : cNodes )
	    		{
	    			//String cParentbrowsenode = cNode.getNode2parentbrowsenode();
	    			Node cParentNode = cNode.getNode();
        			
        			String cParentbrowsenode = cParentNode.getBrowsenodeid();
	    			
					for( NodeMO cCatNode : cCategory.getcCategoryNodes())
					{
						if ( cCatNode.getBrowsenodeid().equals(cParentbrowsenode))
						{
							NodeMO cNodeMO = new NodeMO();
	        				
	        				cNodeMO.setcNode(cNode);
	        				
							cCatNode.getcSubNodes().add(cNodeMO);
						}
					}
	    		}
			}
	        
	        //-----------------------------------------
	        
	        this.setLcRes(cRes = true); 
	        
	        return cRes;
		}
		catch( Exception e)
		{
			this.setLcRes(cRes = false);
			
			this.addMessage("ERROR", cMethodName, e.getMessage());
			
			System.out.println("M.V. Custom::" + cMethodName + "::" + e.getMessage());
			
			this.setLcRes(cRes = false); return cRes;
		}
	}

	//--------
	public LinkedList<Item> getSelectedCategoryItems(long cPageNum)
	{
		boolean cRes = true;
		
		String cMethodName = "";
		
		LinkedList<Item> cItems = new LinkedList<Item>();
		
		try
		{
			StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
	        StackTraceElement ste = stacktrace[1];
	        cMethodName = ste.getMethodName();
	        
			if ( null == this.getcSelectedCategory() )
			{
				System.out.println("M.V. Custom::" + cMethodName + "::cSelectedCategory is null!");
				
				cRes = false;
			}
			
			if ( cRes )
			{
				/*LinkedHashMap<Long, LinkedList<Item>> cItemsMap = 
						new LinkedHashMap<Long, LinkedList<Item>>();
				
				if ( this.cNodeLevel == NodeLevel.CATEGORY )
				{
					cItemsMap = this.getcSelectedCategory().getcItems();
				}
				else if ( this.cNodeLevel == NodeLevel.NODE )
				{
					cItemsMap = this.getcSelectedCategoryNode().getcItems();
				}
				else if ( this.cNodeLevel == NodeLevel.SUBNODE )
				{
					cItemsMap = this.getcSelectedCategorySubNode().getcItems();
				}
				else
				{
					cItemsMap = this.getcSelectedCategory().getcItems();
				}
				
				for ( Map.Entry<Long, LinkedList<Item>> cItemsEntry : cItemsMap.entrySet() )
				{
					if ( cItemsEntry.getKey().longValue() == cPageNum )
					{
						cItems.addAll(cItemsEntry.getValue());
					}
				}*/
				for ( Map.Entry<Long, LinkedList<Item>> cItemsEntry : this.cSourceItemsMap.entrySet() )
				{
					if ( cItemsEntry.getKey().longValue() == cPageNum )
					{
						cItems.addAll(cItemsEntry.getValue());
					}
				}
			}
			
			return cItems;
		}
		catch( Exception e)
		{
			this.setLcRes(cRes = false);
			
			this.addMessage("ERROR", cMethodName, e.getMessage());
			
			System.out.println("M.V. Custom::" + cMethodName + "::" + e.getMessage());
			
			return new LinkedList<Item>();
		}
	}
	//--------
	public LinkedList<Item> getSelectedCategoryItems(long cPageNumStart, long cPageNumEnd)
	{
		boolean cRes = true;
		
		String cMethodName = "";
		
		LinkedList<Item> cItems = new LinkedList<Item>();
		
		try
		{
			StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
	        StackTraceElement ste = stacktrace[1];
	        cMethodName = ste.getMethodName();
	        
			if ( null == this.getcSelectedCategory() )
			{
				System.out.println("M.V. Custom::" + cMethodName + "::cSelectedCategory is null!");
				
				cRes = false;
			}
			
			if ( cRes )
			{
				/*LinkedHashMap<Long, LinkedList<Item>> cItemsMap = 
						new LinkedHashMap<Long, LinkedList<Item>>();
				
				if ( this.cNodeLevel == NodeLevel.CATEGORY )
				{
					cItemsMap = this.getcSelectedCategory().getcItems();
				}
				else if ( this.cNodeLevel == NodeLevel.NODE )
				{
					cItemsMap = this.getcSelectedCategoryNode().getcItems();
				}
				else if ( this.cNodeLevel == NodeLevel.SUBNODE )
				{
					cItemsMap = this.getcSelectedCategorySubNode().getcItems();
				}
				else
				{
					cItemsMap = this.getcSelectedCategory().getcItems();
				}
				
				for ( Map.Entry<Long, LinkedList<Item>> cItemsEntry : cItemsMap.entrySet() )
				{
					long cPageNumKey = cItemsEntry.getKey().longValue();
					
					if ( (cPageNumKey >= cPageNumStart) && (cPageNumKey <= cPageNumEnd))
					{
						cItems.addAll(cItemsEntry.getValue());
					}
				}*/
				
				for ( Map.Entry<Long, LinkedList<Item>> cItemsEntry : this.cSourceItemsMap.entrySet() )
				{
					long cPageNumKey = cItemsEntry.getKey().longValue();
					
					if ( (cPageNumKey >= cPageNumStart) && (cPageNumKey <= cPageNumEnd))
					{
						cItems.addAll(cItemsEntry.getValue());
					}
				}
			}
			
			return cItems;
		}
		catch( Exception e)
		{
			this.setLcRes(cRes = false);
			
			this.addMessage("ERROR", cMethodName, e.getMessage());
			
			System.out.println("M.V. Custom::" + cMethodName + "::" + e.getMessage());
			
			return new LinkedList<Item>();
		}
	}
	
	@SuppressWarnings({ "unchecked" })
	public boolean getCategoryButtonItemSearchList(CategoryMO cCategory, long cItemPage, boolean isResetData)
	{
		boolean cRes = true;
		
		String  cMethodName = "";
		
		List<Items> cSearchItems = new LinkedList<Items>();
		
		try
		{
			StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
	        StackTraceElement ste = stacktrace[1];
	        cMethodName = ste.getMethodName();
	
	        //-----------------------------------------
	        Map<String, Object> cMethodParams = new TreeMap<String, Object>();
	        Map<String, Object> cMethodResults = new TreeMap<String, Object>();
	        
	        Map<String, String> params = new HashMap<String, String>();
	        
	        if ( null == this.cOperationsBeanAWS )
	        {
	        	String cMesage = cMethodName + "::cOperationsBeanAWS not initialised!";
	        	
	        	FacesContext.getCurrentInstance().addMessage(null, 
	        			new FacesMessage(FacesMessage.SEVERITY_ERROR, cMethodName, cMesage));
	        	
	        	System.out.println(cMesage);
	        	
	        	this.setLcRes(cRes = false);
	        }
	        
	        if ( null == cCategory.getName() )
	        {
	        	String cMesage = cMethodName + "::cSerachIndex is null!";
	        	
	        	System.out.println(cMesage);
	        	
	        	this.setLcRes(cRes = false);
	        }
	        
	        if ( null == cCategory.getRootbrowsenode() )
	        {
	        	String cMesage = cMethodName + "::cBrowseNode is null!";
	        	
	        	System.out.println(cMesage);
	        	
	        	this.setLcRes(cRes = false);
	        }
	        //-----------------------------------------
	        if ( cRes )
	        {
	        	String cSerachIndex = cCategory.getSearchIndex();
	        	
	        	params.put("SearchIndex", cSerachIndex);
	 	        params.put("ResponseGroup", "Images,ItemAttributes,ItemIds,Offers,SalesRank");
	 	        //params.put("Sort", "salesrank");
	 	      
	 	        params.put("Keywords", this.getcCategorySearchInputText());
	 	        params.put("Availability", "Available");
	 	        params.put("ItemPage", String.valueOf(cItemPage));
	 	        params.put("Condition", "All");
	 	        
	 	        cMethodParams.put("p1", params);
	 	        
	        	String cRequestMethodName = "handleItemSearchList"; 
	        	
	        	cMethodResults = this.cOperationsBeanAWS.handleRequest(cRequestMethodName, cMethodParams);
	        			
	        	if ( null == cMethodResults )
	        	{
	        		String cMesage = cMethodName + "::cOperationsBeanAWS not initialised!";
	        		
	        		FacesContext.getCurrentInstance().addMessage(null, 
	        				new FacesMessage(FacesMessage.SEVERITY_ERROR, cMethodName, cMesage));
	        		
	        		System.out.println(cMesage);
	        		
	        		this.setLcRes(cRes = false);
	        	}
	        }
	        //---
	        if ( cRes )
	        {
	        	cSearchItems =  (List<Items>)cMethodResults.get("r1");
	        	
	        	if ( null == cSearchItems )
	        	{
	        		String cMesage = cMethodName + "::Categories Nodes list is null!";
	        		
	        		FacesContext.getCurrentInstance().addMessage(null, 
	        				new FacesMessage(FacesMessage.SEVERITY_ERROR, cMethodName, cMesage));
	        		
	        		System.out.println(cMesage);
	        		
	        		this.setLcRes(cRes = false);
	        	}
	        }
	        //---
	        if ( cRes )
	        {
	        	if ( isResetData )
        		{
        			for ( Map.Entry<Long, LinkedList<Item>> cSourceItemEntry : cSourceItemsMap.entrySet() )
        			{
        				cSourceItemEntry.getValue().clear();
        			}
        		}
	        }
	        //---
	        if ( cRes )
	        {
        		for( Items cSearchItem : cSearchItems )
        		{
        			List<Item> cItems = cSearchItem.getItem();
        			
        			for( Item cItem : cItems )
        			{
        				System.out.println(cMethodName + "::" + cItem.getASIN());
        			}
        			
        			if( this.cSourceItemsMap != null )
        			{
        				Long cItemPageKey = new Long(cItemPage);
        				
        				if ( this.cSourceItemsMap.containsKey(cItemPageKey) )
        				{
        					List<Item> cItemsCurrent = this.cSourceItemsMap.get(cItemPageKey);
        					
        					cItemsCurrent.clear();
        					
        					cItemsCurrent.addAll(cItems);
        				}
        				else
        				{
        					LinkedList<Item> cItemsCurrent = new LinkedList<Item>();
        					
        					cItemsCurrent.addAll(cItems);
        					
        					this.cSourceItemsMap.put(cItemPageKey, cItemsCurrent);
        				}
        			}
        		}
	        }
	        //---
	        
	        this.setLcRes(cRes = true); 
	        
	        return cRes;
		}
		catch( Exception e)
		{
			this.addMessage("ERROR", cMethodName, e.getMessage());
			
			System.out.println("M.V. Custom::" + cMethodName + "::" + e.getMessage());
			
			this.setLcRes(cRes = false); 
			
			return cRes;
		}
	}
	
	//------------------------------------------------------------------
	@SuppressWarnings({ "unchecked" })
	public boolean getCategoryItemSearchList(CategoryMO cCategory, long cItemPage, boolean isResetData)
	{
		boolean cRes = true;
		
		String  cMethodName = "";
		
		List<Items> cSearchItems = new LinkedList<Items>();
				
		try
		{
			StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
	        StackTraceElement ste = stacktrace[1];
	        cMethodName = ste.getMethodName();
	
	        //-----------------------------------------
	        Map<String, Object> cMethodParams = new TreeMap<String, Object>();
	        Map<String, Object> cMethodResults = new TreeMap<String, Object>();
	        
	        Map<String, String> params = new HashMap<String, String>();
	        
	        if ( null == this.cOperationsBeanAWS )
	        {
	        	String cMesage = cMethodName + "::cOperationsBeanAWS not initialised!";
	        	
	        	FacesContext.getCurrentInstance().addMessage(null, 
	        			new FacesMessage(FacesMessage.SEVERITY_ERROR, cMethodName, cMesage));
	        	
	        	System.out.println(cMesage);
	        	
	        	this.setLcRes(cRes = false);
	        }
	        
	        if ( null == cCategory.getName() )
	        {
	        	String cMesage = cMethodName + "::cSerachIndex is null!";
	        	
	        	System.out.println(cMesage);
	        	
	        	this.setLcRes(cRes = false);
	        }
	        
	        if ( null == cCategory.getRootbrowsenode() )
	        {
	        	String cMesage = cMethodName + "::cBrowseNode is null!";
	        	
	        	System.out.println(cMesage);
	        	
	        	this.setLcRes(cRes = false);
	        }
	        //---
	        if ( cRes )
	        {
		        /* Example
		        params.put("SearchIndex", "Baby");
		        params.put("ResponseGroup", "Images,ItemAttributes,ItemIds,Offers,SalesRank");
		        params.put("Sort", "salesrank");
		        params.put("BrowseNode", "10286847011");
		        params.put("Availability", "Available");
		        params.put("ItemPage", "1");
		        params.put("Condition", "All");
		        */
		   
	        	String cSerachIndex = cCategory.getSearchIndex();
	        	String cBrowseNode = cCategory.getRootbrowsenode();
	        	
	        	params.put("SearchIndex", cSerachIndex);
	 	        params.put("ResponseGroup", "Images,ItemAttributes,ItemIds,Offers,SalesRank");
	 	        //params.put("Sort", "salesrank");
	 	        
	 	        //---if not All 
	 	        params.put("BrowseNode", cBrowseNode);
	 	        params.put("Availability", "Available");
	 	        params.put("ItemPage", String.valueOf(cItemPage));
	 	        params.put("Condition", "All");
	 	        
	 	        cMethodParams.put("p1", params);
	 	        
	        	String cRequestMethodName = "handleItemSearchList"; 
	        	
	        	cMethodResults = this.cOperationsBeanAWS.handleRequest(cRequestMethodName, cMethodParams);
	        			
	        	if ( null == cMethodResults )
	        	{
	        		String cMesage = cMethodName + "::cOperationsBeanAWS not initialised!";
	        		
	        		FacesContext.getCurrentInstance().addMessage(null, 
	        				new FacesMessage(FacesMessage.SEVERITY_ERROR, cMethodName, cMesage));
	        		
	        		System.out.println(cMesage);
	        		
	        		this.setLcRes(cRes = false);
	        	}
	        }
	        //---
	        if ( cRes )
	        {
	        	cSearchItems =  (List<Items>)cMethodResults.get("r1");
	        	
	        	if ( null == cSearchItems )
	        	{
	        		String cMesage = cMethodName + "::Categories Nodes list is null!";
	        		
	        		FacesContext.getCurrentInstance().addMessage(null, 
	        				new FacesMessage(FacesMessage.SEVERITY_ERROR, cMethodName, cMesage));
	        		
	        		System.out.println(cMesage);
	        		
	        		this.setLcRes(cRes = false);
	        	}
	        }
	        //---
        	if ( cRes )
	        {
        		if ( isResetData )
        		{
        			for ( Map.Entry<Long, LinkedList<Item>> cSourceItemEntry : cSourceItemsMap.entrySet() )
        			{
        				cSourceItemEntry.getValue().clear();
        			}
        		}
	        }
        	//---
        	if ( cRes )
	        {
        		for( Items cSearchItem : cSearchItems )
        		{
        			List<Item> cItems = cSearchItem.getItem();
        			
        			for( Item cItem : cItems )
        			{
        				System.out.println(cMethodName + "::" + cItem.getASIN());
        			}
        			
        			if( this.cSourceItemsMap != null )
        			{
        				Long cItemPageKey = new Long(cItemPage);
        				
        				if ( this.cSourceItemsMap.containsKey(cItemPageKey) )
        				{
        					List<Item> cItemsCurrent = this.cSourceItemsMap.get(cItemPageKey);
        					
        					cItemsCurrent.clear();
        					
        					cItemsCurrent.addAll(cItems);
        				}
        				else
        				{
        					LinkedList<Item> cItemsCurrent = new LinkedList<Item>();
        					
        					cItemsCurrent.addAll(cItems);
        					
        					this.cSourceItemsMap.put(cItemPageKey, cItemsCurrent);
        				}
        			}
        		}
	        }
	        //---
	        
	        this.setLcRes(cRes = true); return cRes;
		}
		catch( Exception e)
		{
			this.setLcRes(cRes = false);
			
			this.addMessage("ERROR", cMethodName, e.getMessage());
			
			System.out.println("M.V. Custom::" + cMethodName + "::" + e.getMessage());
			
			this.setLcRes(cRes = false); return cRes;
		}
	}

	//------------------------------------------------------------------
	@SuppressWarnings({ "unchecked" })
	public boolean getCategoryNodeItemSearchList(CategoryMO cCategory, NodeMO cNode, long cItemPage, boolean isResetData)
	{
		boolean cRes = true;
		
		String  cMethodName = "";
		
		List<Items> cSearchItems = new LinkedList<Items>();
		
		try
		{
			StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
	        StackTraceElement ste = stacktrace[1];
	        cMethodName = ste.getMethodName();
	
	        //-----------------------------------------
	        Map<String, Object> cMethodParams = new TreeMap<String, Object>();
	        Map<String, Object> cMethodResults = new TreeMap<String, Object>();
	        
	        Map<String, String> params = new HashMap<String, String>();
	        
	        if ( null == this.cOperationsBeanAWS )
	        {
	        	String cMesage = cMethodName + "::cOperationsBeanAWS not initialised!";
	        	
	        	FacesContext.getCurrentInstance().addMessage(null, 
	        			new FacesMessage(FacesMessage.SEVERITY_ERROR, cMethodName, cMesage));
	        	
	        	System.out.println(cMesage);
	        	
	        	this.setLcRes(cRes = false);
	        }
	        
	        if ( null == cCategory )
	        {
	        	String cMesage = cMethodName + "::cCategory is null!";
	        	
	        	System.out.println(cMesage);
	        	
	        	this.setLcRes(cRes = false);
	        }
	        
	        if ( null == cNode )
	        {
	        	String cMesage = cMethodName + "::cNode is null!";
	        	
	        	System.out.println(cMesage);
	        	
	        	this.setLcRes(cRes = false);
	        }
	        
	        if ( null == cNode.getName() )
	        {
	        	String cMesage = cMethodName + "::cSerachName is null!";
	        	
	        	System.out.println(cMesage);
	        	
	        	this.setLcRes(cRes = false);
	        }
	        
	        if ( null == cNode.getBrowsenodeid() )
	        {
	        	String cMesage = cMethodName + "::cBrowseNodeId is null!";
	        	
	        	System.out.println(cMesage);
	        	
	        	this.setLcRes(cRes = false);
	        }
	        //---
	        if ( cRes )
	        {
	        	String cSerachIndex = cCategory.getSearchIndex();
	        	String cBrowseNode = cNode.getBrowsenodeid();
	        	
	        	params.put("SearchIndex", cSerachIndex);
	 	        params.put("ResponseGroup", "Images,ItemAttributes,ItemIds,Offers,SalesRank");
	 	        //params.put("Sort", "salesrank");
	 	        params.put("BrowseNode", cBrowseNode);
	 	        params.put("Availability", "Available");
	 	        params.put("ItemPage", String.valueOf(cItemPage));
	 	        params.put("Condition", "All");
	 	        
	 	        cMethodParams.put("p1", params);
	 	        
	        	String cRequestMethodName = "handleItemSearchList"; 
	        	
	        	cMethodResults = this.cOperationsBeanAWS.handleRequest(cRequestMethodName, cMethodParams);
	        			
	        	if ( null == cMethodResults )
	        	{
	        		String cMesage = cMethodName + "::cOperationsBeanAWS not initialised!";
	        		
	        		FacesContext.getCurrentInstance().addMessage(null, 
	        				new FacesMessage(FacesMessage.SEVERITY_ERROR, cMethodName, cMesage));
	        		
	        		System.out.println(cMesage);
	        		
	        		this.setLcRes(cRes = false);
	        	}
	        }
	        //---
	        if ( cRes )
	        {
	        	cSearchItems =  (List<Items>)cMethodResults.get("r1");
	        	
	        	if ( null == cSearchItems )
	        	{
	        		String cMesage = cMethodName + "::Categories Nodes list is null!";
	        		
	        		FacesContext.getCurrentInstance().addMessage(null, 
	        				new FacesMessage(FacesMessage.SEVERITY_ERROR, cMethodName, cMesage));
	        		
	        		System.out.println(cMesage);
	        		
	        		this.setLcRes(cRes = false);
	        	}
	        }
	        //---
	        if ( cRes )
	        {
	        	if ( isResetData )
        		{
        			for ( Map.Entry<Long, LinkedList<Item>> cSourceItemEntry : cSourceItemsMap.entrySet() )
        			{
        				cSourceItemEntry.getValue().clear();
        			}
        		}
	        }
	        //---
	        if ( cRes )
	        {
	        	for( Items cSearchItem : cSearchItems )
        		{
        			List<Item> cItems = cSearchItem.getItem();
        			
        			for( Item cItem : cItems )
        			{
        				System.out.println(cMethodName + "::" + cItem.getASIN());
        			}
        			
        			if( this.cSourceItemsMap != null )
        			{
        				Long cItemPageKey = new Long(cItemPage);
        				
        				if ( this.cSourceItemsMap.containsKey(cItemPageKey) )
        				{
        					List<Item> cItemsCurrent = this.cSourceItemsMap.get(cItemPageKey);
        					
        					cItemsCurrent.clear();
        					
        					cItemsCurrent.addAll(cItems);
        				}
        				else
        				{
        					LinkedList<Item> cItemsCurrent = new LinkedList<Item>();
        					
        					cItemsCurrent.addAll(cItems);
        					
        					this.cSourceItemsMap.put(cItemPageKey, cItemsCurrent);
        				}
        			}
        		}
	        }
	        //---
	        
	        this.setLcRes(cRes = true); return cRes;
		}
		catch( Exception e)
		{
			this.setLcRes(cRes = false);
			
			this.addMessage("ERROR", cMethodName, e.getMessage());
			
			System.out.println("M.V. Custom::" + cMethodName + "::" + e.getMessage());
			
			this.setLcRes(cRes = false); return cRes;
		}
	}
		
	//------------------------------------------------------------------
	@SuppressWarnings({ "unchecked" })
	public boolean getCategorySubNodeItemSearchList(CategoryMO cCategory, NodeMO cNode, long cItemPage, boolean isResetData)
	{
		boolean cRes = true;
		
		String  cMethodName = "";
		
		List<Items> cSearchItems = new LinkedList<Items>();
		
		try
		{
			StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
	        StackTraceElement ste = stacktrace[1];
	        cMethodName = ste.getMethodName();
	
	        //---
	        Map<String, Object> cMethodParams = new TreeMap<String, Object>();
	        Map<String, Object> cMethodResults = new TreeMap<String, Object>();
	        
	        Map<String, String> params = new HashMap<String, String>();
	        
	        if ( null == this.cOperationsBeanAWS )
	        {
	        	String cMesage = cMethodName + "::cOperationsBeanAWS not initialised!";
	        	
	        	FacesContext.getCurrentInstance().addMessage(null, 
	        			new FacesMessage(FacesMessage.SEVERITY_ERROR, cMethodName, cMesage));
	        	
	        	System.out.println(cMesage);
	        	
	        	this.setLcRes(cRes = false);
	        }
	        
	        if ( null == cCategory )
	        {
	        	String cMesage = cMethodName + "::cCategory is null!";
	        	
	        	System.out.println(cMesage);
	        	
	        	this.setLcRes(cRes = false);
	        }
	        
	        if ( null == cNode )
	        {
	        	String cMesage = cMethodName + "::cNode is null!";
	        	
	        	System.out.println(cMesage);
	        	
	        	this.setLcRes(cRes = false);
	        }
	        
	        if ( null == cNode.getName() )
	        {
	        	String cMesage = cMethodName + "::cSerachName is null!";
	        	
	        	System.out.println(cMesage);
	        	
	        	this.setLcRes(cRes = false);
	        }
	        
	        if ( null == cNode.getBrowsenodeid() )
	        {
	        	String cMesage = cMethodName + "::cBrowseNodeId is null!";
	        	
	        	System.out.println(cMesage);
	        	
	        	this.setLcRes(cRes = false);
	        }
	        //---
	        if ( cRes )
	        {
	        	String cSerachIndex = cCategory.getSearchIndex();
	        	String cBrowseNode = cNode.getBrowsenodeid();
	        	
	        	params.put("SearchIndex", cSerachIndex);
	 	        params.put("ResponseGroup", "Images,ItemAttributes,ItemIds,Offers,SalesRank");
	 	        //params.put("Sort", "salesrank");
	 	        params.put("BrowseNode", cBrowseNode);
	 	        params.put("Availability", "Available");
	 	        params.put("ItemPage", String.valueOf(cItemPage));
	 	        params.put("Condition", "All");
	 	        
	 	        cMethodParams.put("p1", params);
	 	        
	        	String cRequestMethodName = "handleItemSearchList"; 
	        	
	        	cMethodResults = this.cOperationsBeanAWS.handleRequest(cRequestMethodName, cMethodParams);
	        			
	        	if ( null == cMethodResults )
	        	{
	        		String cMesage = cMethodName + "::cOperationsBeanAWS not initialised!";
	        		
	        		FacesContext.getCurrentInstance().addMessage(null, 
	        				new FacesMessage(FacesMessage.SEVERITY_ERROR, cMethodName, cMesage));
	        		
	        		System.out.println(cMesage);
	        		
	        		this.setLcRes(cRes = false);
	        	}
	        }
	        //---
	        if ( cRes )
	        {
	        	cSearchItems =  (List<Items>)cMethodResults.get("r1");
	        	
	        	if ( null == cSearchItems )
	        	{
	        		String cMesage = cMethodName + "::Categories Nodes list is null!";
	        		
	        		FacesContext.getCurrentInstance().addMessage(null, 
	        				new FacesMessage(FacesMessage.SEVERITY_ERROR, cMethodName, cMesage));
	        		
	        		System.out.println(cMesage);
	        		
	        		this.setLcRes(cRes = false);
	        	}
	        }
	        //---
	        if ( cRes )
	        {
	        	if ( isResetData )
        		{
        			for ( Map.Entry<Long, LinkedList<Item>> cSourceItemEntry : cSourceItemsMap.entrySet() )
        			{
        				cSourceItemEntry.getValue().clear();
        			}
        		}
	        }
	        //---
	        if ( cRes )
	        {
	        	for( Items cSearchItem : cSearchItems )
        		{
        			List<Item> cItems = cSearchItem.getItem();
        			
        			for( Item cItem : cItems )
        			{
        				System.out.println(cMethodName + "::" + cItem.getASIN());
        			}
        			
        			if( this.cSourceItemsMap != null )
        			{
        				Long cItemPageKey = new Long(cItemPage);
        				
        				if ( this.cSourceItemsMap.containsKey(cItemPageKey) )
        				{
        					List<Item> cItemsCurrent = this.cSourceItemsMap.get(cItemPageKey);
        					
        					cItemsCurrent.clear();
        					
        					cItemsCurrent.addAll(cItems);
        				}
        				else
        				{
        					LinkedList<Item> cItemsCurrent = new LinkedList<Item>();
        					
        					cItemsCurrent.addAll(cItems);
        					
        					this.cSourceItemsMap.put(cItemPageKey, cItemsCurrent);
        				}
        			}
        		}
	        }
	        //---
	        
	        this.setLcRes(cRes = true); 
	        
	        return cRes;
		}
		catch( Exception e)
		{
			this.setLcRes(cRes = false);
			
			this.addMessage("ERROR", cMethodName, e.getMessage());
			
			System.out.println("M.V. Custom::" + cMethodName + "::" + e.getMessage());
			
			this.setLcRes(cRes = false); return cRes;
		}
	}
		
	//------------------------------------------------------------------
	public boolean buildCategoriesMenuModel() 
	{
		boolean cRes = true;
		
		String cMethodName = "";
		
		try
		{
			StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
	        StackTraceElement ste = stacktrace[1];
	        cMethodName = ste.getMethodName();
	        
	        this.cCategoriesMenuModel = new DefaultMenuModel();
	        
	        for( CategoryMO cCategory : this.cCategories)
	        {
	        	DefaultSubMenu cSubMenu = new DefaultSubMenu(cCategory.getName());
	        	
	        	this.cCategoriesMenuModel.addElement(cSubMenu);
	        	
	        	//-----
	        	DefaultMenuItem cMenuItem = new DefaultMenuItem();
	        		
	        	String update  = ":pAmazonMainForm:pAmazonMainMessages";
	        	String value   = "All Items";
	        	String id      = cCategory.getName() + "#" + value + "#" + String.valueOf(cCategory.getCategoryid());
	        	String command = String.format("#{cAmazonDataBean.onSetCategoriesMenu('%s')}", id);
	        	
	        	cMenuItem.setId(id);
	        	cMenuItem.setValue(value);
	        	cMenuItem.setCommand(command);
	        	cMenuItem.setUpdate(update);
	        	cMenuItem.setProcess("@this");
	        	//cMenuItem.setAjax(true);
	        	
	        	cSubMenu.addElement(cMenuItem);

	        }
	        
			this.setLcRes(cRes);
			
			return cRes;
		}
		catch( Exception e)
		{
			this.setLcRes(cRes = false);
			
			this.addMessage("ERROR", cMethodName, e.getMessage());
			
			System.out.println("M.V. Custom::" + cMethodName + "::" + e.getMessage());
			
			return cRes;
		}
    }
	
	//------------------------------------------------------------------
	public boolean buildCategoriesSearchList() 
	{
		boolean cRes = true;
		
		String cMethodName = "";
		
		try
		{
			StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
	        StackTraceElement ste = stacktrace[1];
	        cMethodName = ste.getMethodName();
        	
	        this.cCategoriesItems.add(new SelectItem(
        			"All", "All Departments"));
	        
	        for( CategoryMO cCategory : this.cCategories)
	        {
	        	this.cCategoriesItems.add(new SelectItem(
	        			cCategory.getName(), cCategory.getName()));
	        }
        	
        	//---
			this.setLcRes(cRes);
			
			return cRes;
		}
		catch( Exception e)
		{
			this.setLcRes(cRes = false);
			
			this.addMessage("ERROR", cMethodName, e.getMessage());
			
			System.out.println("M.V. Custom::" + cMethodName + "::" + e.getMessage());
			
			return cRes;
		}
    }
	//------------------------------------------------------------------
	public void onSetCategorySearchMenu(Object x) 
	{
		boolean cRes = true;
		
		String cMethodName = "";
		
		try
		{
			StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
	        StackTraceElement ste = stacktrace[1];
	        cMethodName = ste.getMethodName();
	     
	        if ( null == x )
	        {
	        	FacesMessage msg = new FacesMessage(
		        		FacesMessage.SEVERITY_ERROR, cMethodName, "Selected Menu Item is null!");
					
				FacesContext.getCurrentInstance().addMessage(null, msg);
				
				System.out.println("M.V. Custom::" + cMethodName + "Selected Menu Item is null!");
				
				this.setLcRes(cRes = false);
	        }
	        
	        if ( cRes )
	        {
	        	this.setcNodeLevel(NodeLevel.CATEGORY);
	        }
	        
	        if ( cRes )
	        {
	        	String selectedId = String.valueOf((String)x);
	    
	        	FacesMessage msg = new FacesMessage(
		        		FacesMessage.SEVERITY_INFO, cMethodName, "Selected Menu Item is:" + selectedId);
					
				FacesContext.getCurrentInstance().addMessage(null, msg);
				
	        	System.out.println("Menu changed " + selectedId);
	        }
		}
		catch( Exception e)
		{
			this.setLcRes(cRes = false);
			
			this.addMessage("ERROR", cMethodName, e.getMessage());
			
			System.out.println("M.V. Custom::" + cMethodName + "::" + e.getMessage());
		}
	}

	//------------------------------------------------------------------
	public void onSetCategoryMenu(Object x) 
	{
		boolean cRes = true;
		
		String cMethodName = "";
		
		try
		{
			StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
	        StackTraceElement ste = stacktrace[1];
	        cMethodName = ste.getMethodName();
	     
	        if ( null == x )
	        {
	        	FacesMessage msg = new FacesMessage(
		        		FacesMessage.SEVERITY_ERROR, cMethodName, "Selected Menu Item is null!");
					
				FacesContext.getCurrentInstance().addMessage(null, msg);
				
				System.out.println("M.V. Custom::" + cMethodName + "Selected Menu Item is null!");
				
				this.setLcRes(cRes = false);
	        }
	        
	        if ( cRes )
	        {
	        	this.setcNodeLevel(NodeLevel.CATEGORY);
	        }
	        
	        if ( cRes )
	        {
	        	String selectedId = String.valueOf((String)x);
	    
	        	FacesMessage msg = new FacesMessage(
		        		FacesMessage.SEVERITY_INFO, cMethodName, "Selected Menu Item is:" + selectedId);
					
				FacesContext.getCurrentInstance().addMessage(null, msg);
				
	        	System.out.println("Menu changed " + selectedId);
	        }
		}
		catch( Exception e)
		{
			this.setLcRes(cRes = false);
			
			this.addMessage("ERROR", cMethodName, e.getMessage());
			
			System.out.println("M.V. Custom::" + cMethodName + "::" + e.getMessage());
		}
	}
	
	//------------------------------------------------------------------
	public void onSetCategoryNodeMenu(Object x) 
	{
		boolean cRes = true;
		
		String cMethodName = "";
		
		try
		{
			StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
	        StackTraceElement ste = stacktrace[1];
	        cMethodName = ste.getMethodName();
	     
	        if ( null == x )
	        {
	        	FacesMessage msg = new FacesMessage(
		        		FacesMessage.SEVERITY_ERROR, cMethodName, "Selected Menu Item is null!");
					
				FacesContext.getCurrentInstance().addMessage(null, msg);
				
				System.out.println("M.V. Custom::" + cMethodName + "Selected Menu Item is null!");
				
				this.setLcRes(cRes = false);
	        }
	        
	        if ( cRes )
	        {
	        	this.setcNodeLevel(NodeLevel.NODE);
	        }
	        
	        if ( cRes )
	        {
	        	String selectedId = String.valueOf((String)x);
	    
	        	FacesMessage msg = new FacesMessage(
		        		FacesMessage.SEVERITY_INFO, cMethodName, "Selected Menu Item is:" + selectedId);
					
				FacesContext.getCurrentInstance().addMessage(null, msg);
				
	        	System.out.println("Menu changed " + selectedId);
	        }
		}
		catch( Exception e)
		{
			this.setLcRes(cRes = false);
			
			this.addMessage("ERROR", cMethodName, e.getMessage());
			
			System.out.println("M.V. Custom::" + cMethodName + "::" + e.getMessage());
		}
	}
	//------------------------------------------------------------------
	public void onSetCategorySubNodeMenu(Object x) 
	{
		boolean cRes = true;
		
		String cMethodName = "";
		
		try
		{
			StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
	        StackTraceElement ste = stacktrace[1];
	        cMethodName = ste.getMethodName();
	     
	        String selectedId = "";
	        String cSubNodeBrowseId = "";
	        	
	        boolean isSubNodeFound = false;
	        
	        if ( null == x )
	        {
	        	FacesMessage msg = new FacesMessage(
		        		FacesMessage.SEVERITY_ERROR, cMethodName, "Selected Menu Item is null!");
					
				FacesContext.getCurrentInstance().addMessage(null, msg);
				
				System.out.println("M.V. Custom::" + cMethodName + "Selected Menu Item is null!");
				
				this.setLcRes(cRes = false);
	        }
	        
	        if ( cRes )
	        {
	        	this.setcNodeLevel(NodeLevel.SUBNODE);
	        }
	        
	        if ( cRes )
	        {
	        	selectedId = String.valueOf((String)x);
	        	
	        	/*FacesMessage msg = new FacesMessage(
		        		FacesMessage.SEVERITY_INFO, cMethodName, "Selected Menu Item is:" + selectedId);
					
				FacesContext.getCurrentInstance().addMessage(null, msg);*/
				
	        	System.out.println("Menu changed " + selectedId);
	        }
	        
	        if ( cRes )
	        {
	        	String[] selectedIdDel = selectedId.split("#");
	        	
	        	if ( selectedIdDel.length >= 3 )
	        	{
	        		cSubNodeBrowseId = selectedIdDel[2];
	        	}
	        	
	        	if ( !cSubNodeBrowseId.equals("") )
	        	{
	        		for( NodeMO cCatNode : this.cSelectedCategory.getcCategoryNodes())
	        		{
	        			if ( isSubNodeFound )
        				{
        					break;
        				}
	        			
	        			for( NodeMO cSubNode : cCatNode.getcSubNodes())
	        			{
	        				if ( isSubNodeFound )
	        				{
	        					break;
	        				}
	        				
	        				if ( cSubNode.getBrowsenodeid().equals(cSubNodeBrowseId))
	        				{
	        					this.setcSelectedCategorySubNode(cSubNode);
	        					
	        					System.out.println("M.V. Custom::" + cMethodName + "::SubNode Found:" + 
	        									   cSubNode.getName() + ":" + cSubNode.getBrowsenodeid());
	        					
	        					isSubNodeFound = true;
	        				}
	        			}
	        		}
	        	}
	        }
	        
	        if ( cRes && isSubNodeFound)
	        {
	        	cRes = this.handleCategorySubNodeView(this.cSelectedCategory, this.cSelectedCategorySubNode);
	        }
	        
	        
		}
		catch( Exception e)
		{
			this.setLcRes(cRes = false);
			
			this.addMessage("ERROR", cMethodName, e.getMessage());
			
			System.out.println("M.V. Custom::" + cMethodName + "::" + e.getMessage());
		}
	}

	//------------------------------------------------------------------
	public void onCategoriesTabChange(TabChangeEvent event) 
	{
		boolean cRes = true;
		
		String cMethodName = "";
		
		try
		{
			StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
	        StackTraceElement ste = stacktrace[1];
	        cMethodName = ste.getMethodName();
	     
	        if ( null == event )
	        {
	        	FacesMessage msg = new FacesMessage(
		        		FacesMessage.SEVERITY_ERROR, cMethodName, "TabChangeEvent is null!");
					
				FacesContext.getCurrentInstance().addMessage(null, msg);
				
				System.out.println("M.V. Custom::" + cMethodName + "TabChangeEvent is null!");
				
				this.setLcRes(cRes = false);
	        }
	        
	        if ( cRes )
	        {
	        	this.setcNodeLevel(NodeLevel.CATEGORY);
	        }
	        
	        if ( cRes )
	        {
	        	String cSelectedCategoryName = event.getTab().getTitle();
	        	
	        	FacesMessage msg = new FacesMessage("Selected Category", cSelectedCategoryName);
	        	FacesContext.getCurrentInstance().addMessage(null, msg);
	        	
	        	if ( this.cCategories != null )
		        {
		        	for( CategoryMO cCategory : this.cCategories )
		        	{
		        		if ( cCategory.getName().equals(cSelectedCategoryName) )
		        		{
		        			this.setcSelectedCategory(cCategory);
		        			
		        			break;
		        		}
		        	}
		        }
	        }
	        
	        if ( cRes )
	        {
	        	cRes = this.handleCategoryView(this.getcSelectedCategory());
	        }
		}
		catch( Exception e)
		{
			this.setLcRes(cRes = false);
			
			this.addMessage("ERROR", cMethodName, e.getMessage());
			
			System.out.println("M.V. Custom::" + cMethodName + "::" + e.getMessage());
		}
    }

	public void onCategoriesTabClose(TabCloseEvent event) 
    {
    	boolean cRes = true;
		
		String cMethodName = "";
		
		try
		{
			StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
	        StackTraceElement ste = stacktrace[1];
	        cMethodName = ste.getMethodName();
	     
	        if ( null == event )
	        {
	        	FacesMessage msg = new FacesMessage(
		        		FacesMessage.SEVERITY_ERROR, cMethodName, "TabChangeEvent is null!");
					
				FacesContext.getCurrentInstance().addMessage(null, msg);
				
				System.out.println("M.V. Custom::" + cMethodName + "TabChangeEvent is null!");
				
				this.setLcRes(cRes = false);
	        }
	        
	        if ( cRes )
	        {
	        	if ( (this.cSelectedCategory != null) && (this.cSelectedCategory.getcCategoryNodes() != null) )
	        	{
		        	//--clear current sub nodes of the level 3 
	    			for( NodeMO cCatNode : this.cSelectedCategory.getcCategoryNodes() )
	    			{
	    				cCatNode.getcSubNodes().clear();
	    			}
	        	}
	        }
	        
	        if ( cRes )
	        {
	        	FacesMessage msg = new FacesMessage("Closed Category", event.getTab().getTitle());
	            FacesContext.getCurrentInstance().addMessage(null, msg);
	        }
		}
		catch( Exception e)
		{
			this.setLcRes(cRes = false);
			
			this.addMessage("ERROR", cMethodName, e.getMessage());
			
			System.out.println("M.V. Custom::" + cMethodName + "::" + e.getMessage());
		}
    }
    //----------
    public void onCategoryNodeChange()
    {
    	boolean cRes = true;
		
		String cMethodName = "";
		
		try
		{
			StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
	        StackTraceElement ste = stacktrace[1];
	        cMethodName = ste.getMethodName();
	
	        String cBrowseNodeId = "";
	        String cBrowseNodeName = "";
	        String cCategoryNodeName = "";
	        
	        if ( cRes )
	        {
	        	if ( (this.cSelectedCategory != null) && (this.cSelectedCategoryNode != null) )
	        	{
	        		cCategoryNodeName = this.cSelectedCategory.getName();
	        		
	        		cBrowseNodeId = this.cSelectedCategoryNode.getBrowsenodeid();
	        		
	        		cBrowseNodeName = this.cSelectedCategoryNode.getName();
	        		
	        		System.out.println("M.V. Custom::"   + cMethodName + 
	        				           "::cNewValue is:" + cCategoryNodeName + ":" + 
	        				           					   cBrowseNodeId);
	        	}
	        	else
	        	{
	        		System.out.println("M.V. Custom::" + cMethodName + "::cNewValue is null.");
	        		
	        		cRes = false;
	        	}
	        }
	        
	        if ( cRes )
	        {
	        	this.setcNodeLevel(NodeLevel.NODE);
	        }
	        
	        if ( cRes )
	        {
	        	cRes = this.handleCategoryNodeView(this.cSelectedCategory, this.cSelectedCategoryNode);
	        }
	        
	        if ( cRes )
	        {
	        	FacesMessage msg = new FacesMessage("Category Node:", cBrowseNodeName);
	            FacesContext.getCurrentInstance().addMessage(null, msg);
	        }
		}
		catch( Exception e)
		{
			this.setLcRes(cRes = false);
			
			this.addMessage("ERROR", cMethodName, e.getMessage());
			
			System.out.println("M.V. Custom::" + cMethodName + "::" + e.getMessage());
		}
    }
    
    // --------------------------------------------------------------
 	public void onTopHeaderPanelSearchButton() 
 	{
 		String cMethodName = "";

 		boolean cRes = true, isCategoryFound = false;

 		try 
 		{
 			StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
 			StackTraceElement ste = stacktrace[1];
 			cMethodName = ste.getMethodName();
 			
 			this.addMessage("INFO", 
 					"Search For",  this.getcCategorySearchInputText());
 			
 			System.out.println("M.V. Custom::" + cMethodName + 
 					"Search For" + this.getcCategorySearchInputText());
 			
 			//---
 			CategoryMO cCategory = new CategoryMO(ToolkitConstants.AMP_ALL_CATEGORY_VALUE);
 			
 			if ( this.cCategories != null )
	        {
	        	for( CategoryMO cCategoryL : this.cCategories )
	        	{
	        		if ( cCategoryL.getName().equals(this.cSelectedCategoryItem) )
	        		{
	        			cCategory = cCategoryL;
	        			
	        			isCategoryFound = true;
	        			
	        			break;
	        		}
	        	}
	        }
 			//---
 			if ( !isCategoryFound )
 			{
 				Source cSource = new Source();
 				cSource.setName(ToolkitConstants.AMP_AMAZON_SOURCE);
 				
 				cCategory.getcCategory().setSource(cSource);
 				
 				cCategory.getcCategory().setName(
 						ToolkitConstants.AMP_ALL_CATEGORY_VALUE);
 				
 				cCategory.getcCategory().setSearchindex(
 						ToolkitConstants.AMP_ALL_CATEGORY_VALUE);
 				
 				cCategory.getcCategory().setRootbrowsenode(
 						ToolkitConstants.AMP_ALL_CATEGORY_BROWSE_NODE);
 			}
	        //---		        
	        if ( cRes )
	        {
	        	cRes = this.getDefaultCategorySubNodes(cCategory);
	        }
	       
	        //---
			if ( cRes )
			{
				cRes = this.getCategoryButtonItemSearchList(cCategory, 1, true);
			}
			//---
			if ( cRes )
			{
				cRes = this.getCategoryButtonItemSearchList(cCategory, 2, false);
			}
			
	        //---
	        if ( cRes )
	        {
	        	cRes = this.setDefaultCategorySubNodesMenuModel(cCategory);
	        }

 			this.setLcRes(cRes);
 		} 
 		catch (Exception e) 
 		{
 			this.setLcRes(cRes = false);
 			
 			System.out.println("M.V. Custom::" + cMethodName + "::" + e.getMessage());
 			
 			this.addMessage("ERROR", cMethodName, e.getMessage());
 		}
 	}

	//------------------------------------------------------------------
	public boolean setDefaultCategory() 
	{
		boolean cRes = true;
		
		String cMethodName = "";
		
		CategoryMO cCategory = new CategoryMO();
		
		try
		{
			StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
	        StackTraceElement ste = stacktrace[1];
	        cMethodName = ste.getMethodName();
	       
	        if ( cRes )
	        {
	        	if ( ( this.cCategories != null ) &&  ( this.cCategories.size() >= 0 ))
		        {
	        		cCategory = this.cCategories.get(0);
	        		
	        		this.setcSelectedCategory(cCategory);
		        }
	        	else
	        	{
	        		cRes = false;
	        	}
	        }
	        //---		        
	        if ( cRes )
	        {
	        	cRes = this.getDefaultCategorySubNodes(cCategory);
	        }
	       
	        //---
			if ( cRes )
			{
				cRes = this.getCategoryItemSearchList(cCategory, 1, true);
			}
			//---
			if ( cRes )
			{
				cRes = this.getCategoryItemSearchList(cCategory, 2, false);
			}
			
	        //---
	        if ( cRes )
	        {
	        	cRes = this.setDefaultCategorySubNodesMenuModel(cCategory);
	        }
	        
	        return cRes;
		}
		catch( Exception e)
		{
			this.setLcRes(cRes = false);
			
			this.addMessage("ERROR", cMethodName, e.getMessage());
			
			System.out.println("M.V. Custom::" + cMethodName + "::" + e.getMessage());
			
			return cRes;
		}
	}

	//------------------------------------------------------------------
	@SuppressWarnings("unchecked")
	public boolean getDefaultCategorySubNodes(CategoryMO cCategory)
	{
		boolean cRes = true;
		
		String  cMethodName = "";
		
		try
		{
			StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
	        StackTraceElement ste = stacktrace[1];
	        cMethodName = ste.getMethodName();
	
	        //-----------------------------------------
	        List<Node> cNodes = new LinkedList<Node>();
	        		
	        Map<String, Object> cMethodParams = new TreeMap<String, Object>();
	        Map<String, Object> cMethodResults = new TreeMap<String, Object>();
	
	        //-----------------------------------------
	        if ( null == this.cOperationsBeanAWS )
	        {
	        	String cMesage = cMethodName + "::cOperationsBeanAWS not initialised!";
	        	
	        	FacesContext.getCurrentInstance().addMessage(null, 
	        			new FacesMessage(FacesMessage.SEVERITY_ERROR, cMethodName, cMesage));
	        	
	        	System.out.println(cMesage);
	        	
	        	this.setLcRes(cRes = false);
	        }
	        //-----------------------------------------
	        if ( null == cCategory )
	        {
	        	String cMesage = cMethodName + "::cSelectedCategory not initialised!";
	        	
	        	FacesContext.getCurrentInstance().addMessage(null, 
	        			new FacesMessage(FacesMessage.SEVERITY_ERROR, cMethodName, cMesage));
	        	
	        	System.out.println(cMesage);
	        	
	        	this.setLcRes(cRes = false);
	        }
	        //-----------------------------------------
	        if ( cRes )
	        {
	        	
	    		String cRootbrowsenode = cCategory.getRootbrowsenode();
			
	    		if ( !this.cCategoriesMap.containsKey(cRootbrowsenode))
				{
	    			this.setLcRes(cRes = false);
				}
	        }
	        //-----------------------------------------
	        if ( cRes )
	        {
	        	String cCallMethodName = cMethodName.replace("Default", "Root");
	        	
	        	cMethodParams.put("p1", this.cSourceName);
	        	cMethodParams.put("p2", cCategory.getRootbrowsenode());
	        	
	        	cMethodResults = this.cOperationsBeanAWS.handleRequest(cCallMethodName, cMethodParams);
	        			
	        	if ( null == cMethodResults )
	        	{
	        		String cMesage = cMethodName + "::cOperationsBeanAWS not initialised!";
	        		
	        		FacesContext.getCurrentInstance().addMessage(null, 
	        				new FacesMessage(FacesMessage.SEVERITY_ERROR, cMethodName, cMesage));
	        		
	        		System.out.println(cMesage);
	        		
	        		this.setLcRes(cRes = false);
	        	}
	        }
	        //-----------------------------------------
	        if ( cRes )
	        {
	        	cNodes =  (List<Node>)cMethodResults.get("r1");
	        	
	        	if ( null == cNodes )
	        	{
	        		String cMesage = cMethodName + "::Categories Nodes list is null!";
	        		
	        		FacesContext.getCurrentInstance().addMessage(null, 
	        				new FacesMessage(FacesMessage.SEVERITY_ERROR, cMethodName, cMesage));
	        		
	        		System.out.println(cMesage);
	        		
	        		this.setLcRes(cRes = false);
	        	}
	        }
	       
	        //-----------------------------------------
			if ( cRes )
			{
	    		for( Node cNode : cNodes )
	    		{
	    			//String cParentbrowsenode = cNode.getNode2parentbrowsenode();
	    			
	    			Node cParentNode = cNode.getNode();
        			
        			String cParentbrowsenode = cParentNode.getBrowsenodeid();
        			
					for( NodeMO cCatNode : cCategory.getcCategoryNodes())
					{
						if ( cCatNode.getBrowsenodeid().equals(cParentbrowsenode))
						{
							NodeMO cNodeMO = new NodeMO();
	        				
	        				cNodeMO.setcNode(cNode);
	        				
							cCatNode.getcSubNodes().add(cNodeMO);
						}
					}
	    		}
			}
	        
	        //-----------------------------------------
	        
	        this.setLcRes(cRes = true); 
	        
	        return cRes;
		}
		catch( Exception e)
		{
			this.setLcRes(cRes = false);
			
			this.addMessage("ERROR", cMethodName, e.getMessage());
			
			System.out.println("M.V. Custom::" + cMethodName + "::" + e.getMessage());
			
			this.setLcRes(cRes = false); return cRes;
		}
	}

	//------------------------------------------------------------------
	public boolean setDefaultCategorySubNodesMenuModel(CategoryMO cCategory) 
	{
		boolean cRes = true;
		
		String cMethodName = "";
		
		try
		{
			StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
	        StackTraceElement ste = stacktrace[1];
	        cMethodName = ste.getMethodName();
	        
	        this.cCategoriesNodesMenuModel = new DefaultMenuModel();
	        
	        if ( cCategory != null )
	        {
	        	String cCategoryBrowseNodeId = cCategory.getRootbrowsenode();
	        	
	        	for( NodeMO cCatNode : cCategory.getcCategoryNodes() )
	        	{
	        		//---
	        		String cCategoryNodeBrowseNodeId   = cCatNode.getBrowsenodeid();
	        		String cCategoryNodeBrowsenodeName = cCatNode.getName();
	
	        		DefaultSubMenu cCategNodeAllSubMenu = new DefaultSubMenu("Items Menu");
	        		
	        		//---All Items Menu
	        		String value   = "All " + cCatNode.getName();
		        	String update  = ":pAmazonMainForm:pAmazonMainMessages, "   + 
		        					  ":pAmazonMainForm:cCategNodesMenuPanel, " + 
		        					  ":pAmazonMainForm:cItemsDataViewPanel";
		        	String id      = cCategoryBrowseNodeId + "#" + cCategoryNodeBrowseNodeId + "#All";
		        	String command = String.format("#{cAmazonDataBean.onSetCategoryNodeMenu('%s')}", id);
		        	
	        		DefaultMenuItem cMenuItemAll = new DefaultMenuItem();
	        		cMenuItemAll.setId(id);
	        		cMenuItemAll.setValue(value);
	        		cMenuItemAll.setCommand(command);
	        		cMenuItemAll.setUpdate(update);
	        		cMenuItemAll.setOnstart("PF('blockUIWidgetCategoriesMenu').block()");
	        		cMenuItemAll.setOncomplete("PF('blockUIWidgetCategoriesMenu').block()");
		        	cCategNodeAllSubMenu.addElement(cMenuItemAll);
		        	//---
		        	
	        		for( NodeMO cSubNode : cCatNode.getcSubNodes())
	        		{
	        			String cSubNodeBrowseNodeId   = cSubNode.getBrowsenodeid();
		        		String cSubNodeBrowseNodeName = cSubNode.getName();
		        		
		        		value   = cSubNodeBrowseNodeName;
		        		update  = ":pAmazonMainForm:pAmazonMainMessages, "  + 
	        					  ":pAmazonMainForm:cCategNodesMenuPanel, " + 
	        					  ":pAmazonMainForm:cItemsDataViewPanel";
			        	id      = cCategoryBrowseNodeId + "#" + cCategoryNodeBrowseNodeId + "#" + cSubNodeBrowseNodeId;
			        	command = String.format("#{cAmazonDataBean.onSetCategorySubNodeMenu('%s')}", id);
			        	
			        	DefaultMenuItem cMenuItem = new DefaultMenuItem();
			        	cMenuItem.setId(id);
			        	cMenuItem.setValue(value);
			        	cMenuItem.setCommand(command);
			        	cMenuItem.setUpdate(update);
			        	cMenuItem.setOnstart("PF('blockUIWidgetCategoriesMenu').block()");
		        		cMenuItem.setOncomplete("PF('blockUIWidgetCategoriesMenu').block()");
			        	//cMenuItem.setProcess("@this");
			        	cCategNodeAllSubMenu.addElement(cMenuItem);
	        		}
	        		
	
		        	//---
		        	DefaultMenuColumn cCategSubMenuColumn = new DefaultMenuColumn();
		        	cCategSubMenuColumn.addElement(cCategNodeAllSubMenu);
		        	
		        	//---
		        	DefaultSubMenu cCategNodeSubMenu = new DefaultSubMenu(cCategoryNodeBrowsenodeName);
		        	cCategNodeSubMenu.addElement(cCategSubMenuColumn);
		        	
		        	//---
		        	this.cCategoriesNodesMenuModel.addElement(cCategNodeSubMenu);
	        	}
	        }
	        
			this.setLcRes(cRes);
			
			return cRes;
		}
		catch( Exception e)
		{
			this.setLcRes(cRes = false);
			
			this.addMessage("ERROR", cMethodName, e.getMessage());
			
			System.out.println("M.V. Custom::" + cMethodName + "::" + e.getMessage());
			
			return cRes;
		}
	}

	//------------------------------------------------------------------
	public boolean setCategorySubNodesMenuModel(CategoryMO cCategory) 
	{
		boolean cRes = true;
		
		String cMethodName = "";
		
		try
		{
			StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
	        StackTraceElement ste = stacktrace[1];
	        cMethodName = ste.getMethodName();
	        
	        this.cCategoriesNodesMenuModel = new DefaultMenuModel();
	        
	        if ( cCategory != null )
	        {
	        	String cCategoryBrowseNodeId = cCategory.getRootbrowsenode();
	        	
	        	for( NodeMO cCatNode : cCategory.getcCategoryNodes() )
	        	{
	        		//---
	        		String cCategoryNodeBrowseNodeId   = cCatNode.getBrowsenodeid();
	        		String cCategoryNodeBrowsenodeName = cCatNode.getName();
	
	        		DefaultSubMenu cCategNodeAllSubMenu = new DefaultSubMenu("Items Menu");
	        		
	        		//---All Items Menu
	        		String value   = "All " + cCatNode.getName();
		        	String update  = ":pAmazonMainForm:pAmazonMainMessages, "   + 
		        					  ":pAmazonMainForm:cCategNodesMenuPanel, " + 
		        					  ":pAmazonMainForm:cItemsDataViewPanel";
		        	String id      = cCategoryBrowseNodeId + "#" + cCategoryNodeBrowseNodeId + "#All";
		        	String command = String.format("#{cAmazonDataBean.onSetCategoryNodeMenu('%s')}", id);
		        	
	        		DefaultMenuItem cMenuItemAll = new DefaultMenuItem();
	        		cMenuItemAll.setId(id);
	        		cMenuItemAll.setValue(value);
	        		cMenuItemAll.setCommand(command);
	        		cMenuItemAll.setUpdate(update);
	        		cMenuItemAll.setOnstart("PF('blockUIWidgetCategoriesMenu').block()");
	        		cMenuItemAll.setOncomplete("PF('blockUIWidgetCategoriesMenu').block()");
		        	cCategNodeAllSubMenu.addElement(cMenuItemAll);
		        	//---
		        	
	        		for( NodeMO cSubNode : cCatNode.getcSubNodes())
	        		{
	        			String cSubNodeBrowseNodeId   = cSubNode.getBrowsenodeid();
		        		String cSubNodeBrowseNodeName = cSubNode.getName();
		        		
		        		value   = cSubNodeBrowseNodeName;
		        		update  = ":pAmazonMainForm:pAmazonMainMessages, "  + 
	        					  ":pAmazonMainForm:cCategNodesMenuPanel, " + 
	        					  ":pAmazonMainForm:cItemsDataViewPanel";
			        	id      = cCategoryBrowseNodeId + "#" + cCategoryNodeBrowseNodeId + "#" + cSubNodeBrowseNodeId;
			        	command = String.format("#{cAmazonDataBean.onSetCategorySubNodeMenu('%s')}", id);
			        	
			        	DefaultMenuItem cMenuItem = new DefaultMenuItem();
			        	cMenuItem.setId(id);
			        	cMenuItem.setValue(value);
			        	cMenuItem.setCommand(command);
			        	cMenuItem.setUpdate(update);
			        	cMenuItem.setOnstart("PF('blockUIWidgetCategoriesMenu').block()");
		        		cMenuItem.setOncomplete("PF('blockUIWidgetCategoriesMenu').block()");
			        	//cMenuItem.setProcess("@this");
			        	cCategNodeAllSubMenu.addElement(cMenuItem);
	        		}
	        		
	
		        	//---
		        	DefaultMenuColumn cCategSubMenuColumn = new DefaultMenuColumn();
		        	cCategSubMenuColumn.addElement(cCategNodeAllSubMenu);
		        	
		        	//---
		        	DefaultSubMenu cCategNodeSubMenu = new DefaultSubMenu(cCategoryNodeBrowsenodeName);
		        	cCategNodeSubMenu.addElement(cCategSubMenuColumn);
		        	
		        	//---
		        	this.cCategoriesNodesMenuModel.addElement(cCategNodeSubMenu);
	        	}
	        }
	        
			this.setLcRes(cRes);
			
			return cRes;
		}
		catch( Exception e)
		{
			this.setLcRes(cRes = false);
			
			this.addMessage("ERROR", cMethodName, e.getMessage());
			
			System.out.println("M.V. Custom::" + cMethodName + "::" + e.getMessage());
			
			return cRes;
		}
	}

	//------------------------------------------------------------------
	public boolean setCategorySearchMenuModel() 
	{
		boolean cRes = true;
		
		String cMethodName = "";
		
		try
		{
			StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
	        StackTraceElement ste = stacktrace[1];
	        cMethodName = ste.getMethodName();
	        
	        String cIdPrefix =  "pAllDepartmentsSearchMenu";
	        //---
	        DefaultSubMenu cAllDepartmentsMenu = new DefaultSubMenu("All Departments");
	       
	        DefaultSubMenu cAllDepartmentsMenuItem = new DefaultSubMenu("Select");
			
	    	String update  = "";
	    	String value   = "";
	    	String id      = "";
	    	String command = "";
	    	
	    	cAllDepartmentsMenuItem.setId(id);
	
	    	cAllDepartmentsMenu.addElement(cAllDepartmentsMenuItem);
	    	//---
	    	
	        for( CategoryMO cCategory : this.cCategories)
	        {
	        	//-----
	        	DefaultMenuItem cMenuItem = new DefaultMenuItem();
	        		
	        	update  = ":pAmazonMainForm:pAmazonMainMessages";
	        	value   = cCategory.getName();
	        	id      = cIdPrefix + "#" + cCategory.getName() + 
	        			"#" + value + "#" + String.valueOf(cCategory.getCategoryid());
	        	
	        	command = String.format("#{cAmazonDataBean.onSetCategorySearchMenu('%s')}", id);
	        	
	        	cMenuItem.setId(id);
	        	cMenuItem.setValue(value);
	        	cMenuItem.setCommand(command);
	        	cMenuItem.setUpdate(update);
	        	cMenuItem.setOnstart("PF('blockUIWidgetCategoriesMenu').block()");
        		cMenuItem.setOncomplete("PF('blockUIWidgetCategoriesMenu').block()");
	        	cMenuItem.setProcess("@this");
	        	
	        	cAllDepartmentsMenuItem.addElement(cMenuItem);
	        }
	        
	        DefaultMenuColumn cCategSearchMenuColumn = new DefaultMenuColumn();
	        
	        cCategSearchMenuColumn.addElement(cAllDepartmentsMenuItem);
	        
	        cAllDepartmentsMenu.addElement(cCategSearchMenuColumn);
	        
	        this.cCategoriesSearchMenuModel = new DefaultMenuModel();
	        
	    	this.cCategoriesSearchMenuModel.addElement(cAllDepartmentsMenu);
	    	
	    	//---
			this.setLcRes(cRes);
			
			return cRes;
		}
		catch( Exception e)
		{
			this.setLcRes(cRes = false);
			
			this.addMessage("ERROR", cMethodName, e.getMessage());
			
			System.out.println("M.V. Custom::" + cMethodName + "::" + e.getMessage());
			
			return cRes;
		}
	}
}
