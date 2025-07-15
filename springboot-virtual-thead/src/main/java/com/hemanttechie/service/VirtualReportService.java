package com.hemanttechie.service;

import com.hemanttechie.entity.Customer;
import com.hemanttechie.repository.CustomerRepository;
import com.hemanttechie.util.CsvReportUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.Executor;

@Slf4j
@Service
public class VirtualReportService {

    private final CustomerRepository repository;

    private final Executor virtualThreadExecutor ;

    public VirtualReportService(CustomerRepository repository, Executor virtualThreadExecutor) {
        this.repository = repository;
        this.virtualThreadExecutor = virtualThreadExecutor;
    }


    public void generateReportForRegion(String region) {

        virtualThreadExecutor.execute(()->{
            log.info("Virtual generating report for region: {} | {}", region, Thread.currentThread());

            List<Customer> customers = repository.findByRegion(region);//1
            try {
                CsvReportUtil.writeCustomersToCsv("virtual_" + region, customers);//2
            } catch (Exception e) {
                System.out.println("❌ Virtual Error writing report for region: " + region);
            }
        });

    }
}

