package com.lucenyo.neighbour.community.infrastructure.rest.mapper

import com.lucenyo.neighbour.community.domain.Community
import com.lucenyo.neighbour.community.domain.PersonRole
import com.lucenyo.neighbour.community.infrastructure.rest.model.CommunityRequest
import com.lucenyo.neighbour.community.infrastructure.rest.model.CommunityResponse
import com.lucenyo.neighbour.community.infrastructure.rest.model.PersonRoleResponse
import java.util.UUID


fun CommunityRequest.toDomain(id: UUID) = Community(
  id = id,
  name = name,
)

fun Community.toResponse() = CommunityResponse(
  id = id,
  name = name,
  deleted = deleted,
  neighbors = neighbors.map { it.toResponse() }.toList()
)

fun PersonRole.toResponse() = PersonRoleResponse(
  personId = personId,
  role = role.name
)
