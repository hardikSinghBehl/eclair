Feature: Checking Health of application

	Background:
	  Given url apiBaseUrl 

	Scenario: calling /ping endpoint
		Given path '/ping'
		When method GET
		Then status 200
		And match response.message == 'pong'