import breeze.linalg._

object MallSalesAnalysis {

  def main(args: Array[String]): Unit = {

    // -----------------------------
    // Sales Matrix (3x3)
    // -----------------------------
    val sales = DenseMatrix(
      (12.0, 15.0, 18.0),
      (10.0, 14.0, 16.0),
      (11.0, 13.0, 17.0)
    )

    // -----------------------------
    // Display Matrix
    // -----------------------------
    println("Mall Sales Matrix (₹ in 1000):")
    println(sales)

    // -----------------------------
    // Transpose
    // -----------------------------
    println("\nTranspose of Sales Matrix:")
    println(sales.t)

    // -----------------------------
    // Determinant
    // -----------------------------
    val determinant = det(sales)
    println("\nDeterminant of Sales Matrix:")
    println(determinant)
  }
}