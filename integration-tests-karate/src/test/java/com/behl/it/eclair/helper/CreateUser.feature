Feature: Creating a new user record

	Scenario: Create a new user
	  Given url apiBaseUrl
	  Given path '/users'
	  And request {"fullName": "Hardik Singh Behl", "emailId": "dummy123@gmail.com", "password": "dummy123"}
	  When method POST
		Then assert responseStatus == 201 || responseStatus == 409
	  
	  Given path '/login'
	  And request {"emailId": "dummy123@gmail.com", "password": "dummy123"}
	  When method POST
	  Then status 200
	  * def accessToken = response.accessToken 
	  * def refreshToken = response.refreshToken 

