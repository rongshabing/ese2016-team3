package ch.unibe.ese.team3.model.dao;

import org.springframework.data.repository.CrudRepository;

import ch.unibe.ese.team3.model.Ad;
import ch.unibe.ese.team3.model.BuyMode;
import ch.unibe.ese.team3.model.Type;
import ch.unibe.ese.team3.model.User;

public interface AdDao extends CrudRepository<Ad, Long> {
	
	public Iterable<Ad> findByUser(User user);

	public Iterable<Ad> findByPrizePerMonthLessThanAndTypeInAndBuyMode(int i, Type[] types, BuyMode buyMode);

	public Iterable<Ad> findByPrizePerMonthLessThanAndBuyMode(int i, BuyMode buyMode);
}