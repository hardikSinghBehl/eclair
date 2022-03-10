function fn() {

	var config = {
		apiBaseUrl: 'http://mongo-crud-poc.hardiksinghbehl.com'
	}

	var createUserResult = karate.callSingle('classpath:com/behl/it/eclair/helper/CreateUser.feature', config);
	config.accessTokenJwt =	createUserResult.accessToken

	return config;
}