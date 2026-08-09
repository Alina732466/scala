import breeze.linalg._
import breeze.plot._

object Alina_74_p5 {

  def main(args: Array[String]): Unit = {

    val x = DenseVector(
      160000.0,
      180000.0,
      200000.0,
      210000.0,
      240000.0
    )

    val y = DenseVector(
      60.0,
      65.0,
      70.0,
      72.0,
      80.0
    )

    println("Original data:")
    println(s"Duration: $x")
    println(s"Popularity: $y")

    val xMean = x.toArray.sum / x.length
    val yMean = y.toArray.sum / y.length

    val numerator = x.toArray.zip(y.toArray)
      .map { case (xi, yi) =>
        (xi - xMean) * (yi - yMean)
      }.sum

    val denominator = x.toArray
      .map(xi => (xi - xMean) * (xi - xMean))
      .sum

    val slope = numerator / denominator
    val intercept = yMean - slope * xMean

    println("\nLinear Regression Coefficients:")
    println(s"Intercept (c): $intercept")
    println(s"Slope (m): $slope")

    println("\nRegression Equation:")
    println(s"Popularity = $intercept + ($slope × Duration)")

    val newX = 220000.0

    val predictedY = intercept + slope * newX

    println(s"\nPredicting popularity for duration = $newX ms:")
    println(s"Predicted value: $predictedY")

    val predictedValues =
      x.map(value => intercept + slope * value)

    println("\nPredicted Model Values:")
    println(predictedValues)

    val ssTotal = y.toArray
      .map(value => (value - yMean) * (value - yMean))
      .sum

    val ssResidual = y.toArray
      .zip(predictedValues.toArray)
      .map { case (actual, predicted) =>
        (actual - predicted) * (actual - predicted)
      }
      .sum

    val rSquared = 1.0 - (ssResidual / ssTotal)

    println(s"\nModel R-squared: $rSquared")

    val figure = Figure()
    val plot = figure.subplot(0)

    plot += scatter(
      x,
      y,
      _ => 10.0,
      _ => java.awt.Color.BLUE,
      _ => "",
      _ => "",
      "Original Data"
    )

    plot += breeze.plot.plot(
      x,
      predictedValues
    )

    plot.xlabel = "Duration (ms)"
    plot.ylabel = "Popularity"
    plot.title = "Linear Regression: Duration vs Popularity"

    figure.saveas("linear_regression.png")
  }
}