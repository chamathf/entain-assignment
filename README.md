# entain-assignment

#  Entain  API Automation Framework

##  Overview
This project automates testing of the **Swagger Petstore API** `/pet` endpoints, designed as part of the **Principle SDET / SDET III Pre-Interview Task**.

The goal is to demonstrate advanced API automation design and implementation skills — with emphasis on **reusability**, **maintainability**, **data-driven validation**, and **clean environment handling**.

---

##  Tech Stack
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
├── pom.xml                             # Maven project definition  
├── README.md                           # Project documentation  
├── .gitignore                          # Ignore build and IDE artifacts  
│
├── src/  
│   ├── main/  
│   │   ├── java/  
│   │   │   └── org/petstore/  
│   │   │       ├── clients/             # REST clients for API endpoints  
│   │   │       │   └── PetClient.java  
│   │   │       ├── models/              # POJOs representing API payloads  
│   │   │       │   ├── Pet.java  
│   │   │       │   ├── Category.java  
│   │   │       │   └── Tag.java  
│   │   │       └── utils/               # Utilities and helpers  
│   │   │           ├── ScenarioContext.java  
│   │   │           ├── TestDataGenerator.java  
│   │   │           └── ConfigReader.java  
│   │   └── resources/  
│   │       └── config.properties        # Base URL and environment configs  
│
│   └── test/  
│       ├── java/  
│       │   └── org/petstore/tests/  
│       │       ├── hooks/               # Global test setup and teardown  
│       │       │   └── Hooks.java  
│       │       ├── stepdefinitions/     # Cucumber step definitions  
│       │       │   ├── CreatePetSteps.java  
│       │       │   ├── UpdatePetSteps.java  
│       │       │   ├── FindPetsByStatusSteps.java  
│       │       │   ├── DeletePetSteps.java  
│       │       │   └── ErrorHandlingSteps.java  
│       │       └── runners/             # Cucumber TestNG runner  
│       │           └── CucumberTestRunner.java  
│       │
│       └── resources/  
│           ├── features/                # Gherkin feature files  
│           │   ├── CreatePet.feature  
│           │   ├── UpdatePet.feature  
│           │   ├── FindByStatus.feature  
│           │   ├── DeletePet.feature  
│           │   └── ErrorHandling.feature  
│           └── schemas/                 # JSON Schemas for validation  
│               ├── pet-schema.json  
│               └── findByStatus-schema.json  
│
└── target/  
    ├── cucumber-report.html             # HTML execution report  
    ├── cucumber.json                    # JSON report  
    └── surefire-reports/                # Maven TestNG reports  

```


---

## How to Run the Tests

###  Prerequisites
- Java **11** or higher  
- Maven **3.8+**
- Internet access (for hitting the live Swagger Petstore API)

###  Setup
1. Clone the repository:
   ```bash
   git clone https://github.com/<your-username>/EntainAssignment.git
   cd EntainAssignment


