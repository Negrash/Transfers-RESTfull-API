# Account transfer Rest API

A Java RESTful API for transfers between accounts.
Application starts on localhost port 8090 an H2 in memory database.

# Endpoints

| Method | Url                       | Request body example                      | Description                            |
|:-------|:--------------------------|:------------------------------------------|:---------------------------------------|
| GET    | /accounts                 |                                           | get all accounts                       |
| GET    | /accounts/{id}            |                                           | get account by id                      |
| POST   | /accounts/{name}/{amount} |                                           | create a new account                   |
| GET    | /transfers                |                                           | get all transfers                      |
| PUT    | /transfers                | {"fromId": 1, "toId": 2, "amount": 33.33} | perform transaction between 2 accounts |
