package com.lucenyo.neighbour.incidents.application.usecases

import com.lucenyo.neighbour.incidents.application.`in`.FindIncident
import com.lucenyo.neighbour.incidents.application.out.respository.IncidentRepository
import com.lucenyo.neighbour.incidents.domain.Incident

class FindIncidentUseCase(
  val repository: IncidentRepository
): FindIncident {

  override fun invoke(): List<Incident> {
    return repository.find()
  }
}