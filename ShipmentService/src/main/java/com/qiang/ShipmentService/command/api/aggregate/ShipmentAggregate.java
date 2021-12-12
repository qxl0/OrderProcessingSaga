package com.qiang.ShipmentService.command.api.aggregate;

import com.qiang.CommonService.commands.ShipOrderCommand;
import com.qiang.CommonService.events.OrderShippedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class ShipmentAggregate {
    @AggregateIdentifier
    private String shipmentId;
    private String orderId;
    private String shipmentStatus;

    public ShipmentAggregate(){

    }

    @CommandHandler
    public ShipmentAggregate(ShipOrderCommand shipOrderCommand){
        // Validate
        // publish Order shipped event
        OrderShippedEvent orderShippedEvent =
                OrderShippedEvent.builder()
                        .shipmentId(shipOrderCommand.getShipmentId())
                        .orderId(shipOrderCommand.getOrderId())
                        .shipmentStatus("COMPLETED")
                        .build();
        AggregateLifecycle.apply(orderShippedEvent);
    }

    @EventSourcingHandler
    public void on(OrderShippedEvent event){
       this.orderId = event.getOrderId();
       this.shipmentId = event.getShipmentId();
       this.shipmentStatus = event.getShipmentStatus();
    }
}
