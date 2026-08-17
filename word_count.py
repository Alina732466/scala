import os
import sys
PYTHON_PATH = sys.executable
os.environ["PYSPARK_PYTHON"] = PYTHON_PATH
os.environ["PYSPARK_DRIVER_PYTHON"] = PYTHON_PATH
from pyspark.sql import SparkSession
spark = SparkSession.builder \
    .appName("WordCount") \
    .master("local[*]") \
    .config("spark.pyspark.python", PYTHON_PATH) \
    .config("spark.pyspark.driver.python", PYTHON_PATH) \
    .getOrCreate()
text_file = spark.sparkContext.textFile("input.txt")
word_counts = (
    text_file
    .flatMap(lambda line: line.split())
    .map(lambda word: (word, 1))
    .reduceByKey(lambda a, b: a + b)
)
for word, count in word_counts.collect():
    print(word, ":", count)
spark.stop()