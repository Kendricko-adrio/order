package com.okcir.et.order.order.mapper;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.okcir.et.order.order.redis.OrderRedis;
import com.okcir.et.order.order.state.dto.RfqOrderDTO;

/**
 * Mapper class for converting between RfqOrderDTO and OrderRedis entities.
 */
@Component
public class OrderMapper {

  /**
   * Converts RfqOrderDTO to OrderRedis with a newly generated UUID.
   * 
   * @param rfqOrderDTO the source DTO
   * @return OrderRedis entity with all fields mapped and a new UUID
   */
  public OrderRedis toOrderRedis(RfqOrderDTO rfqOrderDTO) {
    if (rfqOrderDTO == null) {
      return null;
    }

    OrderRedis orderRedis = new OrderRedis();
    orderRedis.setId(UUID.randomUUID());
    orderRedis.setDealerId(rfqOrderDTO.getDealerId());
    orderRedis.setClientId(rfqOrderDTO.getClientId());
    orderRedis.setSpotPrice(rfqOrderDTO.getSpotPrice());
    orderRedis.setClientPrice(rfqOrderDTO.getClientPrice());
    orderRedis.setFwdPoints(rfqOrderDTO.getFwdPoints());
    orderRedis.setAllInrate(rfqOrderDTO.getAllInrate());
    orderRedis.setAmount1(rfqOrderDTO.getAmount1());
    orderRedis.setCcyPair(rfqOrderDTO.getCcyPair());

    return orderRedis;
  }

  /**
   * Converts RfqOrderDTO to OrderRedis with a specified UUID.
   * 
   * @param rfqOrderDTO the source DTO
   * @param id          the UUID to assign to the OrderRedis entity
   * @return OrderRedis entity with all fields mapped and the specified UUID
   */
  public OrderRedis toOrderRedis(RfqOrderDTO rfqOrderDTO, UUID id) {
    if (rfqOrderDTO == null) {
      return null;
    }

    OrderRedis orderRedis = toOrderRedis(rfqOrderDTO);
    orderRedis.setId(id);

    return orderRedis;
  }

  /**
   * Converts OrderRedis to RfqOrderDTO.
   * Note: The UUID field from OrderRedis is not mapped to RfqOrderDTO.
   * 
   * @param orderRedis the source Redis entity
   * @return RfqOrderDTO with all matching fields mapped
   */
  public RfqOrderDTO toRfqOrderDTO(OrderRedis orderRedis) {
    if (orderRedis == null) {
      return null;
    }

    RfqOrderDTO rfqOrderDTO = new RfqOrderDTO();
    rfqOrderDTO.setDealerId(orderRedis.getDealerId());
    rfqOrderDTO.setClientId(orderRedis.getClientId());
    rfqOrderDTO.setSpotPrice(orderRedis.getSpotPrice());
    rfqOrderDTO.setClientPrice(orderRedis.getClientPrice());
    rfqOrderDTO.setFwdPoints(orderRedis.getFwdPoints());
    rfqOrderDTO.setAllInrate(orderRedis.getAllInrate());
    rfqOrderDTO.setAmount1(orderRedis.getAmount1());
    rfqOrderDTO.setCcyPair(orderRedis.getCcyPair());

    return rfqOrderDTO;
  }

  /**
   * Updates an existing OrderRedis entity with values from RfqOrderDTO.
   * The UUID is preserved from the existing entity.
   * 
   * @param rfqOrderDTO        the source DTO
   * @param existingOrderRedis the existing OrderRedis entity to update
   * @return the updated OrderRedis entity
   */
  public OrderRedis updateOrderRedis(RfqOrderDTO rfqOrderDTO, OrderRedis existingOrderRedis) {
    if (rfqOrderDTO == null || existingOrderRedis == null) {
      return existingOrderRedis;
    }

    existingOrderRedis.setDealerId(rfqOrderDTO.getDealerId());
    existingOrderRedis.setClientId(rfqOrderDTO.getClientId());
    existingOrderRedis.setSpotPrice(rfqOrderDTO.getSpotPrice());
    existingOrderRedis.setClientPrice(rfqOrderDTO.getClientPrice());
    existingOrderRedis.setFwdPoints(rfqOrderDTO.getFwdPoints());
    existingOrderRedis.setAllInrate(rfqOrderDTO.getAllInrate());
    existingOrderRedis.setAmount1(rfqOrderDTO.getAmount1());
    existingOrderRedis.setCcyPair(rfqOrderDTO.getCcyPair());

    return existingOrderRedis;
  }
}
