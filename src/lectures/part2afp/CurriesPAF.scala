package lectures.part2afp

object CurriesPAF extends App {
  val supperAdder: Int => Int => Int = x => y => x + y
  val add3 = supperAdder(3)
  println(add3(5))
  println(supperAdder(3)(5))

  def curriedAdder(x: Int)(y: Int): Int = x + y

  val add4: Int => Int = curriedAdder(4)

  //lifting = ETA-expansion
  //function != methods (JVM limitation)

  def inc(x: Int) = x + 1

  List(1, 2, 3).map(inc) // what compiler do is List(1,2,3).map(x=>inc(x)) //EAT-expansion

  //Partial functions applications
  val add5 = curriedAdder(5) _

  //Excercise
  val simpleAddFunction = (x: Int, y: Int) => x + y

  def simpleAddMethod(x: Int, y: Int): Int = x + y

  def curriedAddMethod(x: Int)(y: Int): Int = x + y

  val add7 = (x: Int) => simpleAddMethod(7, x)
  val add7_1: Int => Int = curriedAddMethod(7)
  val add7_2 = curriedAddMethod(7) _
  val add7_3: Int => Int = simpleAddFunction(7, _)
  val add7_4 = simpleAddMethod(7, _: Int)
  val add7_5 = simpleAddFunction.curried(7)

  def concatenator(a: String, b: String, c: String) = a + b + c
  val insertName = concatenator("Hello" , _ , "How are you")

  println(insertName("adam"))
}
