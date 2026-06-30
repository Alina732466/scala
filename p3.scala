object WaterIntakeStats {

  def main(args: Array[String]): Unit = {

    val data = Array(2.0, 3.0, 4.0, 3.0, 3.0)
    val n = data.length

    // Mean
    val mean = data.sum / n

    // Variance
    val variance = data.map(x => math.pow(x - mean, 2)).sum / n

    // Standard Deviation
    val stdDev = math.sqrt(variance)

    println(s"Water Intake Data: ${data.mkString(", ")}")
    println(f"Mean = $mean%.2f")
    println(f"Variance = $variance%.2f")
    println(f"Standard Deviation = $stdDev%.2f")
  }
}