package com.lucenyo.neighbour.incidents.application.`in`

import com.lucenyo.neighbour.incidents.domain.Incident
import java.util.UUID

interface CreateIncident {

  operator fun invoke(incident: Incident): UUID

}