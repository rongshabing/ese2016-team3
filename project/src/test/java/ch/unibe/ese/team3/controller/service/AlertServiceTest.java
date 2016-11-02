package ch.unibe.ese.team3.controller.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import ch.unibe.ese.team3.controller.pojos.forms.AlertForm;
import ch.unibe.ese.team3.model.Ad;
import ch.unibe.ese.team3.model.AdPicture;
import ch.unibe.ese.team3.model.Alert;
import ch.unibe.ese.team3.model.AlertType;
import ch.unibe.ese.team3.model.Gender;
import ch.unibe.ese.team3.model.Type;
import ch.unibe.ese.team3.model.User;
import ch.unibe.ese.team3.model.UserRole;
import ch.unibe.ese.team3.model.dao.AdDao;
import ch.unibe.ese.team3.model.dao.AlertDao;
import ch.unibe.ese.team3.model.dao.UserDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/config/springMVC.xml",
		"file:src/main/webapp/WEB-INF/config/springData.xml",
		"file:src/main/webapp/WEB-INF/config/springSecurity.xml"})
@WebAppConfiguration
public class AlertServiceTest {
	
	@Autowired
	AdDao adDao;
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	AlertDao alertDao;
	
	@Autowired
	AlertService alertService;
	
	@Test
	public void createAlerts() {
		ArrayList<Alert> alertList = new ArrayList<Alert>();
		
		// Create user Adolf Ogi
		User adolfOgi = createUser("adolf@ogi.ch", "password", "Adolf", "Ogi",
				Gender.MALE);
		adolfOgi.setAboutMe("Wallis rocks");
		userDao.save(adolfOgi);
		
		// Create 2 alerts for Adolf Ogi
		Alert alert = new Alert();
		alert.setUser(adolfOgi);
		//alert.setStudio(true);
		alert.setCity("Bern");
		alert.setZipcode(3000);
		alert.setPrice(1500);
		alert.setRadius(100);
		
		// create list of AlertTypes
		AlertType typeApartment = new AlertType();
		typeApartment.setType(Type.APARTMENT);
		AlertType typeLoft = new AlertType();
		typeLoft.setType(Type.LOFT);
		
		List<AlertType> alertTypes = new ArrayList<>();
		alertTypes.add(typeLoft);
		alertTypes.add(typeApartment);
		
		alert.setAlertTypes(alertTypes);
		alertDao.save(alert);
		
		alert = new Alert();
		alert.setUser(adolfOgi);
		//alert.setStudio(true);
		alert.setCity("Bern");
		alert.setZipcode(3002);
		alert.setPrice(1000);
		alert.setRadius(5);
		alertDao.save(alert);
		
		//copy alerts to a list
		Iterable<Alert> alerts = alertService.getAlertsByUser(adolfOgi);
		for(Alert returnedAlert: alerts)
			alertList.add(returnedAlert);
		
		//begin the actual testing
		assertEquals(2, alertList.size());
		assertEquals(adolfOgi, alertList.get(0).getUser());
		assertEquals("Bern", alertList.get(1).getCity());
		assertTrue(alertList.get(0).getRadius() > alertList.get(1).getRadius());
	}
	
	@Test
	public void mismatchChecks() {
		ArrayList<Alert> alertList = new ArrayList<Alert>();
		
		User thomyF = createUser("thomy@f.ch", "password", "Thomy", "F",
				Gender.MALE);
		thomyF.setAboutMe("Supreme hustler");
		userDao.save(thomyF);
		
		// create list of AlertTypes
		AlertType typeApartment = new AlertType();
		typeApartment.setType(Type.APARTMENT);
		AlertType typeLoft = new AlertType();
		typeLoft.setType(Type.LOFT);
		
		List<AlertType> alertTypes = new ArrayList<>();
		alertTypes.add(typeLoft);
		alertTypes.add(typeApartment);
		
		// Create 2 alerts for Thomy F
		Alert alert = new Alert();
		alert.setUser(thomyF);
		alert.setAlertTypes(alertTypes);
		alert.setCity("Bern");
		alert.setZipcode(3000);
		alert.setPrice(1500);
		alert.setRadius(100);
		alertDao.save(alert);
		
		alertTypes.remove(0); // remove Apartment from list
		
		alert = new Alert();
		alert.setUser(thomyF);
		alert.setAlertTypes(alertTypes);
		alert.setCity("Bern");
		alert.setZipcode(3002);
		alert.setPrice(1000);
		alert.setRadius(5);
		alertDao.save(alert);
		
		Iterable<Alert> alerts = alertService.getAlertsByUser(userDao.findByUsername("thomy@f.ch"));
		for(Alert returnedAlert: alerts)
			alertList.add(returnedAlert);
		
		//save an ad
		Date date = new Date();
		Ad oltenResidence= new Ad();
		oltenResidence.setZipcode(4600);
		oltenResidence.setMoveInDate(date);
		oltenResidence.setCreationDate(date);
		oltenResidence.setPrizePerMonth(1200);
		oltenResidence.setSquareFootage(42);
		oltenResidence.setType(Type.LOFT);
		oltenResidence.setRoomDescription("blah");
		oltenResidence.setUser(thomyF);
		oltenResidence.setTitle("Olten Residence");
		oltenResidence.setStreet("Florastr. 100");
		oltenResidence.setCity("Olten");
		oltenResidence.setBalcony(false);
		oltenResidence.setGarage(false);

		adDao.save(oltenResidence);
		/*
		assertFalse("Olten no radius mismatch first alert", alertService.radiusMismatch(oltenResidence, alertList.get(0)));
		assertTrue("Olten radius mismatch second alert", alertService.radiusMismatch(oltenResidence, alertList.get(1)));
		assertTrue("Olten type mismatch fist alert", alertService.typeMismatch(oltenResidence, alertList.get(0)));
		assertFalse("Olten no type mismatch second alert", alertService.typeMismatch(oltenResidence, alertList.get(1)));
		*/
	}
	
	//Lean user creating method
	User createUser(String email, String password, String firstName,
			String lastName, Gender gender) {
		User user = new User();
		user.setUsername(email);
		user.setPassword(password);
		user.setEmail(email);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEnabled(true);
		user.setGender(gender);
		Set<UserRole> userRoles = new HashSet<>();
		UserRole role = new UserRole();
		role.setRole("ROLE_USER");
		role.setUser(user);
		userRoles.add(role);
		user.setUserRoles(userRoles);
		return user;
	}
}
