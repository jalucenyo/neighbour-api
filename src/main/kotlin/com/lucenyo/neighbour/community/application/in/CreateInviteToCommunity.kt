package com.lucenyo.neighbour.community.application.`in`

import com.lucenyo.neighbour.community.domain.Invite
import java.util.UUID

interface CreateInviteToCommunity {

  operator fun invoke(invite: Invite): UUID

}