package model

import scala.beans.{BeanInfo, BeanProperty}

sealed trait Opts
case object OptOne extends Opts
case object OptTwo extends Opts

object MyEnum extends Enumeration {
  type MyEnum = Value
  val My1,My2 = Value
}

@BeanInfo
class WithEnum {
  import MyEnum._
  @BeanProperty
	var id: Int = _
  @BeanProperty
	var opt: Opts = _
  @BeanProperty
  var myopt: MyEnum = _

  override def toString(): String = s"$id : $opt $myopt"
}