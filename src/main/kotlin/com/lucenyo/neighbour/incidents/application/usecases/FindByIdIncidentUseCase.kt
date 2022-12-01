package com.lucenyo.neighbour.incidents.application.usecases

import com.lucenyo.neighbour.incidents.application.`in`.FindByIdIncident
import com.lucenyo.neighbour.incidents.application.out.respository.IncidentRepository
import com.lucenyo.neighbour.incidents.domain.Incident
import com.lucenyo.neighbour.shared.application.exceptions.NotFoundException
import java.util.*

class FindByIdIncidentUseCase(
  val repository: IncidentRepository
) : FindByIdIncident {

  override fun invoke(id: UUID): Incident {
    return repository.findById(id).orElseThrow { throw NotFoundException("incident.not_found") }
  }

}