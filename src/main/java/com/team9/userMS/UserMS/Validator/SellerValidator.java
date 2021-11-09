package com.team9.userMS.UserMS.Validator;

import com.team9.userMS.UserMS.DTO.SellerDTO;
import com.team9.userMS.UserMS.Exception.UserException;

public class SellerValidator {
	
public static void validateSeller(SellerDTO seller) throws UserException {
		
		if(!validateName(seller.getName()))
			throw new UserException("Validator.PLEASE_ENTER_VALID_NAME");
		
		if(!validateEmail(seller.getEmail()))
			throw new UserException("Validator.PLEASE_ENTER_VALID_EMAIL");
		
		if(!validateContactNumber(seller.getPhoneNumber()))
			throw new UserException("Validator.PLEASE_ENTER_VALID_NUMBER");
		
			
		if(!validatePassword(seller.getPassword()))
			throw new UserException("Validator.PLEASE_Enter_VALID_PASSWORD");
		
	}
	
	
	public static boolean validateName(String name)
	{
		
		String regex = "[A-Za-z]+([ ][A-Za-z]+)*";
		
		if(name.matches(regex))
			return true;
		
		return false;
		
	}
	
	public static boolean validateEmail(String email)
	{
		String regex = "[A-za-z]+@[A-za-z]+[\\.]com";
		
		if(email.matches(regex))
			return true;
		
		return false;
	}
	
	public static boolean validateContactNumber(String contactNumber)
	{
		
		String regex = "[6,7,8,9][0-9]{9}";
		
		if(contactNumber.matches(regex))
			return true;
		
		return false;
	}
	
	public static boolean validatePassword(String password)
	{
		String regex = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{7,20}$";
		
		if(password.matches(regex))
			return true;
		
		return false;
	}	


}
