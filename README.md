# Book Management Server

A simple HTTP server that handles book management operations like adding, deleting, and listing books, implemented in Java. The server responds to HTTP requests with appropriate data, including JSON responses for book information.

## Getting Started

These instructions will guide you to get a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

To run this project, you need the following software installed on your local machine:

- **Java 11+**: The project is built using Java. 
- **Maven**: Used for dependency management.
- **IDE (optional)**: An Integrated Development Environment like IntelliJ IDEA can be used for development.

### Installing

Follow these steps to get the development environment running:

1. **Clone the repository:**
   ```bash
   git clone https://github.com/Nat15005/AREP-Taller-1.git
   ```
2. **Navigate to the project folder:**
   ```bash
   cd AREP-Taller-1
   ```
3. **Build the project using Maven:**
   ```bash
   mvn clean install
   ```

### Running the Server

Once the project is built, you can start the server with the following command:

```bash
java -cp target/classes edu.escuelaing.arep.HttpServer
```

The server will start and listen on port `35000`.

### Accessing the Application

Open your web browser and go to:

```
http://localhost:35000/
```

You should see the main page of the application.

![image](https://github.com/user-attachments/assets/ee733dbf-a387-4ed7-b243-9d8bdeaf2666)


### Running Tests

To run the unit tests, use the following command:

```bash
mvn test
```
![image](https://github.com/user-attachments/assets/c2143d97-8e98-4700-8a12-555f806fd48c)

### Project Structure

```
AREP-Taller-1
│── src
│   ├── main
│   │   ├── java
│   │   │   └── edu.escuelaing.arep
│   │   │       ├── Book.java
│   │   │       ├── FileHandler.java
│   │   │       ├── HttpServer.java
│   │   │       ├── RequestHandler.java
│   │   ├── resources/static
│   │       ├── fondo.jpg
│   │       ├── index.css
│   │       ├── index.html
│   │       ├── index.js
│   │       ├── pato.png
│   ├── test
│       ├── java/edu.escuelaing.arep
│       │   ├── BookTest.java
│       │   ├── FileHandlerTest.java
│       │   ├── MockSocket.java
│       │   ├── RequestHandlerTest.java
```

### Technologies Used

- **Java** - Main programming language
- **Maven** - Dependency management and build tool
- **JUnit** - For unit testing
- **HTML, CSS, JavaScript** - Frontend components

### Author

Developed by **Natalia Rojas** https://github.com/Nat15005.

### License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

