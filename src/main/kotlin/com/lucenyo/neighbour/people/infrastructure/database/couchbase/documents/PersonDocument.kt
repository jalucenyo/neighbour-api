package com.lucenyo.neighbour.people.infrastructure.database.couchbase.documents

import com.lucenyo.neighbour.shared.infrastructure.database.AuditDocument
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Version
import org.springframework.data.couchbase.core.mapping.Document
import java.util.UUID

@Document
data class PersonDocument (

  @Id
  val id: UUID,
  @Version
  var version: Long? = null,
  var audit: AuditDocument = AuditDocument(),
  var deleted: Boolean = false,

  var firstName: String,
  var lastName: String,
  var userId: String,

)