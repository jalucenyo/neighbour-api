package com.lucenyo.neighbour.incidents.application.`in`

import java.util.UUID

interface DeleteIncident {

  operator fun invoke(id: UUID)

}