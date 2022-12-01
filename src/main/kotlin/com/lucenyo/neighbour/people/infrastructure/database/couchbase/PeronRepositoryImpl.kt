package com.lucenyo.neighbour.people.infrastructure.database.couchbase

import com.lucenyo.neighbour.people.application.out.PersonRepository
import com.lucenyo.neighbour.people.domain.Person
import com.lucenyo.neighbour.people.infrastructure.database.couchbase.documents.PersonDocument
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class PeronRepositoryImpl(
  val repository: SpringDataPerson,
): PersonRepository {

  override fun findByUserId(userId: String): Optional<Person> {
    return repository.findByUserId(userId).map { it.toDomain() }
  }

  override fun create(person: Person): Person {
   return repository.save(person.toDocument()).toDomain()
  }

}

interface SpringDataPerson : PagingAndSortingRepository<PersonDocument, UUID> {

  fun findByUserId(userId: String): Optional<PersonDocument>

}