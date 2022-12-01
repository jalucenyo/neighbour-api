package com.lucenyo.neighbour.shared.domain

import java.time.OffsetDateTime

data class Audit (
  var createDate: OffsetDateTime? = null,
  var modifiedDate: OffsetDateTime? = null,
)
