# UI tests

![Static Badge](https://img.shields.io/badge/Java-17-brown?style=plastic)
![Static Badge](https://img.shields.io/badge/Maven-3.9.1-blue?style=plastic)
![Static Badge](https://img.shields.io/badge/TestNG-7.7.0-blue?style=plastic)
![Static Badge](https://img.shields.io/badge/Selenium-4.8.0-green?style=plastic)
![Static Badge](https://img.shields.io/badge/Selenide-6.12.4-green?style=plastic)

## Description

This is my pet project with web UI tests. Here are several simple tests that test my favorite digital marketplace [Steam](https://store.steampowered.com). 
They do basic checks : view the game page, description, system requirements, DLS list, screenshots and videos. 
The project has a simple structure : page object classes and few basic tests. The test code is written in Java using Selenium, Selenide and TestNG libraries.Maven was used to build project.

```java
    @Test
    public void test() {
        page.navigateOnMainPage()
                .waitForPageLoaded();

        search = page.getSearch();

        search.type(GAME_NAME)
                .select(1);

        gamePage.waitForPageLoaded()
                .getSearch()
                .isDisplayed(true);

        gamePage.getHeader()
                .isDisplayed(true);

        gamePage.getFooter()
                .isDisplayed(true);

        gamePage
                .checkDescription(GAME_DESCRIPTION)
                .checkRequirements(GAME_REQUIREMENTS)
                .getMediaZone()
                .isDisplayed(true)
                .checkScreenshotsThumbnailsUrls(GAME_SCREENSHOTS_URLS);
    }
```


## Running tests

At the moment these tests can only be run localy, but I'm working on implementing a full Jenkins pipeline running in parallel across multiple browsers inside Docker conteiners(based on Selenoid).


![Screenshot of test run](/resources/assets/uiTestScreenshot.jpg)

