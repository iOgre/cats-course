package part1Recap

import java.util.concurrent.Executors

import scala.concurrent.{ExecutionContext, Future}
import scala.util.Try

object Essentials extends App {

  val aBoolean: Boolean = false
  val expr              = if (2 > 3) "bigger" else "smaller"
  val theUnit           = println("hello")

  //OOP

  class Animal
  class Cat   extends Animal
  class Sheep extends Animal

  trait Carnivore {
    def eat(a: Animal): Unit
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

  val processedList = List(1, 2, 3).map(incrementer)

  val longerList = List(1, 2, 3).flatMap(x => List(x, x + 1)) //List(1, 2, 2, 3, 3, 4)

  //options and try

  val anOption: Option[Int] = Option( /*something that can be null*/ 3)
  val doubledOption         = anOption.map(_ * 2) // Some(6)

  val anAttempt       = Try( /*smth that can fail*/ 3)
  val modifiedAttempt = anAttempt.map(_ + 10)

  //pattern matching

  val unknown: Any = 45

  val ordinal = unknown match {
    case 1 => "first"
    case 2 => "second"
    case _ => "another value"
  }

  val optionDescription: String = anOption match {
    case Some(value) => s"the option is not empty, value = $value"
    case None        => "the option Is empty"
  }

  //for-comp

  val nums = List(1, 2, 3)
  val ltrs = List('a', 'b', 'c')

  val checkerBoard: Seq[(Int, Char)] = nums.flatMap(i => ltrs.map(c => (i, c)))

  val anotherCB: Seq[(Int, Char)] = for {
    i <- nums
    c <- ltrs
  } yield (i, c) //equivalent

  //partial functions

  val pf: PartialFunction[Int, String] = {
    case 1 => "ONE"
    case 2 => "TWO"
    case _ => "BIG NUMBA"
  }

  //HKT

  trait HigherKindedType[F[_]]

  trait SequenceChecker[F[_]] {
    def isSequential: Boolean
  }

  val listChecker = new SequenceChecker[List] {
    override def isSequential: Boolean = true
  }

  //Futures

  //import scala.concurrent.ExecutionContext.Implicits.global

  implicit val ec: ExecutionContext = ExecutionContext.fromExecutorService(Executors.newFixedThreadPool(5))

  val futureIt = Future(42)
}
