package com.lucenyo.neighbour.community.infrastructure.rest

import com.lucenyo.neighbour.DatabaseBaseTest
import com.lucenyo.neighbour.community.application.out.CommunityRepository
import com.lucenyo.neighbour.community.domain.Community
import com.lucenyo.neighbour.community.domain.PersonRole
import com.lucenyo.neighbour.community.domain.NeighborRole
import com.lucenyo.neighbour.community.infrastructure.rest.model.CommunityRequest
import com.lucenyo.neighbour.community.infrastructure.rest.model.InviteRequest
import org.hamcrest.Matchers.containsInAnyOrder
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.oauth2Login
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import java.util.*

class CommunityRestControllerTest: DatabaseBaseTest()  {

  val endpointUrl = "/v1/community"

  @Autowired
  lateinit var communityRepository: CommunityRepository

  @Test
  fun `should register community when request is correct then location header id community`(){

    // when
    val (_, userOauth) = registerUser()

    val expected = CommunityRequest(
      name = "Test Community",
    )

    // given
    val registerResult = mockMvc.perform(
      post(endpointUrl)
        .with(csrf()).with(oauth2Login().oauth2User(userOauth))
        .contentType(MediaType.APPLICATION_JSON)
        .content(toJsonString(expected))
    )

    // then
    registerResult.andDo(print())
      .andExpect(status().isCreated)
      .andExpect(header().exists("Location"))

  }

  @Test
  fun `should register community then auth user in neighbors with role PRESIDENT`(){

    // when
    val (userId, userOauth) = registerUser()

    val expected = CommunityRequest(
      name = "Test Community",
    )

    // given
    val communityId = mockMvc.perform(
      post(endpointUrl)
        .with(csrf()).with(oauth2Login().oauth2User(userOauth))
        .contentType(MediaType.APPLICATION_JSON)
        .content(toJsonString(expected))
    ).andReturn().response.getHeaderValue("Location").toString()

    // then
    val communitySaved = communityRepository.findById(UUID.fromString(communityId)).get()
    assertTrue(communitySaved.neighbors.stream()
      .anyMatch{ it.personId.toString() == userId && NeighborRole.PRESIDENT == it.role})

  }


  @Test
  fun `should get community when community exist then return community`(){

    // when
    val (userId, userOauth) = registerUser()

    val expected = Community(
      id = UUID.randomUUID(),
      name = "Test community",
      neighbors = setOf(PersonRole(UUID.fromString(userId), NeighborRole.PRESIDENT))
    )
    val communityId = communityRepository.create(expected)

    // given
    val result = mockMvc.perform(
      get("$endpointUrl/$communityId")
        .with(csrf()).with(oauth2Login().oauth2User(userOauth))
        .contentType(MediaType.APPLICATION_JSON))

    // then
    result
      .andDo(print())
      .andExpect(status().isOk)
      .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(jsonPath("$.id", equalTo(communityId.toString())))
      .andExpect(jsonPath("$.name", equalTo(expected.name)))
      .andExpect(jsonPath("$.neighbors[*].personId", containsInAnyOrder(expected.neighbors.first().personId.toString())))
      .andExpect(jsonPath("$.neighbors[*].role", containsInAnyOrder(expected.neighbors.first().role.name)))

  }

  @Test
  fun `should return error when user auth not in get community`(){

    // when
    val (_, userNotCommunityAuth) = registerUser()

    val expected = Community(
      id = UUID.randomUUID(),
      name = "Test community",
      neighbors = setOf(
        PersonRole(UUID.randomUUID(), NeighborRole.PRESIDENT),
        PersonRole(UUID.randomUUID(), NeighborRole.NEIGHBOUR)
      )
    )
    val communityId = communityRepository.create(expected)

    // given
    val result = mockMvc.perform(
      get("$endpointUrl/$communityId")
        .with(csrf()).with(oauth2Login().oauth2User(userNotCommunityAuth))
        .contentType(MediaType.APPLICATION_JSON))

    // then
    result
      .andDo(print())
      .andExpect(status().isNotFound)
  }

//  @Test
//  fun `should update community when community exist then return status no content`(){
//
//    // when
//    val (userId, user) = registerUser2()
//
//    val expected = Community(
//      id = UUID.randomUUID(),
//      name = "Test community",
//      neighbors = setOf(PersonRole(UUID.fromString(userId), Role.PRESIDENT))
//    )
//    val communityId = communityRepository.create(expected)
//
//    val c =  CommunityRequest(name = "")
//
//
//    // given
//    val resultUpdated = mockMvc.perform(
//      put("$endpointUrl/$communityId")
//        .with(csrf()).with(oauth2Login().oauth2User(user))
//        .contentType(MediaType.APPLICATION_JSON)
//        .content(toJsonString(expected)))
//
//    val communitySaved = communityRepository.findById(UUID.fromString(id)).get()
//    Assertions.assertTrue(communitySaved.neighbors.stream().anyMatch{ it.personId.toString() == userId })
//
//
////    val result = mockMvc.perform(
////      get("$endpointUrl/$id")
////        .with(oauth2Login())
////        .contentType(MediaType.APPLICATION_JSON))
//
//    // then
//    resultUpdated
//      .andExpect(status().isNoContent)
//
//
//
//    result
//      .andDo(print())
//      .andExpect(status().isOk)
//      .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//      .andExpect(jsonPath("$.id", equalTo(communitySaved.id)))
//      .andExpect(jsonPath("$.name", equalTo(expected.name)))
//
//  }
//

//  @Test
//  fun `should delete community when community exist then mark community deleted`(){
//
//    // when
//    val (id, expected) = createCommunity()
//
//    // given
//    val resultDeleted = mockMvc.perform(
//      delete("$endpointUrl/$id")
//        .with(csrf()).with(oauth2Login().oauth2User(getTestUser()))
//        .contentType(MediaType.APPLICATION_JSON))
//
//    val result = mockMvc.perform(get("$endpointUrl/$id")
//      .with(oauth2Login().oauth2User(getTestUser()))
//      .contentType(MediaType.APPLICATION_JSON))
//
//    // then
//    resultDeleted
//      .andExpect(status().isNoContent)
//
//    result
//      .andExpect(status().isOk)
//      .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//      .andExpect(jsonPath("$.id", equalTo(id)))
//      .andExpect(jsonPath("$.deleted", equalTo(true)))
//
//  }

//  @Test
//  fun `should return permission denied when delete community user not president`(){
//
//    // when
//    val (id) = createCommunity()
//
//    // given
//    val resultDeleted = mockMvc.perform(
//      delete("$endpointUrl/$id")
//        .with(csrf()).with(oauth2Login().oauth2User(getAnotherUser()))
//        .contentType(MediaType.APPLICATION_JSON))
//
//    // then
//    resultDeleted
//      .andExpect(status().isUnauthorized)
//
//  }


//  private fun createCommunity(): Pair<String, CommunityRequest> {
//    val incidentCreate = CommunityRequest(
//      name = "Test Community"
//    )
//
//    val created = mockMvc.perform(
//      post(endpointUrl).with(csrf()).with(oauth2Login().oauth2User(getTestUser()))
//        .contentType(MediaType.APPLICATION_JSON)
//        .content(toJsonString(incidentCreate)))
//      .andReturn()
//
//    val id = created.response.getHeaderValue("Location").toString()
//
//    return Pair(id, incidentCreate)
//  }

}