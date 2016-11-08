"use strict"

const pg = require('pg');

function Postgresql() {
  this.pgConfig = {
    database: process.env.POSTGRES_DATABASE,
    host: process.env.POSTGRES_HOST,
    port: process.env.POSTGRES_PORT,
    user: process.env.POSTGRES_USER,
    password: process.env.POSTGRES_PASSWD,
    max: process.env.POSTGRES_CONNECTIONS || '20',
    idleTimeoutMillis:  process.env.POSTGRES_TIMEOUT || '10000'
  };
  console.log('Starting Postgresql connection w/ configuration ' + JSON.stringify(this.pgConfig));
  this.pool = new pg.Pool(this.pgConfig);
}
var klass = Postgresql.prototype;

klass.selectVersion = function(callback) {
  this.pool.connect(function(err, client, done) {
    if(err) return console.error('error fetching client from pool', err);

    const query = 'SELECT * FROM version();';

    client.query(query, function(err, result) {
      done();
      callback(err, result);
    });
  });
};

module.exports = Postgresql;
