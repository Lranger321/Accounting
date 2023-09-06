# Accounting:

## Stack :
```
Spring Boot, PostgreSQL, Redis, JUnit, Liqubase

Clean Architecture

Proxy, Repositories, Factory method, Strategy 
```

# API: 

## Get accounts

```http
GET /api/accounts/
```

| Parameter | Type     | Description |
|:----------|:---------|:------------|
| `name`    | `string` | user name   |

### Response:

```
{
    "accountNumber": "string",
    "name": "string",
    "value": "double"
}

```

## Create account

```http
POST /api/accounts/
```

| Parameter | Type     | Description |
|:----------|:---------|:------------|
| `name`    | `string` | user name   |
| `pin`     | `string` | pin code    |

### Response:

```
{
    "accountNumber": "string",
    "name": "string",
    "value": "double"
}
```

`accountNumber` - user account number 16 symbols

`name` - user name

`value` - how much money have user on this account

## Make deposit transaction

```http
POST /api/transactions/deposit
```

| Parameter | Type     | Description               |
|:----------|:---------|:--------------------------|
| `account` | `string` | account number 16 symbols |
| `pin`     | `string` | pin code                  |
| `value`   | `double` | value to deposit          |

### Response:

```
{
    "account": {
    "accountNumber": "string",
    "name": "string",
    "value": "double"
    },
    "createdAt": "date",
    "error": "string",
    "status": "string",
    "type": "string",
    "value": "double"
}
```

`accountNumber` - user account number 16 symbols

`name` - user name

`value` - how much money have user on this account

`status` - status of transaction [SUCCESS, FAIL]

`type` - always DEPOSIT

`createdAt` - time of creation transaction

`error` - error string, if transaction status is fail

```
## Make widthdraw transaction
```http
POST /api/transactions/withdraw
```

| Parameter | Type     | Description               |
|:----------|:---------|:--------------------------|
| `account` | `string` | account number 16 symbols |
| `pin`     | `string` | pin code                  |
| `value`   | `double` | value to withdraw         |

### Response:

```
{
    "account": {
    "accountNumber": "string",
    "name": "string",
    "value": "double"
    },
    "createdAt": "date",
    "error": "string",
    "status": "string",
    "type": "string",
    "value": "double"
}
```

`accountNumber` - user account number 16 symbols

`name` - user name

`value` - how much money have user on this account

`status` - status of transaction [SUCCESS, FAIL]

`type` - always WITHDRAW

`createdAt` - time of creation transaction

`error` - error string, if transaction status is fail

## Make transfer transaction
```http
POST /api/transactions/transfer
```

| Parameter           | Type     | Description                           |
|:--------------------|:---------|:--------------------------------------|
| `account`           | `string` | account number 16 symbols             |
| `accountToTransfer` | `string` | account number 16 symbols transfer to |
| `pin`               | `string` | pin code                              |
| `value`             | `double` | value to transfer                     |

### Response:

```
{
    "account": {
    "accountNumber": "string",
    "name": "string",
    "value": "double"
    },
    "accountTo": {
    "accountNumber": "string",
    "name": "string",
    "value": 0
    },
    "createdAt": "date",
    "error": "string",
    "status": "string",
    "type": "string",
    "value": "double"
}
```

`accountNumber` - user account number 16 symbols

`name` - user name

`value` - how much money have user on this account

`status` - status of transaction [SUCCESS, FAIL]

`type` - always TRANSFER

`createdAt` - time of creation transaction

`error` - error string, if transaction status is fail

## Get transactions

```http
GET /api/transactions/
```

| Parameter       | Type     | Description               |
|:----------------|:---------|:--------------------------|
| `accountNumber` | `string` | account number 16 symbols |

### Response:

```
{
    "accountNumber": "string",
    "name": "string",
    "value": "double"
}

```

## Status Codes

| Status Code | Description             |
|:------------|:------------------------|
| 200         | `OK`                    |
| 404         | `NOT FOUND`             |
| 500         | `INTERNAL SERVER ERROR` |

## Run

```
mvn clean

mvn compile

mvn exec:java -Dexec.mainClass="Main" 

```
