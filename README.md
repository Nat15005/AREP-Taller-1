# Book Management Server

This project implements a simple HTTP server in Java that handles multiple client requests sequentially. The server is capable of reading files from the local disk and serving any requested files, including HTML pages, JavaScript, CSS, and images. It features a basic HTTP server architecture, which processes client requests and sends responses. Additionally, the project incorporates asynchronous communication through REST services in the backend. As a demonstration of the server's functionality, a web application was developed for managing books, allowing users to add, delete, and list books.

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

![image](https://github.com/user-attachments/assets/f2382c33-b777-4073-8d8f-9a355a512263)



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

### Acknowledgments

- Java and Networking Documentation - For offering essential references on socket programming.

- Open Source Community - For tools and resources that helped in the development of this project.


