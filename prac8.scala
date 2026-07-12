import com.github.tototoshi.csv._
import java.io.File

object prac8 {
  def main(args: Array[String]): Unit = {

    val reader = CSVReader.open(new File("heart.csv"))

    val data = reader.all()
    reader.close()

    val header = data.head
    val rows = data.tail

    for (i <- header.indices) {

      val values = rows.flatMap { row =>
        try {
          Some(row(i).toDouble)
        } catch {
          case _: Exception => None
        }
      }

      if (values.nonEmpty) {
        val count = values.length
        val sum = values.sum
        val mean = sum / count
        val min = values.min
        val max = values.max

        println("--------------------------------")
        println("Column : " + header(i))
        println("Count  : " + count)
        println("Sum    : " + sum)
        println("Mean   : " + mean)
        println("Min    : " + min)
        println("Max    : " + max)
      }
    }
  }
}
