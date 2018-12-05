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

  //infix patterns

  case class Or[A,B](a:A,b:B)

  var either = Or(2,"two")

  val humanDescription = either match {
    case number Or string => s"$number is written as $string" // the same as case Or(number,string)=>....
  }

  abstract class MyList[+A]{
    def head:A= ???
    def tail:MyList[A]= ???
  }

  case object Empty extends MyList[Nothing]
  case class Cons[+A](override val head:A, override val tail:MyList[A]) extends MyList[A]

  object MyList{
    def unapplySeq[A](list: MyList[A]): Option[Seq[A]] =
      if(list==Empty)Some(Seq.empty)
      else unapplySeq(list.tail).map(list.head +: _)
  }

  val myList:MyList[Int]=Cons(1,Cons(2,Cons(3,Empty)))
  val decomposed= myList match{
    case MyList(1,2,_*)=>"starting with 1,2"
    case _ =>"something else"
  }

  println(decomposed)

  abstract class Wrapper[T]{
    def isEmpty:Boolean
    def get:T
  }

  object PersonWrapper{
    def unapply(arg: Person): Wrapper[String] = new Wrapper[String] {
      override def isEmpty = false

      override def get = arg.name
    }
  }


  println(bob match{
    case PersonWrapper(name)=>s"This is $name"
    case _=> "An alien"
  })


}
