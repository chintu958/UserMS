package com.team9.userMS.Service;

import com.team9.userMS.DTO.SellerDTO;
import com.team9.userMS.Exception.UserException;

public interface SellerService {
	
	
	public String sellerRegistration(SellerDTO sellerDTO) throws UserException;
	
	public String sellerLogin(String email, String password) throws UserException;
	public String deleteSeller(String id);
	

}
