package com.lucenyo.neighbour.community.infrastructure.events

import com.lucenyo.neighbour.community.infrastructure.events.model.InviteEvent
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service


@Service(value = "communitySubscribeCreatedInviteEventImpl")
class SubscribeCreatedInviteEventImpl(

){

  @Async
  @EventListener
  fun handleCreatedInviteEvent(invite: InviteEvent) {
    // TODO: Send email
  }

}
