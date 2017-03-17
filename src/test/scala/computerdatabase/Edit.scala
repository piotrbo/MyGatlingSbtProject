package computerdatabase

import java.util.concurrent.ThreadLocalRandom

import io.gatling.core.Predef._
import io.gatling.http.Predef._;

object Edit {
    val edit = exec(http("GET /computers/new")
		.get("/computers/new"))
		.pause(1)
      .tryMax(2){
		exec(http("POST /computers")
      .post("/computers")
      .formParam("name", "ZX Spectrum +")
      .formParam("introduced", "1990-04-01")
      .formParam("discontinued", "1995-04-01")
      .formParam("company", "23")
    )//      .check(status.is(session => 200 + ThreadLocalRandom.current().nextInt(2))))
      }
    .exitHereIfFailed

}
