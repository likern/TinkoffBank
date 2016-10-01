package com.vmalov.tinkoff

import java.time.temporal.ChronoUnit
import java.time.{LocalDate, Period, YearMonth}

/**
  * Created by victor on 28.09.16.
  */
class TableGenerator(startDate: LocalDate = LocalDate.of(1, 1, 1)) {
  private val randGen = scala.util.Random
  private val endDate = LocalDate.now()
  require(startDate.isBefore(endDate), "start date should be less than current date")

  def randomTable(rows: Int = 1): Vector[Row] = {
    require(rows >= 0, "number of rows in table can't be less than 0")
    (1 to rows).map(x => Row(x, randomDate)).toVector
  }

  def randomRow(maxInt: Int = Int.MaxValue): Row = {
    Row(randGen.nextInt(maxInt), randomDate)
  }

  def randomDate: LocalDate = {
    def ration = randGen.nextDouble

    val days = (ration * ChronoUnit.DAYS.between(startDate, endDate)).toLong
    startDate.plusDays(days)
  }
}
