def multiplyAndJoin(line: Int): String = {
  (1 to 9).map(_ * line).mkString(" ")
}


object Main extends App {
    val line = scala.io.StdIn.readLine().toInt
    //println((1 to 9).map(_ * line).mkString(" "))
  val result = multiplyAndJoin(line)
  println(result)
}
