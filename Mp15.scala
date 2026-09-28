import scala.io.Source
object Mp15 {
  def main(args: Array[String]): Unit = {
    val file = "C:\\Users\\itlab\\Downloads\\archive (16)\\Fish.csv"
    val lines = Source.fromFile(file).getLines().toList
    val delimiter = ","
    val header = lines.head.split(delimiter).map(_.trim)
    val lengthIndex = header.indexOf("Length1")
    println("Length1 Column Index: " + lengthIndex)
    val lengthData = lines.tail.map { line =>
      val values = line.split(delimiter).map(_.trim)
      values(lengthIndex).toDouble
    }
    println()
    println("Fish Market Dataset - Polynomial Features")
    println("------------------------------------------")
    println("Length1\tX^2\tX^3")
    lengthData.take(10).foreach { x =>
      val x2 = x * x
      val x3 = x * x * x
      println(f"$x%.2f\t$x2%.2f\t$x3%.2f")
    }
  }
}