# entain-assignment

#  Entain  API Automation Framework

##  Overview
This project automates testing of the **Swagger Petstore API** `/pet` endpoints, designed as part of the **Principle SDET / SDET III Pre-Interview Task**.

The goal is to demonstrate advanced API automation design and implementation skills — with emphasis on **reusability**, **maintainability**, **data-driven validation**, and **clean environment handling**.

---

##  Tech Stack

For this project use Java and cucumber beacuse specfloe is no longer avilable after dece,ber 31st 2024

| Component | Description |
|------------|-------------|
| **Language** | Java 11+ |
| **Build Tool** | Maven |
| **Test Framework** | Cucumber (BDD) with TestNG |
| **HTTP Client** | Rest-Assured |
| **Data Generator** | Java Faker |
| **Schema Validation** | JSON Schema Validator |
| **Synchronization** | Awaitility |
| **Assertions** | Hamcrest |
| **Reporting** | Built-in Cucumber HTML & JSON Reports |

---

## Project Structure


EntainAssignment/
├── pom.xml                          # Maven project definition
├── README.md                        # Project documentation
├── testng.xml                       # TestNG configuration file
├── cucumber.json                    # JSON report
├── cucumber-report.html             # HTML execution report
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── org/petstore/
│   │   │       ├── clients/         # REST clients for API endpoints
│   │   │       │   └── PetClient.java
│   │   │       ├── config/
│   │   │       │   └── ConfigManager.java
│   │   │       ├── models/          # POJOs representing API payloads
│   │   │       │   ├── Category.java
│   │   │       │   ├── Pet.java
│   │   │       │   └── Tag.java
│   │   │       └── utils/           # Utilities and helpers
│   │   │           ├── ScenarioContext.java
│   │   │           └── TestDataGenerator.java
│   │   └── resources/
│   │       └── config.properties    # Base URL and environment configs
│   │
│   └── test/
│       ├── java/
│       │   └── org/entain/tests/
│       │       ├── hooks/           # Global test setup and teardown
│       │       │   └── Hooks.java
│       │       ├── retry/           # Retry mechanism for failed tests
│       │       │   ├── RetryAnalyzer.java
│       │       │   └── RetryTransformer.java
│       │       ├── runners/         # Cucumber TestNG runner
│       │       │   └── TestNGCucumberRunnerTest.java
│       │       └── stepdefinitions/ # Cucumber step definitions
│       │           ├── CreatePetSteps.java
│       │           ├── DeletePetSteps.java
│       │           ├── ErrorHandlingSteps.java
│       │           ├── FindPetSteps.java
│       │           └── UpdatePetSteps.java
│       │
│       └── resources/
│           ├── features/             # Gherkin feature files
│           │   ├── CreatePet.feature
│           │   ├── DeletePet.feature
│           │   ├── ErrorHandling.feature
│           │   ├── FindPet.feature
│           │   └── UpdatePet.feature
│           └── schemas/              # JSON Schemas for validation
│               ├── findByStatus-schema.json
│               └── pet-schema.json
 

```


## How to Run the Tests

###  Prerequisites
- Java **11** or higher  
- Maven **3.8+**


###  Setup
1. Clone the repository:
   ```bash
   git clone https://github.com/<your-username>/EntainAssignment.git
2. Run the Command iin Terminal
   ```bash
   mvn clean test "-DsuiteXmlFile=testng.xml"
3. for Retry Flaky tests
   ``` bash
   mvn "-Dsurefire.suiteXmlFiles=testng.xml" "-Dretry.maxAttempts=2" clean test



