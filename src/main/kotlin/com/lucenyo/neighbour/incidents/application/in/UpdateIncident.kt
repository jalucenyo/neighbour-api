package com.lucenyo.neighbour.incidents.application.`in`

import com.lucenyo.neighbour.incidents.domain.Incident

interface UpdateIncident {

  operator fun invoke(incident: Incident)

}