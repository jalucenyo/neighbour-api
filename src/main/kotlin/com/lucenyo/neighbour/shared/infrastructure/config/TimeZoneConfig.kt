package com.lucenyo.neighbour.shared.infrastructure.config

import org.springframework.context.annotation.Configuration
import java.util.*
import javax.annotation.PostConstruct

@Configuration
class TimeZoneConfig {

  @PostConstruct
  fun timeZoneConfig() = TimeZone.setDefault(TimeZone.getTimeZone("UTC"))

}
