package com.team9.userMS.UserMS.Entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.team9.userMS.UserMS.Utility.CompoundKey;

@Entity
@Table(name = "wishlist")
public class WishlistEnity {
	
	@EmbeddedId
	private CompoundKey compoundId;

	public CompoundKey getCompoundId() {
		return compoundId;
	}

	public void setCompoundId(CompoundKey compoundId) {
		this.compoundId = compoundId;
	}
	
	

}
