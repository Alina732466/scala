import breeze.linalg.{DenseVector, euclideanDistance}

object p7 {

  case class DataPoint(features: DenseVector[Double], label: String)

  def main(args: Array[String]): Unit = {

    val dataset = Seq(
      DataPoint(DenseVector(7.4, 0.70, 0.00, 9.4), "5"),
      DataPoint(DenseVector(7.8, 0.88, 0.00, 9.8), "5"),
      DataPoint(DenseVector(7.8, 0.76, 0.04, 9.8), "5"),
      DataPoint(DenseVector(11.2, 0.28, 0.56, 9.8), "6"),
      DataPoint(DenseVector(7.4, 0.66, 0.00, 9.4), "5"),
      DataPoint(DenseVector(7.9, 0.60, 0.06, 9.4), "5"),
      DataPoint(DenseVector(7.3, 0.65, 0.00, 10.0), "7"),
      DataPoint(DenseVector(7.8, 0.58, 0.02, 9.5), "7"),
      DataPoint(DenseVector(7.5, 0.50, 0.36, 10.5), "6"),
      DataPoint(DenseVector(6.7, 0.58, 0.08, 9.2), "5")
    )

    println("Wine Quality Training Data:")

    dataset.foreach(p =>
      println(s"Features: ${p.features}, Quality: ${p.label}")
    )

    val newPointFeatures = DenseVector(7.6, 0.55, 0.03, 9.6)

    println(s"\nNew wine sample to classify: $newPointFeatures")

    var minDistance = Double.MaxValue
    var predictedLabel = ""

    for (point <- dataset) {

      val dist = euclideanDistance(newPointFeatures, point.features)

      println(
        s"Distance to wine with quality '${point.label}': $dist"
      )

      if (dist < minDistance) {
        minDistance = dist
        predictedLabel = point.label
      }
    }

    println("\nClassification Result:")

    println(
      s"The nearest wine is at a distance of: $minDistance"
    )

    println(
      s"The predicted wine quality is: $predictedLabel"
    )
  }
}