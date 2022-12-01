package com.lucenyo.neighbour.community.infrastructure.rest

import com.lucenyo.neighbour.community.application.`in`.RegisterCommunity
import com.lucenyo.neighbour.community.application.`in`.DeleteCommunity
import com.lucenyo.neighbour.community.application.`in`.FindByIdCommunity
import com.lucenyo.neighbour.community.application.`in`.FindCommunity
import com.lucenyo.neighbour.community.application.`in`.UpdateCommunity
import com.lucenyo.neighbour.community.infrastructure.rest.mapper.toDomain
import com.lucenyo.neighbour.community.infrastructure.rest.mapper.toResponse
import com.lucenyo.neighbour.community.infrastructure.rest.model.CommunityRequest
import com.lucenyo.neighbour.community.infrastructure.rest.model.CommunityResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import java.net.URI
import java.util.*


@RestController
class CommunityRestController(
  val registerCommunity: RegisterCommunity,
  val findByIdCommunity: FindByIdCommunity,
  val findCommunity: FindCommunity,
  val updateCommunity: UpdateCommunity,
  val deleteCommunity: DeleteCommunity,
): CommunityApi {

  override fun create(communityRequest: CommunityRequest): ResponseEntity<Unit> {
    return ResponseEntity.created(URI.create(
      registerCommunity(communityRequest.toDomain(UUID.randomUUID())).toString()
    )).build()
  }

  override fun delete(id: UUID): ResponseEntity<Unit> {
    deleteCommunity(id)
    return ResponseEntity.noContent().build()
  }

  override fun find(): ResponseEntity<List<CommunityResponse>> {
    return ResponseEntity.ok(findCommunity().map { it.toResponse() })
  }

  override fun findById(id: UUID): ResponseEntity<CommunityResponse> {
    return ResponseEntity.ok(findByIdCommunity(id).toResponse())
  }

  override fun update(id: UUID, communityRequest: CommunityRequest): ResponseEntity<Unit> {
    updateCommunity(communityRequest.toDomain(id))
    return ResponseEntity.noContent().build()
  }

}