import breeze.linalg._

object PRAC7 {

  def main(args: Array[String]): Unit = {

    val matrix1 = DenseMatrix(
      (2.0, 4.0, 6.0),
      (8.0, 10.0, 12.0),
      (14.0, 16.0, 18.0)
    )

    val matrix2 = DenseMatrix(
      (1.0, 2.0, 3.0),
      (4.0, 5.0, 6.0),
      (7.0, 8.0, 9.0)
    )

    val addition = matrix1 + matrix2
    val subtraction = matrix1 - matrix2

    val multiplication = DenseMatrix.tabulate(matrix1.rows, matrix1.cols) {
      (i, j) => matrix1(i, j) * matrix2(i, j)
    }

    val division = DenseMatrix.tabulate(matrix1.rows, matrix1.cols) {
      (i, j) => matrix1(i, j) / matrix2(i, j)
    }

    println("Matrix 1:")
    println(matrix1)

    println("\nMatrix 2:")
    println(matrix2)

    println("\nAddition:")
    println(addition)

    println("\nSubtraction:")
    println(subtraction)

    println("\nElement-wise Multiplication:")
    println(multiplication)

    println("\nElement-wise Division:")
    println(division)
  }
}