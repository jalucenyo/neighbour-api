package com.lucenyo.neighbour.incidents.infrastructure.database.postgresql.couchbase

import com.lucenyo.neighbour.incidents.application.out.respository.IncidentRepository
import com.lucenyo.neighbour.incidents.domain.Incident
import com.lucenyo.neighbour.incidents.infrastructure.database.postgresql.couchbase.documents.IncidentDocument
import com.lucenyo.neighbour.incidents.infrastructure.database.couchbase.toDocument
import com.lucenyo.neighbour.incidents.infrastructure.database.couchbase.toDomain
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class IncidentRepositoryImpl(
  val repository: SpringDataIncidentRepository
): IncidentRepository{

  override fun create(incident: Incident): UUID {
    return repository.save(incident.toDocument()).id
  }

  override fun findById(id: UUID): Optional<Incident> {
    return repository.findById(id).map { it.toDomain() }
  }

  override fun delete(id: UUID) {
    repository.deleteById(id)
  }

  override fun update(incident: Incident) {
    val incidentUpdate = repository.findById(incident.id).get()
    incidentUpdate.description = incident.description
    incidentUpdate.categoryId = incident.categoryId
    incidentUpdate.priority = incident.priority
    incidentUpdate.status = incident.status
    repository.save(incidentUpdate)
  }

  override fun find(): List<Incident> {
    return repository.findAll().map { it.toDomain() }
  }

}

interface SpringDataIncidentRepository: PagingAndSortingRepository<IncidentDocument, UUID> {

  //TODO: Soft delete
//  @Query()
//  override fun findAll(): Iterable<IncidentDocument>

}
