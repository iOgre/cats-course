val incrementer: Int => Int = x => x + 1

val incremented = incrementer(45)

//map, flatMap, filter

val processedList = List(1,2,3).map(incrementer)

val longerList: Seq[Int] = List(1,2,3).flatMap(x => List(x, incrementer(x)))

val anOption: Option[Int] = Option(/*something that can be null*/ 3 )
val doubledOption = anOption.map(_ * 2) // Some(6)

//val doubleIt = noneOption.map(_*2)