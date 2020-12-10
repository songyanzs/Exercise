package com.syzs.dao;

import com.syzs.domain.Product;
import com.syzs.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDao extends JpaRepository<Product,Integer>{
}
