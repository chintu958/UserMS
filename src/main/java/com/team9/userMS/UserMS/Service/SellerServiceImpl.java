package com.team9.userMS.UserMS.Service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.team9.userMS.UserMS.DTO.SellerDTO;
import com.team9.userMS.UserMS.Entity.SellerEntity;
import com.team9.userMS.UserMS.Exception.UserException;
import com.team9.userMS.UserMS.Repository.SellerRepository;
import com.team9.userMS.UserMS.Validator.SellerValidator;

@Service(value = "sellerSrvice")
@Transactional
public class SellerServiceImpl implements SellerService{
	
	private static int s;
	
	static {
		s=100;
		
	}
	
	@Autowired
	private SellerRepository sellerRepository;

	@Override
	public String sellerRegistration(SellerDTO sellerDTO) throws UserException {
		SellerValidator.validateSeller(sellerDTO);
		
		SellerEntity seller = sellerRepository.findByPhoneNumber(sellerDTO.getPhoneNumber());
		
		if(seller != null)
			throw new UserException("Seller Already present");
		
		String id = "S" + s++;
		
		seller = new SellerEntity();
		
		seller.setEmail(sellerDTO.getEmail());
		seller.setSellerId(id);
		seller.setName(sellerDTO.getName());
		seller.setPassword(sellerDTO.getPassword());
		seller.setIsActive("False");
		seller.setPhoneNumber(sellerDTO.getPhoneNumber());
		
		sellerRepository.save(seller);
		
		return seller.getSellerId();
	}

	@Override
	public String sellerLogin(String email, String password) throws UserException {
		if(!SellerValidator.validateEmail(email))
			throw new UserException("Enter valid email id");
		
		SellerEntity seller = sellerRepository.findByEmail(email);
		
//		System.out.println(seller);
		
		if(seller == null)
			throw new UserException("Wrong credentials");
		
		if(!seller.getPassword().equals(password))
			throw new UserException("Wrong credentials");
		
		seller.setIsActive("True");
			
		sellerRepository.save(seller);
		
		return "Login Success";
	}

	@Override
	public String deleteSeller(String id) {
		SellerEntity seller = sellerRepository.findBySellerId(id);
		
		sellerRepository.delete(seller);
		
		return "Account successfully deleted";
	}
	
	
	
	

}
