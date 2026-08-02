import scala.io.Source

object p2 {

  def main(args: Array[String]): Unit = {

    val stream = getClass.getResourceAsStream("/SBI Dataset.csv")

    if (stream == null) {
      println("File not found!")
      return
    }

    val file = Source.fromInputStream(stream)

    val data = file.getLines().drop(1).flatMap { line =>
      val cols = line.split(",")

      if (cols.length >= 5) {
        cols(4).trim.toDoubleOption
      } else None
    }.toList

    file.close()

    val window = 5

    // Simple Moving Average
    val sma = data.sliding(window).map(_.sum / window).toList

    // Weighted Moving Average
    val weights = (1 to window).toList
    val weightSum = weights.sum.toDouble

    val wma = data.sliding(window).map { values =>
      values.zip(weights).map {
        case (v, w) => v * w
      }.sum / weightSum
    }.toList

    // Exponential Moving Average
    val alpha = 2.0 / (window + 1)

    var ema = List[Double]()
    var previous = data.head

    ema = previous :: ema

    for (price <- data.tail) {
      val current = alpha * price + (1 - alpha) * previous
      ema = ema :+ current
      previous = current
    }

    println("========== MOVING AVERAGE ==========")
    println(s"Total Records : ${data.length}")

    println("\nFirst 10 Close Prices")
    data.take(10).foreach(println)

    println("\nFirst 10 SMA Values")
    sma.take(10).foreach(x => println(f"$x%.2f"))

    println("\nFirst 10 WMA Values")
    wma.take(10).foreach(x => println(f"$x%.2f"))

    println("\nFirst 10 EMA Values")
    ema.take(10).foreach(x => println(f"$x%.2f"))
  }
}
