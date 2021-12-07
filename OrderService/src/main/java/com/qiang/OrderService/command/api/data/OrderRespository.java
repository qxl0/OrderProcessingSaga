package com.qiang.OrderService.command.api.data;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRespository extends JpaRepository<Order,String> {
}
