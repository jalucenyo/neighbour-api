//package com.lucenyo.neighbour.shared.infraestructure.config
//
//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.Configuration
//import org.springframework.data.auditing.DateTimeProvider
//import org.springframework.data.jpa.repository.config.EnableJpaAuditing
//import java.time.ZonedDateTime
//import java.util.*
//
//@Configuration
//@EnableJpaAuditing(dateTimeProviderRef = "auditingDateTimeProvider")
//class AuditPersistConfig {
//
//  @Bean
//  fun auditingDateTimeProvider() = DateTimeProvider { Optional.of(ZonedDateTime.now()) }
//
//}