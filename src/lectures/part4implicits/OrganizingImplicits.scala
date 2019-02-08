package lectures.part4implicits

object OrganizingImplicits extends App{

  implicit val reverseOrdering: Ordering[Int]= Ordering.fromLessThan(_>_)

  println(List(1,4,5,6,2).sorted)

  //scala.Predef
  /*
    Implicits (used as implicit parameters)
    -val/var
    -object
    -accessor methods = def with no parentheses
   */
  //Excercise
  case class Person(name:String, age:Int)

  val persons=List(
    Person("Steve",30),
    Person("Amy",22),
    Person("John",66)
  )

//  object Person /* if other name - not companion object of Person-code will note compile */{
//    implicit val alphabeticalOrdering:Ordering[Person]=Ordering.fromLessThan((p1, p2)=>p1.name.compareTo(p2.name) > 0  )
//  }


 // println(persons.sorted)

  /*
  Implicit scope
  - normal scope
  - imported scope
  -companion object of all types involved in method signature
      -List
      -Ordering
      -all the types involved =! or any supertypes
   */

  object AlphabeticOrdering{
    implicit val alphabeticalOrdering:Ordering[Person]=Ordering.fromLessThan((p1, p2)=>p1.name.compareTo(p2.name) > 0  )
  }

  object AgeOrdering{
    implicit val ageOrdering:Ordering[Person]=Ordering.fromLessThan((p1, p2)=>p1.age > p2.age   )

  }

  import AlphabeticOrdering._

  println(persons.sorted)


}
