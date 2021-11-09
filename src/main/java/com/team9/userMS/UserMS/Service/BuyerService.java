package com.team9.userMS.UserMS.Service;

import java.util.List;

import com.team9.userMS.UserMS.DTO.BuyerDTO;
import com.team9.userMS.UserMS.DTO.CartDTO;
import com.team9.userMS.UserMS.Exception.UserException;

public interface BuyerService {
	
	public String buyerRegistration(BuyerDTO buyerDTO) throws UserException;
	public String buyerLogin(String email, String password) throws UserException;
	public String deleteBuyer(String id);
	public String wishlistService(String prodId,String buyerId);
	
	public String cartService(String prodId, String buyerId, Integer quantity);
	
	public List<CartDTO> getCartProducts(String id) throws UserException;
	
	public String removeFromCart(String buyerId, String prodId) throws UserException;
	
	public String updateRewardPoint(String buyerId, Integer rewPoints) throws UserException;

}
