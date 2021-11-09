package com.team9.userMS.UserMS.Service;

import com.team9.userMS.UserMS.DTO.SellerDTO;
import com.team9.userMS.UserMS.Exception.UserException;

public interface SellerService {
	
	
	public String sellerRegistration(SellerDTO sellerDTO) throws UserException;
	
	public String sellerLogin(String email, String password) throws UserException;
	public String deleteSeller(String id);
	

}
