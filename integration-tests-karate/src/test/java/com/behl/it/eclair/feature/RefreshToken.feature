Feature: generating new access token using refresh token

	Background:
	  Given url apiBaseUrl

	Scenario: calling /token endpoint
		Given path '/token'
		And request {"refreshToken":"#(refreshTokenJwt)"}
		When method PUT
		Then status 200
		And match response.refreshToken == refreshTokenJwt
		And match response.accessToken == '#present'