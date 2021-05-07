# Mongodb
NoSql Database

#### What is MongoDB?
- Document based NoSQL database
- Stores data in JSON like documents(BSON)
- Very flexible structure to store the data
  - In RDBMS, a coloumn can not store the multiple fields, but in NoSQL a field could have multiple fields
- Large amount of data can be easily managed

#### Studio 3T MongoDB UI Connector
#### Difference between SQL and NoSQL data base
|SQL|NoSQL|
|---|---|
|Have fixed or static or predefined schema|Have dynamic schema|
|display data in form of tables so it is known as table-based database|display data as collection of key-value pair, documents, graph databases or wide-column stores|
|Vertically scalable|Horizontally scalable|
|Best suited for complex queries|not so good for complex queries because these are not as powerful as SQL queries|
|not best suited for hierarchical data storage|best suited for hierarchical data storage|
|Follows ACID(Atomicity, Consistency, Isolation, and Durability) property|Follows CAP(consistency, availability, partition tolerance)|
#### MongoDB Terms
|SQL|NoSQL|
|---|---|
|`Database`|`Database`|
|`Table`|`Collection`|
|`Column`|`Field`|
|`Row`|`Document`|
|`Foreign Key`|`Sub Document(with or without Reference)`|

#### MongoDB Add Documents
``` ruby
// Add Document
// 1
{
  "name" : "Pratap Narayan"
}

// 2

{
   "name" : "Sankalp Pratap Nishad", 
    "mail" : "nishad.sankalp@gmail.com", 
    "department" : {
        "name" : "CS", 
        "location" : "IND"
    }
}

// 3

{
  "name" : "Deepika", 
    "mail" : "nishad.deepika@yahoo.com", 
    "subjects" : [
        {
            "name" : "Java", 
            "marks_obtained" : 70.0
        }, 
        {
            "name" : "Spring", 
            "marks_obtained" : 80.0
        }
    ]
}

```

#### MonogDB Queries
``` ruby
 1. AND Query
{
    $and : [
        {
            "name" : "Sankalp Pratap Nishad"
        },
        {
            "mail" : "nishad.sankalp@gmail.com"
        }
    ]
}

2. OR Query
{
    $or : [
        {
            "name" : "Sankalp Pratap Nishad"
        },
        {
            "mail" : "nishad.sankalp@gmail.com"
        }
    ]
}

3. IN query

{
  "name" : {
    $in : ["Pratap Narayan", "Sankalp Pratap Nishad"]
  }
}

```
