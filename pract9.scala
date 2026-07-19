  import com.github.tototoshi.csv._
  import java.io.File
  import scala.util.Try

  object Pract9 {

    def median(values: Seq[Double]): Double = {
      val sorted = values.sorted
      val n = sorted.length
      if (n == 0) 0.0
      else if (n % 2 == 0)
        (sorted(n / 2 - 1) + sorted(n / 2)) / 2
      else
        sorted(n / 2)
    }

    def mode(values: Seq[Double]): Double = {
      if (values.isEmpty) 0.0
      else values.groupBy(identity).maxBy(_._2.size)._1
    }

    def main(args: Array[String]): Unit = {

      val inputFile = new File("melb_data.csv")

      val reader = CSVReader.open(inputFile)
      val allRows = reader.allWithHeaders()
      reader.close()

      val numericColumns = Seq("Car", "BuildingArea", "YearBuilt")

      val stats = numericColumns.map { col =>

        val values = allRows.map(_.getOrElse(col, "").trim)

        val numbers = values.flatMap(v => Try(v.toDouble).toOption)

        val mean =
          if (numbers.nonEmpty) numbers.sum / numbers.size
          else 0.0

        val med = median(numbers)

        val mod = mode(numbers)

        val missing = values.count(v => Try(v.toDouble).isFailure)

        println("\nColumn : " + col)
        println(f"Mean   : $mean%.2f")
        println(f"Median : $med%.2f")
        println(f"Mode   : $mod%.2f")
        println("Missing Values : " + missing)

        (col, mean)
      }.toMap

      val cleanedRows = allRows.map { row =>
        numericColumns.foldLeft(row) { (r, col) =>
          val value = r.getOrElse(col, "").trim

          val newValue =
            Try(value.toDouble).toOption match {
              case Some(_) => value
              case None => f"${stats(col)}%.2f"
            }

          r.updated(col, newValue)
        }
      }

      val writer = CSVWriter.open(new File("melb_data_cleaned.csv"))

      val headers = cleanedRows.head.keys.toSeq

      writer.writeRow(headers)

      cleanedRows.foreach(row => writer.writeRow(headers.map(row)))

      writer.close()

      println("\nMissing values replaced successfully.")
      println("Cleaned file saved as melb_data_cleaned.csv")
    }
  }


