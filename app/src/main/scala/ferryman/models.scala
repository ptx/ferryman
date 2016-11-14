package ferryman

import io.circe.syntax._
import io.circe.{Encoder, Json}
import io.circe.java8.time.encodeOffsetDateTimeDefault
import java.time.OffsetDateTime

object models {

  case class PostgresqlStatus(version: String, startTimestamp: OffsetDateTime)
  object PostgresqlStatus {
    implicit val encodePostgresqlStatus: Encoder[PostgresqlStatus] = new Encoder[PostgresqlStatus] {
      final def apply(ps: PostgresqlStatus): Json = Json.obj(
        ("version", Json.fromString(ps.version)),
        ("start_timestamp", ps.startTimestamp.asJson) //Json.fromString(ps.startTimestamp))
      )
    }
  }

  case class Status(postgresqlStatus: PostgresqlStatus)
  object Status {
    final implicit val encodeStatus: Encoder[Status] = new Encoder[Status] {
      final def apply(s: Status): Json = Json.obj(
        ("postgresql", s.postgresqlStatus.asJson)
      )
    }
  }
}
