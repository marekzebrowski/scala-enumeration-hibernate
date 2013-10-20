import model.{OptTwo, WithEnum}
import org.hibernate.cfg.Configuration

object HiRunner extends App {
  val cfg = new Configuration()
     .addResource("WithEnum.hbm.xml")
     .configure()
  val sf = cfg.buildSessionFactory()

  import model.MyEnum._
  var bean = new WithEnum
  bean.setId(1)
  bean.setOpt(OptTwo)
  bean.setMyopt(My1)
  val session = sf.openSession()
  val tx = session.beginTransaction()
  session.save(bean)
  tx.commit()
  println(s"Saved    to db: $bean")
  var loaded = session.get(classOf[WithEnum],1)
  session.close()
  println(s"Loaded from db: $loaded")
}
