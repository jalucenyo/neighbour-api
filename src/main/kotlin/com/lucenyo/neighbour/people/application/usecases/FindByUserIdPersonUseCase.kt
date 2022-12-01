package com.lucenyo.neighbour.people.application.usecases

import com.lucenyo.neighbour.people.application.`in`.FindByUserIdPerson
import com.lucenyo.neighbour.people.application.out.PersonRepository
import com.lucenyo.neighbour.people.domain.Person
import com.lucenyo.neighbour.shared.application.exceptions.NotFoundException

class FindByUserIdPersonUseCase(
  val repository: PersonRepository
): FindByUserIdPerson {

  override fun invoke(userId: String): Person {
    return repository.findByUserId(userId).orElseThrow{ NotFoundException("person.not_found") }
  }

}