package com.team9.userMS.Repository;

import org.springframework.data.repository.CrudRepository;

import com.team9.userMS.Entity.BuyerEntity;

public interface BuyerRepository extends CrudRepository<BuyerEntity, String> {
	
	public BuyerEntity findByPhoneNumber(String phoneNumber);
	
	public BuyerEntity findByEmail(String email);
	
	public BuyerEntity findByBuyerId(String id);

}
