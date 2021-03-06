package com.automation.tests;

import com.automation.driver.Driver;
import com.automation.pages.SearchPage;
import com.automation.tests.Listener;
import com.automation.utils.DB;
import com.automation.utils.HttpRequest;
import com.automation.utils.Json;
import com.automation.utils.Mongo;
import org.bson.Document;
import org.testng.*;
import org.testng.annotations.*;
import java.lang.reflect.Method;

@Listeners(Listener.class)
public class Tests {

    // Test data
    public final String SEARCH_VALUE_SELENIUM = "selenium";
    public String searchValueWhy = "why git is so complicated";
    public String expectedTitleGitHate = "why do i hate git";
    public String expectedTitleAltGoogle = "Google";

    // Error messages
    public String altTextIncorrect = "Alternative text is incorrect";
    public String searchValueIncorrect = "Search field value is incorrect";

    // WebDriver wrapper
    public Driver driver;


    @DataProvider (name="SearchValues")
    private static Object[] getSearchValues() {
        return new Object[] { "jmeter", "selenium"};
    }

    @BeforeMethod
    public void setUp (Method method, ITestResult testResult) {
        driver = new Driver(method.getName());
        driver.get("https://google.com");
        testResult.setAttribute("driver", driver);
    }


    @Ignore
    @Test
    public void mongoTest() {
        Mongo mongo = new Mongo("192.168.1.57", "automation");
        for (Document doc : mongo.getDocuments("id",1)) {
            System.out.println(doc.getString("name"));
        }
    }

    @Ignore
    @Test
    public void sqlTest() {
        String user = "sa";
        String password = "123123ok";
        String server = "192.168.202.1";
        DB db = new DB(server, user, password);
        db.executeQuery("select * from automation.dbo.names");
    }

    @Ignore
    @Test
    public void jsonTest() {
        Json json = new Json("test_data.json");
        String value = json.getString("test_key_single");
        int elementZero = json.getArray("test_key_array")[0];
        System.out.println(elementZero);
        String email = new HttpRequest("https://jsonplaceholder.typicode.com/comments").getEmail(0);
        System.out.println(email);
    }

    @Test (dataProvider = "SearchValues")
    public void searchFieldTest(String searchValue) {
        SearchPage searchPage = new SearchPage(driver);
        searchPage.setSearchFieldEnter(searchValue);
        Assert.assertTrue(searchPage.getSearchFieldValue().equals(searchValue), searchValueIncorrect);
    }

    @Test
    public void jsTest() {
        driver.webDriver.get("https://hotline.ua/computer/noutbuki-netbuki/");
        driver.execJs("for (var i=0; i < 10; i++) { document.getElementsByClassName('check')[i].click(); }");
        Assert.fail();
    }

    @Ignore
    @Test
    public void firstResultTest() {
        SearchPage searchPage = new SearchPage(driver);
        searchPage.setSearchFieldEnter(searchValueWhy);
        searchPage.clickFirstResult();
        String pageHeader = searchPage.getPageHeader();
        Assert.assertEquals(expectedTitleGitHate, pageHeader);
    }

    @Ignore
    @Test
    public void altTextTitleTest() {
        SearchPage searchPage = new SearchPage(driver);
        String titleAltText = searchPage.getTitleAltText();
        Assert.assertEquals(expectedTitleAltGoogle, titleAltText);
    }

    @Ignore
    @Test
    public void searchResultLinkTest() {
        SearchPage searchPage = new SearchPage(driver);
        searchPage.setSearchFieldEnter(SEARCH_VALUE_SELENIUM);
        String searchResultLinkText = searchPage.getSecondResultUrl();
        Assert.assertTrue(searchResultLinkText.toLowerCase().contains(SEARCH_VALUE_SELENIUM));
    }

    @AfterMethod
    public void tearDown(Method method) {
        try {
            driver.close();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
}
