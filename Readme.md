#  API Testing for 'Petstore' website

Click [here](https://petstore.swagger.io/) to take a look at the website!


## Technologies used in the project:

[<img alt="Java" height="70" src="images/logo/Java.svg" width="70"/>](https://www.java.com/)
[<img alt="IDEA" height="70" src="images/logo/Idea.svg" width="70"/>](https://www.jetbrains.com/idea/)
[<img alt="Github" height="70" src="images/logo/GitHub.svg" width="70"/>](https://github.com/)
[<img alt="JUnit 5" height="70" src="images/logo/Junit5.svg" width="70"/>](https://junit.org/junit5/)
[<img alt="Gradle" height="70" src="images/logo/Gradle.svg" width="70"/>](https://gradle.org/)
[<img alt="Rest-assured" height="70" src="images/logo/rest-assured-logo.svg" width="70"/>](https://rest-assured.io/)
[<img alt="Allure" height="70" src="images/logo/Allure.svg" width="70"/>](https://github.com/allure-framework/allure2)
[<img alt="Jenkins" height="70" src="images/logo/Jenkins.svg" width="70"/>](https://www.jenkins.io/)
[<img alt="Allure_EE" height="70" src="images/logo/Allure_EE.svg" width="70"/>](https://qameta.io/)


## What is special about this project:

✓ RestAssured library

✓ Specifications

✓ Lombok models

✓ Request log custom templates


## To run tests locally use:

```
gradle clean test 
```


## Test cases

✓ Add a new pet to the store

✓ Find pet by ID

✓ Find pet by status

✓ Delete a pet

✓ Find non-existing pet by ID


## Jenkins Job

Jenkins is an automation server which lets us run tests

Click <a target="_blank" href="https://jenkins.autotests.cloud/job/11-Mara_dol-api/">here</a> to see the job for "Petstore"

<p align="center">
<img title="Jenkins job" src="images/screenshot/jenkinsApi.PNG">
</p>

## Allure report

Allure Report is a flexible, lightweight test reporting tool. It provides clear graphical reports and allows extracting 
the maximum of information from the everyday testing process.

Click <a target="_blank" href="https://jenkins.autotests.cloud/job/11-Mara_dol-api/2/allure/">here</a> to see the report for "Petstore"


### There are test suites

<p align="center">
<img title="Allure Test Suites" src="images/screenshot/allureTestSuites.PNG">
</p>


### There are graphs

<p align="center">
<img title="Allure Graphs" src="images/screenshot/allureGraphs.PNG">
</p>


## Telegram Notification

After the tests are completed, the report comes to Telegram using a bot

