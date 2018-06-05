import org.junit.*;
import org.junit.rules.TestName;

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

    @Rule public TestName name = new TestName();

    @Before
    public void setUp () {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        driver = new Driver(BrowserType.FIREFOX);
        driver.get("https://google.com");
        screenshot = new Screenshot(driver, methodName);
    }

    @Test
    public void searchFieldTest() {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        screenshot.setFileName(methodName);
        SearchPage searchPage = new SearchPage(driver);
        searchPage.setSearchFieldEnter(SEARCH_VALUE_SELENIUM);
        Assert.assertTrue(searchValueIncorrect, searchPage.getSearchFieldValue().equals(SEARCH_VALUE_SELENIUM));
    }

    @Test
    public void firstResultTest() {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        screenshot.setFileName(methodName);
        SearchPage searchPage = new SearchPage(driver);
        searchPage.setSearchFieldEnter(searchValueWhy);
        searchPage.clickFirstResult();
        String pageHeader = searchPage.getPageHeader();
        Assert.assertEquals(expectedTitleGitHate, pageHeader);
    }

    @Test
    public void altTextTitleTest() {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        screenshot.setFileName(methodName);
        SearchPage searchPage = new SearchPage(driver);
        String titleAltText = searchPage.getTitleAltText();
        Assert.assertEquals(expectedTitleAltGoogle, titleAltText);
    }

    @Test
    public void searchResultLinkTest() {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        screenshot.setFileName(methodName);
        SearchPage searchPage = new SearchPage(driver);
        searchPage.setSearchFieldEnter(SEARCH_VALUE_SELENIUM);
        String searchResultLinkText = searchPage.getSecondResultUrl();
        Assert.assertTrue(searchResultLinkText.toLowerCase().contains(SEARCH_VALUE_SELENIUM));
    }

    @After
    public void tearDown() {
        screenshot.takeScreenshot();
        try {
            driver.close();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
}
