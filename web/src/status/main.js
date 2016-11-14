"use strict"

const React      = require('react');
const ReactDOM   = require('react-dom');
const StatusTabs = require('./status-tabs.js');

ReactDOM.render(
  <StatusTabs url='/status.json' pollInterval='2500'/>,
  document.getElementById('content')
);
