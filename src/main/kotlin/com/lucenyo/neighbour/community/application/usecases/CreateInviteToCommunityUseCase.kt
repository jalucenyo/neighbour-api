package com.lucenyo.neighbour.community.application.usecases

import com.lucenyo.neighbour.community.application.`in`.CreateInviteToCommunity
import com.lucenyo.neighbour.community.application.out.CommunityRepository
import com.lucenyo.neighbour.community.application.out.InviteRepository
import com.lucenyo.neighbour.community.application.out.PublishCreatedInviteEvent
import com.lucenyo.neighbour.community.domain.Community
import com.lucenyo.neighbour.community.domain.Invite
import com.lucenyo.neighbour.community.domain.NeighborRole
import com.lucenyo.neighbour.shared.application.UserContext
import com.lucenyo.neighbour.shared.application.exceptions.NotFoundException
import com.lucenyo.neighbour.shared.application.exceptions.PermissionDeniedException
import com.lucenyo.neighbour.shared.domain.UserInfo
import org.apache.commons.lang3.RandomStringUtils
import java.util.UUID


class CreateInviteToCommunityUseCase(
  val inviteRepository: InviteRepository,
  val communityRepository: CommunityRepository,
  val publishCreatedInviteEvent: PublishCreatedInviteEvent,
  val userContext: UserContext,
): CreateInviteToCommunity {

  override fun invoke(invite: Invite): UUID {

    val userInfo = userContext.getUserInfo()

    val community = communityRepository.findByIdAndPersonId(invite.communityId, userInfo.id)
      .orElseThrow { throw NotFoundException("community.not_found") }

    checkUserIsPresident(community, userInfo)

    val inviteWithCode = invite.copy(
      code = RandomStringUtils.randomNumeric(6)
    )

    val inviteId = inviteRepository.create(inviteWithCode)

    publishCreatedInviteEvent(inviteWithCode)

    return inviteId
  }

  private fun checkUserIsPresident(community: Community, userInfo: UserInfo) {
    if (community.neighbors.stream().noneMatch { it.personId == userInfo.id && NeighborRole.PRESIDENT == it.role }) {
      throw PermissionDeniedException("community.is_not_president")
    }
  }

}