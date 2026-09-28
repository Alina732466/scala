object P14 {
  def main(args: Array[String]): Unit = {

    val sales = (1 to 10).map(day => (day, 1000 + day * 50))

    println("Daily Sales Data:")
    sales.foreach { case (day, amount) =>
      println(s"Day $day: Rs. $amount")
    }

    val totalSales = sales.map(_._2).sum
    val averageSales = totalSales / sales.length
    val maxSales = sales.map(_._2).max
    val minSales = sales.map(_._2).min

    println("\nTotal Sales: Rs. " + totalSales)
    println("Average Sales: Rs. " + averageSales)
    println("Maximum Sales: Rs. " + maxSales)
    println("Minimum Sales: Rs. " + minSales)

    println("\nSales Trend:")
    sales.foreach { case (day, amount) =>
      println(s"Day $day -> Rs. $amount")
    }
  }
}