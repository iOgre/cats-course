package part1Recap

import java.util.UUID

import part1Recap.Implicits.Person
import part1Recap.Implicits.PersonEnhancer.toPerson

object Implicits {

  case class Person(name: String) {
    def greet: String           = s"Hi, my name is $name"
    def greets(p: Person): Unit = println(s"Hello ${p.name}, my name is $name")
  }

  implicit class PersonEnhancer(p: Person) {
    def jump: Unit = println(s"${p.name} now jumping!")
  }

  object PersonEnhancer {
    implicit def toPerson(n: String): Person = Person(n)
  }

  def main(args: Array[String]): Unit =
    "Joe".greet

  "Joe" greets "Peter"

  Person("Bill").jump
}

object AnotherImplicits {

  def increment(x: Int)(
    implicit
    amount: Int
  ) = x + amount

  def main(args: Array[String]): Unit = {
    implicit val defaultAmount = 11

    println(increment(5)) // implicit argument passed by the computer
  }
}

object MoreComplex {

  trait JSONSerialiser[T] {
    def toJson(value: T): String
  }

  def listToJson[T](list: List[T])(
    implicit
    serialiser: JSONSerialiser[T]
  ): String = list.map(l => serialiser.toJson(l)).mkString("[", ",", "]")

  implicit val PersonSerializer: JSONSerialiser[Person] = (p: Person) => s"""
       |{"name": "${p.name}"
       |}
       |""".stripMargin

  val personsJson: String = listToJson(List(Person("Jon7"), Person("Bob")))

  //implicit defs

  implicit def oneArgCaseClassSerializer[T <: Product]: JSONSerialiser[T] =
    new JSONSerialiser[T] {

      override def toJson(value: T): String =
        s"""
           |"${value.productElementName(0)}" : "${value.productElement(0)}"
           |""".stripMargin.trim
    }
  case class Record(identifier: UUID = UUID.randomUUID())
  case class Cat(catname: String)
  val catsToJson = listToJson(List(Cat("Tom"), Cat("Garfield")))

  //implicit methods can be used for implicit conversions (DISCOURAGED)

  def main(args: Array[String]): Unit = {
    println(oneArgCaseClassSerializer[Record].toJson(Record()))
    println(oneArgCaseClassSerializer[Record].toJson(Record()))
    println(personsJson)
  }
}
