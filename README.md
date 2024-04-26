## Description
This project is JWT (JSON Web Token) Authentication example. You can test with either API from postman or Frontend. 

### JWT (JSON Web Token)
![jwt_image](/src/main/resources/image/jwt_image.png)
### Basic vs Bearer authentication
- <b>Basic authentication</b>: 
  Basic authentication is a simple and widely supported authentication method that uses the HTTP header to send the username and password of the client.
  ```http request
  Authorization : basic base64({USERNAME}:{PASSWORD})
  ```
  The client encodes the credentials in base64 and sends them with every request. The server decodes the credentials and checks if they are valid.
  <br><br>
  
  GOOD : simple and low-risk scenarios, such as testing or prototyping <br>
  BAD 
    1. The credentials are not encrypted 
    2. The client has to store the credentials securely and send them repeatedly, which can affect the performance and security of the API
  <br><br>

- <b>Bearer authentication</b>:
  Bearer authentication is a more advanced and secure authentication method that uses tokens instead of credentials.
   ```http request
  Authorization: Bearer <token>
  ```
  The client obtains a token from an authentication server by providing valid credentials or other information. The client then sends the token with every request using the HTTP header or the query string. The server validates the token and grants access to the API.
  <br><br>
  GOOD
  1. Reduces the risk of exposure and improves the performance of the API
  2. Have an expiration date, which limits the duration of the access
    
### Process
1. POST /login with ID and Password
2. Creates a JWT with a secret
3. Sends the JWT to the browser
4. Sends the JWT
5. Checks the JWT signature, Gets user information from the JWT
6. Sends response to the client