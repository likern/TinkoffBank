package com.vmalov.tinkoff

import java.time.LocalDate

/**
  * Created by victor on 29.09.16.
  */
object Main extends App {
  if (args.length != 1) {
    val program = new Exception().getStackTrace.head.getFileName
    println(s"$program <number of rows in table>")
    sys.exit(1)
  }


  val tableGen = new TableGenerator()
  val table = tableGen.randomTable(args.head.toInt)
  println("Original table: ")
  table.foreach(println)
  println("Invalid rows: ")
  val rows = Solution.solution1(table)
  rows.foreach(println)
}
