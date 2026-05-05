package com.okcir.et.order.order.state.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RfqStatus {

  /**
   * Tahap 1: Client baru saja menekan tombol initiate.
   * Belum divalidasi secara sistem.
   */
  CREATED("RFQ has been created and is awaiting validation"),

  /**
   * Tahap 2: Validasi lolos, RFQ sudah di-broadcast ke semua dealer.
   * Status ini menunggu aksi 'pick up' dari salah satu dealer.
   */
  BROADCASTED("RFQ is live and available for dealers to pick up"),

  /**
   * Tahap 3: Seorang dealer berhasil melakukan 'pick up'.
   * RFQ terkunci untuk dealer tersebut.
   */
  CLAIMED("RFQ has been claimed by a dealer"),

  /**
   * Tahap 4: Proses pengecekan kredit sedang berjalan di background.
   * Dealer belum bisa melakukan submit rate di tahap ini.
   */
  CREDIT_CHECKING("Asynchronous credit limit check is in progress"),

  /**
   * Tahap 5: Credit check berhasil.
   * Dealer sekarang memiliki wewenang untuk mengisi margin dan submit rate ke
   * client.
   */
  READY_TO_SUBMIT("Credit check passed. Dealer can now modify and submit rates"),

  /**
   * Tahap 6: Dealer sudah mengirimkan All-in rate ke client.
   * Menunggu keputusan client (Accept/Reject).
   */
  SUBMITTED("Rate has been submitted to the client for approval"),

  /**
   * Tahap 7 (Final - Success): Client menerima rate.
   * Deal (trade) resmi terbentuk.
   */
  ACCEPTED("Client accepted the rate. Trade execution successful"),

  /**
   * Tahap 7 (Transition): Client menolak rate.
   * RFQ kembali ke dealer (biasanya kembali ke status READY_TO_SUBMIT untuk
   * revisi rate).
   */
  REJECTED("Client rejected the rate. Awaiting dealer revision"),

  /**
   * Terminal State: Credit check gagal.
   * RFQ tidak bisa dilanjutkan.
   */
  CREDIT_CHECK_FAILED("RFQ aborted due to insufficient credit limit"),

  /**
   * Terminal State: RFQ melewati batas waktu (timeout).
   */
  EXPIRED("RFQ expired due to inactivity or market timing"),

  /**
   * Terminal State: Dibatalkan secara manual oleh client atau sistem.
   */
  CANCELLED("RFQ has been cancelled");

  private final String description;
}