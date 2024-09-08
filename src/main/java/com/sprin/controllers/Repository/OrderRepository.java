package com.sprin.controllers.Repository;

import com.sprin.controllers.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
