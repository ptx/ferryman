"use strict"

const axios = require('axios')
//require('ssl-root-cas').inject();

   // "rejectUnauthorized": false,
var config = {
  rejectUnauthorized: false,
  headers: {
    //"Authorization": "Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJrdWJlcm5ldGVzL3NlcnZpY2VhY2NvdW50Iiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9uYW1lc3BhY2UiOiJkZWZhdWx0Iiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9zZWNyZXQubmFtZSI6ImZlcnJ5bWFuLXRva2VuLWdjNXUyIiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9zZXJ2aWNlLWFjY291bnQubmFtZSI6ImZlcnJ5bWFuIiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9zZXJ2aWNlLWFjY291bnQudWlkIjoiMDIxOTk1YzEtYTgzZC0xMWU2LTk3MjktNDIwMTBhODAwMjBlIiwic3ViIjoic3lzdGVtOnNlcnZpY2VhY2NvdW50OmRlZmF1bHQ6ZmVycnltYW4ifQ.gyZXp1pkfWTrQegC75Hg1ud-uaWvC6drGiS7NX_mTujdgsV0EaIT6pyg4as4jKtpBPBeOqo1cENFD-UCV2YUkmw0L6T4t0QuBeijpTz5ZkT9fMT_f62g2WUe8MunGzCcm1WHgXPqOlGBrz9zmbkJgNGw9SOobVfN-bkNj3nIvgR4tZVaKe0smmtUQG18l29sPd0oAPMHX64D_SR7vssFiy4tDk1Pi7QsnfodkX6nvOXW8UMfOukvqYpXLdgt10BevCgeIFROxngOetMMfDkNg_VJM8f09VpEUXpTgmTD2RQwSpvIb2lKqOvsF2NW7LG_B5yX45Yy_BCe-SPWpq1vjA", 
    "User-Agent" : "axios",
    "Accept" : " application/json"
  }
};

var x = axios.get("https://105.198.172.191/version", config).then(function (response) {
//var x = axios.get("https://api.github.com/", config).then(function (response) {
  console.log(response); 
}).catch(function (error) {
  console.log(error);
});
console.log("axios call completed");

