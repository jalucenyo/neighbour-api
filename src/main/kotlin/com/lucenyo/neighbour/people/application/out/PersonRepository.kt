package com.lucenyo.neighbour.people.application.out

import com.lucenyo.neighbour.people.domain.Person
import java.util.Optional

interface PersonRepository {

  fun findByUserId(userId: String): Optional<Person>

  fun create(person: Person): Person

}