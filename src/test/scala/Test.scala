import com.vmalov.tinkoff.Row
import com.vmalov.tinkoff.Solution
import com.vmalov.tinkoff.TableGenerator
import org.scalatest.{AppendedClues, FunSpec}
/**
  * Created by victor on 30.09.16.
  */
class SolutionTest extends FunSpec with AppendedClues {

  trait Empty

  describe("A solution1 and solution2") {
    describe("when table is empty") {
      it("should be empty") {
        def check(func: Vector[Row] => List[Row]) = {
          val table = Vector.empty[Row]
          val result = func(table)
          assert(result.isEmpty)
        }

        check(Solution.solution1)
        check(Solution.solution2)
      }
    }

    describe("when table contains one element") {
      it("should be empty") {
        def check(func: Vector[Row] => List[Row]) = {
          val tableGen = new TableGenerator()
          val table = Vector(tableGen.randomRow())
          val result = func(table)
          assert(result.isEmpty)
        }

        check(Solution.solution1)
        check(Solution.solution2)
      }
    }

    describe("when table contains all correct rows") {
      it("should be empty") {
        def check(func: Vector[Row] => List[Row]) = {
          val tableGen = new TableGenerator()
          val table = tableGen.randomTable(100)
            .sortWith(_.time isBefore _.time).distinct
          val result = func(table)
          assert(result.isEmpty) withClue {
            "\nOriginal table:\n" +
            table.mkString("\n")}
        }

        check(Solution.solution1)
        check(Solution.solution2)
      }
    }

    describe("when table contains all equal by date rows") {
      it("should be all rows except first row") {
        def check(func: Vector[Row] => List[Row]) = {
          val tableGen = new TableGenerator()
          val row = tableGen.randomRow()
          val table = Vector.fill(100)(row)
          val result = func(table)
          assert(result.length + 1 == table.length)
          assert(result.forall(_ == result.head))
        }

        check(Solution.solution1)
        check(Solution.solution2)
      }
    }

    describe("when table contains exactly one invalid row") {
      it("should contain exactly that one row") {
        def check(func: Vector[Row] => List[Row]) = {
          val randGen = scala.util.Random
          val tableGen = new TableGenerator()

          // table contains all correct (and distinct) rows
          val sortTable = tableGen.randomTable(100)
            .sortWith(_.time isBefore _.time).distinct

          // between 1 and length - 1
          val randIdx = randGen.nextInt(sortTable.length - 1) + 1
          val prevRandIdx = randGen.nextInt(randIdx)
          require(prevRandIdx < randIdx)

          // update future row by this element
          val elem = sortTable(prevRandIdx)

          // now contains one invalid row
          val table = sortTable.updated(randIdx, elem)

          val result = func(table)
          assert(result.length == 1)
          assertResult(elem)(result.head)
        }

        check(Solution.solution1)
        check(Solution.solution2)
      }
    }
  }
}
