package com.lucenyo.neighbour.community.infrastructure.database.couchbase

import com.lucenyo.neighbour.community.application.out.CommunityRepository
import com.lucenyo.neighbour.community.domain.Community
import com.lucenyo.neighbour.community.infrastructure.database.couchbase.documents.CommunityDocument
import com.lucenyo.neighbour.community.infrastructure.database.couchbase.mapper.toDocument
import com.lucenyo.neighbour.community.infrastructure.database.couchbase.mapper.toDomain
import com.lucenyo.neighbour.shared.application.exceptions.NotFoundException
import org.springframework.data.couchbase.repository.Query
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Service
import java.util.*


@Service
class CommunityRepositoryImpl(
  val repository: SpringDataCommunityRepository
): CommunityRepository {

  override fun create(community: Community): UUID {
    return repository.save(community.toDocument()).id
  }

  override fun finByPersonId(personId: UUID): List<Community> {
    return repository.findByPersonId(personId).map{ it.toDomain() }
  }

  override fun findByIdAndPersonId(communityId: UUID, personId: UUID): Optional<Community>{
    return repository.findByIdAndPersonId(communityId, personId).map { it.toDomain() }
  }

  override fun findById(id: UUID): Optional<Community> {
    return repository.findById(id).map { it.toDomain() }
  }

  override fun find(): List<Community> {
    return repository.findAll().map { it.toDomain() }
  }

  override fun update(community: Community) {
    val communityUpdate = repository.findById(community.id)
      .orElseThrow{ throw NotFoundException("community.not_found") }
    communityUpdate.name = community.name
    repository.save(communityUpdate)
  }

  override fun delete(id: UUID) {
    val communityDelete = repository.findById(id)
      .orElseThrow { throw NotFoundException("community.not_found") }
    communityDelete.deleted = true
    repository.save(communityDelete)
  }

}

interface SpringDataCommunityRepository: PagingAndSortingRepository<CommunityDocument, UUID> {

    @Query("#{#n1ql.selectEntity} WHERE" +
        " ANY ne IN neighbors SATISFIES ne.personId = \$personId END" +
        " AND #{#n1ql.filter}")
    fun findByPersonId(@Param("personId") personId: UUID): List<CommunityDocument>

    @Query("#{#n1ql.selectEntity} WHERE" +
        " ANY ne IN neighbors SATISFIES ne.personId = \$personId END" +
        " AND meta(#{#n1ql.bucket}).id = \$communityId" +
        " AND #{#n1ql.filter}")
    fun findByIdAndPersonId(
      @Param("communityId") communityId: UUID,
      @Param("personId") personId: UUID): Optional<CommunityDocument>

}