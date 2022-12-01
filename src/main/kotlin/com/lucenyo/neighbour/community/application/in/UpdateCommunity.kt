package com.lucenyo.neighbour.community.application.`in`

import com.lucenyo.neighbour.community.domain.Community
import java.util.UUID

interface UpdateCommunity {

  operator fun invoke(community: Community)

}