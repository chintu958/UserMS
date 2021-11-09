package com.team9.userMS.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.team9.userMS.Entity.CartEntity;
import com.team9.userMS.Utility.CompoundKey;

public interface CartRepository extends CrudRepository<CartEntity, CompoundKey>{
	
	public List<CartEntity> findByCompoundKeyBuyerId(String id); 
	
	public void deleteByCompoundKeyBuyerIdAndCompoundKeyProdId(String buyId,String prodId);
	
	public CartEntity findByCompoundKeyBuyerIdAndCompoundKeyProdId(String buyId,String ProdId);

}
