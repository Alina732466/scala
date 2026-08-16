import breeze.linalg._
import breeze.stats.distributions.Rand

object p8 {
  def main(args: Array[String]): Unit = {

    val numSamples = 10
    val numFeatures = 4
    val k = 2
    val maxIterations = 100

    println("Loading Wine Quality dataset...")

    val data = DenseMatrix(
      (7.4, 0.70, 0.00, 9.4),
      (7.8, 0.88, 0.00, 9.8),
      (7.8, 0.76, 0.04, 9.8),
      (11.2, 0.28, 0.56, 9.8),
      (7.4, 0.66, 0.00, 9.4),
      (7.9, 0.60, 0.06, 9.4),
      (7.3, 0.65, 0.00, 10.0),
      (7.8, 0.58, 0.02, 9.5),
      (7.5, 0.50, 0.36, 10.5),
      (6.7, 0.58, 0.08, 9.2)
    )

    println(s"Loaded dataset with ${data.rows} samples and ${data.cols} features.")

    var centroids = DenseMatrix.zeros[Double](k, numFeatures)

    val randomIndices = (0 until data.rows)
      .sortBy(_ => Rand.uniform.get)
      .take(k)

    for (i <- 0 until k) {
      centroids(i, ::) := data(randomIndices(i), ::)
    }

    println(s"\nInitial centroids:\n$centroids")

    var assignments = DenseVector.zeros[Int](data.rows)
    var previousAssignments = DenseVector.fill[Int](data.rows)(-1)

    var iteration = 0
    var converged = false

    while (iteration < maxIterations && !converged) {

      println(s"\n--- Iteration ${iteration + 1} ---")

      for (i <- 0 until data.rows) {

        val point = data(i, ::).t
        var minDistance = Double.MaxValue
        var closestCentroidIndex = -1

        for (j <- 0 until k) {

          val centroid = centroids(j, ::).t
          val dist = euclideanDistance(point, centroid)

          if (dist < minDistance) {
            minDistance = dist
            closestCentroidIndex = j
          }
        }

        assignments(i) = closestCentroidIndex
      }

      if (assignments == previousAssignments) {
        converged = true
      } else {
        previousAssignments = assignments.copy
      }

      val newCentroids =
        DenseMatrix.zeros[Double](k, numFeatures)

      val clusterCounts =
        DenseVector.zeros[Int](k)

      for (i <- 0 until data.rows) {

        val clusterId = assignments(i)

        newCentroids(clusterId, ::) :=
          newCentroids(clusterId, ::) + data(i, ::)

        clusterCounts(clusterId) += 1
      }

      for (i <- 0 until k) {

        if (clusterCounts(i) > 0) {

          newCentroids(i, ::) :=
            newCentroids(i, ::) / clusterCounts(i).toDouble
        }
      }

      centroids = newCentroids

      println(s"Updated centroids:\n$centroids")

      iteration += 1
    }

    println("\n--- Final Results ---")

    println(s"K-means algorithm converged in $iteration iterations.")

    println(s"Final centroids:\n$centroids")

    println(s"Final cluster assignments:\n$assignments")
  }
}