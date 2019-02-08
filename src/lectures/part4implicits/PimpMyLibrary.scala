package lectures.part4implicits

object PimpMyLibrary extends App {

  implicit class RichInt(val value: Int) extends AnyVal {
    def isEven: Boolean = value % 2 == 0

    def sqrt: Double = Math.sqrt(value)

    def times(func: () => Unit): Unit = {
      def timesAux(numberOfTImes: Int): Unit = {
        if (numberOfTImes <= 0) ()
        else {
          func()
          timesAux(numberOfTImes - 1)
        }
      }

      timesAux(value)
    }

    def *(list: List[AnyVal]): List[AnyVal] = {

      def concatenate(list: List[AnyVal],n:Int):List[AnyVal] = {
        if (n <= 0) List.empty else {
          concatenate(list, n - 1) ++ list
        }
      }

      concatenate(list,value)
    }
  }

  new RichInt(42).sqrt

  42.isEven

  // type enrichment = pimping

  /*
      Enrich the string class
      -as Int
      -encrypt
      john->Lnjp

      Keep enriching the INt class
      -times(function)
      3.times(()=>....)
      -*
       3*List(1,2)=>List(1,2,1,2,1,2)
   */

  implicit class RichString(val str: String) extends AnyVal {
    def asInt = Integer.valueOf(str)

    def encrypt(distance: Int) = str.map(c => (c + distance).asInstanceOf[Char])


  }

  println("John is really nice person".encrypt(2))
  println("10".asInt + 4)

  4.times(() => println("WOW"))

  println(4 * List(1,2,3))

}
