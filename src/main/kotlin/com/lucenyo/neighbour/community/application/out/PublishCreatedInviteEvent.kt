package com.lucenyo.neighbour.community.application.out

import com.lucenyo.neighbour.community.domain.Invite

interface PublishCreatedInviteEvent {

  operator fun invoke(invite: Invite)

}