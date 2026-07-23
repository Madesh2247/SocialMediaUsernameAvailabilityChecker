# 🔍 Social Media Username Availability Checker

<p align="center">
<img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white"/>
<img src="https://img.shields.io/badge/Data%20Structures-HashMap-blue?style=for-the-badge"/>
<img src="https://img.shields.io/badge/Algorithms-Username%20Search-success?style=for-the-badge"/>
<img src="https://img.shields.io/badge/Collections-Java-orange?style=for-the-badge"/>
<img src="https://img.shields.io/badge/License-MIT-green?style=for-the-badge"/>
</p>

<h1 align="center">🔍 Social Media Username Availability Checker</h1>

<p align="center">
A Java application that efficiently checks username availability, suggests alternative usernames, tracks search attempts, and demonstrates the use of HashMap-based data structures for fast username management.
</p>

---

# 📖 About the Project

Social media platforms require millions of usernames to be stored and searched efficiently.

This project demonstrates how **HashMaps** can be used to perform fast username lookups while providing intelligent username suggestions when the requested username is already taken.

It also keeps track of username search frequency to identify the most requested usernames.

This project was developed to demonstrate practical applications of **Java Collections Framework**, **HashMaps**, and **efficient searching algorithms**.

---

# ✨ Features

✅ Username Registration

✅ Username Availability Checking

✅ Alternative Username Suggestions

✅ Search Attempt Tracking

✅ Most Frequently Requested Username

✅ Fast HashMap Lookup (O(1))

✅ Java Collections Framework Implementation

---

# 🏗️ System Workflow

```text
User enters Username
          │
          ▼
Check HashMap
          │
 ┌────────┴────────┐
 │                 │
 ▼                 ▼
Available       Already Exists
 │                 │
 ▼                 ▼
Register     Generate Suggestions
                  │
                  ▼
Track Search Attempts
                  │
                  ▼
Display Most Requested Username
```

---

# 📂 Project Structure

```text
SocialMediaUsernameAvailabilityChecker/
│
├── src/
│   ├── Main.java
│   ├── SocialMediaUsernameAvailabilityChecker.java
│
└── README.md
```

---

# 🛠 Technologies Used

| Technology | Purpose |
|------------|---------|
| Java | Core Programming |
| HashMap | Username Storage |
| ArrayList | Username Suggestions |
| Java Collections | Data Management |
| OOP | Application Design |

---

# ⚡ Core Concepts Used

- HashMap
- ArrayList
- Java Collections Framework
- String Manipulation
- Username Searching
- Alternative Suggestion Algorithm
- Frequency Counter
- Object-Oriented Programming

---

# 🚀 Getting Started

## Clone Repository

```bash
git clone https://github.com/Madesh2247/SocialMediaUsernameAvailabilityChecker.git
```

## Navigate

```bash
cd SocialMediaUsernameAvailabilityChecker
```

## Compile

```bash
javac *.java
```

## Run

```bash
java SocialMediaUsernameAvailabilityChecker
```

---

# 📸 Sample Output

```text
john_doe available: false

jane_smith available: true

Suggestions:
john_doe1
john_doe2
john_doe3
john_doe4
john_doe5
john.doe

Most Attempted Username:
admin (3 attempts)
```

---

# 💡 Applications

- Social Media Platforms
- User Registration Systems
- Gaming Platforms
- Student Portals
- Online Banking
- E-Commerce Websites
- Email Services
- Community Forums

---

# 🚀 Future Improvements

- Database Integration (MySQL)
- GUI Version (JavaFX / Swing)
- REST API Support
- Password Validation
- AI Username Generator
- Random Username Suggestions
- Emoji Username Support
- Spring Boot Backend
- Web Version

---

# 📈 Time Complexity

| Operation | Complexity |
|-----------|------------|
| Register User | **O(1)** |
| Username Search | **O(1)** |
| Availability Check | **O(1)** |
| Suggest Username | **O(n)** |
| Attempt Tracking | **O(1)** |

---

# 👨‍💻 Author

## Madeshwaran

🎓 B.Tech Artificial Intelligence

🏫 SRM Institute of Science and Technology

🌐 Portfolio  
https://madesh2247.github.io/portfolio-website/

💻 GitHub  
https://github.com/Madesh2247

---

# ⭐ Support

If you like this project,

⭐ Star this repository

🍴 Fork this repository

🐛 Report Issues

💡 Suggest Improvements

---

<h3 align="center">

Made with ❤️ by **Madeshwaran**

</h3>
