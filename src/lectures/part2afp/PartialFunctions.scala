package lectures.part2afp

object PartialFunctions  extends App{

  val aFunction= (x:Int)=>x+1 // Function1[Int,Int]=== Int=>Int
  val aFussyFunction=(x:Int)=>
    if(x==1) 42
    else if(x==2)56
    else if(x==5)999
    else throw new FunctionNotApplicableException

  class FunctionNotApplicableException extends RuntimeException

  val aNicerFussyFunction=(x:Int)=>x match{
    case 1=>42
    case 2=>56
    case 5=>999
  }

  // function only for {1,2,5}

  val aPartialFunction:PartialFunction[Int,Int]={
    case 1=>42
    case 2=>56
    case 5=>999
  }// partial function value - equivalent of function above

  println(aPartialFunction(2))
  //println(aPartialFunction(333)) // will throw MatchError

  //PF utilities

  println(aPartialFunction.isDefinedAt(67))

  //lift
  val lifted = aPartialFunction.lift//Int=>Option[Int]
  println(lifted(2))
  println(lifted(44))

  val pfChain= aPartialFunction.orElse[Int,Int]{
    case 45 => 68
  }

  println(pfChain(2))
  println(pfChain(45))
  //println(pfChain(222)) will throw MatchError

  //PR extend normal functions

  val aTotalFunction:Int=>Int= {
    case 1 =>99
  }

  val aMappedList= List(1,2,3).map {
    case 1=>42
    case 2=>56
    case 3=>1000
  }

  println(aMappedList)

  /*
    Note : PF can only have ONE parameter type
   */


  /*
    Exercises
    1- construct a PF instance yourself (anonymous class)
    2- dumb chatbot as PF

   */

  //scala.io.Source.stdin.getLines().foreach(line=>println("You said " +line))

  val aPartialFunctionEx1= new PartialFunction[Int,Int] {

    override def isDefinedAt(x: Int) = x==42 || x==56 || x== 100


   override def apply(x: Int) = x match{
     case 1=>42
     case 2=>56
     case 3=>1000
   }
  }

  val chatbot:PartialFunction[String,String]={
    case "Hello"=>"Hello human, robot here"
    case "Goodbye"=>"Bye"
  }

  //scala.io.Source.stdin.getLines().foreach(line=>println("Chatbot said " +chatbot(line)))
  // equal as sentence above
  scala.io.Source.stdin.getLines().map(chatbot).foreach(println)
}
