 import breeze.linalg._

object PRAC6 {
  def main(args: Array[String]): Unit = {

    val matrix = DenseMatrix(
      (2, 4, 6, 8, 10),
      (1, 3, 5, 7, 9),
      (11, 13, 15, 17, 19),
      (20, 22, 24, 26, 28),
      (30, 32, 34, 36, 38)
    )

    println("Original Matrix:")
    println(matrix)
    val subMatrix = matrix(1 to 3, 2 to 4)
    println("\nExtracted Sub-Matrix:")
    println(subMatrix)
    val rowSums = sum(subMatrix(*, ::))
    println("\nRow Sums:")
    println(rowSums)
    val colSums = sum(subMatrix(::, *))
    println("\nColumn Sums:")
    println(colSums)
  }
}


