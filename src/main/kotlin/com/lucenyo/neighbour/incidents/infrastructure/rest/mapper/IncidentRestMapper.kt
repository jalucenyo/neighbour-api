package com.lucenyo.neighbour.incidents.infrastructure.rest

import com.lucenyo.neighbour.incidents.domain.Incident
import com.lucenyo.neighbour.incidents.domain.IncidentPriority
import com.lucenyo.neighbour.incidents.domain.IncidentStatus
import com.lucenyo.neighbour.incidents.infrastructure.rest.model.IncidentRequest
import com.lucenyo.neighbour.incidents.infrastructure.rest.model.IncidentResponse
import java.util.UUID

fun Incident.toResponse() = IncidentResponse(
  id = id,
  description = description,
  priority = priority.toResponse(),
  status = status.toResponse(),
  categoryId = categoryId,
)

fun IncidentRequest.toDomain(id: UUID) = Incident (
  id = id,
  description = description!!,
  priority = priority!!.toDomain(),
  status = status!!.toDomain(),
  categoryId = categoryId!!,
)

fun IncidentRequest.Priority.toDomain() = IncidentPriority.valueOf(value)

fun IncidentRequest.Status.toDomain() = IncidentStatus.valueOf(value)

fun IncidentPriority.toResponse() = IncidentResponse.Priority.valueOf(name)

fun IncidentStatus.toResponse() = IncidentResponse.Status.valueOf(name)
