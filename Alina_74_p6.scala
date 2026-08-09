import breeze.linalg._
import breeze.numerics.sigmoid

object Alina_74_p6 {

  def main(args: Array[String]): Unit = {

    val x = DenseMatrix(
      (160000.0, 60.0),
      (180000.0, 65.0),
      (200000.0, 68.0),
      (210000.0, 72.0),
      (220000.0, 75.0),
      (230000.0, 78.0),
      (240000.0, 80.0),
      (250000.0, 85.0)
    )

    val y = DenseVector(
      0.0,
      0.0,
      0.0,
      1.0,
      1.0,
      1.0,
      1.0,
      1.0
    )

    println("Original dataset:")
    println("Duration(ms)\tPopularity\tClass")

    for (i <- 0 until x.rows) {
      println(s"${x(i, 0)}\t\t${x(i, 1)}\t\t${y(i).toInt}")
    }

    val X = DenseMatrix.horzcat(
      DenseMatrix.ones[Double](x.rows, 1),
      x
    )

    val weights = DenseVector(
      -10.0,
      0.00001,
      0.10
    )

    println("\nLogistic Regression Model:")
    println(s"Intercept (w0): ${weights(0)}")
    println(s"Duration weight (w1): ${weights(1)}")
    println(s"Popularity weight (w2): ${weights(2)}")

    val newPoint1 =
      DenseVector(1.0, 170000.0, 62.0)

    val probability1 =
      sigmoid(weights dot newPoint1)

    val predictedClass1 =
      if (probability1 >= 0.5) 1 else 0

    println("\nPrediction for new song 1:")
    println("Duration: 170000 ms")
    println("Popularity: 62")
    println(s"Predicted probability: $probability1")
    println(s"Predicted class: $predictedClass1")

    val newPoint2 =
      DenseVector(1.0, 235000.0, 82.0)

    val probability2 =
      sigmoid(weights dot newPoint2)

    val predictedClass2 =
      if (probability2 >= 0.5) 1 else 0

    println("\nPrediction for new song 2:")
    println("Duration: 235000 ms")
    println("Popularity: 82")
    println(s"Predicted probability: $probability2")
    println(s"Predicted class: $predictedClass2")
  }
}