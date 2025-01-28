document.addEventListener("DOMContentLoaded", function () {
    const bookList = document.getElementById("book-list");
    const addBtn = document.getElementById("add-btn");

    function deleteBook(event) {
        if (event.target.classList.contains("delete-btn")) {
            event.target.parentElement.remove();
        }
    }

    bookList.addEventListener("click", deleteBook);

    addBtn.addEventListener("click", function () {
        const books = ["Cumbres Borrascosas", "Querida yo, tenemos que hablar"];
        const randomBook = books[Math.floor(Math.random() * books.length)];

        const li = document.createElement("li");
        li.innerHTML = `${randomBook} <button class="delete-btn">Delete</button>`;
        bookList.appendChild(li);
    });
});
