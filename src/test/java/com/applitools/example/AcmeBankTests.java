package com.applitools.example;
import com.applitools.eyes.*;
import com.applitools.eyes.fluent.BatchClose;
import com.applitools.eyes.selenium.BrowserType;
import com.applitools.eyes.selenium.Configuration;
import com.applitools.eyes.selenium.Eyes;
import com.applitools.eyes.selenium.fluent.Target;
import com.applitools.eyes.visualgrid.model.ChromeEmulationInfo;
import com.applitools.eyes.visualgrid.model.DesktopBrowserInfo;
import com.applitools.eyes.visualgrid.model.DeviceName;
import com.applitools.eyes.visualgrid.model.ScreenOrientation;
import com.applitools.eyes.visualgrid.services.RunnerOptions;
import com.applitools.eyes.visualgrid.services.VisualGridRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AcmeBankTests {

    private final static BatchInfo BATCH = new BatchInfo("ACME Login - functional vs visual");

    // Soft-assertion log: every functional check is recorded, failures don't stop the run
    private static final List<String> FAILURES = new ArrayList<>();
    private static int checks = 0;

    public static void main(String [] args) {

        EyesRunner runner = null;
        Eyes eyes = null;
        WebDriver driver = null;

        try {
            // Configure Applitools SDK to run on the Ultrafast Grid
            BATCH.addProperty("TestingProperty", "myfirstpropertyadded");
            BATCH.setSequenceName("ACME Login Nightly");
            BATCH.setId("0bb869f6-115e-4a81-89cb-492d78f069");
            BATCH.setNotifyOnCompletion(true);
            runner = new VisualGridRunner(new RunnerOptions().testConcurrency(5));
//            runner.setDontCloseBatches(true);
            eyes = new Eyes(runner);
            Configuration config = eyes.getConfiguration();
            config.setApiKey(System.getenv("APPLITOOLS_API_KEY"));
            config.setBatch(BATCH);
            config.setMatchLevel(MatchLevel.LAYOUT);
//            config.setBranchName("MyFirstBranchName");
            config.addBrowsers(
                    new DesktopBrowserInfo(800, 1024, BrowserType.CHROME),
                    new DesktopBrowserInfo(1600, 1200, BrowserType.FIREFOX),
                    new DesktopBrowserInfo(1024, 768, BrowserType.SAFARI),
                    new ChromeEmulationInfo(DeviceName.Pixel_2, ScreenOrientation.PORTRAIT),
                    new ChromeEmulationInfo(DeviceName.Nexus_10, ScreenOrientation.LANDSCAPE)
            );
            eyes.setConfiguration(config);
            ChromeOptions options = new ChromeOptions();
            if (Boolean.parseBoolean(System.getenv("HEADLESS"))) {
                options.addArguments("--headless=new");
            }
            driver = new ChromeDriver(options);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

            // Start Applitools Visual AI Test
            eyes.open(driver,"ACME", "ACME- with matchlevel and region", new RectangleSize(1200, 600));
            driver.get("https://demo.applitools.com/");
//            new WebDriverWait(driver, Duration.ofSeconds(10))
//                    .until(ExpectedConditions.visibilityOfElementLocated(By.id("log-in")));
//
//            // =====================================================================
//            // FUNCTIONAL UI VALIDATION (traditional assertions)
//            // =====================================================================
//            JavascriptExecutor js = (JavascriptExecutor) driver;
//
//            // ---------- Page ----------
//            check("Page title", "ACME Demo App by Applitools".equals(driver.getTitle()));
//
//            // ---------- Logo ----------
//            WebElement logo = driver.findElement(By.cssSelector("img[src*='logo-big.png']"));
//            check("Logo visible", logo.isDisplayed());
//            check("Logo image loaded", isImageLoaded(js, logo));
//
//            // ---------- Header ----------
//            WebElement header = driver.findElement(By.xpath("//h4[normalize-space()='Login Form']"));
//            check("'Login Form' header visible", header.isDisplayed());
//            check("'Login Form' header text", "Login Form".equals(header.getText().trim()));
//
//            // ---------- Username ----------
//            WebElement usernameLabel = driver.findElement(By.xpath("//label[normalize-space()='Username']"));
//            check("Username label visible", usernameLabel.isDisplayed());
//            WebElement username = driver.findElement(By.id("username"));
//            check("Username field visible", username.isDisplayed());
//            check("Username field enabled", username.isEnabled());
//            check("Username placeholder", "Enter your username".equals(username.getAttribute("placeholder")));
//            check("Username empty by default", "".equals(username.getAttribute("value")));
//            check("Username icon visible", isVisible(driver, By.cssSelector(".os-icon-user-male-circle")));
//
//            // ---------- Password ----------
//            WebElement passwordLabel = driver.findElement(By.xpath("//label[normalize-space()='Password']"));
//            check("Password label visible", passwordLabel.isDisplayed());
//            WebElement password = driver.findElement(By.id("password"));
//            check("Password field visible", password.isDisplayed());
//            check("Password field enabled", password.isEnabled());
//            check("Password is masked", "password".equals(password.getAttribute("type")));
//            check("Password placeholder", "Enter your password".equals(password.getAttribute("placeholder")));
//            check("Password icon visible", isVisible(driver, By.cssSelector(".os-icon-fingerprint")));
//
//            // ---------- Sign in button ----------
//            WebElement signIn = driver.findElement(By.id("log-in"));
//            check("Sign in visible", signIn.isDisplayed());
//            check("Sign in enabled", signIn.isEnabled());
//            check("Sign in text", "Sign in".equals(signIn.getText().trim()));
//            check("Sign in has primary style", signIn.getAttribute("class").contains("btn-primary"));
//            String btnColor = Color.fromString(signIn.getCssValue("background-color")).asHex();
//            System.out.println("Sign in background color: " + btnColor);
//            check("Sign in not black", !"#000000".equals(btnColor));
//            check("Sign in text is white",
//                    "#ffffff".equals(Color.fromString(signIn.getCssValue("color")).asHex()));
//
//            // ---------- Remember Me ----------
//            WebElement rememberLabel = driver.findElement(By.xpath("//label[contains(normalize-space(),'Remember Me')]"));
//            check("Remember Me label visible", rememberLabel.isDisplayed());
//            WebElement rememberBox = driver.findElement(By.cssSelector("input[type='checkbox']"));
//            check("Remember Me unchecked by default", !rememberBox.isSelected());
//            rememberBox.click();
//            check("Remember Me can be checked", rememberBox.isSelected());
//            rememberBox.click();
//            check("Remember Me can be unchecked", !rememberBox.isSelected());
//
//            // ---------- Social icons ----------
//            List<WebElement> social = driver.findElements(By.cssSelector("img[src*='social-icons/']"));
//            check("Exactly 3 social icons", social.size() == 3);
//            for (String icon : new String[]{"twitter.png", "facebook.png", "linkedin.png"}) {
//                List<WebElement> found = driver.findElements(By.cssSelector("img[src*='social-icons/" + icon + "']"));
//                check(icon + " present", !found.isEmpty());
//                if (!found.isEmpty()) {
//                    check(icon + " visible", found.get(0).isDisplayed());
//                    check(icon + " loaded", isImageLoaded(js, found.get(0)));
//                }
//            }
//
//            // ---------- No broken images anywhere ----------
//            for (WebElement img : driver.findElements(By.tagName("img"))) {
//                check("Image loaded: " + img.getAttribute("src"), isImageLoaded(js, img));
//            }
//
//            // ---------- Layout / order ----------
//            check("Logo above header", logo.getLocation().getY() < header.getLocation().getY());
//            check("Header above username", header.getLocation().getY() < username.getLocation().getY());
//            check("Username above password", username.getLocation().getY() < password.getLocation().getY());
//            check("Password above Sign in", password.getLocation().getY() < signIn.getLocation().getY());
//            if (!social.isEmpty()) {
//                check("Sign in above social icons", signIn.getLocation().getY() < social.get(0).getLocation().getY());
//            }
//            check("Username/Password left-aligned",
//                    Math.abs(username.getLocation().getX() - password.getLocation().getX()) <= 2);
//            check("Username/Password same width",
//                    Math.abs(username.getSize().getWidth() - password.getSize().getWidth()) <= 2);
//            check("Remember Me right of Sign in",
//                    rememberLabel.getLocation().getX() > signIn.getLocation().getX());
//            double btnMid = signIn.getLocation().getY() + signIn.getSize().getHeight() / 2.0;
//            double remMid = rememberLabel.getLocation().getY() + rememberLabel.getSize().getHeight() / 2.0;
//            check("Remember Me aligned with Sign in", Math.abs(btnMid - remMid) <= 15);
//            if (social.size() == 3) {
//                check("Social icons left to right",
//                        social.get(0).getLocation().getX() < social.get(1).getLocation().getX()
//                                && social.get(1).getLocation().getX() < social.get(2).getLocation().getX());
//            }
//            Long scrollWidth = (Long) js.executeScript("return document.documentElement.scrollWidth;");
//            Long clientWidth = (Long) js.executeScript("return document.documentElement.clientWidth;");
//            check("No horizontal overflow", scrollWidth <= clientWidth);
//
//            // ---------- Functional summary ----------
//            System.out.println("\n===== Functional UI checks: " + (checks - FAILURES.size()) + "/" + checks + " passed =====");
//            FAILURES.forEach(f -> System.out.println("  FAILED: " + f));

            // =====================================================================
            // VISUAL AI VALIDATION - one line covers everything above and more
            // =====================================================================
            eyes.check(
                    Target.window().fully().withName("Main page")); // strict, button as layout region

//            eyes.check("Main page with dynamic",
//                    Target.window().fully().matchLevel(MatchLevel.DYNAMIC));
//
//            eyes.check("Main page with layout",
//                    Target.window().fully().matchLevel(MatchLevel.LAYOUT));
//
//            eyes.check("Main page with ignore colors",
//                    Target.window().fully().matchLevel(MatchLevel.IGNORE_COLORS));

            // ---------- Sign-in flow ----------
//            username.sendKeys("Chris");
//            password.sendKeys("CorrectHorseBatteryStaple");
//            signIn.click();
//            new WebDriverWait(driver, Duration.ofSeconds(10))
//                    .until(ExpectedConditions.urlContains("app.html"));
//            check("Navigates to app.html after Sign in", driver.getCurrentUrl().contains("app.html"));
//
//            eyes.check(Target.window().fully().withName("Dashboard"));

            // End Applitools Eyes Visual AI Test
            eyes.closeAsync();
            String batchId = BATCH.getId();
            BatchClose batchClose = new BatchClose();
            batchClose.setBatchId(Collections.singletonList(batchId)).close();
        }
        catch (Exception e) {
            e.printStackTrace();
            if (eyes != null)
                eyes.abortAsync();
        } finally {
            if (driver != null)
                driver.quit();

            boolean failed = !FAILURES.isEmpty();
            if (runner != null) {
//                TestResultsSummary allTestResults = runner.getAllTestResults(false);
//                for (TestResultContainer container : allTestResults.getAllResults()) {
//                    if (container.getTestResults() != null && !container.getTestResults().isPassed()) {
//                        failed = true;
//                    }
//                }
            }
            System.exit(failed ? 1 : 0);
        }
    }

    // ---------- Helpers ----------
    private static void check(String name, boolean condition) {
        checks++;
        if (condition) {
            System.out.println("PASS: " + name);
        } else {
            System.out.println("FAIL: " + name);
            FAILURES.add(name);
        }
    }

    private static boolean isVisible(WebDriver driver, By locator) {
        List<WebElement> els = driver.findElements(locator);
        return !els.isEmpty() && els.get(0).isDisplayed();
    }

    private static boolean isImageLoaded(JavascriptExecutor js, WebElement img) {
        Object result = js.executeScript("return arguments[0].complete && arguments[0].naturalWidth > 0;", img);
        return Boolean.TRUE.equals(result);
    }
}