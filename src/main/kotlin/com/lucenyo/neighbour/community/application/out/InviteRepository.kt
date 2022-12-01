package com.lucenyo.neighbour.community.application.out

import com.lucenyo.neighbour.community.domain.Invite
import java.util.*

interface InviteRepository {

  fun create(invite: Invite): UUID

}