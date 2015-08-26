#Leaf Automation
This is project structure for Leaf Web Automation. 

As a base, it has implementation of different profiles and including RemoteWebDriver, 
Screenshot implementation and more.

##Reporting
As reporting project uses Allure.

**In order to run the project simple execute**
`mvn test`
This will execution the browser and get the results.

**In order to create reports, execute:**
`mvn site`

Report will be in /target/site/allure-mvn-plugin/index.html **( only firefox)**