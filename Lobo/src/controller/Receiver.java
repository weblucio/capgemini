package controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.Gson;

import model.DAO;
import model.Empresa;
import model.JDBC;
	
	
	@ServerEndpoint("/actions")
	public class Receiver {

		Gson gson=new Gson();
		
	    @OnOpen
	        public void open(Session session) {
	    	System.out.println("Abriu 1");
	    }
	    

	    @OnClose
	        public void close(Session session) {
	    }

	    @OnError
	        public void onError(Throwable error) {
	    }

	    @OnMessage
	        public void handleMessage(String message, Session session) {
	    	
	    	Protocol incoming=gson.fromJson(message, Protocol.class);
	    	//DAO dao=new DAO();
	    	
	    	if(incoming.getOperation().equals("load")) {
	    	try {
	    		
	    		System.out.println("Here"+incoming.getTable());
	    		
	    		
	    		//GenericTable empresa1=new GenericTable();
	    	//	System.out.println("Minha classe=>"+myobj.getClass().getName());
	    		
	    		
	    		JDBC bd=JDBC.getInstance();
	    		
	    		
	    	List<ArrayList<GenericTable>> lista=new ArrayList();
	    	ResultSet set=bd.load(incoming.getTable());
	    
	    	while(set.next()){
	    int colunas=1;
	    
	    		int numcolunas=set.getMetaData().getColumnCount();
	 lista.add(new ArrayList<GenericTable>());   
	   
	 while(colunas<=numcolunas) {
	 lista.get(lista.size()-1).add(new GenericTable().setColumnName(set.getMetaData().getColumnName(colunas)).setValueColumn(set.getString(colunas)));   	
	    	
	    	colunas++;
	    }		
	    		
	    		
	    		
	    	}
	    	
	    	for(ArrayList<GenericTable> ls:lista) {
	    		for(GenericTable gt:ls) {
	    			
	    			System.out.println("Título=>"+gt.getColumnName());

	    			System.out.println("Valor=>"+gt.getValueColumn());
	    			
	    		}
	    		
	    	}
	    	
	    	
	    //	for(String obj:dao.search(incoming.getTable())) {	
	    	
	    		
	    		//lista.add((Empresa)obj);
	    	
	   // 	};
	    	
	    	session.getBasicRemote().sendText(gson.toJson(lista));
	    
}catch(Exception e) {
	    		
	    		System.out.println(e.getCause());
	    		
	    	}
	    	
	    	}else if(incoming.getOperation().equals("addRow")){
	    	
	    		
	    		
	    		JDBC j=JDBC.getInstance();
	    		if(j.insert(incoming.getTable())){
	    			
	    			System.out.println("Inseriu");
	    		
	    			List<ArrayList<GenericTable>> lista=new ArrayList();
    		    	
	    	    	ResultSet set=j.load(incoming.getTable());
	    		    
	    	    	try {
						while(set.next()){
 	    int colunas=1;
 	    
							int numcolunas=set.getMetaData().getColumnCount();
							
							lista.add(new ArrayList<GenericTable>());   
  	   
  	 while(colunas<=numcolunas) {
  	 lista.get(lista.size()-1).add(new GenericTable().setColumnName(set.getMetaData().getColumnName(colunas)).setValueColumn(set.getString(colunas)));   	
						
						colunas++;
 	    }		
							
							
							
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	    	    	
	    	    	for(ArrayList<GenericTable> ls:lista) {
	    	    		for(GenericTable gt:ls) {
	    	    			
	    	    			System.out.println("Título=>"+gt.getColumnName());

	    	    			System.out.println("Valor=>"+gt.getValueColumn());
	    	    			
	    	    		}
	    	    		
	    	    	}
	    	    	
	    	    	
	    	    //	for(String obj:dao.search(incoming.getTable())) {	
	    	    	
	    	    		
	    	    		//lista.add((Empresa)obj);
	    	    	
	    	   // 	};
	    	    	
	    	    	try {
						session.getBasicRemote().sendText(gson.toJson(lista));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	    		
	    		
	    		}
	    			
	    			
	    			
	    		
	    	/*	try {
	 
	    			GenericTable empresa=new GenericTable();
		    		empresa.setNome("digite aqui o valor");
		 
	    			dao.save(empresa);

	    			dao=new DAO();
	    		List<Empresa> lista=new ArrayList();
		    	
	    		for(Object obj:dao.search(new Empresa())) {	
	    	    	
		    		lista.add((Empresa)obj);
		    	
		    	};
		    	
		    	session.getBasicRemote().sendText(gson.toJson(lista));
	    		
	    	}catch(Exception e) {
	    		
	    		
	    	}		*/
	    
	    		
	    	}else if(incoming.getOperation().equals("deleteRow")){
	    	
	    		/*try {
	 
	    	   		Empresa empresa=new Empresa();
		 
	    			dao.remove(empresa, Long.parseLong(incoming.value));
	    			
	    			dao=new DAO();
	    	
		    	session.getBasicRemote().sendText(gson.toJson("Excluido"));
	    		
	    	}catch(Exception e) {
	    		
	    		
	    	}
	    		*/
	    		
	    	}

	    	
	    	
	    	
	    	
	    	
	    }
	
	}
