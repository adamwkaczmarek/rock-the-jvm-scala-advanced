package exercises

import lectures.part4implicits.TypeClasses.{User}

object EqualityPlayground extends App{

  /*
      Exercise
      Equality

   */

  trait Equal[T] {
    def equal(o1: T, o2: T): Boolean
  }

  object Equal {
    def apply[T](o1: T, o2: T)(implicit instance: Equal[T]) =
      instance.equal(o1, o2)
  }

  implicit object NameEquality extends Equal[User] {
    override def equal(o1: User, o2: User): Boolean = o1.name == o2.name
  }

  object FullEquality extends Equal[User] {
    override def equal(o1: User, o2: User): Boolean =
      o1.name == o2.name && o1.email == o2.email
  }

  private val john = User("John", 32, "john@gmail.com")
  private val anotherJohn = User("John", 28, "anotherJohn@gmail.com")
  println(Equal(anotherJohn, john))

  // this is AD-HOC polymorphism

  /*
      Exercise - improve the Equal TC with an implicit conversion class
      ===(another:T)
      !===(another:T)
   */

  implicit class TypeSafeEqual[T](value: T) {

    def ===(anotherValue: T)(implicit equalizer: Equal[T]): Boolean =
      equalizer.equal(value, anotherValue)

    def !==(anotherValue: T)(implicit equalizer: Equal[T]): Boolean =
      !equalizer.equal(value, anotherValue)
  }

  println(john === anotherJohn)

  /*
     john === anotherJohn
     new TypeSafeEqual[User](john).===(anotherJohn)(NameEquality)
   */
  /*
    IS TYPE SAFE - What is mean ?
    I cannot write
    // john === 43 - compiler error

   */
  println(john.===(anotherJohn)(FullEquality))
  println(john !== anotherJohn)


}


