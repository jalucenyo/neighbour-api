package com.lucenyo.neighbour.community.application.usecases

import com.lucenyo.neighbour.community.application.`in`.DeleteCommunity
import com.lucenyo.neighbour.community.application.out.CommunityRepository
import com.lucenyo.neighbour.community.domain.NeighborRole
import com.lucenyo.neighbour.shared.application.UserContext
import com.lucenyo.neighbour.shared.application.exceptions.NotFoundException
import com.lucenyo.neighbour.shared.application.exceptions.PermissionDeniedException
import java.util.*

class DeleteCommunityUseCase(
  val repository: CommunityRepository,
  val userContext: UserContext,
): DeleteCommunity{

  override fun invoke(id: UUID) {

    val userInfo = userContext.getUserInfo()

    val community =  repository.findById(id)
      .orElseThrow { NotFoundException("community.not_found") }

    if (community.neighbors.any { NeighborRole.PRESIDENT == it.role && it.personId == userInfo.id }){
      repository.delete(community.id)
    } else {
      throw PermissionDeniedException("community.is_not_president")
    }

  }

}
