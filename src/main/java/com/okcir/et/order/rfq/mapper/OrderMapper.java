package com.okcir.et.order.rfq.mapper;

import java.util.UUID;

import com.github.f4b6a3.uuid.UuidCreator;
import org.springframework.stereotype.Component;

import com.okcir.et.order.rfq.jpa.entity.DealsRfqEvent;
import com.okcir.et.order.rfq.redis.OrderRedis;
import com.okcir.et.order.rfq.state.dto.RfqOrderDTO;

/**
 * Mapper class for converting between RfqOrderDTO, DealsRfqEvent, and OrderRedis.
 */
@Component
public class OrderMapper {

  /**
   * Converts RfqOrderDTO to DealsRfqEvent with a newly generated orderId UUID.
   * Only fields present in the DTO are mapped; all others remain null.
   *
   * @param rfqOrderDTO the source DTO
   * @return DealsRfqEvent entity with mapped fields and a new orderId UUID
   */
  public DealsRfqEvent toDealsRfqEvent(RfqOrderDTO rfqOrderDTO) {
    if (rfqOrderDTO == null) {
      return null;
    }

    DealsRfqEvent event = new DealsRfqEvent();
    event.setOrderId(UuidCreator.getTimeOrderedEpoch());
    event.setAmount1(rfqOrderDTO.getAmount1());
    event.setCurrencyPair(rfqOrderDTO.getCcyPair());
    event.setSourceAccount(rfqOrderDTO.getSourceAccount());
    event.setDestinationAccount(rfqOrderDTO.getDestinationAccount());

    if (rfqOrderDTO.getCustomWindow() != null) {
      event.setIdentityTypeLabel(rfqOrderDTO.getCustomWindow().getIdentityTypeLabel());
      event.setIdentityNumber(rfqOrderDTO.getCustomWindow().getIdentityNumber());
      event.setDealPurposeId(rfqOrderDTO.getCustomWindow().getDealPurposeId());
      event.setDocumentCodeId(rfqOrderDTO.getCustomWindow().getDocumentCodeId());
      event.setDocumentDescription(rfqOrderDTO.getCustomWindow().getDocumentDescription());
      event.setNpwp(rfqOrderDTO.getCustomWindow().getNpwp());
    }

    return event;
  }

  /**
   * Converts DealsRfqEvent to OrderRedis.
   * The OrderRedis id is set from the entity's orderId.
   *
   * @param entity the source JPA entity
   * @return OrderRedis entity with all fields mapped
   */
  public OrderRedis toOrderRedis(DealsRfqEvent entity) {
    if (entity == null) {
      return null;
    }

    OrderRedis orderRedis = new OrderRedis();
    orderRedis.setId(entity.getOrderId());
    orderRedis.setOrderStatus(entity.getOrderStatus());
    orderRedis.setIdentityTypeLabel(entity.getIdentityTypeLabel());
    orderRedis.setIdentityNumber(entity.getIdentityNumber());
    orderRedis.setAmount1(entity.getAmount1());
    orderRedis.setAmount2(entity.getAmount2());
    orderRedis.setFxRate(entity.getFxRate());
    orderRedis.setTransactionType(entity.getTransactionType());
    orderRedis.setDealPurposeId(entity.getDealPurposeId());
    orderRedis.setCustomerName(entity.getCustomerName());
    orderRedis.setBranchCode(entity.getBranchCode());
    orderRedis.setCurrencyPair(entity.getCurrencyPair());
    orderRedis.setDocumentCodeId(entity.getDocumentCodeId());
    orderRedis.setDocumentDescription(entity.getDocumentDescription());
    orderRedis.setNpwp(entity.getNpwp());
    orderRedis.setCounterpartyShortName(entity.getCounterpartyShortName());
    orderRedis.setTypology(entity.getTypology());
    orderRedis.setUserShortName(entity.getUserShortName());
    orderRedis.setTradeTime(entity.getTradeTime());
    orderRedis.setTradeDate(entity.getTradeDate());
    orderRedis.setMaturityDate(entity.getMaturityDate());
    orderRedis.setDisclaimer(entity.getDisclaimer());
    orderRedis.setSpecialTransaction(entity.getSpecialTransaction());
    orderRedis.setDocumentFlag(entity.getDocumentFlag());
    orderRedis.setSourceAccount(entity.getSourceAccount());
    orderRedis.setDestinationAccount(entity.getDestinationAccount());
    orderRedis.setCin(entity.getCin());
    orderRedis.setSettlementOption(entity.getSettlementOption());
    orderRedis.setEquivalentMarginAmountIdr(entity.getEquivalentMarginAmountIdr());
    orderRedis.setEquivalentMarginCurrency(entity.getEquivalentMarginCurrency());
    orderRedis.setCustomerPic(entity.getCustomerPic());
    orderRedis.setMrrType(entity.getMrrType());
    orderRedis.setRiskCurrency(entity.getRiskCurrency());
    orderRedis.setRiskAmount(entity.getRiskAmount());
    orderRedis.setEventReason(entity.getEventReason());
    orderRedis.setFixingParent(entity.getFixingParent());
    orderRedis.setDealStatus(entity.getDealStatus());
    orderRedis.setRefSpot(entity.getRefSpot());
    orderRedis.setNetSpot(entity.getNetSpot());
    orderRedis.setForward(entity.getForward());
    orderRedis.setClientSwapNearLeg(entity.getClientSwapNearLeg());
    orderRedis.setInternalSwapNearLeg(entity.getInternalSwapNearLeg());
    orderRedis.setClientSwapFarLeg(entity.getClientSwapFarLeg());
    orderRedis.setInternalSwapFarLeg(entity.getInternalSwapFarLeg());
    orderRedis.setCaptureDate(entity.getCaptureDate());
    orderRedis.setAmount1Leg2(entity.getAmount1Leg2());
    orderRedis.setAmount2Leg2(entity.getAmount2Leg2());
    orderRedis.setTransactionCode(entity.getTransactionCode());
    orderRedis.setClientId(entity.getClientId());
    orderRedis.setTraderId(entity.getTraderId());
    orderRedis.setComments(entity.getComments());
    orderRedis.setCapturedAmount(entity.getCapturedAmount());
    orderRedis.setValueDate(entity.getValueDate());
    orderRedis.setSettlementType(entity.getSettlementType());
    orderRedis.setCreatedAt(entity.getCreatedAt());
    orderRedis.setUpdatedAt(entity.getUpdatedAt());

    return orderRedis;
  }

