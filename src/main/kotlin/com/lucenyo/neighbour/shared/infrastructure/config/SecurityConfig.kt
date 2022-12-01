package com.lucenyo.neighbour.shared.infrastructure.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain


@Profile("S!test")
@Configuration
@EnableWebSecurity
class SecurityConfig {

  @Bean
  fun web(http: HttpSecurity): SecurityFilterChain {

    http.authorizeHttpRequests()
      .antMatchers("/actuator/**").permitAll()
      .anyRequest().authenticated()
      .and().oauth2ResourceServer().jwt()

    return http.build()
  }

}
