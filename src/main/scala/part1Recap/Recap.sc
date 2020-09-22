val incrementer: Int => Int = x => x + 1

val incremented = incrementer(45)

//map, flatMap, filter

val processedList = List(1, 2, 3).map(incrementer)

val longerList: Seq[Int] = List(1, 2, 3).flatMap(x => List(x, incrementer(x)))

val anOption: Option[Int] = Option( /*something that can be null*/ 3)
val doubledOption         = anOption.map(_ * 2) // Some(6)

val checkerBoard = List(1, 2, 3).flatMap(i => List('a', 'b', 'c').map(c => (i, c)))
//val doubleIt = noneOption.map(_*2)

val nums = List(1, 2, 3)
val ltrs = List('a', 'b', 'c')

val checkerBoard: Seq[(Int, Char)] = nums.flatMap(i => ltrs.map(c => (i, c)))

val anotherCB: Seq[(Int, Char)] = for {
  i <- nums
  c <- ltrs
} yield (i, c) //equivalent

val pf: PartialFunction[Int, String] = {
  case 1 => "ONE"
  case 2 => "TWO"
  case _ => "BIG NUMBA"
}

pf(6)
