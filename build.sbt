name := "scala-hibernate"

version := "1.0"

scalaVersion := "2.10.3"

libraryDependencies ++= Seq("org.hibernate" % "hibernate-core" % "4.1.12.Final",
  						   "com.h2database" % "h2" % "1.3.173",
  						   "mysql" % "mysql-connector-java" % "5.1.26")

