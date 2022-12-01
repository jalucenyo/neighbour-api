package com.lucenyo.neighbour.community.application.out

interface NotificationClient {

  fun sendEmail(sendTo: String, template: String, params: Map<String, String>)

}