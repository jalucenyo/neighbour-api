package com.lucenyo.neighbour.incidents.infrastructure.rest

import com.lucenyo.neighbour.DatabaseBaseTest
import com.lucenyo.neighbour.incidents.infrastructure.rest.model.IncidentRequest
import org.hamcrest.Matchers.equalTo
import org.junit.Test
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.header
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.util.*


class IncidentRestControllerTest: DatabaseBaseTest() {

  val endpointUrl = "/v1/incidents"

  @Test
  fun `list incidents`(){

    // when
    val (idCreated) = createIncident()

    // given
    var result = mockMvc.perform(
      get(endpointUrl)
        .contentType(MediaType.APPLICATION_JSON)
    )

    //then
    result
      .andDo(print())
      .andExpect(status().isOk)

  }

  @Test
  fun `should update incident when exist incident`(){

    // when
    val (idCreated) = createIncident()

    // given
    val expected = IncidentRequest(
      description = "Test Update incident",
      categoryId = UUID.randomUUID(),
      priority = IncidentRequest.Priority.LOW,
      status = IncidentRequest.Status.PENDING
    )

    val result = mockMvc.perform(
      put("$endpointUrl/${idCreated}")
        .contentType(MediaType.APPLICATION_JSON)
        .content(toJsonString(expected)))

    // then
    result
      .andDo(print())
      .andExpect(status().isNoContent)

  }

  @Test
  fun `should delete incident when exist`(){

    // when
    val (idCreated) = createIncident()

    // given
    var result = mockMvc.perform(
      delete("$endpointUrl/${idCreated}")
        .contentType(MediaType.APPLICATION_JSON)
    )

    // then
    result
      .andDo(print())
      .andExpect(status().isNoContent)

  }

  @Test
  fun `should create incident when request is correct`(){

    // when
    val expected = IncidentRequest(
      description = "Test Incident",
      categoryId = UUID.randomUUID(),
      priority = IncidentRequest.Priority.LOW,
      status = IncidentRequest.Status.PENDING
    )

    // given
    val result = mockMvc.perform(
      post(endpointUrl)
        .contentType(MediaType.APPLICATION_JSON)
        .content(toJsonString(expected))
    )

    // then
    result
      .andDo(print())
      .andExpect(status().isCreated)
      .andExpect(header().exists("Location"))

  }

  @Test
  fun `should get incident when exist`(){

    // when
    val (idCreated, expected) = createIncident()

    // given
    val result = mockMvc.perform(
      get("$endpointUrl/${idCreated}")
        .contentType(MediaType.APPLICATION_JSON)
    )

    // then
    result
      .andDo(print())
      .andExpect(status().isOk)
      .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(jsonPath("$.id").exists())
      .andExpect(jsonPath("$.description", equalTo(expected.description)))
      .andExpect(jsonPath("$.categoryId", equalTo(expected.categoryId.toString())))
      .andExpect(jsonPath("$.priority", equalTo(expected.priority!!.name)))
      .andExpect(jsonPath("$.status", equalTo(expected.status!!.name)))

  }


  private fun createIncident(): Pair<String, IncidentRequest> {
    val incidentCreate = IncidentRequest(
      description = "Test Incident",
      categoryId = UUID.randomUUID(),
      priority = IncidentRequest.Priority.LOW,
      status = IncidentRequest.Status.PENDING
    )

    val created = mockMvc.perform(
      post(endpointUrl)
        .contentType(MediaType.APPLICATION_JSON)
        .content(toJsonString(incidentCreate)))
      .andReturn()

    val id = created.response.getHeaderValue("Location").toString()

    return Pair(id, incidentCreate)
  }

}