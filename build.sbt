name := "random-str-collision-graph-scala"

version := "0.1"

scalaVersion := "2.12.4"

val breezeVersion = "0.13.2"

libraryDependencies ++= Seq(
  // ScalaNLP (Natural Language Processing)
  "org.scalanlp" %% "breeze" % breezeVersion,
  // ScalaNLP (For plotting)
  "org.scalanlp" %% "breeze-viz" % breezeVersion,
  "org.scalanlp" %% "breeze-natives" % breezeVersion // If none, it warns "Failed to load implementation from: com.github.fommil.netlib.NativeRefBLAS"  (this will accelerate speed)
)