package com.lucenyo.neighbour.incidents.application.usecases

import com.lucenyo.neighbour.incidents.application.`in`.CreateIncident
import com.lucenyo.neighbour.incidents.application.out.respository.IncidentRepository
import com.lucenyo.neighbour.incidents.domain.Incident
import java.util.UUID

class CreateIncidentUseCase(
  val repository: IncidentRepository,
) : CreateIncident {

  override fun invoke(incident: Incident): UUID {
    return repository.create(incident);
  }

}