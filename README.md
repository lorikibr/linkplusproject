# Bank System Console Application

## Technologies Used
- Java
- Spring Boot
- Lombok
- Spring Data JPA
- H2 (In-memory database)
- Postman (for API testing)

## Functionality
The Bank System Console Application offers the following functionality:

- **Account Creation**: Allows users to create new accounts associated with a bank by providing account details and initial balance.
- **Deposit and Withdrawal**: Supports depositing and withdrawing money from accounts, ensuring balance integrity.
- **Inter-Account Transfer**: Facilitates money transfer between accounts within the same bank, with options for flat fee or percentage fee transactions.
- **Bank Management**: Enables the creation of banks and tracking of total transaction fee amount and total transfer amount.

## Thought Process
- **Error Handling**: Implemented basic error handling for scenarios such as insufficient funds and invalid account IDs.

## API Endpoints Documentation
### 1. Account Operations
#### Create Account
- **URL**: `/api/accounts`
- **Method**: `POST`
- **Description**: Create a new account associated with a bank.
- **Request Body**:
  ```json
  {
    "name": "AccountName",
    "bankId": 1,
    "initialBalance": 1000.00
  }
  ```
- **Response**: HTTP Status `200 OK` or appropriate error message.

#### Deposit Money
- **URL**: `/api/accounts/{accountId}/deposit`
- **Method**: `PUT`
- **Description**: Deposit funds into an existing account.
- **Request Parameter**:
  - `{accountId}`: ID of the account to deposit into.
- **Request Body**:
  ```json
  {
    "amount": 500.00
  }
  ```
- **Response**: HTTP Status `200 OK` or appropriate error message.

#### Withdraw Money
- **URL**: `/api/accounts/{accountId}/withdraw`
- **Method**: `PUT`
- **Description**: Withdraw funds from an existing account.
- **Request Parameter**:
  - `{accountId}`: ID of the account to withdraw from.
- **Request Body**:
  ```json
  {
    "amount": 200.00
  }
  ```
- **Response**: HTTP Status `200 OK` or appropriate error message.

#### Transfer Money
- **URL**: `/api/accounts/{fromAccountId}/{toAccountId}/transfer`
- **Method**: `PUT`
- **Description**: Transfer funds from one account to another within the same bank.
- **Request Parameters**:
  - `{fromAccountId}`: ID of the account to transfer from.
  - `{toAccountId}`: ID of the account to transfer to.
- **Request Body**:
  ```json
  {
    "amount": 300.00
  }
  ```
- **Response**: HTTP Status `200 OK` or appropriate error message.

### 2. Bank Operations
#### Create Bank
- **URL**: `/api/banks`
- **Method**: `POST`
- **Description**: Create a new bank.
- **Request Body**:
  ```json
  {
    "name": "BankName",
    "totalTransactionFeeAmount": 0.00,
    "totalTransferAmount": 0.00,
    "transactionFlatFeeAmount": 10.00,
    "transactionPercentageFeeAmount": 5.00
  }
  ```
- **Response**: HTTP Status `200 OK` or appropriate error message.

#### View Bank Details
- **URL**: `/api/banks/{bankId}`
- **Method**: `GET`
- **Description**: View details of a specific bank.
- **Request Parameter**:
  - `{bankId}`: ID of the bank to view details of.
- **Response**: HTTP Status `200 OK` or appropriate error message.
