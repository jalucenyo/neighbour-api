package com.lucenyo.neighbour.community.application.usecases

import com.lucenyo.neighbour.community.application.`in`.RegisterCommunity
import com.lucenyo.neighbour.community.application.out.CommunityRepository
import com.lucenyo.neighbour.community.domain.Community
import com.lucenyo.neighbour.community.domain.PersonRole
import com.lucenyo.neighbour.community.domain.NeighborRole
import com.lucenyo.neighbour.shared.application.UserContext
import java.util.UUID

class RegisterCommunityUseCase(
  val repository: CommunityRepository,
  val userContext: UserContext,
): RegisterCommunity {

  override fun invoke(community: Community): UUID {

    val userInfo = userContext.getUserInfo()

    val communityCreate = community.copy(
      neighbors = setOf(PersonRole(userInfo.id, NeighborRole.PRESIDENT))
    )

    return repository.create(communityCreate)
  }

}