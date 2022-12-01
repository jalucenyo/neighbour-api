package com.lucenyo.neighbour.incidents.infrastructure.rest

import com.lucenyo.neighbour.incidents.application.`in`.CreateIncident
import com.lucenyo.neighbour.incidents.application.`in`.DeleteIncident
import com.lucenyo.neighbour.incidents.application.`in`.FindIncident
import com.lucenyo.neighbour.incidents.application.`in`.FindByIdIncident
import com.lucenyo.neighbour.incidents.application.`in`.UpdateIncident
import com.lucenyo.neighbour.incidents.infrastructure.rest.model.IncidentRequest
import com.lucenyo.neighbour.incidents.infrastructure.rest.model.IncidentResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import java.net.URI
import java.util.*
import kotlin.collections.List


@RestController
class IncidentRestController(
  val createIncident: CreateIncident,
  val findByIdIncident: FindByIdIncident,
  val deleteIncident: DeleteIncident,
  val updateIncident: UpdateIncident,
  val findIncident: FindIncident,
): IncidentsApi {

  override fun create(incidentRequest: IncidentRequest): ResponseEntity<Unit> {
    return ResponseEntity.created(
      URI.create(
        createIncident(incidentRequest.toDomain(UUID.randomUUID())).toString()
      )
    ).build()
  }

  override fun delete(id: UUID): ResponseEntity<Unit> {
    deleteIncident(id)
    return ResponseEntity.noContent().build()
  }

  override fun find(): ResponseEntity<List<IncidentResponse>> {
    return ResponseEntity.ok(findIncident().map { it.toResponse() })
  }

  override fun findById(id: UUID): ResponseEntity<IncidentResponse> {
    return ResponseEntity.ok(findByIdIncident(id).toResponse())
  }

  override fun update(id: UUID, incidentRequest: IncidentRequest): ResponseEntity<Unit> {
    updateIncident(incidentRequest.toDomain(id));
    return ResponseEntity.noContent().build()
  }

}
