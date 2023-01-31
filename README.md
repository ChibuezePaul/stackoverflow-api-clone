# stackoverflow-api-clone
a simple rest api clone of StackOverflow

The API Documented using Spring OpenAPI. Once the app is started, you can find the documentation at http://localhost:8080/stackoverflow-clone/api/swagger-ui.

# API Brief
A user registers with their email. name and password
A user logs in with their username and password
A user can be retrieved by their email or name

A user creates a question with title, description and tags (comma separated string)
A user can answer a question with the content of their answer
A question can be found by it's words in it's title or it's tags

A user can up vote a answer
A user can down vote an answer
An answer can be found by words in it's content
