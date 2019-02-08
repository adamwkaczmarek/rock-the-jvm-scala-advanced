package lectures.part4implicits

object TypeClasses extends App {

  trait HtmlWritable {
    def toHtml: String
  }

  case class User(name: String, age: Int, email: String) extends HtmlWritable {
    override def toHtml: String = s"<div>$name ($age) <a href=$email/></div>"
  }

  private val john = User("John", 32, "john@gmail.com")
  john.toHtml

  /*
      1- for the type WE write
      2- One implementation out of quite a number
   */

  // option 2

  object HtmlSerializerPM {
    def serializeToHmtl(value: Any): Unit = value match {
      case User(m, a, e) =>
      case _ =>
    }
  }

  /*
      1 -lost type safety
      2- need to modify the code every time
      3- still ONE implementation
   */

  // better implementation now

  //type class
  trait HTMLSerializer[T] {
    def serialize(value: T): String
  }

  //type class instance
  implicit object UserSerializer extends HTMLSerializer[User] {
    override def serialize(user: User): String =
      s"<div>$user.name ($user.age) <a href=$user.email/></div>"
  }

  //type class instance
  object PartialUserSerializer extends HTMLSerializer[User] {
    override def serialize(value: User): String = s"<div>${value.name}</div>"
  }

  // HTMLSerializer is called type class
  // each class with extends HTMLserializer is called type classes instances

  //part 2

  object HTMLSerializer {
    def serializer[T](value: T)(
      implicit serializer: HTMLSerializer[T]): String =
      serializer.serialize(value)

    def apply[T](implicit serializer: HTMLSerializer[T]): HTMLSerializer[T] =
      serializer

  }

  implicit object IntSerializer extends HTMLSerializer[Int] {
    override def serialize(value: Int): String =
      s"<div style:color=blue>$value</div>"

    //and event better would be add apply

  }

  println(HTMLSerializer.serializer(43))
  println(HTMLSerializer.serializer(john))
  println(HTMLSerializer[User].serialize(john))

  // part 3

  //conversion
  implicit class HTMLEnrichment[T](value: T) {
    def toHTML(implicit serializer: HTMLSerializer[T]): String = serializer.serialize(value)
  }

  println(john.toHTML)

  /*
      - extend to new types
      -choose implementation
   */

  println(2.toHTML)
  println(john.toHTML(PartialUserSerializer))

  /*
        - type class itself --- HTMLSerializer[T]
        - type class instances (some of which are implicit) ---- UserSerializer,IntSerializer
        - conversion with implicit classes HTMLEnrichment

   */

  def htmlBoilerplate[T](content:T)(implicit serializer:HTMLSerializer[T]):String=s"<html><body> ${content.toHTML(serializer)}</body></html>"
  def htmlSugar[T:HTMLSerializer](content:T):String={
    //if we need to use serializer we can
    val serializer=implicitly[HTMLSerializer[T]]

    s"<html><body> ${content.toHTML(serializer)}</body></html>"
  }

  // implicitly
  case class Permissions(mask:String)
  implicit val defaultPermissions=Permissions("0744")

  //in some other part of code

  val standardPerms=implicitly[Permissions]


  println(standardPerms)

}
