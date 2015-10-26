var accountService = require('../bankid/account.service.js');
var soccardService = require('../../auth/soccard/soccard.service.js');
var userConvert = require('./user.convert');

var finishRequest = function (req, res, err, result) {
  if (err) {
    res.status(err.code);
    res.send(err);
    res.end();
  } else {
    req.session.subject = result.subject;
    res.send({
      customer: userConvert.convertToCanonical(result.customer),
      admin: result.admin
    });
    res.end();
  }
};

module.exports.fio = function (req, res) {
  var account = req.session.account;
  res.send({firstName: account.firstName, middleName: account.middleName, lastName: account.lastName});
};

module.exports.index = function (req, res) {
  var config = require('../../config/environment');
  var type = req.session.type;
  if (type === 'bankid' || type === 'eds') {
    accountService.syncWithSubject(req.session.access, function (err, result) {
      finishRequest(req, res, err, result);
    });
  } else if (type === 'soccard') {
    soccardService.getUser(req.session.access, function (err, result) {
      finishRequest(req, res, err, result);
    });
  }
};
