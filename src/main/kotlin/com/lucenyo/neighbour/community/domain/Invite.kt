package com.lucenyo.neighbour.community.domain

import java.time.OffsetDateTime
import java.util.UUID

data class Invite(
  val id: UUID,
  val communityId: UUID,
  val code: String? = null,
  val sendTo: String,
  val status: InviteStatus,
  val expireDate: OffsetDateTime,
)
