from pyspark.sql import SparkSession
spark = SparkSession.builder \
    .appName("EmployeeProjectJoin") \
    .getOrCreate()
employees = spark.read \
    .option("header", True) \
    .option("inferSchema", True) \
    .csv("employees.csv")
projects = spark.read \
    .option("header", True) \
    .option("inferSchema", True) \
    .csv("employee_projects.csv")
result = employees.join(
    projects,
    on="employee_id",
    how="inner"
)
result.show()
result.write \
    .option("header", True) \
    .mode("overwrite") \
    .csv("employee_project_output")
spark.stop()
