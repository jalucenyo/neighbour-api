package com.lucenyo.neighbour.people.application.usecases

import com.lucenyo.neighbour.people.application.`in`.RegisterPerson
import com.lucenyo.neighbour.people.application.out.PersonRepository
import com.lucenyo.neighbour.people.domain.Person
import com.lucenyo.neighbour.shared.application.exceptions.DuplicatedException

class RegisterPersonUseCase(
  val repository: PersonRepository
): RegisterPerson {

  override fun invoke(person: Person): Person {
    repository.findByUserId(person.userId).ifPresent { throw DuplicatedException("person.duplicated") }
    return repository.create(person)
  }

}