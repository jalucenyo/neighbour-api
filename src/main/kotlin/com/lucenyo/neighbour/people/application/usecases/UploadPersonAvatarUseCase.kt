package com.lucenyo.neighbour.people.application.usecases

import com.lucenyo.neighbour.people.application.`in`.UploadPersonAvatar
import com.lucenyo.neighbour.people.application.out.PersonAvatarStorage
import com.lucenyo.neighbour.shared.application.ResizeImageService
import java.util.*


class UploadPersonAvatarUseCase(
  val resizeImageService: ResizeImageService,
  val personAvatarStorage: PersonAvatarStorage,
) : UploadPersonAvatar{

  override fun invoke(id: UUID, image: ByteArray) {

    val resizedImage = resizeImageService(image, 128, 128)
    personAvatarStorage.save(name = "$id.png", image = resizedImage)

  }

}