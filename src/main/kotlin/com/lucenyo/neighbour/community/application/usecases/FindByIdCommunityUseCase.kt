package com.lucenyo.neighbour.community.application.usecases

import com.lucenyo.neighbour.community.application.`in`.FindByIdCommunity
import com.lucenyo.neighbour.community.application.out.CommunityRepository
import com.lucenyo.neighbour.community.domain.Community
import com.lucenyo.neighbour.shared.application.UserContext
import com.lucenyo.neighbour.shared.application.exceptions.NotFoundException
import com.lucenyo.neighbour.shared.application.exceptions.PermissionDeniedException
import java.util.UUID

class FindByIdCommunityUseCase(
  val repository: CommunityRepository,
  val userContext: UserContext
): FindByIdCommunity {

  override fun invoke(id: UUID): Community {

    val userIfo = userContext.getUserInfo()

    val community = repository.findByIdAndPersonId(id, userIfo.id)
      .orElseThrow { throw NotFoundException("community.not_found") }

    return community

  }

}