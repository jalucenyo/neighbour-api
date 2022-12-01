package com.lucenyo.neighbour.incidents.infrastructure.config

import com.lucenyo.neighbour.incidents.application.`in`.CreateIncident
import com.lucenyo.neighbour.incidents.application.`in`.DeleteIncident
import com.lucenyo.neighbour.incidents.application.`in`.FindIncident
import com.lucenyo.neighbour.incidents.application.`in`.FindByIdIncident
import com.lucenyo.neighbour.incidents.application.`in`.UpdateIncident
import com.lucenyo.neighbour.incidents.application.out.respository.IncidentRepository
import com.lucenyo.neighbour.incidents.application.usecases.CreateIncidentUseCase
import com.lucenyo.neighbour.incidents.application.usecases.DeleteIncidentUseCase
import com.lucenyo.neighbour.incidents.application.usecases.FindIncidentUseCase
import com.lucenyo.neighbour.incidents.application.usecases.FindByIdIncidentUseCase
import com.lucenyo.neighbour.incidents.application.usecases.UpdateIncidentUseCase
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class IncidentModule(
  private val incidentRepository: IncidentRepository,
) {

  @Bean
  fun createIncidentProvider(): CreateIncident = CreateIncidentUseCase(incidentRepository)

  @Bean
  fun findByIdIncidentProvider(): FindByIdIncident = FindByIdIncidentUseCase(incidentRepository)

  @Bean
  fun deleteIncidentProvider(): DeleteIncident = DeleteIncidentUseCase(incidentRepository)

  @Bean
  fun updateIncidentProvider(): UpdateIncident = UpdateIncidentUseCase(incidentRepository)

  @Bean
  fun findIncidentProvider(): FindIncident = FindIncidentUseCase(incidentRepository)

}