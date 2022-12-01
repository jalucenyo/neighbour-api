package com.lucenyo.neighbour.people.application.out


interface PersonAvatarStorage {

  fun save(name: String, image: ByteArray)

}