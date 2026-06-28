import breeze.linalg._

object WaterConsumptionAnalysis {

  def main(args: Array[String]): Unit = {

    // -----------------------------
    // Dense Vectors (Week 1 & Week 2)
    // -----------------------------
    val week1 = DenseVector(180.0, 195.0, 210.0, 205.0, 220.0, 240.0, 230.0)
    val week2 = DenseVector(175.0, 200.0, 215.0, 210.0, 225.0, 235.0, 225.0)

    // -----------------------------
    // Sparse Vector (Partial Data)
    // -----------------------------
    val sparseWeek = SparseVector(
      (1, 195.0),
      (4, 220.0),
      (6, 230.0)
    )(7)

    // -----------------------------
    // Display Vectors
    // -----------------------------
    println("Week 1 Water Consumption: " + week1)
    println("Week 2 Water Consumption: " + week2)
    println("Sparse Vector (Partial Records): " + sparseWeek)

    // -----------------------------
    // Sum of Week 1
    // -----------------------------
    val total = sum(week1)
    println("\nTotal Water Consumption (Week 1) = " + total + " Liters")

    // -----------------------------
    // Mean of Week 1
    // -----------------------------
    val mean = total / week1.length
    println("Average Water Consumption (Week 1) = " + f"$mean%.2f Liters")

    // -----------------------------
    // Dot Product (Week 1 vs Week 2)
    // -----------------------------
    val dotProduct = week1 dot week2
    println("Dot Product (Week1 · Week2) = " + dotProduct)
  }
}