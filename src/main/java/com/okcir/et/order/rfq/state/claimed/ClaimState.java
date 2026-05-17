package com.okcir.et.order.rfq.state.claimed;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClaimState extends AbstractClaimRfq{


    @Override
    public void claimed(String orderId) {

    }
}
