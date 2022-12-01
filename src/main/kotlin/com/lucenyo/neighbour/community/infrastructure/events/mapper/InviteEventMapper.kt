package com.lucenyo.neighbour.community.infrastructure.events.mapper

import com.lucenyo.neighbour.community.domain.Invite
import com.lucenyo.neighbour.community.infrastructure.events.model.InviteEvent


fun Invite.toEvent() = InviteEvent(
  id = id,
  code = code!!,
  sendTo = sendTo,
  status = status,
  communityId = communityId,
  expireDate = expireDate
)