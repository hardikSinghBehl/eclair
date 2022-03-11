Feature: Article CRUD operations

	Background:
	  Given url apiBaseUrl
	  * def requestBody = {"title": "DEMO_TITLE","description": "DEMO_DESCRIPTION","tags": "DEMO,KARATE"}
	  
	Scenario: Create an article and validate using GET /articles and /users
	 	Given path '/articles'
	  Given header Authorization = 'Bearer '+accessTokenJwt
	  And request requestBody
	  When method POST
	  Then status 200
	  
	 	Given path '/articles'
	  Given header Authorization = 'Bearer '+accessTokenJwt	 
	  When method GET
	  Then status 200
	  * def retreivedArticle = response[0]
	  And match retreivedArticle.title == requestBody.title
	  And match retreivedArticle.description == requestBody.description
	  * def articleId = retreivedArticle.id
	  
	  Given path '/users'
	  Given header Authorization = 'Bearer '+accessTokenJwt	 
	  When method GET
	  Then status 200
	  And match response.numberOfArticles == 1
	  
	  
	  Scenario: Update an article and confirm modification
	  # Fetching articleId
	  Given path '/articles'
	  Given header Authorization = 'Bearer '+accessTokenJwt	 
	  When method GET
	  Then status 200
	  * def articleId = response[0].id
	  
	  Given path '/articles',articleId
	  Given header Authorization = 'Bearer '+accessTokenJwt
	  And request {"title": "DEMO_TITLE_CHANGED","description": "DEMO_DESCRIPTION_CHANGED","tags": "DEMO,KARATE,THIRD_TAG"}
	  When method PUT
	  Then status 200
	  
	 	Given path '/articles'
	  Given header Authorization = 'Bearer '+accessTokenJwt	 
	  When method GET
	  Then status 200
	  * def retreivedArticle = response[0]
	  And match retreivedArticle.title != requestBody.title
	  And match retreivedArticle.description != requestBody.description
	  
	Scenario: Delete an article and confirm it's deletion using GET /users
	  # Fetching articleId
	  Given path '/articles'
	  Given header Authorization = 'Bearer '+accessTokenJwt	 
	  When method GET
	  Then status 200
	  * def articleId = response[0].id
	  
	  Given path '/articles',articleId
	  Given header Authorization = 'Bearer '+accessTokenJwt
	  When method DELETE
	  Then status 200
	  
	  Given path '/users'
	  Given header Authorization = 'Bearer '+accessTokenJwt	 
	  When method GET
	  Then status 200
	  And match response.numberOfArticles == 0
	  