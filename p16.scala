import breeze.linalg._
import breeze.plot._
import com.github.tototoshi.csv._
import java.io.File

object p16 {
  def main(args: Array[String]): Unit = {
    val reader = CSVReader.open(new File("adult.csv"))
    val data = reader.allWithHeaders()
    reader.close()
    val ageData = data.take(150).flatMap { row =>
      try {
        Some(row("age").toDouble)
      } catch {
        case _: Throwable => None
      }
    }
    val x = DenseVector((0 until ageData.length).map(_.toDouble).toArray)
    val y = DenseVector(ageData.toArray)
    val fig = Figure("Adult Dataset - Line + Scatter Plot")
    val plt = fig.subplot(0)
    plt += plot(x, y, name = "Age Line", colorcode = "b")
    plt += plot(x, y, '.', name = "Age Points", colorcode = "r")
    plt.xlabel = "Record Number"
    plt.ylabel = "Age"
    plt.title = "Age Vs Record Number"
    fig.refresh()
  }
}
