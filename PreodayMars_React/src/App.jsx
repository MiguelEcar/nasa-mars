import React from 'react';
import { Router, Route, Switch, Redirect } from 'react-router-dom';
import { connect } from 'react-redux';

import { Theme } from '@theme';
import { history } from '@theme';

// import {LoginPage} from './pages/LoginPage';
import { HomePage } from './pages/HomePage';
import { SolPage } from './pages/SolPage';

import './App.css';
import './styles/theme.css';

import 'primereact/resources/themes/nova-light/theme.css';
import 'primereact/resources/primereact.min.css';
import 'primeicons/primeicons.css';
import 'bootstrap/dist/css/bootstrap.min.css';


class App extends React.Component {

  /////////////////////////////////////////////////////////////////////////////////
  render() {
    return (
      <>
        <Router history={history}>

          <Switch>
            {/* <Route path="/login" component={LoginPage} /> */}
            <Theme>
              <Route exact path="/" component={HomePage} />
              <Route path="/details" component={SolPage} />
              <Redirect from="*" to="/" />
            </Theme>
          </Switch>

        </Router>
      </>
    );
  }
  /////////////////////////////////////////////////////////////////////////////////
}

function mapStateToProps(state) {
  const { alert } = state;
  return { alert };
}

export const mapDispatchToProps = {}

const connectedApp = connect(mapStateToProps, mapDispatchToProps)(App);
export { connectedApp as App };