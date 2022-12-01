package com.lucenyo.neighbour.shared.infrastructure.database

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate


data class AuditDocument (

  @CreatedDate
  var createDate: Long? = null,

  @LastModifiedDate
  var modifiedDate: Long? = null,

)

