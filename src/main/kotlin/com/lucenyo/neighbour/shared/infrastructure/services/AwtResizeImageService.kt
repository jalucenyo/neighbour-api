package com.lucenyo.neighbour.shared.infrastructure.services

import com.lucenyo.neighbour.shared.application.ResizeImageService
import org.springframework.stereotype.Service
import java.awt.Dimension
import java.awt.image.BufferedImage
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import javax.imageio.ImageIO

@Service
class AwtResizeImageService : ResizeImageService {

  override fun invoke(image: ByteArray, width: Int, height: Int): ByteArray {

    val resizedDimension = Dimension(width, height)
    val originalImage = ImageIO.read(ByteArrayInputStream(image))

    val resizedImage = BufferedImage(resizedDimension.width, resizedDimension.height, BufferedImage.TYPE_INT_ARGB);
    val graphic = resizedImage.createGraphics()

    val scaledDimension = getScaleDimension(
      origin = Dimension(originalImage.width, originalImage.height),
      scaled = resizedDimension
    )

    val centerDimension = getCenterDimension(scaledDimension, resizedDimension)

    graphic.drawImage(originalImage,
      centerDimension.width, centerDimension.height,
      scaledDimension.width, scaledDimension.height,
      null)

    graphic.dispose()

    val outputStream = ByteArrayOutputStream()
    ImageIO.write(resizedImage, "png", outputStream);

    return outputStream.toByteArray()

  }

  private fun getCenterDimension(origin: Dimension, scaled: Dimension): Dimension {
    return Dimension((scaled.width - origin.width) / 2, (scaled.height - origin.height) / 2 )
  }

  private fun getScaleDimension (origin: Dimension, scaled: Dimension): Dimension{

    var new_width = origin.width
    var new_height = origin.height

    if (origin.width > scaled.width) {
      new_width = scaled.width
      new_height = new_width * origin.height / origin.width
    }

    if (new_height > scaled.height) {
      new_height = scaled.height
      new_width = new_height * origin.width / origin.height
    }

    return Dimension(new_width, new_height)
  }

}