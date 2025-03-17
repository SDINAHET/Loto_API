// package com.fdjloto.api.controller;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.web.bind.annotation.*;

// import io.micrometer.core.instrument.MeterRegistry;
// import io.micrometer.core.instrument.Timer;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;
// import java.util.concurrent.TimeUnit;


// import java.util.HashMap;
// import java.util.Map;

// @RestController
// @RequestMapping("/api/performance")
// public class PerformanceController {

//     private final MeterRegistry meterRegistry;

//     public PerformanceController(MeterRegistry meterRegistry) {
//         this.meterRegistry = meterRegistry;
//     }

//     @GetMapping
//     public Map<String, Object> getPerformanceMetrics() {
//         Map<String, Object> metrics = new HashMap<>();

//         // Récupérer le nombre total de requêtes HTTP
//         metrics.put("http_requests_total", meterRegistry.get("http.server.requests").counter().count());

//         // Récupérer le temps moyen des requêtes HTTP
//         Timer timer = meterRegistry.get("http.server.requests").timer();
//         // metrics.put("http_request_avg_time", timer.mean(Timer.TimeUnit.MILLISECONDS));
// 		// metrics.put("http_request_avg_time", timer.mean(Timer::count));
// 		metrics.put("http_request_avg_time", timer.totalTime(TimeUnit.MILLISECONDS) / timer.count());



//         return metrics;
//     }
// }
