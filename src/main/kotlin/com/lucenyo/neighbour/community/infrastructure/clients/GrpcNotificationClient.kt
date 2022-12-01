package com.lucenyo.neighbour.community.infrastructure.clients

import com.lucenyo.neighbour.community.application.out.NotificationClient
import com.lucenyo.notifications.email.infrastructure.grpc.EmailRequest
import com.lucenyo.notifications.email.infrastructure.grpc.EmailServiceGrpcKt
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import net.devh.boot.grpc.client.inject.GrpcClient
import org.springframework.stereotype.Service

@Service
class GrpcNotificationClient : NotificationClient {

  @GrpcClient("notification-email-client") lateinit var emailClientGrp: EmailServiceGrpcKt.EmailServiceCoroutineStub

  override fun sendEmail(sendTo: String, template: String, params: Map<String, String>) {

    GlobalScope.launch {

      val response = emailClientGrp.send(
        EmailRequest.newBuilder()
          .setEmailTo(sendTo)
          .setEmailContent("Hola Mundo desde otro micro")
          .build()
      )

      response.code

    }

  }


}