package com.vmalov.tinkoff

import scala.annotation.tailrec

/**
  * Created by victor on 29.09.16.
  */
object Solution {
  def solution1(seq: IndexedSeq[Row]): List[Row] = {
    @tailrec
    def solutionAcc(seq: IndexedSeq[Row], prevIdx: Int,
                    nowIdx: Int, acc: List[Row]): List[Row] =
      nowIdx match {
        case x if x == seq.length => acc
        case _ => {
          val nowRow = seq(nowIdx)
          val prevRow = seq(prevIdx)

          val (prevIdxArg, nowIdxArg, accArg) = nowRow.time isAfter prevRow.time match {
            case true => (nowIdx, nowIdx + 1, acc)
            case false => (prevIdx, nowIdx + 1, nowRow :: acc)
          }

          solutionAcc(seq, prevIdxArg, nowIdxArg, accArg)
        }
      }

    seq.length match {
      case 0 => Nil
      case 1 => Nil
      case _ => solutionAcc(seq, 0, 1, Nil)
    }
  }

  def solution2(seq: Seq[Row]): List[Row] = {
    if (seq.isEmpty || seq.length == 1) Nil
    else {
      val (head, tail) = (seq.head, seq.tail)
      tail.foldLeft(Accumulator(List.empty[Row], head))((acc, row) => {
        row.time isAfter acc.lastCorrect.time match {
          case true => acc.lastCorrect = row
          case false => acc.list = row :: acc.list
        }
        acc
      }).list
    }
  }
}
