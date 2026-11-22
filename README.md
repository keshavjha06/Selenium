# Selenium Concepts

A collection of Selenium WebDriver practice exercises in Java, one class per concept: locators, waits, dropdowns, frames, alerts, windows, tables, JavaScript execution, and a few end-to-end flows. The tests run on TestNG against public demo and real-world websites.

## Tech stack

| Tool | Version |
|------|---------|
| Java | 25 |
| Selenium Java | 4.53.0 |
| TestNG | 7.12.0 |
| Apache Commons IO | 2.22.0 (used for screenshot file copying) |
| Maven | build and dependency management |

Browser drivers are resolved automatically by **Selenium Manager**, which ships with Selenium 4.6+, so you don't need WebDriverManager or a manual `chromedriver` download.

## Prerequisites

- JDK 25
- Maven 3.9+
- Google Chrome (Firefox is also needed for `EbayLinksCount`)

## Project structure

```
src/test/java/seleniumconcepts/
├── BaseTest.java        # Shared setup: launches Chrome, maximizes, clears cookies; quits after the class
└── *.java               # One exercise per class, each extending BaseTest
src/test/resources/
└── screenshot.png       # Output of the screenshot example in Miscellaneous
```

## Exercises

| Area | Classes | Practice site |
|------|---------|---------------|
| Basics | `SeleniumDemo`, `Miscellaneous` (cookies, screenshots) | google.com, yahoo.com |
| Locators | `Locators1`, `Locators2`, `XpathDemo`, `CSSselectors`, `ParentchildXpath`, `RegularExpression` | facebook.com, login.salesforce.com, rediff.com, qaclickacademy.com |
| Dropdowns | `SelectDropdown`, `UpdatedDropdown`, `Autosuggestive`, `SpicejetDropdown` | rahulshettyacademy.com, spicejet.com |
| Waits & e-commerce | `Ecommerce`, `EcommerceWaits` | rahulshettyacademy.com/seleniumPractise |
| Alerts & popups | `AlertsDemo`, `WindowPopup` (basic auth) | rahulshettyacademy.com, the-internet.herokuapp.com |
| Windows & frames | `WindowHandles`, `FramesDemo`, `NestedFrames` | rahulshettyacademy.com, jqueryui.com, the-internet.herokuapp.com |
| Mouse & keyboard actions | `ActionsDemo` | amazon.com |
| Links & scoping | `ScopeWebdriver`, `BrokenLinks`, `EbayLinksCount` | qaclickacademy.com, rahulshettyacademy.com, ebay.com |
| Tables, sorting & pagination | `TableExercises`, `FilteringDemo`, `PaginationDemo` | cricbuzz.com, rahulshettyacademy.com/greenkart |
| Calendars | `Calendar` | path2usa.com |
| JavaScript executor | `JavascriptExecutorDemo` | rahulshettyacademy.com |
| SSL & file upload | `SSLCertifications`, `FileUpload` | expired.badssl.com, altoconvertpdftojpg.com |
| End-to-end flows | `SpicejetE2E`, `CleartripE2E`, `HerokuExercise`, `Exercise12` | spicejet.com, cleartrip.com, the-internet.herokuapp.com, qaclickacademy.com |

## Running the tests

### From an IDE

Open the project as a Maven project in IntelliJ IDEA or Eclipse with the TestNG plugin, then right-click any class and choose **Run**.

### From the command line

Run a single class:

```bash
mvn test -Dtest=AlertsDemo
```

Run several classes:

```bash
mvn test -Dtest='Locators1,XpathDemo,CSSselectors'
```

A plain `mvn test` won't pick up most of these classes. Maven Surefire only matches names like `*Test` or `Test*` by default, and there is no `testng.xml` suite, so pass `-Dtest=...` to choose what to run.

`EbayLinksCount` is a standalone `main()` program (it uses Firefox and doesn't extend `BaseTest`), so run it directly from your IDE.
