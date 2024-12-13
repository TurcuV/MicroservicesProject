package com.microsiervices.inventory_service.service;

import com.microsiervices.inventory_service.dao.InventoryDao;
import com.microsiervices.inventory_service.dto.InventoryResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryService {

    private final InventoryDao inventoryDao;

    public List<InventoryResponse> isInStock(List<String> skuCode) {
        log.info("Checking Inventory");
        return inventoryDao.findBySkuCodeIn(skuCode).stream()
                .map(inventory ->
                        InventoryResponse.builder()
                                .skuCode(inventory.getSkuCode())
                                .isInStock(inventory.getQuantity() > 0)
                                .build()
                ).toList();
    }

    @KafkaListener(topics = "order-created-topic", autoStartup = "${listen.auto.start:true}", concurrency = "${listen.concurrency:3}")
    public void testKafka(String msg) {
        log.info(msg);
    }

}
