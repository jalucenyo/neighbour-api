package com.lucenyo.neighbour.shared.application

interface ResizeImageService {

  operator fun invoke(image: ByteArray, width: Int, height: Int): ByteArray

}
