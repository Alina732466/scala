import com.github.tototoshi.csv._
import java.io.File

object p10 {

  def main(args: Array[String]): Unit = {

    val reader = CSVReader.open(new File("Mall_Customers.csv"))
    val data = reader.allWithHeaders()
    reader.close()

    val threshold = 70

    val filteredRows = data.filter { row =>
      row.get("Annual Income (k$)").exists(value =>
        value.toIntOption.exists(_ > threshold)
      )
    }

    println(s"\nTotal Rows with Annual Income > $threshold: ${filteredRows.length}\n")

    filteredRows.foreach { row =>
      println(row.values.mkString(", "))
    }
  }
}