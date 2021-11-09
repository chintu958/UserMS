package com.team9.userMS.UserMS.Controller;

import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.team9.userMS.UserMS.DTO.SellerDTO;
import com.team9.userMS.UserMS.Exception.UserException;
import com.team9.userMS.UserMS.Service.SellerService;

@RestController
@CrossOrigin
public class SellerController {
	
	@Autowired
	private SellerService sellerService;
	
	
	@PostMapping(value = "/userMS/seller/register")
	public ResponseEntity<String> registerSeller(@RequestBody SellerDTO sellerDto){
		
		try {
		String s ="Seller registered successfully with seller Id : "+ sellerService.sellerRegistration(sellerDto);
		return new ResponseEntity<>(s,HttpStatus.OK);
		}
		catch(UserException e)
		{
			return new ResponseEntity<>(e.getMessage(),HttpStatus.EXPECTATION_FAILED);
		}

	}
	
	@PostMapping(value = "/userMS/seller/login/{email}/{password}")
	public ResponseEntity<String> loginSeller(@PathVariable String email, @PathVariable String password)
	{
		try {
			String msg = sellerService.sellerLogin(email, password);
			return new ResponseEntity<String>(msg,HttpStatus.OK);
		}
		catch(UserException e)
		{
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping(value = "/userMS/seller/deactivate/{id}")
	public ResponseEntity<String> deleteSellerAccount(@PathVariable String id){
		
		String msg = sellerService.deleteSeller(id);
		
		return new ResponseEntity<>(msg,HttpStatus.OK);
	}
	

}
