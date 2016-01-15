import sbt._

lazy val pushName = settingKey[String]("test name")
lazy val pushKey = settingKey[String]("test key")

lazy val push = taskKey[Unit]("push zip to S3 for CodeDeploy Test")

lazy val commonSettings = Seq (
  version := "1.0",
  scalaVersion := "2.11.7",
    push := {
    TaskTest.push(pushName.value, pushKey.value)
  }
)

lazy val root = (project in file(".")).
  aggregate(proj1, proj2).
  settings(
    name := "multitest",
    pushName := "push name is root",
    pushKey := "push key is root"
  )

lazy val proj1 = (project in file("project1")).
    settings(commonSettings: _*).
    settings (
      name := "project1",
      pushName := "push name is project1",
      pushKey := "push key is project1"
    )


lazy val proj2 = (project in file("project2")).
  settings(commonSettings: _*).
  settings(
    name := "project2",
    pushName := "push name is project2",
    pushKey := "push key is project2"
  )

