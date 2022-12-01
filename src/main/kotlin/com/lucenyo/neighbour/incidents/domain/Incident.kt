package com.lucenyo.neighbour.incidents.domain

import com.lucenyo.neighbour.shared.domain.Audit
import java.util.UUID

data class Incident(
  val id: UUID,
  val description: String,
  val priority: IncidentPriority,
  val status: IncidentStatus,
  val categoryId: UUID,
  val audit: Audit = Audit(),
){
  init {
    require(description.isNotBlank()){ "domain.incident.description_not_blank" }
  }
}
