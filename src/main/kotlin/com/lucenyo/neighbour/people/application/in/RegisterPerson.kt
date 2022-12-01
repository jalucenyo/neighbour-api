package com.lucenyo.neighbour.people.application.`in`

import com.lucenyo.neighbour.people.domain.Person

interface RegisterPerson {

  operator fun invoke(person: Person): Person

}