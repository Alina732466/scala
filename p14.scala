import breeze.linalg._
import breeze.plot._
import com.github.tototoshi.csv._
import java.io.File

object p14 {
  def main(args: Array[String]): Unit = {
    val reader = CSVReader.open(new File("adult.csv"))
    val data = reader.allWithHeaders()
    reader.close()
    val ages = DenseVector(data.map(_("age").toDouble).toArray)
    val fig = Figure("Histogram of Age")
    val binSizes = List(5, 10, 20)
    for ((bins, idx) <- binSizes.zipWithIndex) {
      val plt = fig.subplot(1, binSizes.length, idx)
      plt += hist(ages, bins)
      plt.title = s"Histogram with $bins bins"
      plt.xlabel = "Age"
      plt.ylabel = "Frequency"
    }
    fig.refresh()
  }
}