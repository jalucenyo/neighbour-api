package com.lucenyo.neighbour.people.infrastructure.rest

import com.lucenyo.neighbour.people.application.`in`.RegisterPerson
import com.lucenyo.neighbour.people.application.`in`.UploadPersonAvatar
import com.lucenyo.neighbour.people.infrastructure.rest.mapper.toDomain
import com.lucenyo.neighbour.people.infrastructure.rest.model.PersonRequest
import org.springframework.core.io.Resource
import org.springframework.http.ResponseEntity
import org.springframework.scheduling.annotation.Async
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.RestController
import java.net.URI
import java.util.*


@RestController
class PeopleRestController(
  val registerPerson: RegisterPerson,
  val uploadPersonAvatar: UploadPersonAvatar
) : PeopleApi {

  override fun create(personRequest: PersonRequest): ResponseEntity<Unit> {

    val userId = SecurityContextHolder.getContext().authentication.name

    return ResponseEntity.created(URI.create(
      registerPerson(personRequest.toDomain(UUID.randomUUID(), userId)).id.toString()
    )).build()

  }

  @Async
  override fun uploadAvatar(id: UUID, imageFile: Resource?): ResponseEntity<Unit> {
    uploadPersonAvatar(id, imageFile!!.inputStream.readAllBytes())
    return ResponseEntity.noContent().build()
  }

}