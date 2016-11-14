package ferryman

import com.twitter.finagle.Service
import com.twitter.finagle.{Addr, Address, Name, Service}
import com.twitter.util.{Future, Var}
import roc.postgresql.Request
import java.time.ZonedDateTime //OffsetDateTime
import roc.types.decoders._

object Postgresql {

  private val address = Address(Main.PostgresqlHost(), Main.PostgresqlPort())
  private val client = roc.Postgresql.client
    .withUserAndPasswd(Main.PostgresqlUser(), Main.PostgresqlPasswd())
    .withDatabase(Main.PostgresqlDatabase())
    .newRichClient(
      Name.Bound(Var[Addr](Addr.Bound(address)), "postgresql"),
      "postgresql"
    )

  val SelectVersion: Service[Unit, String] = new Service[Unit, String] {
    def apply(u: Unit): Future[String] = for {
      result <- client.query(queries.selectVersion) 
      row = result.headOption.getOrElse(throw new Exception("Should not return 0 rows"))
      version = row.get('version).as[String]
    } yield version
  }
  val SelectStartTimestamp: Service[Unit, ZonedDateTime] = new Service[Unit, ZonedDateTime] {
    final def apply(u: Unit): Future[ZonedDateTime] = for {
      result <- client.query(queries.selectServerStartTimestamp)
      row = result.headOption.getOrElse(throw new Exception("Should not return 0 rows"))
      startTimestamp = row.get('pg_postmaster_start_time).as[ZonedDateTime]
    } yield startTimestamp
  }
}

object queries {

  @inline def selectVersion: Request = Request("SELECT * FROM version();")
  @inline def selectServerStartTimestamp: Request = Request(
    "SELECT * FROM pg_postmaster_start_time();"
  )
}
