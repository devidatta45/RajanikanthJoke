package com.rajni.utils

import org.json4s.JsonAST.{JField, JString}
import org.json4s.{DefaultFormats, Extraction, Formats}
import org.json4s.jackson.JsonMethods._

/**
  * Created by Donald Pollock on 01/09/2017.
  */
object JsonImplicits {

  def toJson(value: Any): String = {
    if (value.isInstanceOf[String]) value.asInstanceOf[String] else convertToJValue(value)
  }

  implicit val format = DefaultFormats

  def convertToJValue(x: Any) = compact(Extraction.decompose(x))

  def extractEntity[A](json: String)(implicit formats: Formats, mf: Manifest[A]): A = parse(json).extract[A]

  def updateObject[A](obj: A, key: String, value: String)(implicit formats: Formats, mf: Manifest[A]): A = {
    val json = toJson(obj)
    val result = parse(json).transformField { case JField(x, v) if x == key => JField(key, JString(value)) }
    extractEntity[A](toJson(result))
  }
}
