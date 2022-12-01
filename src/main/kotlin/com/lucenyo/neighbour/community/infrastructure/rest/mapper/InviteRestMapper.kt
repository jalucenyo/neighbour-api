package com.lucenyo.neighbour.community.infrastructure.rest.mapper

import com.lucenyo.neighbour.community.domain.InviteStatus
import com.lucenyo.neighbour.community.domain.Invite
import com.lucenyo.neighbour.community.infrastructure.rest.model.InviteRequest
import org.apache.commons.lang3.RandomStringUtils
import java.time.OffsetDateTime
import java.util.UUID

fun InviteRequest.toDomain(id: UUID) = Invite(
  id = id,
  communityId = communityId,
  sendTo = sendTo,
  status = InviteStatus.SEND,
  code = RandomStringUtils.randomNumeric(6),
  expireDate = OffsetDateTime.now().plusHours(72)
)
