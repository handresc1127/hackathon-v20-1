# Hackathon v20-1
Cross Browser Testing.\
Applitools Hackathon

The next table compares some attributes to consider while choosing between 
a traditional or modern approach.

| Attribute| Traditional | Modern |
| ----------- | ----------- |----------- |
| QA Seniority require | Semi Senior | Junior |
| Maintainability | Low | High |
| Running Time | 183 secs | 97 secs |
| Code Lines | 885 | 248 |
| # Locators | 37 | 5 |
| Complexity | High | Low |
| Spent time | 84h | 3h |


## Starting 🚀

### WebDriver Factory
In the traditional testing methods apply the Factory Pattern. 
Eviting problems while designing the automated tests, these 
design patterns could immediately help by proving a formula /
template & saves a lot of time and effort.

### Data Driven
Traditional cross browsing tests must have parameters as the 
browser type. For that reason, instructed TestNG to pass you 
the browser type with the `@Parameters` annotation and a testng.xml file.

### Page Object
Page Object pattern was used for the test implementation which
 goal was to model the pages and their behaviors to achieve clear
  test of writing, understanding, avoid duplicate code, and easy
   to maintenance.

### Project Tree
```
\---src
    \---test
        +---java
        |   \---com
        |       \---handresc1127
        |           |   ModernTestsV1.java
        |           |   ModernTestsV2.java
        |           |   TraditionalTestsV1.java
        |           |   TraditionalTestsV2.java
        |           |
        |           +---pages
        |           |       detailsPage.java
        |           |       headerPage.java
        |           |       homePage.java
        |           |
        |           \---utils
        |               |   BaseTests.java
        |               |   EyesManager.java
        |               |   HackathonReports.java
        |               |   PropertyLoader.java
        |               |
        |               \---webDriverFactory
        |                       ChromeDriverManager.java
        |                       DriverManager.java
        |                       DriverManagerFactory.java
        |                       EdgeDriverManager.java
        |                       FirefoxDriverManager.java
        |
        \---resources
            |   author.png
            |   test.properties
            |   testngModernV1.xml
            |   testngModernV2.xml
            |   testngTraditionalV1.xml
            |   testngTraditionalV2.xml
            |
            \---drivers
                +---linux
                |       chromedriver
                |       geckodriver
                |
                +---mac
                |       chromedriver
                |       geckodriver
                |       msedgedriver
                |
                \---windows
                        chromedriver.exe
                        geckodriver.exe
                        msedgedriver.exe
```

## Pre-requirements 📋

1. Chrome V80+
2. Firefox V75.0+
3. Microsoft Edge V83+
4. Java 11
5. Config src\test\resources\test.properties

## Install 🔧

```
1. git clone
2. mvn clean install 
```

## Tests Run ⚙️

...


## Build 🛠️

### _WebDrivers_
* Google Chrome   - [ChromeDriver](https://chromedriver.chromium.org/downloads) 83.0.4103.39
* Mozilla Firefox - [GeckoDriver](https://github.com/mozilla/geckodriver/releases)  0.26.0
* Microsoft Edge  - [MsEdgeDriver](https://developer.microsoft.com/en-us/microsoft-edge/tools/webdriver/) 83.0.478.50

### _Tools_
* [Maven](https://maven.apache.org/) - Dependency manager
* [TestNG](https://testng.org/) - Testing Framework

## Author ✒️

* **Henry Andrés Correa Correa** - [Linkedin](https://www.linkedin.com/in/henryandrescorrea/) -  [h.andresc1127@gmai.com](mailto:h.andresc1127@gmai.com) \
![author](src/test/resources/author.png "Henry Andres Correa Correa")

### Hackathon2019
![GoldWinner](src/test/resources/goldWinner.jpg "Hackathon 2019")