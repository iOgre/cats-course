package part1recap

import scala.concurrent.ExecutionContext
import scala.util.Try

object Essentials extends App {

  val aBoolean: Boolean = false
  val expr = if(2 >3) "bigger" else "smaller"
  val theUnit = println("hello")

  //OOP

  class Animal
  class Cat extends Animal
  class Sheep extends Animal
  trait Carnivore {
    def eat(a:Animal):Unit
  }

  class Crocodile extends Animal with Carnivore {
    override def eat(a: Animal): Unit = println(s"Crunching $a")
  }

  //singleton

  object MySingleton //singleton pattern in one line

  //companions

  object Carnivore // companion object for Carnivore trait

  //generics

  class MyList[A]

  //method notation

  val three = 1 + 2

  val anotherThree = 1.+(2)

  //functional programming

  val incrementer: Int => Int = x => x + 1

  val incremented = incrementer(45)

  //map, flatMap, filter

  val processedList = List(1,2,3).map(incrementer)

  val longerList = List(1,2,3).flatMap(x => List(x, x +1)) //List(1, 2, 2, 3, 3, 4)

  //options and try

  val anOption: Option[Int] = Option(/*something that can be null*/ 3 )
  val doubledOption = anOption.map(_ * 2) // Some(6)

  val anAttempt = Try(/*smth that can fail*/ 3)
  val modifiedAttempt = anAttempt.map(_ + 10)

  //pattern matching

  val unknown:Any = 45

  val ordinal = unknown match {
    case 1 => "first"
    case 2 => "second"
    case _ => "another value"
  }

  val optionDescription:String = anOption match {
    case Some(value) => s"the option is not empty, value = $value"
    case None => "the option Is empty"
  }

  //Futures

  //import scala.concurrent.ExecutionContext.Implicits.global

  implicit val ec:ExecutionContext = ExecutionContext.fromExecutorService(Executors)


  val sheep = new Sheep
  val crocodile = new Crocodile

  crocodile eat sheep

}
