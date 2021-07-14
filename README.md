# petstore
Automation of the store endpoint for https://petstore.swagger.io/#/store
Rest Assured with Cucumber Framework

1 positive and 1 negative scenario for each.

To run, simply pull and "mvn clean install" then you can run by selecting the feature file "CTRL+SHIFT+F10" or running " mvn test -Dcucumber.options="--tags @API"   "


Notes:
- Used @Data which comes from lombok as to avoid unneccesary getters/setters
- Would've liked to use a response POJO but was experiencing issues and due to time constraints wanted to get this submitted
- Observed sometimes the endpoints run too quickly when run as an e2e journey (i.e. place order, get order, delete order) this can be resolved by using a class like Awaitility
but saw it pass consistently so have not implemented this for now. 
