package com.lucenyo.neighbour.people.infrastructure.rest

import com.lucenyo.neighbour.DatabaseBaseTest
import com.lucenyo.neighbour.community.infrastructure.rest.model.CommunityRequest
import com.lucenyo.neighbour.people.infrastructure.rest.model.PersonRequest
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.oauth2Login
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.oidcLogin
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

class PeopleRestControllerTest: DatabaseBaseTest()  {

  val endpointUrl = "/v1/people"

  @Test
  fun `should register person when request is correct then location header id person`(){

    // when
    val expected = PersonRequest(
      firstName = "testUserFirstName",
      lastName = "testUserLastName"
    )

    // given
    val result = mockMvc.perform(
      post(endpointUrl).with(csrf()).with(oauth2Login())
        .contentType(MediaType.APPLICATION_JSON)
        .content(toJsonString(expected))
    )

    // then
    result
      .andDo(print())
      .andExpect(MockMvcResultMatchers.status().isCreated)
      .andExpect(MockMvcResultMatchers.header().exists("Location"))

  }

//
//  @Test
//  fun `should get community when community exist then return community`(){
//
//    // when
//    val (id, expected) = createCommunity()
//
//    // given
//
//    val result = mockMvc.perform(
//      get("$endpointUrl/$id").with(oidcLogin())
//        .contentType(MediaType.APPLICATION_JSON))
//
//    // then
//    result
//      .andDo(print())
//      .andExpect(MockMvcResultMatchers.status().isOk)
//      .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
//      .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
//      .andExpect(MockMvcResultMatchers.jsonPath("$.name", equalTo(expected.name)))
//
//  }
//
//  @Test
//  fun `should update community when community exist then return status no content`(){
//
//    // when
//    val (id) = createCommunity()
//
//    val expected = CommunityRequest("Updated test")
//
//    // given
//    val resultUpdated = mockMvc.perform(
//      put("$endpointUrl/$id")
//        .contentType(MediaType.APPLICATION_JSON)
//        .content(toJsonString(expected)))
//
//    val result = mockMvc.perform(
//      get("$endpointUrl/$id")
//        .contentType(MediaType.APPLICATION_JSON))
//
//    // then
//    resultUpdated
//      .andExpect(MockMvcResultMatchers.status().isNoContent)
//
//    result
//      .andDo(print())
//      .andExpect(MockMvcResultMatchers.status().isOk)
//      .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
//      .andExpect(MockMvcResultMatchers.jsonPath("$.id", equalTo(id)))
//      .andExpect(MockMvcResultMatchers.jsonPath("$.name", equalTo(expected.name)))
//
//  }
//
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
//        .contentType(MediaType.APPLICATION_JSON))
//
//    val result = mockMvc.perform(get("$endpointUrl/$id")
//      .contentType(MediaType.APPLICATION_JSON))
//
//    // then
//    resultDeleted
//      .andExpect(MockMvcResultMatchers.status().isNoContent)
//
//    result
//      .andExpect(MockMvcResultMatchers.status().isOk)
//      .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
//      .andExpect(MockMvcResultMatchers.jsonPath("$.id", equalTo(id)))
//      .andExpect(MockMvcResultMatchers.jsonPath("$.deleted", equalTo(true)))
//
//  }
//
//
//  private fun createCommunity(): Pair<String, CommunityRequest> {
//    val incidentCreate = CommunityRequest(
//      name = "Test Community"
//    )
//
//    val created = mockMvc.perform(
//      post(endpointUrl)
//        .contentType(MediaType.APPLICATION_JSON)
//        .content(toJsonString(incidentCreate)))
//      .andReturn()
//
//    val id = created.response.getHeaderValue("Location").toString()
//
//    return Pair(id, incidentCreate)
//  }

}