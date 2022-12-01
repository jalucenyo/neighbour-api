package com.lucenyo.neighbour.incidents.application.`in`

import com.lucenyo.neighbour.incidents.domain.Incident

interface FindIncident {

  operator fun invoke(): List<Incident>

}