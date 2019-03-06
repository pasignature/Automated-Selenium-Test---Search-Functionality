Automation Test for Search Functionality using Selenium and Java.
===

[![CircleCI](https://circleci.com/gh/pasignature/qaseleniumjava.svg?style=svg)](https://circleci.com/gh/pasignature/qaseleniumjava)

This repository is a very small demonstration of using selenium webdriver and java binding to run automated test in Chrome web Browser. This repo is deployed on the CircleCI continuous integration platform.

Discussion
---

I used the following technologies: Selenium 2, TestNG, Java, Gradle, and Android Studio IDE. I created the project from scratch in Android Studio IDE and pushed it to version control system, github. I wrote the scripts in such a way that it would be easy for another tester to pick it up and continue working on it. I also deployed the test on the CircleCI's continuous integration and delivery platform for ease of running the test in an automated way, after every changes or updates.

Prerequisites
---

- ChromeDriver
- JDK 8+
- Any IDE

Instructions to run test
---

Method one:
1. Clone this repo to your computer.
2. Modify the class searchFunctionalityTest.java to point to ChromeDriver's path on your system.
3. On any terminal, move to the project's root folder and execute the following command:
   ./gradlew clean test

Method two:
1. Clone this repo to your computer.
2. Import project into your favorite IDE.
3. Modify the class searchFunctionalityTest.java to point to ChromeDriver's path on your system.
4. Build and run the test.
   
Enjoy!
