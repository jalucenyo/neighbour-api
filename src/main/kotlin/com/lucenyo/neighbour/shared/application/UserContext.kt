package com.lucenyo.neighbour.shared.application

import com.lucenyo.neighbour.shared.domain.UserInfo


interface UserContext {

  fun getUserInfo(): UserInfo

}