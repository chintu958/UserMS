package com.team9.userMS.UserMS.Controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import com.team9.userMS.UserMS.DTO.BuyerDTO;
import com.team9.userMS.UserMS.DTO.CartDTO;
import com.team9.userMS.UserMS.DTO.ProductDTO;
import com.team9.userMS.UserMS.Exception.UserException;
import com.team9.userMS.UserMS.Service.BuyerService;

@RestController
@CrossOrigin
public class BuyerController {
	
	private static final Log LOGGER = LogFactory.getLog(BuyerController.class);
	
	@Autowired
	private BuyerService buyerService;
	
	@Value("${product.uri}")
	String prodUri;
	
	@PostMapping(value = "/userMS/buyer/register")
	public ResponseEntity<String> registerBuyer(@RequestBody BuyerDTO buyerDto){
		
		try {
		String s ="Buyer registered successfully with buyer Id : " + buyerService.buyerRegistration(buyerDto);
		return new ResponseEntity<>(s,HttpStatus.OK);
		}
		catch(UserException e)
		{
			String s = e.getMessage();
			return new ResponseEntity<>(s,HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	
	@PostMapping(value = "/userMS/buyer/login/{email}/{password}")
	public ResponseEntity<String> loginBuyer(@PathVariable String email, @PathVariable String password)
	{
		try {
			String msg = buyerService.buyerLogin(email, password);
			return new ResponseEntity<>(msg,HttpStatus.OK);
		}
		catch(UserException e)
		{
			return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
		}
	}
	
	
	
	@DeleteMapping(value = "/userMS/buyer/deactivate/{id}")
	public ResponseEntity<String> deleteBuyerAccount(@PathVariable String id){
		
		String msg = buyerService.deleteBuyer(id);
		
		return new ResponseEntity<>(msg,HttpStatus.OK);
		
		
	}
	
	
	
	@PostMapping(value = "/userMS/buyer/wishlist/add/{buyerId}/{prodId}")
	public ResponseEntity<String> addProductToWishlist(@PathVariable String buyerId, @PathVariable String prodId) throws UserException
	{
		try {
//		List<ServiceInstance> instances=client.getInstances("PRODUCTMS");
//		ServiceInstance instance=instances.get(0);
//		URI productUri = instance.getUri();
		
		ProductDTO product = new RestTemplate().getForObject(prodUri+"/prodMS/getById/"+prodId, ProductDTO.class);
		
		String msg = buyerService.wishlistService(product.getProdId(), buyerId);
		
		return new ResponseEntity<>(msg,HttpStatus.ACCEPTED);
		}
		catch(Exception e)
		{
			System.out.println(e);
			String newMsg = "There was some error";
			if(e.getMessage().equals("404 null"))
			{
				newMsg = "There are no PRODUCTS for the given product ID";
			}
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,newMsg,e);
		}
	}
	
	@PostMapping(value = "/userMS/buyer/cart/add/{buyerId}/{prodId}/{quantity}")
	public ResponseEntity<String> addProductToCart(@PathVariable String buyerId, @PathVariable String prodId, @PathVariable Integer quantity) throws UserException
	{
		try {
//		List<ServiceInstance> instances=client.getInstances("PRODUCTMS");
//		ServiceInstance instance=instances.get(0);
//		URI productUri = instance.getUri();
//		System.out.println(productUri);
		
		ProductDTO product = new RestTemplate().getForObject(prodUri+"/prodMS/getById/"+prodId, ProductDTO.class);
		System.out.println(product);
		System.out.println(product instanceof ProductDTO);
		String msg = buyerService.cartService(product.getProdId(), buyerId, quantity);
		
		return new ResponseEntity<>(msg,HttpStatus.ACCEPTED);
		}
		catch(Exception e)
		{
			String newMsg = "There was some error";
			if(e.getMessage().equals("404 null"))
			{
				newMsg = "There are no PRODUCTS for the given product ID";
			}
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,newMsg,e);
		}
	}
	
	
	@GetMapping(value = "/userMS/buyer/cart/get/{buyerId}")
	public ResponseEntity<List<CartDTO>> getProductListFromCart(@PathVariable String buyerId) throws UserException
	{
		
		//ProductDTO product = new RestTemplate().getForObject("http://localhost:8100/prodMs/getByName/"+prodName, ProductDTO.class);
		try {
		List<CartDTO> list = buyerService.getCartProducts(buyerId);
		return new ResponseEntity<>(list,HttpStatus.ACCEPTED);
		}
		catch(UserException e)
		{
			System.out.println(e.getMessage());
			String msg = e.getMessage();
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, msg, e);
			
		}
	}
	
	@PostMapping(value = "/userMS/buyer/cart/remove/{buyerId}/{prodId}")
	public ResponseEntity<String> removeFromCart(@PathVariable String buyerId,@PathVariable String prodId) throws UserException
	{
		
		//ProductDTO product = new RestTemplate().getForObject("http://localhost:8100/prodMs/getByName/"+prodName, ProductDTO.class);
		try {
		String msg = buyerService.removeFromCart(buyerId, prodId);
		
		return new ResponseEntity<>(msg,HttpStatus.OK);
		}
		catch(UserException e)
		{
			String msg = e.getMessage();
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, msg, e);
			
		}
	}
	
	@GetMapping(value = "/userMS/updateRewardPoints/{buyerId}/{rewPoints}")
	public ResponseEntity<String> updateRewardPoints(@PathVariable String buyerId, @PathVariable Integer rewPoints)
	{
		try {
			String msg = buyerService.updateRewardPoint(buyerId, rewPoints);
			return new ResponseEntity<>(msg,HttpStatus.OK);
		}
		catch(Exception e)
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage(),e);
		}
	}

}
