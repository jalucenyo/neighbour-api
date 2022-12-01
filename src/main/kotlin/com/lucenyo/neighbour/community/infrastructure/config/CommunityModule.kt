package com.lucenyo.neighbour.community.infrastructure.config

import com.lucenyo.neighbour.community.application.out.CommunityRepository
import com.lucenyo.neighbour.community.application.out.InviteRepository
import com.lucenyo.neighbour.community.application.out.PublishCreatedInviteEvent
import com.lucenyo.neighbour.community.application.usecases.RegisterCommunityUseCase
import com.lucenyo.neighbour.community.application.usecases.DeleteCommunityUseCase
import com.lucenyo.neighbour.community.application.usecases.FindByIdCommunityUseCase
import com.lucenyo.neighbour.community.application.usecases.FindCommunityUseCase
import com.lucenyo.neighbour.community.application.usecases.CreateInviteToCommunityUseCase
import com.lucenyo.neighbour.community.application.usecases.UpdateCommunityUseCase
import com.lucenyo.neighbour.shared.application.UserContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class CommunityModule(
  val userContext: UserContext,
  val communityRepository: CommunityRepository,
  val inviteRepository: InviteRepository,
  val publishCreatedInviteEvent: PublishCreatedInviteEvent,
) {

  @Bean
  fun createCommunityProvider() = RegisterCommunityUseCase(communityRepository, userContext)

  @Bean
  fun updateCommunityProvider() = UpdateCommunityUseCase(communityRepository)

  @Bean
  fun deleteCommunityProvider() = DeleteCommunityUseCase(communityRepository, userContext)

  @Bean
  fun findCommunityProvider() = FindCommunityUseCase(communityRepository, userContext)

  @Bean
  fun findByIdCommunityProvider() = FindByIdCommunityUseCase(communityRepository, userContext)

  @Bean
  fun inviteToCommunityProvider() = CreateInviteToCommunityUseCase(
    inviteRepository, communityRepository, publishCreatedInviteEvent, userContext)

}