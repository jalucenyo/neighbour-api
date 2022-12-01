package com.lucenyo.neighbour.people.application.`in`

import com.lucenyo.neighbour.people.domain.Person

interface FindByUserIdPerson {

  operator fun invoke(userId: String): Person

}