package lectures.part1as

import scala.annotation.tailrec

object Recap extends App {

  val aCondition: Boolean = false
  val aConditionedVal = if (aCondition) 42 else 65
  //instructions vs expressions

  //compiler infer types fo us
  val codeBlock = {
    if (aCondition) 54
    56
  }

  //Unit

  val theUnit = println("hello scala")

  //functions
  def aFunction(x: Int): Int = x + 1

  @tailrec def factorial(n: Int, accumulator: Int): Int =
    if (n <= 0) accumulator
    else factorial(n - 1, n * accumulator)

  println(factorial(3, 2))

  class Animal

  class Dog extends Animal

  val aDog: Animal = new Dog // subtyping polymorphism

  trait Carnivore {
    def eat(animal: Animal): Unit
  }

  class Crocodile extends Animal with Carnivore {
    override def eat(animal: Animal): Unit = println("crunch !!!")
  }

  val aCrocodile = new Crocodile

  aCrocodile eat aDog // natural language

  // anoonymous classes

  val aCarnivore = new Carnivore {
    override def eat(animal: Animal): Unit = println("roar!!")
  }

  abstract class MyList[+A] // variance problems
  object MyList

  // singeltons and companions

  // case classes

  case class Person(name: String, age: Int)

  // exceptions try/catch/finally

  // val throwsException= throw new  RuntimeException // Nothing

  //  val aPotentialFailure=try{
  //    throw new RuntimeException
  //  }catch {
  //    case e:Exception=>"I catch it !!!"
  //  }finally {
  //    println("Some logs")
  //  }


  //functional programing

  val incrementer = new Function1[Int, Int] {
    override def apply(v1: Int) = v1 + 1
  }

  incrementer(1)

  val anonymousIncerementer = (x: Int) => x + 1
  List(1, 2, 3).map(anonymousIncerementer) // HOF - Hire order function
  // map, flatMap, filter

  // for comprehension

  val pairs = for {
    num <- List(1, 2, 3)
    char <- List('a', 'b', 'c')
  } yield num + "-" + char

  println(pairs)

  // Scala collections ; Seq,Arrays,Lists,Vectors,Maps,Tuples

  val map = Map("Daniel" -> 789, "Jess" -> 555)

  // collections:Options, Try

  // pattern matching
  val x = 2
  val order = x match {
    case 1 => "first"
    case 2 => "second"
    case 3 => "third"
    case _ => x + "th"
  }

}
