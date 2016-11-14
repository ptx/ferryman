package ferryman

import io.finch._
import models.{PostgresqlStatus, Status}

object endpoints {

  def Show: Endpoint[String] = get("ping") { 
    Ok("!!! PONG !!!") 
  }

  def ShowStatus: Endpoint[Status] = get("status.json") {
    for {
      version   <- Postgresql.SelectVersion(())
      timestamp <- Postgresql.SelectStartTimestamp(())
      pgStatus  = PostgresqlStatus(version, timestamp.toOffsetDateTime)
    } yield Ok(Status(pgStatus))
  }
}
