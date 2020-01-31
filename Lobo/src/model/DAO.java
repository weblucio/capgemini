package model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Query;


//@NamedQueries({@NamedQuery(name="inicialPorUsuario",query="Select p FROM avaliacaoinicial p JOIN p.paciente_codigo pp WHERE (DAY(p.dataligacao)>=25 AND MONTH(p.dataligacao)=:inicio) OR (DAY(p.dataligacao)<=25 AND MONTH(p.dataligacao)=:final) GROUP BY pp.profissionalresponsavel")})

public class DAO {
	static final int mes=Calendar.getInstance().get(Calendar.MONTH);
	String resposta="";
	private EntityManager conn=ConnectionFactory.getConnection();
	//private EntityManager conn=null;
public DAO() {

	
}
	public void save(Object obj) {

		try {
			conn.getTransaction().begin();

			conn.persist(obj);

			conn.getTransaction().commit();

			System.out.println("Funcionou");
		}catch(Exception e) {
			
			System.out.println(e.getMessage());
			conn.getTransaction().rollback();
        throw e;
		}finally {
			conn.close();
		}

	}

	public Object searchById(Object obj,Long id) {




		try {		
	
			return conn.find(obj.getClass(), id);

			
		}catch(Exception e){
//return			resposta="Erro";
			System.out.print(e.getMessage());

throw e;
		}finally {
			
			
			conn.close();

		}	

	}
	
	public List<String> search(String table) {
		
		System.out.println("Listando");
		List<String> lista=conn.createQuery("FROM "+table+" lista").getResultList();
		
		System.out.println("Size=>"+lista.size());
		
		
		
		return lista; 
	}
	
	public void remove(Object obj,Long id) {
	try {
		
		conn.getTransaction().begin();
		
		conn.remove(conn.getReference(obj.getClass(), id));
	
		conn.getTransaction().commit();

	}catch(Exception e) {
		System.out.println(e.getMessage());
		
		conn.getTransaction().rollback();
	
		throw e;
	}finally {
		conn.close();}
	
	}

	

}
