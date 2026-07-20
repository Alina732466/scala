import breeze.linalg._
import breeze.plot._
import com.github.tototoshi.csv._
import java.io.File

object p15 {
  def main(args: Array[String]): Unit = {
    val reader = CSVReader.open(new File("adult.csv"))
    val data = reader.allWithHeaders()
    reader.close()
    val values = data.take(150).flatMap { row =>
      try {
        Some(row("age").toDouble)
      } catch {
        case _: Throwable => None
      }
    }
    val x = DenseVector((0 until values.length).map(_.toDouble).toArray)
    val y = DenseVector(values.toArray)
    val fig = Figure("Age Trend")
    val plt = fig.subplot(0)
    plt += plot(x, y, name = "Age", colorcode = "b")
    plt.xlabel = "Record Number"
    plt.ylabel = "Age"
    plt.title = "Age Over First 150 Records"
    fig.refresh()
  }
}