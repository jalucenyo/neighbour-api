package com.lucenyo.neighbour.incidents.application.usecases

import com.lucenyo.neighbour.incidents.application.`in`.UpdateIncident
import com.lucenyo.neighbour.incidents.application.out.respository.IncidentRepository
import com.lucenyo.neighbour.incidents.domain.Incident

class UpdateIncidentUseCase(
  val repository: IncidentRepository
): UpdateIncident {

  override fun invoke(incident: Incident) {
    repository.update(incident)
  }

}