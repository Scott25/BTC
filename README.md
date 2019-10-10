### Pre requirment
- Java 11
- Intellij

### Steps to run the project
- git clone https://github.com/Scott25/BTC.git
- Open Intellij and import the project as maven project
- Run the project as pre defined configuration "BitcoinApplication"

### To change the port configuration
- Locate "pom.xml" and <properties> field and change the define <port>
- Default port is 82

### After run the project visit the following link to see 1 BTC price according given currency code
- http://localhost:82/btc?currency=USD

### After run the project visit the following link to see 1 BTC price 1 month ago and current month of given currency code
- http://localhost:82/history?currency=USD

