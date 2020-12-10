package com.syzs.dao;

import com.syzs.domain.Order;
import com.syzs.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDao extends JpaRepository<Order,Long>{
}
