package ca.sheridancollege.patemitm.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ca.sheridancollege.patemitm.beans.Ticket;

@Repository
public class TicketRepo 
{
	@Autowired
	private NamedParameterJdbcTemplate jdbc;
	

	public void addTicket(Ticket ticket) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "INSERT INTO ticket (name, age, zones, seat, rate) VALUES " + "(:name, :a, :z, :s, :r)";
		
		parameters.addValue("name", ticket.getName());
		parameters.addValue("a", ticket.getAge());
		parameters.addValue("z", ticket.getZones());
		parameters.addValue("s", ticket.getSeat());	
		parameters.addValue("r", ticket.getRate());	
		jdbc.update(query,parameters);
	}
	
	
	
	//getting name from database and displaying in view page
			public ArrayList<Ticket> getTicket()
			{
				ArrayList<Ticket> ticket = new ArrayList<Ticket>();
				MapSqlParameterSource parameters = new MapSqlParameterSource();
				
				String query = "SELECT * FROM ticket";
				List<Map<String,Object>> rows = jdbc.queryForList(query, parameters);
				for(Map<String,Object> row : rows)
				{
					//name, age, zones, seat, rate
					
					Ticket t= new Ticket();
					t.setId((int)row.get("id"));
					t.setName((String)row.get("name")); 
					t.setAge((String)row.get("age")); 
					t.setZones((String)row.get("zones"));
					t.setSeat((String)row.get("seat")); 
					t.setRate((String)row.get("rate")); 
					
					ticket.add(t);		
				}
				return ticket;
			}
			
			
			
			//help to edit and put databack in database
			public Ticket getTicketById(int id)
			{
				ArrayList<Ticket> ticket = new ArrayList<Ticket>();
				MapSqlParameterSource parameters = new MapSqlParameterSource();
				
				String query = "SELECT * FROM ticket WHERE id=:meow";
				parameters.addValue("meow", id);
				List<Map<String,Object>> rows = jdbc.queryForList(query, parameters);
				for(Map<String,Object> row : rows)
				{
					Ticket t= new Ticket();
					t.setId((int)row.get("id"));
					t.setName((String)row.get("name")); 
					t.setAge((String)row.get("age")); 
					t.setZones((String)row.get("zones"));
					t.setSeat((String)row.get("seat")); 
					t.setRate((String)row.get("rate")); 
					
					ticket.add(t);
				}
			if(ticket.size()>0)
			{
				return ticket.get(0);
			}
			else 
			{
				return null;	
			}
				
}
			
			
			public void editTicket(Ticket ticket)
			{
				MapSqlParameterSource parameters = new MapSqlParameterSource();
				parameters.addValue("id", ticket.getId());
				parameters.addValue("name", ticket.getName());
				parameters.addValue("a", ticket.getAge());
				parameters.addValue("z", ticket.getZones());
				parameters.addValue("s", ticket.getSeat());	
				parameters.addValue("r", ticket.getRate());	
				
			
				String query = "UPDATE ticket SET name=:name," 
			    +"age=:a, zones=:z, seat=:s, rate=:r WHERE id=:id";
				jdbc.update(query, parameters);
			}
			
			//to delete specific selected id
			public void deleteTicket(Ticket ticket)
			{
				MapSqlParameterSource parameters = new MapSqlParameterSource();
				
				String query = "DELETE  FROM ticket where id=:id";
				parameters.addValue("id", ticket.getId());
				jdbc.update(query, parameters);
			}
			
			public ArrayList<Ticket> getTicketStat()
			{
				ArrayList<Ticket> ticket = new ArrayList<Ticket>();
				MapSqlParameterSource parameters = new MapSqlParameterSource();
				
				String query = "SELECT MAX(rate) FROM ticket";
				List<Map<String,Object>> rows = jdbc.queryForList(query, parameters);
				for(Map<String,Object> row : rows)
				{
					//name, age, zones, seat, rate
					
					Ticket t= new Ticket();
					t.setId((int)row.get("id"));
					t.setName((String)row.get("name")); 
					t.setAge((String)row.get("age")); 
					t.setZones((String)row.get("zones"));
					t.setSeat((String)row.get("seat")); 
					t.setRate((String)row.get("rate")); 
					
					ticket.add(t);		
				}
				return ticket;
			}

}
