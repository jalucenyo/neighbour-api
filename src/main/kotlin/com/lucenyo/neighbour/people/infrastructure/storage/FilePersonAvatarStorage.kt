package com.lucenyo.neighbour.people.infrastructure.storage

import com.lucenyo.neighbour.people.application.out.PersonAvatarStorage
import org.springframework.stereotype.Service
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

@Service
class FilePersonAvatarStorage(

): PersonAvatarStorage {

  override fun save(name: String, image: ByteArray) {
    val destinationFile: Path = Paths.get("storage", name)
    Files.write(destinationFile, image)
  }

}