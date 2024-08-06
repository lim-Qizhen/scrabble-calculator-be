# scrabble-calculator-be

## Project Description

Some simple endpoints to calculate scrabble word score and save them

## Table of Contents

- [Getting Started](#getting-started)
- [Backend (Kotlin)](#backend-kotlin)
- [Usage](#usage)

## Getting Started

### Prerequisites

- Java JDK v21.X.X
- Kotlin v1.9.24

### Installation

1. Clone the backend repo
    ```sh
    git clone https://github.com/lim-Qizhen/scrabble-calculator-be
    ```
2. Prepare docker for postgres database

   Refer to https://hub.docker.com/_/postgres to use the postgresql image. Create and note down your username and password


3. Get api key for dictionary api

   Obtain an API key from https://api-ninjas.com/


4. Prepare application-local and application-test files

   a. Create application-local.yml file in
    - main
        - resources

   with the following fields

   ```yaml
   spring:
     datasource:
       url: jdbc:postgresql://localhost:5432/postgres
       username: <your username from step 2>
       password: <your password from step 2>
       driver-class-name: org.postgresql.Driver
   web:
      cors:
        allowed-origins: "http://localhost:3000" #if you want to call it from the frontend
   
   api:
      api-ninja:
        base-url: https://api.api-ninjas.com/v1
        key: <your api key from step 3>
   ```
   b. Create application-test.yml file in
    - test
        - resources

   with the following fields
   ```yaml
   api:
      api-ninja:
        key: <your api key from step 3>
   ```
5. Install dependencies for backend

   ```sh
   ./gradlew build'
   ```

## Backend (Kotlin)

### Setup

To start the backend development server:

```sh
./gradlew bootRun --args='--spring.profiles.active=local
```

## Usage

To use the application, navigate to http://localhost:8000/swagger-ui/index.html# for the swagger page as below

![Screenshot 2024-08-06 at 4 05 17 PM](https://github.com/user-attachments/assets/f9d3e0ee-9950-4083-9f14-50cc3cc2852b)

