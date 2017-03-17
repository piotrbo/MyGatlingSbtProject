package computerdatabase

import io.gatling.core.Predef._
import io.gatling.http.Predef._

/**
  * Created by piotrbo on 17/03/17.
  */
object Search {

  val feeder = csv("search.csv").random;

  val searchByFilter = exec(Home.home)
    .feed(feeder).exec(http("GET /computers?f=").get("/computers?f=${searchCriterion}")
    .check(css("a:contains('${searchComputerName}')", "href").saveAs("computerURL"))
    //    .check(xpath("//a[text()='${searchComputerName}']"))//SAXParseException; lineNumber: 17; columnNumber: 7; The element type "link" must be terminated by the matching end-tag "</link>".
  )

  val searchByFilterThenSelectOne = searchByFilter
    .pause(1)
    .exec(http("GET computer").get("${computerURL}"))
    .pause(1)

}
