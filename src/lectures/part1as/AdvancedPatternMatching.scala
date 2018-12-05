package lectures.part1as

object AdvancedPatternMatching extends App {

  val numbers = List(1)
  val description = numbers match {
    case head :: Nil => println(s"This is only one element is $head")
    case _ =>
  }

  /*
    matching for :
     -constants
     -wildcards
     - case classes
     - tuples
     -some special magic like above

   */

  class Person(val name: String, val lastName: String, val age: Int)

  object Person {
    def unapply(arg: Person): Option[(String, String, Int)] =
      if (arg.age < 20) None
      else
        Some((arg.name, arg.lastName, arg.age))

    def unapply(age: Int): Option[(String)] =
      if(age <26)
        Some("minor")
      else
        Some("major")

  }

  val bob = new Person("BOB", "SOS", 25)

  val result = bob match {
    case Person(name, lastName, age) => s"This is $name $lastName and he is $age"
  }
  println(result)

  val result1= bob.age match {
    case Person(status)=>println(status)
  }

  /*
      Exercise
   */

  val n:Int=3

   val mathPropert = n match{
     case x if x < 10 => "single dignit"
     case x if x % 2==0 => "an even number"
     case _=> "no  propery"
   }


  object even {
    def unapply(arg: Int): Boolean = arg % 2==0

  }

  object singleDignit {
    def unapply(arg: Int): Boolean = arg > -10  && arg < 10
  }


  val mathPropert1 = n match {
    case even() =>  "an even number"
    case singleDignit() =>  "single dignit"
    case _=> "no  propery"
  }

  println(mathPropert1)
}
