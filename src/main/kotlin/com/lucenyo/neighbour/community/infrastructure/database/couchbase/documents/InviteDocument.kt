package com.lucenyo.neighbour.community.infrastructure.database.couchbase.documents

import com.lucenyo.neighbour.community.domain.InviteStatus
import com.lucenyo.neighbour.shared.infrastructure.database.AuditDocument
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Version
import org.springframework.data.couchbase.core.mapping.Document
import java.time.OffsetDateTime
import java.util.UUID

@Document
data class InviteDocument (

  @Id
  val id: UUID,
  @Version
  var version: Long? = null,
  var audit: AuditDocument = AuditDocument(),
  var deleted: Boolean = false,

  var communityId: UUID,
  var code: String?,
  var sendTo: String?,
  var status: InviteStatus,
  var expireDate: OffsetDateTime,

  )