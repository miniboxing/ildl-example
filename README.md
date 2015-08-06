#ildl-example
<img src="http://scala-miniboxing.org/images/ildl-logo.png" alt="ildl logo" width="150" align="right">

This is an example of using the ildl (data-centric metaprogramming) plugin with your sbt project. 

Example usage:
```
$ git clone https://github.com/miniboxing/ildl-example.git

$ cd ildl-example

$ cat src/main/scala/Test.scala 
import ildl._

object Test {

  /**
   * Stores Long objects as Ints, losing precision. It's a
   * rather dumb transformation, but it shows what's happening.
   */
  object RangeReduction extends TransformationDescription {
    def toRepr(v: Long): Int @high = v.toInt
    def toHigh(v: Int @high): Long = v.toLong

    // this method hijacks the + operation on transformed values:
    def extension_+(o1: Int @high, o2: Int @high): Int @high = 42
    // comment it out to get the right result :)
  }

  // original method:
  def plus1(v1: Long, v2: Long) = v1 + v2

  // transformed method:
  adrt(RangeReduction) {
    def plus2(v1: Long, v2: Long) = v1 + v2
  }

  def main(args: Array[String]): Unit = {
    val v1 = 4
    val v2 = 5
    println(s"$v1 + $v2 = ${plus1(v1,v2)}")
    println(s"$v1 + $v2 = ${plus2(v1,v2)}")
  }
}

$ sbt
[info] Loading project definition from /mnt/data1/Work/Workspace/dev/ildl-example/project
[info] Set current project to ildl-example (in build file:/mnt/data1/Work/Workspace/dev/ildl-example/)

> run
[info] Running Test 
4 + 5 = 9
4 + 5 = 42
```
