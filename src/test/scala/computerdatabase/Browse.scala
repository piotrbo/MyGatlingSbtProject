package computerdatabase;

import io.gatling.core.Predef._;
import io.gatling.http.Predef._;

object Browse {
  val browse = exec(Home.home).repeat(3, "n"){exec(gotoPage("${n}"))}

  def gotoPage(nr: String)  = exec(http("GET Page " + nr).get("/computers?p=" + nr))
    .pause(1)
}
