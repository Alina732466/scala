object MeanMedianMode {

  def main(args: Array[String]): Unit = {

    // Example Data
    val numbers = Array(10, 20, 30, 40, 50)

    println("Original Data: " + numbers.mkString(", "))

    // ------------------ Mean ------------------
    val sum = numbers.sum
    val mean = sum.toDouble / numbers.length

    println("\n===== MEAN =====")
    println("Formula: Mean = Σx / n")
    println("Sum = " + sum)
    println("Number of Values = " + numbers.length)
    println(f"Mean = $mean%.2f")

    // ------------------ Median ------------------
    val sortedNumbers = numbers.sorted

    val median =
      if (sortedNumbers.length % 2 == 0) {
        val mid = sortedNumbers.length / 2
        (sortedNumbers(mid - 1) + sortedNumbers(mid)) / 2.0
      } else {
        sortedNumbers(sortedNumbers.length / 2).toDouble
      }

    println("\n===== MEDIAN =====")
    println("Sorted Data: " + sortedNumbers.mkString(", "))
    println("Median = " + median)

    // ------------------ Mode ------------------
    val frequency = numbers.groupBy(identity).view.mapValues(_.length).toMap
    val highestFrequency = frequency.values.max
    val modes = frequency.filter(_._2 == highestFrequency).keys.toSeq.sorted

    println("\n===== MODE =====")

    if (highestFrequency == 1)
      println("No Mode (Every value appears only once)")
    else
      println("Mode = " + modes.mkString(", "))
  }
}