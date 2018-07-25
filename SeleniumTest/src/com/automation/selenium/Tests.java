import org.bson.Document;
import org.json.JSONArray;
import org.testng.*;
import org.testng.annotations.*;
import java.lang.reflect.Method;
import java.util.Random;

@Listeners(Listener.class)
public class Tests {

    // Test data
    public final String SEARCH_VALUE_SELENIUM = "selenium";
    public String searchValueWhy = "почему гит такой сложный";
    public String expectedTitleGitHate = "Почему я ненавижу Git или Git не должен быть таким сложным для изучения";
    public String expectedTitleAltGoogle = "Google";

    // Error messages
    public String altTextIncorrect = "Alternative text is incorrect";
    public String searchValueIncorrect = "Search field value is incorrect";

    // WebDriver wrapper
    public Driver driver;
    public Screenshot screenshot;

    //@Rule public TestName name = new TestName();


    @DataProvider (name="SearchValues")
    private static Object[] getSearchValues() {
        return new Object[] { "jmeter", "selenium"};
    }

    @BeforeMethod
    public void setUp (Method method) {
        driver = new Driver(BrowserType.FIREFOX, method.getName());
        driver.get("https://google.com");
    }

    @Test
    public void mongoTest() {
        Mongo mongo = Mongo.getInstance("192.168.1.57", "automation");
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
        screenshot = new Screenshot(driver, method.getName());
        screenshot.takeScreenshot();
        try {
            driver.close();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
}
