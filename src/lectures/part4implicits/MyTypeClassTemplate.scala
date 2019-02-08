package lectures.part4implicits

  //TYPE CLASS

  trait MyTypeClassTemplate[T] {
    def actions(value: T): String
  }

  object MyTypeClassTemplate {
    def apply[T](implicit instance: MyTypeClassTemplate[T]): MyTypeClassTemplate[T] = instance
  }
