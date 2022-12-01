package com.lucenyo.neighbour.community.infrastructure.rest

import com.lucenyo.neighbour.community.application.`in`.CreateInviteToCommunity
import com.lucenyo.neighbour.community.infrastructure.rest.mapper.toDomain
import com.lucenyo.neighbour.community.infrastructure.rest.model.InviteRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import java.net.URI
import java.util.*


@RestController
class InviteRestController(
  val createInviteToCommunity: CreateInviteToCommunity
): InviteApi {

  override fun createInvite(inviteRequest: InviteRequest): ResponseEntity<Unit> {

    return ResponseEntity.created(
      URI.create(
      createInviteToCommunity(inviteRequest.toDomain(UUID.randomUUID())).toString()
    )).build()

  }

}