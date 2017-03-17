package computerdatabase

import io.gatling.core.Predef._
import io.gatling.http.Predef._;

object Home {
  val home = exec(http("GET main page").get("/")).pause(1)
}
