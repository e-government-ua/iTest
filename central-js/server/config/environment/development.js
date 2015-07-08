'use strict';

// Development specific configuration
// ==================================
module.exports = {

  debug: true,

  bankid: {
    sProtocol_AccessService_BankID: 'https', //Test
    sHost_AccessService_BankID: 'bankid.privatbank.ua', //Test
    sProtocol_ResourceService_BankID: 'https', //Test
    sHost_ResourceService_BankID: 'bankid.privatbank.ua', //Test
    client_id: 'testIgov',
    client_secret: 'testIgovSecret'
  },

  server: {
    protocol: 'http',
    port: '9000',
    session: {
      secret: 'put yor session secret here',
      key: ['solt for session 1', 'solt for session 2'],
      secure: false,
      maxAge: 180000 // 3 * 60 * 1000 = 3 min
    }
  },

  activiti: {
    protocol: 'https',
    hostname: 'test.igov.org.ua',
    port: '8443',
    path: '/wf-central/service',
    username: 'activiti-master',
    password: 'UjhtJnEvf!'
  }
};
