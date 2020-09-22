package part1Recap

object TypeClasses {

  case class Person(name: String, age: Int)

  //part 1 - type class definition
  trait JsonSerializer[T] {
    def toJson(v: T): String
  }

  //part 2 - create implicit type class INSTANCES

  implicit object StringSerializer extends JsonSerializer[String] {
    override def toJson(v: String): String = s""""${v}""""
  }

  implicit object IntSerializer extends JsonSerializer[Int] {
    override def toJson(v: Int): String = v.toString
  }

  implicit object PersonSerializer extends JsonSerializer[Person] {

    import JSONSyntax._

    override def toJson(v: Person): String =
      s"""
         | {"name": ${v.name.toJson}, "age": ${v.age.toJson}}
         |""".stripMargin.trim
  }

  //part 3 - provide some kind of API

  def listToJsonSerializer[T](list: List[T])(
    implicit
    serialiser: JsonSerializer[T]
  ): String = list.map(v => serialiser.toJson(v)).mkString("[", ",", "]")

  //TODO: LATERR
  /*def convertToJson[F[Z], T](smth: F[T])(
    implicit
    sr: JsonSerializer[T],
    sr1: JsonSerializer[F[_]]
  ) = sr1.toJson(smth)*/

  //part 4 - extending the existing types

  object JSONSyntax {

    implicit class JSONSerializable[T](value: T)(
      implicit
      ser: JsonSerializer[T]
    ) {
      def toJson: String = ser.toJson(value)
    }
  }

  def main(args: Array[String]): Unit = {
    println(listToJsonSerializer(List(Person("Alice", 23), Person("Xavier", 33))))
    val bob = Person("Bob", 55)
    import JSONSyntax._
    println(bob.toJson)

    val somePerson = Some(bob)
  }
}
