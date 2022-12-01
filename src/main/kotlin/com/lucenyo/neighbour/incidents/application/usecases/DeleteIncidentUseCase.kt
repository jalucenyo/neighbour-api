package com.lucenyo.neighbour.incidents.application.usecases

import com.lucenyo.neighbour.incidents.application.`in`.DeleteIncident
import com.lucenyo.neighbour.incidents.application.out.respository.IncidentRepository
import java.util.*

class DeleteIncidentUseCase(
  val repository: IncidentRepository
): DeleteIncident {

  override fun invoke(id: UUID) {
    repository.delete(id);
  }

}