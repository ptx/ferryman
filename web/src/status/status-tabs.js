"use strict"

const React                            = require('react');
const { Tab, Tabs, TabList, TabPanel } = require('react-tabs');

class StatusTabs extends React.Component {
  constructor(props) {
    super(props);
    this.url = this.props.url;
    this.pollInterval = this.props.pollInterval;
    this.state = {
      data: {}
    }
    this.loadStatusFromServer = this.loadStatusFromServer.bind(this);
  }

  loadStatusFromServer() {
    //console.log(JSON.stringify(this.state));
    $.ajax({
      url: this.url,
      dataType: 'json',
      cache: false,
      success: function(data) {
        this.setState({data: data});
      }.bind(this),
      error: function(xhr, status, err) {
        console.error(this.props.url, status, err.toString());
      }.bind(this)
    });
  }

  componentDidMount() {
    this.loadStatusFromServer();
    setInterval(this.loadStatusFromServer, this.pollInterval);
  }

  render() {
    var {data} = this.state;
    return (
        <Tabs className="status-tabs">
          <TabList>
            <Tab>Database</Tab>
            <span>+</span>
          </TabList>

          <TabPanel>
            <h2>Version</h2>
            <p>{(data['postgresql'] in window) ? "" : data['postgresql']['version']}</p>
            <h2>Server Start Timestamp</h2>
            <p>{(data['postgresql'] in window) ? "" : data['postgresql']['start_timestamp']}</p>
          </TabPanel>
        </Tabs>
    );
  }
}

module.exports = StatusTabs;
