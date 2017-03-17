package computerdatabase

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class MySimulation extends Simulation {

	val httpProtocol = http
		.baseURL("http://computer-database.gatling.io")
		.inferHtmlResources(BlackList(""".*\.css""", """.+\.js""", """.+\.ico"""), WhiteList())
		.acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
		.acceptEncodingHeader("gzip, deflate")
		.acceptLanguageHeader("en-US,en;q=0.5")
		.userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.10; rv:47.0) Gecko/20100101 Firefox/47.0")


	val userScenario = scenario("userScenario").exec(Search.searchByFilterThenSelectOne, Browse.browse)

	val adminScenario = scenario("adminScenario").exec(Search.searchByFilterThenSelectOne, Browse.browse).exec(Edit.edit)

	setUp(
		userScenario.inject(rampUsers(5)  over 5),
		adminScenario.inject(atOnceUsers(1))
	).protocols(httpProtocol)
}