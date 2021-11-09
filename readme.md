This is my Visma Java Developer Task for Internship.

URI's of the required endpoints:
<ul>
  <li>REST API endpoint to add a new book:  <br> http://localhost:8080/api/visma-task/books/addBook <br> Example of body required: <br> <pre> {
    "name": "Pavyzdys",
    "author": "Vardenis Pavardenis",
    "category": "ROMANCE",
    "language": "LT",
    "publicationDate": "2018-01-20",
    "isbn": "257-851-547",
    "guid": "963258714174",
    "taken": true
} </pre> </li>
  <li> REST API endpoint to take a book from the library: <br> http://localhost:8080/api/visma-task/bookTaking/take <br> Exaple of body required: <br>
    <pre> [
    {
    "memberId": 987521458,
    "bookGuid": "963258714174",
    "days": 59
}
] </pre>
  
  </li>
  <li> REST API endpoint to get a book by GUID: <br> http://localhost:8080/api/visma-task/books/963258714174 </li>
  <li>REST API endpoint to list all the books adding following parameters: <br> 
    <ul>
      <li>Filter by author: <br> http://localhost:8080/api/visma-task/books/author/Susanna </li>
      <li>Filter by category: <br> http://localhost:8080/api/visma-task/books/category/crime  </li>
      <li>Filter by language <br> http://localhost:8080/api/visma-task/books/language/lt</li>
      <li>Filter by ISBN <br> http://localhost:8080/api/visma-task/books/isbn/0</li>
      <li>Filter by name <br> http://localhost:8080/api/visma-task/books/name/Harry</li>
      <li>Filter taken or available books: <br> http://localhost:8080/api/visma-task/books/taken/true</li>
    </ul>
  </li>
  <li>REST API endpoint to delete a book: <br>
  http://localhost:8080/api/visma-task/books/deleteBook <br>
    Example of body required: <br>
    <pre> {
    "name": "Pavyzdys",
    "author": "Vardenis Pavardenis",
    "category": "ROMANCE",
    "language": "LT",
    "publicationDate": "2018-01-20",
    "isbn": "257-851-547",
    "guid": "963258714174",
    "taken": true
} </pre>
  </li>
  </ul>
  <br><br>
  Unit tests are written for all of the Service classes.
