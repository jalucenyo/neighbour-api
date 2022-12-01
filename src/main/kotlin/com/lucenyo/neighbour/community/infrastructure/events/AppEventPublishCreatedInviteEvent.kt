package com.lucenyo.neighbour.community.infrastructure.events

import com.lucenyo.neighbour.community.application.out.PublishCreatedInviteEvent
import com.lucenyo.neighbour.community.domain.Invite
import com.lucenyo.neighbour.community.infrastructure.events.mapper.toEvent
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service


@Service
class AppEventPublishCreatedInviteEvent(
  val publisher: ApplicationEventPublisher
):  PublishCreatedInviteEvent{

  override fun invoke(invite: Invite) {
    publisher.publishEvent(invite.toEvent())
  }

}
