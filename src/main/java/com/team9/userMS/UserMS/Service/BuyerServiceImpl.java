package com.team9.userMS.UserMS.Service;

import java.util.ArrayList;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.team9.userMS.UserMS.DTO.BuyerDTO;
import com.team9.userMS.UserMS.DTO.CartDTO;
import com.team9.userMS.UserMS.Entity.BuyerEntity;
import com.team9.userMS.UserMS.Entity.CartEntity;
import com.team9.userMS.UserMS.Entity.WishlistEnity;
import com.team9.userMS.UserMS.Exception.UserException;
import com.team9.userMS.UserMS.Repository.BuyerRepository;
import com.team9.userMS.UserMS.Repository.CartRepository;
import com.team9.userMS.UserMS.Repository.WishlistRepository;
import com.team9.userMS.UserMS.Utility.CompoundKey;
import com.team9.userMS.UserMS.Validator.BuyerValidator;

@Service(value = "buyerService")
@Transactional
public class BuyerServiceImpl implements BuyerService {
	
	
	private static int b;
	
	
	static {
		b=100;
		
	}
	
	@Autowired
	private BuyerRepository buyerRepository;
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private WishlistRepository wishlistRepository;
	
	
	

	@Override
	public String buyerRegistration(BuyerDTO buyerDTO) throws UserException {
		BuyerValidator.validateBuyer(buyerDTO);
		
		BuyerEntity buy = buyerRepository.findByPhoneNumber(buyerDTO.getPhoneNumber());
		
		if(buy != null)
			throw new UserException("Buyer already present");
		
		String id = "B" + b++;
		
		buy = new BuyerEntity();
		
		buy.setBuyerId(id);
		buy.setEmail(buyerDTO.getEmail());
		buy.setName(buyerDTO.getName());
		buy.setPhoneNumber(buyerDTO.getPhoneNumber());
		buy.setPassword(buyerDTO.getPassword());
		buy.setIsActive("False");
		buy.setIsPrivileged("False");
		buy.setRewardPoints("0");
		
		buyerRepository.save(buy);
		
		return buy.getBuyerId();
		
	}

	@Override
	public String buyerLogin(String email, String password) throws UserException {
		if(!BuyerValidator.validateEmail(email))
			throw new UserException("Enter valid email id");
		
		BuyerEntity buyer = buyerRepository.findByEmail(email);
		
		
		if(buyer == null)
			throw new UserException("Wrong credentials");
		
		if(!buyer.getPassword().equals(password))
			throw new UserException("Wrong credentials");
		
		buyer.setIsActive("True");
			
		buyerRepository.save(buyer);
		
		return "Login Success";
	}

	@Override
	public String deleteBuyer(String id) {
		
		BuyerEntity buyer = buyerRepository.findByBuyerId(id);
		
		buyerRepository.delete(buyer);
		
		return "Account successfully deleted";
	}

	@Override
	public String wishlistService(String prodId, String buyerId) {
		CompoundKey cust = new CompoundKey(prodId,buyerId);
		
		WishlistEnity w = new WishlistEnity();
		
		w.setCompoundId(cust);
		
		wishlistRepository.save(w);
		
		return "Added Successfully to Wishlist";
	}

	@Override
	public String cartService(String prodId, String buyerId, Integer quantity) {
		CompoundKey cust = new CompoundKey(prodId,buyerId);
		
		CartEntity cart = new CartEntity();
		
		cart.setCompoundKey(cust);
		
		cart.setQuantity(quantity);
		
		cartRepository.save(cart);
		
		return "Added Successfully to Cart";
	}

	@Override
	public List<CartDTO> getCartProducts(String id) throws UserException {
		List<CartEntity> list = cartRepository.findByCompoundKeyBuyerId(id);
		
		if(list.isEmpty())
			throw new UserException("No Items In Cart");
		
		List<CartDTO> li = new ArrayList<>();
		
		for(CartEntity cart : list)
		{
			CartDTO cartDTO = new CartDTO();
			
			cartDTO.setBuyerId(cart.getCompoundKey().getBuyerId());
			cartDTO.setProdId(cart.getCompoundKey().getProdId());
			cartDTO.setQuantity(cart.getQuantity());
			
			li.add(cartDTO);
		}
		
		return li;
	}

	@Override
	public String removeFromCart(String buyerId, String prodId) throws UserException {
		CartEntity cart = cartRepository.findByCompoundKeyBuyerIdAndCompoundKeyProdId(buyerId, prodId);
		
		if(cart==null)
			throw new UserException("No Such Item In Cart");
		
		cartRepository.deleteByCompoundKeyBuyerIdAndCompoundKeyProdId(buyerId, prodId);
		
		return "The product was deleted successfully";
	}

	@Override
	public String updateRewardPoint(String buyerId, Integer rewPoints) throws UserException {
		BuyerEntity buyer = buyerRepository.findByBuyerId(buyerId);
		
		if(buyer==null)
			throw new UserException("Buyer not found");
		
		buyer.setRewardPoints(rewPoints.toString());
		
		buyerRepository.save(buyer);
		
		return "Reward Points Updated for buyer Id : "+ buyer.getBuyerId();
	}

}
