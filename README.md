# Rule Engine with Abstract Syntax Tree (AST)

This project is a Rule Engine that allows users to create, combine, and evaluate rules dynamically. The rules are represented as an Abstract Syntax Tree (AST), and you can evaluate the rules based on specific conditions provided as input.

## Features
- **Create Rule**: Create rules by passing a string representing the logic.
- **Combine Rules**: Combine two rules using operators (AND/OR).
- **Evaluate Rule**: Evaluate rules based on input data provided as JSON.

## Prerequisites
- Java 17 or higher
- Spring Boot 3.0 or higher
- MySQL 8.0 or higher
- Maven

## Getting Started

### Step 1: Clone the repository
First, clone this repository to your local machine:

```bash
git clone https://github.com/your-username/your-repo-name.git
cd your-repo-name
Step 2: Setup MySQL Database
Create a MySQL database for the project. You can name the database as ast_rule_engine or any name of your choice.

sql
Copy code
CREATE DATABASE ast_rule_engine;
Make sure to update the MySQL connection properties in application.properties based on your setup.

properties
Copy code
# application.properties
spring.datasource.url=jdbc:mysql://localhost:3306/ast_rule_engine
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
Step 3: Install Dependencies
Install the required dependencies using Maven. Run the following command in the root of the project directory:

bash
Copy code
mvn clean install
Step 4: Run the Application
To start the Spring Boot application, run the following command:

bash
Copy code
mvn spring-boot:run
The application will be running on http://localhost:8080.

Step 5: Test the API Endpoints
You can test the API endpoints using any REST client (such as Postman) or by using the provided front-end form (index.html).

Create Rule:

Endpoint: POST /api/rules/create
Example Body: "age > 18"
Combine Rules:

Endpoint: POST /api/rules/combine?operator=AND
Example Body:
json
Copy code
{
    "rule1": {
        "id": 1
    },
    "rule2": {
        "id": 2
    }
}
Evaluate Rule:

Endpoint: POST /api/rules/evaluate
Example Body:
json
Copy code
{
    "rule": {
        "id": 1
    },
    "data": {
        "age": 20
    }
}
Step 6: Access the Frontend
You can access the simple HTML form provided in src/main/resources/static/index.html to interact with the backend API:

Open the index.html in your browser (http://localhost:8080/index.html).
Enter rule strings to create, combine, and evaluate rules via the form.
Step 7: Deployment
Build the application with:
bash
Copy code
mvn clean package
Deploy the generated JAR file from the target/ directory to your server:
bash
Copy code
java -jar target/ast-rule-engine-0.0.1-SNAPSHOT.jar
Project Structure
bash
Copy code
src/
├── main/
│   ├── java/com/example/AST/
│   │   ├── controller/          # Contains RuleController
│   │   ├── dto/                 # DTOs for rule requests
│   │   ├── model/               # Node entity representing rule structure
│   │   ├── repository/          # RuleRepository to interact with database
│   │   └── service/             # RuleService for business logic
│   ├── resources/
│   │   ├── static/              # Contains static HTML (e.g. index.html)
│   │   └── application.properties # Configuration file for database connection
│   └── test/                    # Unit tests for the application
How It Works
Create Rule: The rule string is parsed into an Abstract Syntax Tree (AST) and saved to the database.
Combine Rules: Two rules are combined into a new rule using the AND/OR operator, creating a new node.
Evaluate Rule: The AST is traversed to evaluate the condition based on the input data.
Example Rule
Input Rule: "age > 18 AND income < 50000"
AST Representation:
markdown
Copy code
      AND
     /   \
 age > 18  income < 50000
Example Evaluation
If you pass { "age": 20, "income": 30000 } as data, the rule will evaluate to true.

![image](https://github.com/user-attachments/assets/f965ef9a-e2f6-4968-815f-a504333f000e)
