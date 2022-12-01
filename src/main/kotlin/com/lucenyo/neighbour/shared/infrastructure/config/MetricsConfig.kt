//package com.lucenyo.neighbour.shared.infrastructure.config
//
//import io.micrometer.core.instrument.MeterRegistry
//import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer
//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.Configuration
//
//
//@Configuration
//class MetricsConfig {
//
//  @Bean
//  fun metricsCommonTags(): MeterRegistryCustomizer<MeterRegistry>? {
//    return MeterRegistryCustomizer { registry: MeterRegistry ->
//      registry.config().commonTags("application", "MYAPPNAME")
//    }
//  }
//
//}