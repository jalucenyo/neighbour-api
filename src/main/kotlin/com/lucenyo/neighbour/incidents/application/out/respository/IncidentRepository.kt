package com.lucenyo.neighbour.incidents.application.out.respository

import com.lucenyo.neighbour.incidents.domain.Incident
import java.util.*

interface IncidentRepository {

  fun create(incident: Incident): UUID

  fun findById(id: UUID): Optional<Incident>

  fun delete(id: UUID)

  fun update(incident: Incident)

  fun find(): List<Incident>

}