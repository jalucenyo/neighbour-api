package com.lucenyo.neighbour.community.application.out

import com.lucenyo.neighbour.community.domain.Community
import java.util.*

interface CommunityRepository {

  fun create(community: Community): UUID

  fun findById(id: UUID): Optional<Community>

  fun find(): List<Community>

  fun update(community: Community)

  fun delete(id: UUID)

  fun finByPersonId(personId: UUID): List<Community>

  fun findByIdAndPersonId(communityId: UUID, personId: UUID): Optional<Community>

}