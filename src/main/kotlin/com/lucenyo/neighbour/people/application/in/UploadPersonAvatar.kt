package com.lucenyo.neighbour.people.application.`in`

import java.util.*

interface UploadPersonAvatar {

  operator fun invoke(id: UUID, image: ByteArray)

}