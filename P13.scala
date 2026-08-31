import org.apache.spark.sql.SparkSession
import org.apache.spark.ml.Pipeline
import org.apache.spark.ml.classification.LogisticRegression
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.evaluation.BinaryClassificationEvaluator
object P13 {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName("Online Shopping Classification")
      .master("local[*]")
      .getOrCreate()
    val data = spark.read
      .option("header", "true")
      .option("inferSchema", "true")
      .csv("src/main/resources/online_shopping_classification.csv")
    println("===== ONLINE SHOPPING DATASET =====")
    data.show(10)
    println("===== DATASET STRUCTURE =====")
    data.printSchema()
    val selectedData = data.select(
      "Age",
      "TimeOnSite",
      "PagesViewed",
      "PreviousPurchases",
      "DiscountPercent",
      "CartItems",
      "Purchased"
    )
    val cleanData = selectedData.na.drop()
    val finalData = cleanData.withColumn(
      "label",
      cleanData("Purchased").cast("double")
    )
    val assembler = new VectorAssembler()
      .setInputCols(Array(
        "Age",
        "TimeOnSite",
        "PagesViewed",
        "PreviousPurchases",
        "DiscountPercent",
        "CartItems"
      ))
      .setOutputCol("features")
    val logisticRegression = new LogisticRegression()
      .setFeaturesCol("features")
      .setLabelCol("label")
      .setMaxIter(10)
    val pipeline = new Pipeline()
      .setStages(Array(
        assembler,
        logisticRegression
      ))
    val Array(trainingData, testingData) =
      finalData.randomSplit(Array(0.8, 0.2), seed = 42)
    println("Training Data Count: " + trainingData.count())
    println("Testing Data Count: " + testingData.count())
    val model = pipeline.fit(trainingData)
    val predictions = model.transform(testingData)
    println("===== PREDICTIONS =====")
    predictions.select(
      "Age",
      "TimeOnSite",
      "PagesViewed",
      "PreviousPurchases",
      "CartItems",
      "Purchased",
      "prediction"
    ).show(20)
    val evaluator = new BinaryClassificationEvaluator()
      .setLabelCol("label")
      .setRawPredictionCol("rawPrediction")
      .setMetricName("areaUnderROC")
    val accuracy = evaluator.evaluate(predictions)
    println("======================================")
    println("ONLINE SHOPPING CLASSIFICATION")
    println("======================================")
    println("Area Under ROC = " + accuracy)
    println("======================================")
    spark.stop()
  }
}