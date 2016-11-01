package ch.unibe.ese.team3.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * Describes an alert. An alert can be created by a user. If ads matching the
 * criteria of the alert are added to the platform later, the user will be
 * notified.
 */
@Entity
public class Alert {

	@Id
	@GeneratedValue
	private long id;

	@ManyToOne
	private User user;

	@Column(nullable = false)
	private int zipcode;

	@Column(nullable = false)
	private String city;

	@Column(nullable = false)
	private int price;

	@Column(nullable = false)
	private int radius;

	@Column
	private boolean studio;

	@Column
	private boolean room;

	@Column
	private boolean bothRoomAndStudio;
	
	@Fetch(FetchMode.SELECT)
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)	
	private List<AlertType> alertTypes;
	
	
	private Type type; 
	
	public List<AlertType> getAlertTypes() {
		return alertTypes;
	}

	public void setAlertTypes(List<AlertType> alertTypes) {
		this.alertTypes = alertTypes;
	}

	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getZipcode() {
		return zipcode;
	}

	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	
	// new 
	public Type getType(){
		return type;
	}
	public void setType(Type type){
		this.type = type;
	}
	
	/*
	public boolean getStudio() {
		return studio;
	}

	public void setStudio(boolean studio) {
		this.studio = studio;
	}
	*/

	public boolean getRoom() {
		return room;
	}

	public void setRoom(boolean room) {
		this.room = room;
	}

	public boolean getBothRoomAndStudio() {
		return bothRoomAndStudio;
	}

	public void setBothRoomAndStudio(boolean bothRoomAndStudio) {
		this.bothRoomAndStudio = bothRoomAndStudio;
	}
}
