package com.oneeyedmen.konsent

import com.natpryce.hamkrest.equalTo
import com.oneeyedmen.konsent.webdriver.*
import org.junit.runner.RunWith
import java.net.URI

//README_TEXT
@RunWith(Konsent::class)
@Preamble(
    "As a developer named Duncan",
    "I want to know that example.com is up and running")
class KonsentExampleTests : ChromeAcceptanceTest() {

    val duncan = actorNamed("Duncan")

    @Scenario(1) fun `Example_dot_com loads`() {
        Given(duncan).loadsThePageAt("http://example.com")
        Then(duncan) {
            shouldSee(::`the page location`, pathContains("example.com"))
            shouldSee(::`the page title`, equalTo("Example Domain"))
            shouldSee(::`the page content`, containsALink("More information...", "http://www.iana.org/domains/example"))
        }
    }

    @Scenario(2, "Following a link from example.com") fun cant_have_dots_in_quoted_method_names() {
        Given(duncan).loadsThePageAt("http://example.com")
        When(duncan).followsTheLink("More information...", "http://www.iana.org/domains/example")
        Then(duncan).shouldSee(::`the page location`, equalTo(URI("http://www.iana.org/domains/reserved")))
    }

    @Scenario(3) fun `Dispensing with the given when then`() {
        duncan.he.loadsThePageAt("http://example.com")
        duncan.he.followsTheLink("More information...", "http://www.iana.org/domains/example")
        duncan.shouldSee(::`the page location`, equalTo(URI("http://www.iana.org/domains/reserved")))
    }
}
//README_TEXT