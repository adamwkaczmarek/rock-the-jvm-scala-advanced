package lectures.part1as

import scala.util.Try

object DarkSugars extends App {
  // syntax sugar #1 method with single param

  def singleArgMethod(arg: Int): String = s"$arg little ducks"

  val desc = singleArgMethod {
    42
  }
  val aTryInstance = Try {
    throw new RuntimeException
  }

  List(1, 2, 3).map {
    x => x + 1
  }

  // syntax sugar #2 single abstract method
  trait Action {
    def act(x: Int): Int
  }

  val anInstance: Action = new Action {
    override def act(x: Int): Int = x + 1
  }

  val aFunkyInstance: Action = (x: Int) => x + 1

  // example :Runnables

  val aThread = new Thread(new Runnable {
    override def run(): Unit = println("hello,scala")
  })

  val aSweeterThread = new Thread(() => println("hello,sweeter scala"))

  abstract class AnAbstractType {
    def implemented: Int = 22

    def f(a: Int): Unit
  }

  val anAbstractTypeInstance: AnAbstractType = (a: Int) => print(s"sweet $a")

  // syntax sugar #3 the :: and #:: methods are special

  val prependedList = 2 :: List(3, 4)
  //2.::(List(3,4))  -no
  //List(3,4).::(2)

  //scala spec : last char decides associativity of method
  1 :: 2 :: 3 :: List(3, 4)
  List(3, 4).::(3).::(2).::(1) // equivalent

  class MyStream[T] {
    def --->:(value: T): MyStream[T] = this
  }

  val myStream = 1 --->: 2 --->: 3 --->: new MyStream[Int]

  // syntax sugar #4:multi-word method naming

  class TeenGirl(name: String) {
    def `and then said`(gossip: String): Unit = println(s"$name said $gossip")
  }

  val lilly = new TeenGirl("Lilly")

  lilly `and then said` "Scala is so sweet"

  //sytnax sugar 5# infix types

  class Composite[A, B]

  val composites: Int Composite String = ??? /// Composite[Int,String]
  class -->[A, B]

  val towards: Int --> String = ???

  //syntax sugar #6 : update() is  very special, much like apply()

  val anArray = Array(1, 2, 3)
  anArray(2) = 7 // rewriten to anArray.update(2,7)
  //used in mutable collections

  //syntax sugar #7: setter for mutable containers

  class Mutable {
    private var internalNumber: Int = 9

    def member = internalNumber

    def member_=(value: Int): Unit =
      internalNumber = value
  }

  val aMutable= new Mutable
  aMutable.member=42 // rewrittern as member_=(42)

}
