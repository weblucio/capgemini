package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import controller.GenericTable;

public class JDBC {

	private static JDBC conn=null;
	private Connection myinternalconn=null;
	private JDBC(){
		
		
		
	}
	     
	    private static Connection createConnection() throws SQLException{
	    	try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	    	String url = "jdbc:mysql://localhost:3306/lobo"; //Nome da base de dados
	        String user = "root"; //nome do usuário do MySQL
	        String password = "197818"; //senha do MySQL
	         
	        Connection conexao = null;
	        conexao = DriverManager.getConnection(url, user, password);

	        return conexao;
	    }
public static JDBC getInstance() {
	
	
	if(conn==null) {
		
		conn=new JDBC();
	}
	
	return conn;
}


public ResultSet load(String table) {
	ResultSet result=null;
	try {
	myinternalconn=JDBC.createConnection();
	
	String sql="Select * from "+table;
result=	myinternalconn.prepareStatement(sql).executeQuery();

	
	}catch(Exception e) {
		System.out.println(e.getMessage());
		try {
			myinternalconn.rollback();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}	

		return result;
	
	
	
}

public ResultSet describe(String table) {
	
	ResultSet result=null;
	try {
	myinternalconn=JDBC.createConnection();
	
	String sql="describe "+table;
result=	myinternalconn.prepareStatement(sql).executeQuery();

	
	}catch(Exception e) {
		System.out.println(e.getMessage());
		try {
			myinternalconn.rollback();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}	

		return result;
	
	
	
	
	
}

public boolean insert(String table) {

	try {
	myinternalconn=JDBC.createConnection();
	
	List<GenericTable> lista=new ArrayList();
	ResultSet rs=this.describe(table);
	
	String sql="Insert into "+table;
	String values="";
	String fields="(";
	int contador=0;
	while(rs.next()) {
		if(contador>0) {
		System.out.println("Fields=>"+rs.getString(1));
		lista.add(new GenericTable().setColumnName(rs.getString(1)).setValueColumn("'Digite um valor'"));
		}
		contador++;
		
	}
	
	for(GenericTable gt:lista) {
		fields=fields+gt.getColumnName()+",";
		values=values+gt.getValueColumn()+",";
		
	}
	fields=fields.substring(0,fields.length()-1)+")VALUES(";

	values=values.substring(0,values.length()-1)+")";
	
	sql=sql.concat(fields).concat(values);
	
	
	System.out.println("Insert=>"+sql);		
	
myinternalconn.prepareStatement(sql).execute();
//myinternalconn.commit();
	return true;
	}catch(Exception e) {
		System.out.println(e.getMessage());
		try {
			myinternalconn.rollback();
			return false;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		}
	}	

	
	
}

}
