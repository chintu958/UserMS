package com.team9.userMS.Entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.team9.userMS.Utility.CompoundKey;

@Entity
@Table(name = "cart")
public class CartEntity {
	
	
	@EmbeddedId
	private CompoundKey compoundKey;
	
	private Integer quantity;

	public CompoundKey getCompoundKey() {
		return compoundKey;
	}

	public void setCompoundKey(CompoundKey compoundKey) {
		this.compoundKey = compoundKey;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	


}
