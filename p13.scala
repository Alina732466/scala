import breeze.linalg._
import breeze.plot._
import com.github.tototoshi.csv._
import java.io.File

object p13 {
  def main(args: Array[String]): Unit = {
    val reader = CSVReader.open(new File("adult.csv"))
    val data = reader.allWithHeaders()
    reader.close()
    val male = data.filter(_("sex") == "Male").take(50)
    val female = data.filter(_("sex") == "Female").take(50)
    def extractXY(rows: List[Map[String, String]]) = {
      val x = DenseVector(rows.map(_("age").toDouble).toArray)
      val y = DenseVector(rows.map(_("hours.per.week").toDouble).toArray)
      (x, y)
    }
    val (xMale, yMale) = extractXY(male)
    val (xFemale, yFemale) = extractXY(female)
    val fig = Figure("Adult Scatter Plot")
    val plt = fig.subplot(0)
    plt.title = "Age vs Hours Per Week"
    plt.xlabel = "Age"
    plt.ylabel = "Hours Per Week"
    plt += plot(xMale, yMale, '.', name = "Male", colorcode = "b")
    plt += plot(xFemale, yFemale, '.', name = "Female", colorcode = "r")
    fig.refresh()
  }
}