package com.team9.userMS.Repository;

import org.springframework.data.repository.CrudRepository;

import com.team9.userMS.Entity.SellerEntity;

public interface SellerRepository extends CrudRepository<SellerEntity, String> {
	
	public SellerEntity findByPhoneNumber(String phoneNumber);
	
	public SellerEntity findByEmail(String email);
	
	public SellerEntity findBySellerId(String id);

}
