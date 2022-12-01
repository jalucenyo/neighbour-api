package com.lucenyo.neighbour.community.infrastructure.rest

import com.lucenyo.neighbour.DatabaseBaseTest
import com.lucenyo.neighbour.community.application.out.CommunityRepository
import com.lucenyo.neighbour.community.domain.Community
import com.lucenyo.neighbour.community.domain.PersonRole
import com.lucenyo.neighbour.community.domain.NeighborRole
import com.lucenyo.neighbour.community.infrastructure.rest.model.InviteRequest
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.oauth2Login
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import java.util.*


class InviteRestControllerTest: DatabaseBaseTest()  {

  val endpointUrl = "/v1/invitations"

  @Autowired
  lateinit var communityRepository: CommunityRepository


  @Test
  fun `should create invite when request invite is correct then return header location id invite`(){

    // when
    val (userId, userOauth) = registerUser()

    val community = Community(
      id = UUID.randomUUID(),
      name = "Test community",
      neighbors = setOf(PersonRole(UUID.fromString(userId), NeighborRole.PRESIDENT))
    )
    val communityId = communityRepository.create(community)

    val request = InviteRequest(
      communityId = communityId,
      sendTo = "testInvite@local.local",
    )

    // given
    val registerResult = mockMvc.perform(
      post(endpointUrl)
        .with(csrf()).with(oauth2Login().oauth2User(userOauth))
        .contentType(MediaType.APPLICATION_JSON)
        .content(toJsonString(request))
    )

    // then
    registerResult
      .andDo(print())
      .andExpect(status().isCreated)
      .andExpect(header().exists("Location"))

  }

}