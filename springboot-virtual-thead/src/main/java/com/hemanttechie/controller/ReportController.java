package com.hemanttechie.controller;

import com.hemanttechie.service.PlatformReportService;
import com.hemanttechie.service.ReportService;
import com.hemanttechie.service.VirtualReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reports")
public class ReportController {

    private final ReportService reportService;

    private final PlatformReportService platformReportService;

    private final VirtualReportService virtualReportService;

    public ReportController(ReportService reportService, PlatformReportService platformReportService, VirtualReportService virtualReportService) {
        this.reportService = reportService;
        this.platformReportService = platformReportService;
        this.virtualReportService = virtualReportService;
    }

    @PostMapping("/{region}")
    public ResponseEntity<String> generateReport(@PathVariable String region) {
        reportService.generateReportForRegion(region);
        return ResponseEntity.ok( "✅ report started for region: " + region);
    }

    @PostMapping("/platform/{region}")
    public ResponseEntity<String> generateReportPlatform(@PathVariable String region) {
        platformReportService.generateReportForRegion(region);
        return ResponseEntity.ok( "✅ Platform report started for region: " + region);
    }

    @PostMapping("/virtual/{region}")
    public ResponseEntity<String> generateReportVirtual(@PathVariable String region) {
        virtualReportService.generateReportForRegion(region);
        return ResponseEntity.ok( "✅ Virtual report started for region: " + region);
    }



}
