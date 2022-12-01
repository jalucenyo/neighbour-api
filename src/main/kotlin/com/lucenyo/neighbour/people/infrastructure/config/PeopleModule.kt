package com.lucenyo.neighbour.people.infrastructure.config

import com.lucenyo.neighbour.people.application.out.PersonAvatarStorage
import com.lucenyo.neighbour.people.application.out.PersonRepository
import com.lucenyo.neighbour.people.application.usecases.FindByUserIdPersonUseCase
import com.lucenyo.neighbour.people.application.usecases.RegisterPersonUseCase
import com.lucenyo.neighbour.people.application.usecases.UploadPersonAvatarUseCase
import com.lucenyo.neighbour.shared.infrastructure.services.AwtResizeImageService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class PeopleModule(
  val personRepository: PersonRepository,
  val personAvatarStorage: PersonAvatarStorage
) {

  @Bean
  fun uploadPeopleAvatarProvider() = UploadPersonAvatarUseCase(
    resizeImageService = AwtResizeImageService(),
    personAvatarStorage = personAvatarStorage)

  @Bean
  fun createPersonProvider() = RegisterPersonUseCase(personRepository)

  @Bean
  fun findLoggedPersonProvider() = FindByUserIdPersonUseCase(personRepository)

}