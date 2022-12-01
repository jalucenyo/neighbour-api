package com.lucenyo.neighbour.community.infrastructure.database.couchbase.documents

import com.lucenyo.neighbour.community.domain.NeighborRole
import com.lucenyo.neighbour.shared.infrastructure.database.AuditDocument
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Version
import org.springframework.data.couchbase.core.mapping.Document
import java.util.UUID

@Document
data class CommunityDocument (

  @Id
  val id: UUID,

  @Version
  var version: Long? = null,
  var audit: AuditDocument = AuditDocument(),
  var deleted: Boolean = false,

  var name: String,
  var address: AddressDocument? = null,
  var neighbors: Set<PersonRoleDocument> = setOf()

)

data class PersonRoleDocument(
  var personId: UUID,
  var role: NeighborRole
)

data class AddressDocument(

  var addressLines: List<String>,
  var locality: String,
  var sublocality: String,
  var regionCode: String,
  var postalCode: String,

)