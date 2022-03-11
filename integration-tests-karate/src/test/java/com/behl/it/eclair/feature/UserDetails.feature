Feature: User Account Details

	Background:
	  Given url apiBaseUrl
	 
	Scenario: Fetching Logged-in users account details
	  Given path '/users'
	  Given header Authorization = 'Bearer '+accessTokenJwt
	  When method GET
	  Then status 200
	  * print response
	  And match response.address == null
	  And match response.numberOfArticles == 0
	  
	Scenario: Updating account details and then validating the changes by fetching
	  Given path '/users'
	  Given header Authorization = 'Bearer '+accessTokenJwt
	  * def changedName = "Hardik"
	  * def updatedAddress = {"lineOne": "line-1","lineTwo": "line-2","landMark": "landmark","pinCode": 120240 }
	  And request { "fullName": "#(changedName)","address": "#(updatedAddress)"}
	  When method PUT
	  Then status 200
	  * print response
	  
	  Given path '/users'
	  Given header Authorization = 'Bearer '+accessTokenJwt
	  When method GET
	  Then status 200
	  * print response
	  And match response.fullName == changedName
	  And match response.address == updatedAddress
