package com.lucenyo.neighbour.community.application.usecases

import com.lucenyo.neighbour.community.application.`in`.FindCommunity
import com.lucenyo.neighbour.community.application.out.CommunityRepository
import com.lucenyo.neighbour.community.domain.Community
import com.lucenyo.neighbour.shared.application.UserContext


class FindCommunityUseCase(
  val repository: CommunityRepository,
  val userContext: UserContext
): FindCommunity {

  override fun invoke(): List<Community> {
    val userIfo = userContext.getUserInfo()
    return repository.finByPersonId(userIfo.id)
  }

}