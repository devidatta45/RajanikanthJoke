package com.rajni.controllers

import javax.inject.Singleton

import com.rajni.utils.{Constants, RestService}
import play.api.mvc.{Action, Controller}

import scala.concurrent.Future
import Constants._
import com.rajni.utils.JsonImplicits._
import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by Donald Pollock on 01/09/2017.
  */
@Singleton
class RajniController extends Controller {
  val getJoke = Action.async { request =>
    val result = ExternalService.callRajniJoke("Rajnikanth", "Sir")
    result.map { res =>
      Ok(com.rajni.views.html.joke(res.value.joke))
    }.recover({
      case ex => Ok(com.rajni.views.html.joke("No Joke Available:Server down"))
    })
  }
}

object ExternalService extends RestService {
  def callRajniJoke(firstName: String, lastName: String): Future[Joke] = {
    val url: String = URL + s"?firstName=$firstName&amp;lastName=$lastName"
    val request = client.url(url)
    for {
      result <- request.get()
      joke = extractEntity[Joke](result.body)
    } yield joke
  }
}

case class Joke(value: Result)

case class Result(id: Int, joke: String, categories: List[String])