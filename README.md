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

- Account Creation: Allows users to create new accounts associated with a bank by providing account details and initial balance.
- Deposit and Withdrawal: Supports depositing and withdrawing money from accounts, ensuring balance integrity.
- Inter-Account Transfer: Facilitates money transfer between accounts within the same bank, with options for flat fee or percentage fee transactions.
- Bank Management: Enables the creation of banks and tracking of total transaction fee amount and total transfer amount.

## Thought Process
- **Error Handling**: Implemented basic error handling for scenarios such as insufficient funds and invalid account IDs.

## Functionality Overview
### 1. Account Operations
- **Create Account**: Input account details to create a new account.
- **Deposit Money**: Add funds to an existing account.
- **Withdraw Money**: Withdraw funds from an existing account.
- **Transfer Money**: Transfer funds from one account to another within the same bank wiht percentage and fiat.

### 2. Bank Operations
- **Create Bank**: Create a new bank with specified parameters.
- **View Bank Details**: Check details of existing banks, including total transaction fee amount and total transfer amount.

## Note
- Error messages will be displayed for invalid inputs or failed operations.
- You can test the endpoints using Postman.
