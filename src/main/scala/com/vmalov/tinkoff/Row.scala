package com.vmalov.tinkoff

import java.time.LocalDate

/**
  * Created by victor on 29.09.16.
  */
case class Row(id: Int, time: LocalDate) {
  override def toString: String = {
    val num = id.toString.padTo(4, ' ')
    s"$num ${time.toString}"
  }
}
