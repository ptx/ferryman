package ferryman

import com.twitter.app.Flag
import com.twitter.finagle.http.{Request, Response}
import com.twitter.finagle.{Http, Service}
import com.twitter.server.TwitterServer
import com.twitter.util.Await
import io.circe.syntax._
import io.circe.{Encoder, Json}
import io.finch.circe._

object Main extends TwitterServer {

  private val Api: Service[Request, Response] = (
    endpoints.Show :+:
    endpoints.ShowStatus
  ).toService

  def main(): Unit = {
    val server = Http.server
      .withStatsReceiver(statsReceiver)
      .serve(s":${httpPort()}", Api)

    onExit { server.close(); () }

    log.info(s"Serving Ferryman Http on port :${httpPort()}")
    log.debug(s"Postgres Connection Info \n ${postgresConnection.asJson.spaces2}")
    Await.ready(adminHttpServer)
    ()
  }

  private val httpPort: Flag[Int] = flag("http.port", 8080, "TCP port for HTTP server")
  val PostgresqlHost: Flag[String] = flag("postgresql.host", "127.0.0.1",
    "Host for the Postgresql Database")
  val PostgresqlPort: Flag[Int] = flag("postgresql.port", 5432,
    "Port Number for the Postgresql Database") 
  val PostgresqlDatabase: Flag[String] = flag("postgresql.database", "sabrelabs", 
    "Database to connect to")
  val PostgresqlUser: Flag[String] = flag("postgresql.user", "travelchewy",
    "User to connect to the Postgresql Database")
  val PostgresqlPasswd: Flag[String] = flag("postgresql.password", "",
    "Password to connect to the Postgresql Database")

  case class PostgresqlConn(host: String, port: Int, database: String, user: String,
    passwd: String)
  object PostgresqlConn {
    implicit val encodePostgresqlConn: Encoder[PostgresqlConn] = new Encoder[PostgresqlConn] {
      def apply(x: PostgresqlConn): Json = Json.obj(
        ("host", Json.fromString(x.host)),
        ("port", Json.fromInt(x.port)),
        ("database", Json.fromString(x.database)),
        ("user", Json.fromString(x.user)),
        ("passwd", Json.fromString(x.passwd))
      )
    }
  }
  lazy val postgresConnection = PostgresqlConn(host = PostgresqlHost(), port = PostgresqlPort(),
    database = PostgresqlDatabase(), user = PostgresqlUser(), passwd = PostgresqlPasswd())
}
