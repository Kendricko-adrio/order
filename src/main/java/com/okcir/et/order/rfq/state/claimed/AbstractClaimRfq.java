package com.okcir.et.order.order.state.claimed;

import com.okcir.et.order.order.state.RfqState;
import com.okcir.et.order.order.state.dto.RfqOrderDTO;
import com.okcir.et.order.order.state.dto.RfqStatus;

public abstract class AbstractClaimRfq implements RfqState {
    @Override
    public void created(RfqOrderDTO rfq) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void accepted(RfqOrderDTO rfq) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void rejected(RfqOrderDTO rfq) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void rateSubmit(RfqOrderDTO rfq) {
        throw new UnsupportedOperationException();
    }

    @Override
    public RfqStatus getStatus() {
        return RfqStatus.CLAIMED;
    }
}
