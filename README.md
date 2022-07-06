# ISO-8583 API

## Description

I'm build this project to support my work tasks, you can use this api to parsing iso-8583 message and save the result to db.

## Getting Started

### Dependencies

- Starter parent version 2.2.4.RELEASE (Change it to current version if you want)
- Using NoSQL database (MongoDB)

### Installing

- Maven version 3.8.6
- Java version 11.0.15
- Mongo DB

### Executing Program

- How to run program

```
mvn spring-boot:run
```

- Use iso-8583 parser service
  server path : localhost:9000/api/v1/iso

```
{
    // With Header
"message": "ISO0060000600800822000000000000004000000000000000620150944001989301"

    // Without Header
"message": "0800822000000000000004000000000000000620150944001989301"
}
```

## Author

Andre Ayadi
