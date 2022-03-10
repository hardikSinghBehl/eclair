Feature: Deleting Created Test User

	Background:
	  Given url apiBaseUrl

	Scenario: deleting user
		Given path '/users'
		Given header Authorization = 'Bearer '+accessTokenJwt		
		When method DELETE
		Then status 200