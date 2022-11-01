package ca.sheridancollege.patemitm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import ca.sheridancollege.patemitm.beans.Ticket;
import ca.sheridancollege.patemitm.repository.TicketRepo;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
public class TicketController 
{
	private TicketRepo ticketrepo;
	
	//to go to home page
	@GetMapping("/")
	public String goHome()
	{
		return "root.html";
	}
	
	//to buy ticket
	@GetMapping("/add")
	public String goAdd(Model model)
	{
		model.addAttribute("tickets", new Ticket());
		return "add.html";
	}
	
	//to get data from form and storing in database
	@GetMapping("/process")
	public String addTicket(@ModelAttribute Ticket ticket)
	{
		ticketrepo.addTicket(ticket);
		return "redirect:/add";
		
	}
	
	//to view table
	@GetMapping("/view")
	public String goView(Model model)
	{
		model.addAttribute("hello",ticketrepo.getTicket());
		return "view.html";
	}   
	
	// to get details of specific row selected
	@GetMapping("/edit/{id}")
	public String editTicket(@PathVariable int id, Model model)
	{
		Ticket ticket= ticketrepo.getTicketById(id);
		model.addAttribute("ticket", ticket);
		return "edit.html";
		
	}
	
	//to get data of edited from and to display on view page
	@GetMapping("/editTicket")
	public String processEdit(@ModelAttribute Ticket ticket)
	{
		Ticket t= ticket;	//Modified Drink
		//Update the drink in my database
		ticketrepo.editTicket(t);
		//Go Back to the view page
		
		return "redirect:/view";
	}
	
	@GetMapping("/stat")
	public String goStat(Model model)
	{
		model.addAttribute("hello",ticketrepo.getTicketStat());
		return "stats.html";
	}   

	@GetMapping("/delete/{id}")
	public String deleteDrink(@ModelAttribute Ticket ticket)
	{
		ticketrepo.deleteTicket(ticket);
		return "redirect:/view";
	}
	
	@GetMapping("/stats")
	public String goStat()
	{
		return "stats.html";
	}
	
	
	
}
