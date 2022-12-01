package com.lucenyo.neighbour.incidents.infrastructure.database.couchbase

import com.lucenyo.neighbour.incidents.domain.Incident
import com.lucenyo.neighbour.incidents.infrastructure.database.postgresql.couchbase.documents.IncidentDocument
import com.lucenyo.neighbour.shared.infrastructure.database.mapper.toDomain


fun Incident.toDocument() = IncidentDocument(
  id = id,
  description = description,
  priority = priority,
  status = status,
  categoryId = categoryId,
  deleted = false,
)

fun IncidentDocument.toDomain() = Incident(
  id = id,
  description = description,
  priority = priority,
  status = status,
  categoryId = categoryId,
  audit = audit.toDomain()
)
