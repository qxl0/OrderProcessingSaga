package com.qiang.OrderService.command.api.events;

import com.qiang.CommonService.events.OrderCompletedEvent;
import com.qiang.OrderService.command.api.data.Order;
import com.qiang.OrderService.command.api.data.OrderRespository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class OrderEventsHandler {
    private OrderRespository orderRespository;

    public OrderEventsHandler(OrderRespository orderRespository) {
        this.orderRespository = orderRespository;
    }

    @EventHandler
    public void on(OrderCreatedEvent event){
       Order order = new Order();
        BeanUtils.copyProperties(event, order);

        orderRespository.save(order);
    }


    @EventHandler
    public void on(OrderCompletedEvent event){
       Order order = orderRespository.findById(event.getOrderId()).get();
       order.setOrderStatus(event.getOrderStatus());

       orderRespository.save(order);

    }
}
