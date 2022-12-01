package com.lucenyo.neighbour.incidents.infrastructure.database.postgresql.couchbase.documents

import com.lucenyo.neighbour.incidents.domain.IncidentPriority
import com.lucenyo.neighbour.incidents.domain.IncidentStatus
import com.lucenyo.neighbour.shared.infrastructure.database.AuditDocument
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Version
import org.springframework.data.couchbase.core.mapping.Document
import java.util.*

@Document
data class IncidentDocument(

  @Id
  var id: UUID,
  @Version
  var version: Long? = null,
  var audit: AuditDocument = AuditDocument(),
  var deleted: Boolean = false,

  var description: String,
  var priority: IncidentPriority,
  var status: IncidentStatus,
  var categoryId: UUID,

)