function fn() {
  var env = karate.env; // get system property 'karate.env'
  karate.log('karate.env system property was:', env);
  if (!env) {
    env = 'dev';
  }
  var config = {
    currentEnvironment: '',
    env: env,
    myVarName: 'someValue'
  }
  if (env == 'dev') {
    config.currentEnvironment = java.lang.System.getenv('API_DEV');

    // customize
    // e.g. config.foo = 'bar';
  } else if (env == 'test') {
    config.currentEnvironment = java.lang.System.getenv('API_TEST');

    // customize
  }
  return config;
}