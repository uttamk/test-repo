## Reviewer Instructions
If you are reviewing this submission, then you can do so in two ways

* Look at the changes in [this pull request](https://github.com/equalexperts-assignments/equal-experts-fiery-irresistible-knowledgeable-charm-d17b4d45494c/pull/1)
* Browse the code on Github
    

## Building and Running the Project

### Prerequisites
- Java 11 or higher
- Gradle

### Commands

**Build the project:**

```./gradlew build```

**Run tests:**

```./gradlew test```

**Run the project:*

```./gradlew run```

**The application will display:**

- Cart contents
- Subtotal
- Tax amount (12.5%)
- Total payable amount

---

## :warning: Please read these instructions carefully and entirely first
* Clone this repository to your local machine.
* Use your IDE of choice to complete the assignment.
* When you have completed the assignment, you need to  push your code to this repository and [mark the assignment as completed by clicking here](https://app.snapcode.review/submission_links/ed422893-1df8-4a89-ad9c-ed4a24835256).
* Once you mark it as completed, your access to this repository will be revoked. Please make sure that you have completed the assignment and pushed all code from your local machine to this repository before you click the link.
* There is no time limit for this task - however, for guidance, it is expected to typically take around 1-2 hours.

# Begin the task

Write some code that provides the following basic shopping cart capabilities:

1. Add a product to the cart
   1. Specifying the product name and quantity
   2. Retrieve the product price by issuing a request to the [Price API](#price-api) specified below
   3. Cart state (totals, etc.) must be available

2. Calculate the state:
   1. Cart subtotal (sum of price for all items)
   2. Tax payable (charged at 12.5% on the subtotal)
   3. Total payable (subtotal + tax)
   4. Totals should be rounded up where required (to two decimal places)

## Price API

The price API is an existing API that returns the price details for a product, identified by it's name. The shopping cart should integrate with the price API to retrieve product prices. 

### Price API Service Details

Base URL: `https://equalexperts.github.io/`

View Product: `GET /backend-take-home-test-data/{product}.json`

List of available products
* `cheerios`
* `cornflakes`
* `frosties`
* `shreddies`
* `weetabix`

## Example
The below is a sample with the correct values you can use to confirm your calculations

### Inputs
* Add 1 × cornflakes @ 2.52 each
* Add another 1 x cornflakes @2.52 each
* Add 1 × weetabix @ 9.98 each
  
### Results  
* Cart contains 2 x cornflakes
* Cart contains 1 x weetabix
* Subtotal = 15.02
* Tax = 1.88
* Total = 16.90

## Tips on what we’re looking for

We value simplicity as an architectural virtue and as a development practice. Solutions should reflect the difficulty of the assigned task, and shouldn’t be overly complex. We prefer simple, well tested solutions over clever solutions. 

### DO

* ✅ Include unit tests.
* ✅ Test both any client and logic.
* ✅ Update the README.md with any relevant information, assumptions, and/or tradeoffs you would like to highlight.

### DO NOT

* ❌ Submit any form of app, such as web APIs, browser, desktop, or command-line applications.
* ❌ Add unnecessary layers of abstraction.
* ❌ Add unnecessary patterns/ architectural features that aren’t called for e.g. persistent storage.