package com.estoque.estoque_api.controller;

import com.estoque.estoque_api.dto.DashboardResponse;
import com.estoque.estoque_api.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/dashboard")
@CrossOrigin(origins = "${FRONTEND_URL:http://localhost:5173}")
public class DashboardController {

    @Autowired
    private DashboardService service;

    @GetMapping
    public DashboardResponse getDashboard() {
        LocalDateTime inicio = LocalDateTime.now().withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0);
        LocalDateTime fim = LocalDateTime.now();
        return service.getDashboard(inicio, fim);
    }

    @GetMapping("/periodo")
    public DashboardResponse getDashboardPorPeriodo(
            @RequestParam LocalDateTime inicio,
            @RequestParam LocalDateTime fim) {
        return service.getDashboard(inicio, fim);
    }
}