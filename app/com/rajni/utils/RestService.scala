package com.rajni.utils

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import play.api.libs.ws.ahc.AhcWSClient

/**
  * Created by Donald Pollock on 01/09/2017.
  */
trait RestService {
  implicit val system = ActorSystem()
  implicit val materializer = ActorMaterializer()

  val client = AhcWSClient()
}
object Constants{
  val URL="http://api.icndb.com/jokes/random"
}