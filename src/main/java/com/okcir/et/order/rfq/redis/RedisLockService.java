package com.okcir.et.order.rfq.redis;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

@Slf4j
@Service
@RequiredArgsConstructor
public class RedisLockService {

    private final RedissonClient redissonClient;

    /**
     * Mengeksekusi logika bisnis di dalam distributed lock.
     * * @param lockKey Nama unik untuk lock (misal: lock:rfq:123)
     * @param waitTime Waktu maksimal menunggu antrian lock (dalam detik)
     * @param leaseTime Waktu maksimal lock dipegang sebelum otomatis lepas (dalam detik)
     * @param task Logika bisnis yang akan dijalankan
     * @return Result dari task
     */
    public <T> T executeWithLock(String lockKey, long waitTime, long leaseTime, Supplier<T> task) {
        RLock lock = redissonClient.getLock(lockKey);
        boolean isLockAcquired = false;

        try {
            // Mencoba mendapatkan lock
            isLockAcquired = lock.tryLock(waitTime, leaseTime, TimeUnit.SECONDS);
            
            if (isLockAcquired) {
                log.info("Lock acquired for key: {}", lockKey);
                return task.get();
            } else {
                log.warn("Could not acquire lock for key: {} - Already held by another process", lockKey);
                throw new RuntimeException("Gagal mendapatkan akses. Deal sedang diproses oleh dealer lain.");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Proses terinterupsi saat menunggu lock", e);
        } finally {
            // Selalu lepas lock di blok finally, hanya jika thread ini yang memegangnya
            if (isLockAcquired && lock.isHeldByCurrentThread()) {
                lock.unlock();
                log.info("Lock released for key: {}", lockKey);
            }
        }
    }
}