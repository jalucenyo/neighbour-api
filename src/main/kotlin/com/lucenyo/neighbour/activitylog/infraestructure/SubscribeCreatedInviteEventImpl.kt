package com.lucenyo.neighbour.activitylog.infraestructure

import com.lucenyo.neighbour.community.infrastructure.events.model.InviteEvent
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service


@Service(value = "activitylogSubscribeCreatedInviteEventImpl")
class SubscribeCreatedInviteEventImpl(
  //val saveActivityLog: SaveActivityLog
){

  @Async
  @EventListener
  fun handleCreatedInviteEvent(invite: InviteEvent) {

    // TODO: Send save activity log

  }

}
