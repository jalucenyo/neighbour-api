package com.lucenyo.neighbour.community.infrastructure.database.couchbase

import com.lucenyo.neighbour.community.application.out.InviteRepository
import com.lucenyo.neighbour.community.domain.Invite
import com.lucenyo.neighbour.community.infrastructure.database.couchbase.documents.InviteDocument
import com.lucenyo.neighbour.community.infrastructure.database.couchbase.mapper.toDocument
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class InviteRepositoryImpl(
  val repository: SpringDataInviteRepository
): InviteRepository {

  override fun create(invite: Invite): UUID {
    return repository.save(invite.toDocument()).id
  }

}

interface SpringDataInviteRepository: PagingAndSortingRepository<InviteDocument, UUID>