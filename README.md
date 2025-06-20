# Inventory Management App
Inventory Management App was created as my capstone project/culmination of my BS SWE degree at Western Governor's University. It is configured to run with my [React application](https://github.com/miShelbyT/web-app-frontend), deployed separately. It operates dual permissions for general users and admin (manager) users to handle and update current inventory details for any relatively small sales operation. This deploy is for a boutique clothing store called Rose's Closet. In short:

Upon login, the general user can view the current inventory of the store, and can filter, run and print reports. Upon login, the admin/manager user has full CRUD functionality for the inventory, including price and other status changes, as well as marking items as sold or purchasing additional items.

The Spring Boot application uses several entity classes: mainly User, Manager (a subclass of User), InventoryItem, a DTO LoginRequest, and two other classes that handle JWT creation and management. 

<p style="text-align: center; width: 100%;">
    <img style="width: 65%;" src="resources/ERD_diagram.png" alt="screenshot of login page"> 
</p>

<p style="text-align: center; width: 100%;">
    <img style="width: 65%;" src="resources/LoginReqDTO.png" alt="screenshot of inventory list view"> 
</p>

This application uses JUnit for unit and integration testing, and JPA and Hibernate for object relational mapping/implementation between Spring Boot and the H2 file-based database. (An added benefit of using JPA's built-in methods is parameterized queries that protect against SQL injection attacks.🔥) Spring Starter Security allows use of built-in methods for JWT creation/management. 

CORS configuration allows only the front-end url to make API requests - localhost is not allowed.


The AWS containerized/deployed application uses https protocol - with a Let's Encrypt certificate - to communicate with the Netlify front-end. More information about the React application can be found [here](https://github.com/miShelbyT/web-app-frontend).

Deploy link available upon request - AWS invoices can add up!

*Note: this application  was initially created as a monorepo on my Gitlab account. Gitlab permissions made it difficult to untangle the application, so with a little help from a kind [friend](https://github.com/yibbidy) we got this decoupled so I could continue configuring my front-end and back-end repos to be deployed separately.*
