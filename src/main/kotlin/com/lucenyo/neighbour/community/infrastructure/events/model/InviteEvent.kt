package com.lucenyo.neighbour.community.infrastructure.events.model

import com.lucenyo.neighbour.community.domain.InviteStatus
import java.time.OffsetDateTime
import java.util.UUID

data class InviteEvent (

  val id: UUID,
  val communityId: UUID,
  val code: String,
  val sendTo: String,
  val status: InviteStatus,
  val expireDate: OffsetDateTime,

)