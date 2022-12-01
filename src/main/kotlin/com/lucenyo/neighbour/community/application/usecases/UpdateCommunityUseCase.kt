package com.lucenyo.neighbour.community.application.usecases

import com.lucenyo.neighbour.community.application.`in`.UpdateCommunity
import com.lucenyo.neighbour.community.application.out.CommunityRepository
import com.lucenyo.neighbour.community.domain.Community

class UpdateCommunityUseCase(
  private val repository: CommunityRepository
): UpdateCommunity {

  override fun invoke(community: Community) {
    repository.update(community)
  }

}