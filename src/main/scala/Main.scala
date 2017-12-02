import java.security.SecureRandom

import scala.util.Random

object Main {

  val secureRandom = new Random(new SecureRandom())

  // 1 ~ 9 + 'a' ~ 'z'
  val candidateChars: Seq[Char] = ('0' to '9') ++ ('a' to 'z')

  def main(args: Array[String]): Unit = {


    val length : Int = 3
    var usedStr: Set[String]  = Set.empty
    var tryNums: List[Int]    = List.empty

    // The number of generating = #CANDIDATE ^ STR_LEN
    val generateTimes: Int = Math.pow(candidateChars.length, length).toInt
    for(i <- 1 to generateTimes){
      println(s"i: ${i}")
      val (randomStr, tryNum) = genNoDupRandomStrAndTryNum(length, usedStr)
      usedStr += randomStr
      tryNums :+= tryNum
    }

    tryNums.foreach(println)

  }


  /**
    * Generate not duplicate random string and the number of trys
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
