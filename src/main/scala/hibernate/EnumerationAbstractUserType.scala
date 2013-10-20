package hibernate
import org.hibernate.usertype.UserType
import java.io.Serializable
import java.sql.{Types, ResultSet, PreparedStatement}
import model.MyEnum
import org.hibernate.engine.spi.SessionImplementor

/** Base class for persisting Enumerations as varchar, or enum in mysql */
abstract class EnumerationAbstractUserType(val et: Enumeration) extends UserType {

  override def sqlTypes() =  Array(Types.VARCHAR)

  override def returnedClass = classOf[et.Value]

  override def equals(x: Object, y: Object): Boolean =  x == y

  override def hashCode(x: Object) = x.hashCode()

  override def nullSafeGet(resultSet: ResultSet, names: Array[String], session: SessionImplementor, owner: Object): Object = {
    val value = resultSet.getString(names(0))
    if (resultSet.wasNull()) return null
    else {
      return et.withName(value)
    }
  }
  override def nullSafeSet(statement: PreparedStatement, value: Object, index: Int, session: SessionImplementor): Unit = {
    if (value == null) {
      statement.setNull(index, Types.VARCHAR)
    } else {
      val en = value.toString
      statement.setString(index, en)
    }
  }

  override def deepCopy(value: Object): Object = value
  override def isMutable() = false
  override def disassemble(value: Object) = value.asInstanceOf[Serializable]
  override def assemble(cached: Serializable, owner: Object): Object = cached.asInstanceOf[Object]
  override def replace(original: Object, target: Object, owner: Object) = original
}

/** Base class for persisting Enumerations as integers */
abstract class EnumerationAbstractIntUserType(val et: Enumeration) extends UserType {

  override def sqlTypes() =  Array(Types.INTEGER)

  override def returnedClass = classOf[et.Value]

  override def equals(x: Object, y: Object): Boolean =  x == y

  override def hashCode(x: Object) = x.hashCode()

  override def nullSafeGet(resultSet: ResultSet, names: Array[String], session: SessionImplementor, owner: Object): Object = {
    val value = resultSet.getInt(names(0))
    if (resultSet.wasNull()) return null
    else {
      return et(value)
    }
  }
  override def nullSafeSet(statement: PreparedStatement, value: Object, index: Int, session: SessionImplementor): Unit = {
    if (value == null) {
      statement.setNull(index, Types.INTEGER)
    } else {
      val en = value.asInstanceOf[et.Value]
      statement.setInt(index, en.id)
    }
  }

  override def deepCopy(value: Object): Object = value
  override def isMutable() = false
  override def disassemble(value: Object) = value.asInstanceOf[Serializable]
  override def assemble(cached: Serializable, owner: Object): Object = cached.asInstanceOf[Object]
  override def replace(original: Object, target: Object, owner: Object) = original
}

// actual class used in test code
class MyOptUserType extends EnumerationAbstractUserType(MyEnum)
