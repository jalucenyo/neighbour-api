package com.lucenyo.neighbour.shared.infrastructure.database.mapper

import com.lucenyo.neighbour.shared.domain.Audit
import com.lucenyo.neighbour.shared.infrastructure.database.AuditDocument
import java.time.Instant
import java.time.OffsetDateTime
import java.time.ZoneId

fun AuditDocument.toDomain() = Audit(
  createDate = OffsetDateTime.ofInstant(Instant.ofEpochMilli(createDate!!), ZoneId.systemDefault()),
  modifiedDate = OffsetDateTime.ofInstant(Instant.ofEpochMilli(modifiedDate!!), ZoneId.systemDefault())
)
