package ch.unibe.ese.team3.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ch.unibe.ese.team3.controller.service.AdService;
import ch.unibe.ese.team3.controller.service.AuctionService;
import ch.unibe.ese.team3.controller.service.BidService;
import ch.unibe.ese.team3.controller.service.UserService;
import ch.unibe.ese.team3.model.Ad;
import ch.unibe.ese.team3.model.User;


/**
 * 
 * handels all actions (like bid, etc) concerning auction
 *
 */
@Controller
public class AuctionController {
	
	
	@Autowired
	private BidService bidService;
	@Autowired
	private UserService userService;
	@Autowired
	private AdService adService;
	@Autowired
	private AuctionService auctionService;
	
	private TaskScheduler scheduler = new ConcurrentTaskScheduler();

	
	@RequestMapping(value = "/profile/bidAuction", method = RequestMethod.POST)
	public void bid(Principal principal, int amount , @RequestParam long id, RedirectAttributes redirectAttributes){
		
//		User bidder = userService.findUserByUsername(principal.getName());
//		Ad ad = adService.getAdById(id);
//		
//		bidService.bid(ad, bidder, amount);
		
		
//		ModelAndView model = new ModelAndView("adDescription");
//		redirectAttributes.addFlashAttribute("confirmationMessage",
//				"Your bid was registered successfully.");
//		
//		model = new ModelAndView("searchAd");
//		
//		return model;
		
	}
	
	@RequestMapping(value = "/profile/buyAuction", method = RequestMethod.POST)
	public void buy(Principal principal, int amount , @RequestParam long id){
		// to implement
	}
	
	// TaskScheduler and @Schedule annotation || ScheduledExecutorService
	
	/*
	//@PostConstruct
	private void executeJob() {
	    scheduler.scheduleAtFixedRate(new Runnable() {
	        @Override
	        public void run() {
	            // your business here
	        }
	    }, INTERVAL);
	}
	*/
	
	
	

}
