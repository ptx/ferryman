enablePlugins(JavaAppPackaging)

val buildName = "ferryman"

name := buildName

version := "0.0.1-SNAPSHOT"

organization := "codes.github"

scalaVersion := "2.11.8"

lazy val compilerOptions = Seq(
  "-deprecation",
  "-encoding", "UTF-8",
  "-feature",
  "-unchecked",
  "-Xfatal-warnings",
  "-language:existentials",
  "-Xlint",
  "-language:implicitConversions",
  "-Yinline-warnings",
  "-Yno-adapted-args",
  "-Ywarn-dead-code",
  "-Ywarn-value-discard",
  "-Ywarn-unused-import",
  "-Xfatal-warnings",
  "-Xfuture"
)

lazy val finchVersion = "0.11.0-M2"

lazy val finagleVersion = "6.38.0"

lazy val circeVersion = "0.4.1"

lazy val rocVersion = "0.0.5"

lazy val testDependencies = Seq(
  "org.scalactic"       %%  "scalactic" % "2.2.6",
  "org.scalacheck"      %%  "scalacheck" % "1.12.5",
  "com.github.finagle"  %%  "finch-test" % finchVersion
)

libraryDependencies ++= {
  Seq(
    "com.github.finagle"    %%  "finch-core"        %   finchVersion,
    "com.github.finagle"    %%  "finch-circe"       %   finchVersion,
    "com.twitter"           %%  "twitter-server"    %   "1.23.0",
    "com.twitter"           %%  "finagle-http"      %   finagleVersion, 
    "com.twitter"           %%  "finagle-stats"     %   finagleVersion, 
    "io.circe"              %%  "circe-core"        %   circeVersion, 
    "io.circe"              %%  "circe-java8"       %   circeVersion,
    "com.github.finagle"    %%  "roc-core"          %   rocVersion,
    "com.github.finagle"    %%  "roc-types"         %   rocVersion
  )
}

resolvers ++= {
  Seq(
    "Twitter Maven repo" at "http://maven.twttr.com/"
  )
}
resolvers += Resolver.sonatypeRepo("snapshots")

lazy val root = (project in file(".")).
  settings(
    scalacOptions ++= compilerOptions,
    scalacOptions in (Compile, console) := compilerOptions,
    libraryDependencies ++= testDependencies.map(_ % "test"),
    name := buildName
)
