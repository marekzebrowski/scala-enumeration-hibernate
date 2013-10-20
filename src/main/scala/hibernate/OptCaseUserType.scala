package hibernate

import org.hibernate.usertype.UserType
import java.io.Serializable
import java.sql.{Types, ResultSet, PreparedStatement}
import org.hibernate.engine.spi.SessionImplementor
import model.{OptTwo, OptOne, Opts}

class OptCaseUserType extends UserType {
  def nullSafeSet(st: PreparedStatement, value: scala.Any, index: Int, session: SessionImplementor) = {
    if (value == null) {
      st.setNull(index, Types.VARCHAR)
    } else {
      val en = value.toString.toLowerCase()
      st.setString(index, en)
    }
  }


  def sqlTypes(): Array[Int] = Array(Types.VARCHAR)

  def returnedClass(): Class[_] = classOf[Opts]

  def equals(x: Object, y: Object): Boolean = x == y

  def hashCode(x: Object): Int = x.hashCode()

  def nullSafeGet(rs: ResultSet, names: Array[String], session: SessionImplementor, owner: Object): Object = {
    val value = rs.getString(names(0))
    if (rs.wasNull()) return null
    else {
      value match {
        case "optone" => OptOne
        case "opttwo" => OptTwo
      }
    }
  }

  def deepCopy(value: Object): Object = value

  def isMutable: Boolean = false

  def disassemble(value: Object): Serializable = value.asInstanceOf[Serializable]

  def assemble(cached: Serializable, owner: Object): Object = cached.asInstanceOf[Object]

  def replace(original: Object, target: Object, owner: Object): Object = original
}
