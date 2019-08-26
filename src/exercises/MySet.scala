package exercises

import scala.annotation.tailrec

trait MySet[A] extends (A => Boolean) {

  /*
  EXCERCISE -implement a functional set
   */
  def apply(elem: A) = contains(elem)

  def contains(element: A): Boolean

  def +(elem: A): MySet[A]

  def ++(anotherSet: MySet[A]): MySet[A]

  def map[B](f: A => B): MySet[B]

  def flatMap[B](f: A => MySet[B]): MySet[B]

  def filter(predicate: A => Boolean): MySet[A]

  def foreach(f: A => Unit): Unit

  def -(element: A): MySet[A]

  def &(another: MySet[A]): MySet[A]

  def --(another: MySet[A]): MySet[A]

}

class EmptySet[A] extends MySet[A] {
  override def contains(element: A): Boolean = false

  override def +(elem: A): MySet[A] = new NonEmptySet[A](elem, this)

  override def ++(anotherSet: MySet[A]): MySet[A] = anotherSet

  override def map[B](f: A => B): MySet[B] = new EmptySet[B]

  override def flatMap[B](f: A => MySet[B]): MySet[B] = new EmptySet[B]

  override def filter(predicate: A => Boolean): MySet[A] = this

  override def foreach(f: A => Unit): Unit = ()

  override def -(element: A): MySet[A] = this

  override def &(another: MySet[A]): MySet[A] = ???

  override def --(another: MySet[A]): MySet[A] = another
}

class NonEmptySet[A](head: A, tail: MySet[A]) extends MySet[A] {
  override def contains(element: A): Boolean = head.equals(element) || tail.contains(element)

  override def +(elem: A): MySet[A] = if (!this.contains(elem)) new NonEmptySet(elem, this) else this

  override def ++(anotherSet: MySet[A]): MySet[A] = tail ++ anotherSet + head

  override def map[B](f: A => B): MySet[B] = (tail map f) + f(head)

  override def flatMap[B](f: A => MySet[B]): MySet[B] = (tail flatMap f) ++ f(head)

  override def filter(predicate: A => Boolean): MySet[A] = {
    val fiteredTail = tail filter (predicate)
    if (predicate(head)) fiteredTail + head
    else
      fiteredTail
  }

  override def foreach(f: A => Unit): Unit = {
    f(head)
    tail foreach f
  }

  override def -(element: A): MySet[A] = ???

  override def &(another: MySet[A]): MySet[A] = ???

  override def --(another: MySet[A]): MySet[A] = ???
}

object MySet {
  def apply[A](values: A*): MySet[A] = {
    @tailrec
    def buildSet(seq: Seq[A], acc: MySet[A]): MySet[A] = {
      if (seq.isEmpty) acc else buildSet(seq.tail, acc + seq.head)
    }

    buildSet(values.toSeq, new EmptySet[A])
  }
}

object MySetPlayground extends App {
  val s = MySet(1, 2, 3, 4)

  s + 5 ++ MySet(-1, -2) + 3 map (x => 3 * x) filter (x => x % 2 == 0) foreach println
}