package com.lucenyo.neighbour.community.infrastructure.database.couchbase.mapper

import com.lucenyo.neighbour.community.domain.Invite
import com.lucenyo.neighbour.community.infrastructure.database.couchbase.documents.InviteDocument

fun Invite.toDocument() = InviteDocument (
  id = id,
  communityId = communityId,
  sendTo = sendTo,
  code = code,
  status = status,
  expireDate = expireDate
)