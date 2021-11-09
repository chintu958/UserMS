package com.team9.userMS.UserMS.Repository;

import org.springframework.data.repository.CrudRepository;

import com.team9.userMS.UserMS.Entity.WishlistEnity;
import com.team9.userMS.UserMS.Utility.CompoundKey;

public interface WishlistRepository extends CrudRepository<WishlistEnity, CompoundKey> {

}
