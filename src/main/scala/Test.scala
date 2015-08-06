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