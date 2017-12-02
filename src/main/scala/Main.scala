import java.security.SecureRandom

import scala.util.Random

object Main {

  val secureRandom = new Random(new SecureRandom())

  // 1 ~ 9 + 'a' ~ 'z'
  val candidateChars: Seq[Char] = ('0' to '9') ++ ('a' to 'z')

  def main(args: Array[String]): Unit = {


    if(false){
      generateOnceTryNums()
    }

    if (true){
      generateAverageTryNums(times=2)
    }

  }

  def generateAverageTryNums(times: Int): Unit = {
    import breeze.linalg._
    import breeze.plot._

    val length: Int    = 3
    val vectorLen: Int = Math.pow(candidateChars.length, length).toInt

    val averageTryNums: DenseVector[Double] =
      (1 to times)
        .map { _ =>
          DenseVector[Double](getTryNums(length).map(_.toDouble): _*)
        }
        .foldLeft(DenseVector.zeros[Double](vectorLen)) { (s, e) =>
          s + e
        } / times.toDouble

    val f     = Figure("The number of tries")
    f.visible = false
    f.subplot(0) += plot((1 to vectorLen).map(_.toDouble), averageTryNums)
    f.saveas("average_try_nums.png")
  }

  def generateOnceTryNums(): Unit = {
    import breeze.linalg._
    import breeze.plot._
    val length: Int = 3
    val tryNumNums = getTryNums(length)

    val f     = Figure("The number of tries")
    f.visible = false
    f.subplot(0) += plot(1 to tryNumNums.length, tryNumNums)
    f.saveas("try_nums.png")
  }

  def getTryNums(length: Int): Seq[Int] = {
    var usedStr: Set[String]  = Set.empty
    var tryNums: List[Int]    = List.empty

    // The number of generating = #CANDIDATE ^ STR_LEN
    val generateTimes: Int = Math.pow(candidateChars.length, length).toInt
    for(i <- 1 to generateTimes){
      val (randomStr, tryNum) = genNoDupRandomStrAndTryNum(length, usedStr)
      usedStr += randomStr
      tryNums :+= tryNum
    }

    return tryNums
  }


  /**
    * Generate not duplicate random string and the number of tries
    * @param length   Length of generated string
    * @param usedStrs Already used strings
    * @return
    */
  def genNoDupRandomStrAndTryNum(length: Int, usedStrs: Set[String]): (String, Int) = {
    var tryNum   : Int = 0
    var randomStr: String = null

    do {
      tryNum += 1
       randomStr = genRandomStr(length)
    } while (usedStrs.contains(randomStr))

    return (randomStr, tryNum)
  }


  /**
    * Generate random string
    * @return Random string
    */
  def genRandomStr(length: Int): String = {
    val i = (1 to length).map{ _ =>
      val idx = secureRandom.nextInt(candidateChars.length)
      candidateChars(idx)
    }
    i.mkString
  }

}
