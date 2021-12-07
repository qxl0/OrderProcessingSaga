package com.qiang.UserService.projection;

import com.qiang.CommonService.model.CardDetails;
import com.qiang.CommonService.model.User;
import com.qiang.CommonService.queries.GetUserPaymentDetailsQuery;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

@Component
public class UserProjection {
    @QueryHandler
    public User getUserPaymentDetails(GetUserPaymentDetailsQuery query){
        CardDetails cardDetails =
                CardDetails.builder()
                        .name("John Smith")
                        .validUntilMonth(01)
                        .validUntilYear(24)
                        .cardNumber("123454566")
                        .cvv(111)
                        .build();

        return User.builder()
                .userId(query.getUserId())
                .firstName("John")
                .lastName("Smith")
                .cardDetails(cardDetails)
                .build();
    }
}
