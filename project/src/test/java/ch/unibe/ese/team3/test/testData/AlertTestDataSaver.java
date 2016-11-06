package ch.unibe.ese.team3.test.testData;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ch.unibe.ese.team3.model.Alert;
import ch.unibe.ese.team3.model.AlertType;
import ch.unibe.ese.team3.model.Type;
import ch.unibe.ese.team3.model.User;
import ch.unibe.ese.team3.model.dao.AlertDao;
import ch.unibe.ese.team3.model.dao.UserDao;

/**
 * This inserts some alert test data into the database.
 */
@Service
public class AlertTestDataSaver {

	@Autowired
	private AlertDao alertDao;
	
	@Autowired
	private UserDao userDao;


	@Transactional
	public void saveTestData() throws Exception {
		User ese = userDao.findByUsername("ese@unibe.ch");
		User jane = userDao.findByUsername("jane@doe.com");
		
		// create list of AlertTypes
		AlertType typeApartment = new AlertType();
		typeApartment.setType(Type.APARTMENT);
		AlertType typeLoft = new AlertType();
		typeLoft.setType(Type.LOFT);
		AlertType typeVilla = new AlertType();
		typeVilla.setType(Type.VILLA);
		
		List<AlertType> alertTypes = new ArrayList<>();
		alertTypes.add(typeLoft);
		alertTypes.add(typeApartment);
		
		// second list for test
		List<AlertType> alertTypes2 = new ArrayList<>();
		AlertType typeHouse = new AlertType();
		typeHouse.setType(Type.HOUSE);
		alertTypes2.add(typeHouse);

		
		// 2 Alerts for the ese test-user
		Alert alert = new Alert();
		alert.setUser(ese);
		alert.setAlertTypes(alertTypes);
		alert.setCity("Bern");
		alert.setZipcode(3000);
		alert.setPrice(1500);
		alert.setRadius(30);
		alertDao.save(alert);
		
		alertTypes.add(typeVilla);
		
		Alert alert2 = new Alert();
		alert2.setUser(ese);
		alert2.setAlertTypes(alertTypes2);
		alert2.setCity("Zürich");
		alert2.setZipcode(8000);
		alert2.setPrice(1000);
		alert2.setRadius(25);
		alertDao.save(alert2);
		
		
		// One alert for Jane Doe
		alert = new Alert();
		alert.setUser(jane);
		alert.setCity("Luzern");
		alert.setZipcode(6003);
		alert.setPrice(900);
		alert.setRadius(22);
		alertDao.save(alert);
	}

}
