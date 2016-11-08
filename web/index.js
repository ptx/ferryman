var express      = require('express');
var app          = express();
const Morgan     = require('morgan');
const Postgresql = require('./postgresql.js');
const Postgres   = new Postgresql();

// serve static files
app.use(express.static('public'));

// logging
app.use(Morgan('dev')); // development
/* prduction, error responses only */
//app.use(Morgan('combined', {
//  skip: function(req, res) { return res.statusCode < 400 };
//}));

app.get('/skeleton.json', function(req, res) {
  Postgres.selectVersion(function(err, result) {
    if(err) {
      console.error('error selecting database version', err);
      res.status(500);
      res.end();
    }
    var version = {
      version: result.rows[0].version
    };
    res.json(version).end();
  });
});

app.listen(8080, function() {
  console.log('ferryman serving on port :8080');
});
