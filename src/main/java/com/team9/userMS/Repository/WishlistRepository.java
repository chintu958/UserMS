package com.team9.userMS.Repository;

import org.springframework.data.repository.CrudRepository;

import com.team9.userMS.Entity.WishlistEnity;
import com.team9.userMS.Utility.CompoundKey;

public interface WishlistRepository extends CrudRepository<WishlistEnity, CompoundKey> {

}
