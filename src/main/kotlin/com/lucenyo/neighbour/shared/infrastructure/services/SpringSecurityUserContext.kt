package com.lucenyo.neighbour.shared.infrastructure.services

import com.lucenyo.neighbour.people.application.`in`.FindByUserIdPerson
import com.lucenyo.neighbour.shared.application.UserContext
import com.lucenyo.neighbour.shared.domain.UserInfo
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class SpringSecurityUserContext(
  val findByUserIdPerson: FindByUserIdPerson
): UserContext {

 override fun getUserInfo(): UserInfo{

   val auth = SecurityContextHolder.getContext().authentication
   val person = findByUserIdPerson(auth.name)

   return UserInfo(person.id, "${person.lastName}, ${person.firstName}")
 }

}