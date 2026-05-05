package com.okcir.et.order.order.state;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.okcir.et.order.order.state.dto.RfqStatus;

@Component
public class RfqStateFactory {

  private final Map<RfqStatus, RfqState> states;

  // Spring secara otomatis mengumpulkan semua implementasi RfqState ke dalam Map
  public RfqStateFactory(List<RfqState> stateList) {
    this.states = stateList.stream()
        .collect(Collectors.toMap(RfqState::getStatus, s -> s));
  }

  public RfqState getState(RfqStatus status) {
    return states.get(status);
  }
}
