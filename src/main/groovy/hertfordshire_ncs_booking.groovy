import geb.Browser
import geb.module.RadioButtons
import geb.module.TextInput
import org.openqa.selenium.chrome.ChromeDriver

System.setProperty("webdriver.chrome.driver", "/Users/gajendrabisht/Downloads/chromedriver")

def browser = new Browser(driver: new ChromeDriver())
try {

    // go to home page
    browser.go "https://secure1.zipporah.co.uk/Registrars.Hertfordshire.Live/NCSBookingProcess"
    browser.waitFor { browser.$("#QACollection_0__Answer") }

    // select nationality
    browser.$('#QACollection_0__Answer').click()
    browser.$('#QACollection_0__Answer').find("option").find { it.value() == "97" }.click()
    browser.waitFor { browser.$(name: "QACollection[1].Answer") }

    // no. of adults = 1
    browser.$(name: "QACollection[1].Answer").module(TextInput).text = "1"
    browser.waitFor { browser.$("#btnAnswer") }

    // click submit
    browser.$("#btnAnswer").click()
    browser.waitFor { browser.$("#NCS_PassportChecking") }

    // select joint booking
    browser.$("#NCS_PassportChecking").$(name: "QACollection[0].Answer").module(RadioButtons).checked = "0"
    Thread.sleep(1000)
    browser.waitFor { browser.$("#Continue") }

    // next page
    browser.$("#Continue").click()

    // new page
    Thread.sleep(2000)

    // select hatfield centre
    browser.$("form").ResourceCategory = "120"
    Thread.sleep(2000)
    browser.waitFor { browser.$("#resourceResults") }
    Thread.sleep(2000)

    // try different dates
//    [5,6,7,8,12,13,14,15,16].each{
    browser.$("div.lg-col-8").$("#Date").module(TextInput).text = "05/02/2018"
    Thread.sleep(5000)
    browser.$("div.lg-col-8").$("#calendarSubmit").parent().click()
    Thread.sleep(5000)
//        browser.$("a.ui-state-default", text:"$it").parent().click()
//        Thread.sleep(5000)

    browser.waitFor { browser.$("#resourceResults") }
    if (browser.$("#resourceResults").$("input#bookButton0")) {
        Thread.sleep(50000000)
    }
//    }
    Thread.sleep(5000000)

} catch (Throwable ex) {
    ex.printStackTrace()
} finally {
    browser.quit()
}
