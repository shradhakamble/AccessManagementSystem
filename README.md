Create an access management system for users of an e commerce website


1. Login users can be of two types
  - Customer
  - Service User (like a customer care executive)

2. Login Mechanism
   - Customer : OTP or user id & password login
   - Service User : Org ID and Password login

3. Multiple Device Login
  - Customer : She should be able to log in N devices simultaneously.
  - Service User : Only single device login is permitted

4. Authentication Response
  - Once a user logs in she should be provided with an authentication token which will be used for all the APIs
  - Token creation algorithms can change over time.

5. Authorization Mechanism
  - When a user logs in he can use the application based on the permissions provided to her. 
  - If her permission is changed while she is in active session she must be logged out from all the devices and asked to login in again.