  /**
   * Converts RfqOrderDTO to OrderRedis with a newly generated UUID.
   * Only fields present in the DTO are mapped; all others remain null.
   *
   * @param rfqOrderDTO the source DTO
   * @return OrderRedis entity with matching fields mapped and a new UUID
   */
  public OrderRedis toOrderRedis(RfqOrderDTO rfqOrderDTO) {
    if (rfqOrderDTO == null) {
      return null;
    }

    OrderRedis orderRedis = new OrderRedis();
    orderRedis.setId(UUID.randomUUID());
    orderRedis.setAmount1(rfqOrderDTO.getAmount1());
    orderRedis.setCurrencyPair(rfqOrderDTO.getCcyPair());
    orderRedis.setSourceAccount(rfqOrderDTO.getSourceAccount());
    orderRedis.setDestinationAccount(rfqOrderDTO.getDestinationAccount());

    if (rfqOrderDTO.getCustomWindow() != null) {
      orderRedis.setIdentityTypeLabel(rfqOrderDTO.getCustomWindow().getIdentityTypeLabel());
      orderRedis.setIdentityNumber(rfqOrderDTO.getCustomWindow().getIdentityNumber());
      orderRedis.setDealPurposeId(rfqOrderDTO.getCustomWindow().getDealPurposeId());
      orderRedis.setDocumentCodeId(rfqOrderDTO.getCustomWindow().getDocumentCodeId());
      orderRedis.setDocumentDescription(rfqOrderDTO.getCustomWindow().getDocumentDescription());
      orderRedis.setNpwp(rfqOrderDTO.getCustomWindow().getNpwp());
    }

    return orderRedis;
  }

  /**
   * Converts RfqOrderDTO to OrderRedis with a specified UUID.
   *
   * @param rfqOrderDTO the source DTO
   * @param id          the UUID to assign to the OrderRedis entity
   * @return OrderRedis entity with matching fields mapped and the specified UUID
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
   * Only fields present in both are mapped.
   *
   * @param orderRedis the source Redis entity
   * @return RfqOrderDTO with matching fields mapped
   */
  public RfqOrderDTO toRfqOrderDTO(OrderRedis orderRedis) {
    if (orderRedis == null) {
      return null;
    }

    RfqOrderDTO rfqOrderDTO = new RfqOrderDTO();
    rfqOrderDTO.setAmount1(orderRedis.getAmount1());
    rfqOrderDTO.setCcyPair(orderRedis.getCurrencyPair());
    rfqOrderDTO.setSourceAccount(orderRedis.getSourceAccount());
    rfqOrderDTO.setDestinationAccount(orderRedis.getDestinationAccount());

    RfqOrderDTO.CustomWindow customWindow = new RfqOrderDTO.CustomWindow();
    customWindow.setIdentityTypeLabel(orderRedis.getIdentityTypeLabel());
    customWindow.setIdentityNumber(orderRedis.getIdentityNumber());
    customWindow.setDealPurposeId(orderRedis.getDealPurposeId());
    customWindow.setDocumentCodeId(orderRedis.getDocumentCodeId());
    customWindow.setDocumentDescription(orderRedis.getDocumentDescription());
    customWindow.setNpwp(orderRedis.getNpwp());
    rfqOrderDTO.setCustomWindow(customWindow);

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

    existingOrderRedis.setAmount1(rfqOrderDTO.getAmount1());
    existingOrderRedis.setCurrencyPair(rfqOrderDTO.getCcyPair());
    existingOrderRedis.setSourceAccount(rfqOrderDTO.getSourceAccount());
    existingOrderRedis.setDestinationAccount(rfqOrderDTO.getDestinationAccount());

    if (rfqOrderDTO.getCustomWindow() != null) {
      existingOrderRedis.setIdentityTypeLabel(rfqOrderDTO.getCustomWindow().getIdentityTypeLabel());
      existingOrderRedis.setIdentityNumber(rfqOrderDTO.getCustomWindow().getIdentityNumber());
      existingOrderRedis.setDealPurposeId(rfqOrderDTO.getCustomWindow().getDealPurposeId());
      existingOrderRedis.setDocumentCodeId(rfqOrderDTO.getCustomWindow().getDocumentCodeId());
      existingOrderRedis.setDocumentDescription(rfqOrderDTO.getCustomWindow().getDocumentDescription());
      existingOrderRedis.setNpwp(rfqOrderDTO.getCustomWindow().getNpwp());
    }

    return existingOrderRedis;
  }
}
