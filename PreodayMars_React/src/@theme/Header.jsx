import React from 'react';
import { withTranslation } from 'react-i18next';
import { connect } from 'react-redux';
import { reduxForm } from 'redux-form';
import { Field } from 'redux-form';

import { authActions } from '@redux';
import { userActions } from '@redux';

import jwt from 'jwt-decode';

import Row from 'react-bootstrap/row';
import Col from 'react-bootstrap/col';
import Btn from 'react-bootstrap/button';

import { OverlayPanel } from 'primereact/overlaypanel';

import { history } from '@theme';

class Header extends React.Component {

  /////////////////////////////////////////////////////////////////////////////////
  token = () => {
    try {
      return jwt(this.props.authReducer.user)
    } catch (err) {

    }
  }
  /////////////////////////////////////////////////////////////////////////////////

  /////////////////////////////////////////////////////////////////////////////////
  onLogin = (fields, login) => {
    login(fields).then(
      history.push("/")
    )

  }
  onLogout = async (logout) => {
    await logout();
    await history.push("/");
  }

  onSave = (fields, save, login) => {
    save(fields).then(
      this.onLogin(fields, login)
    )
  }
  /////////////////////////////////////////////////////////////////////////////////

  /////////////////////////////////////////////////////////////////////////////////
  render() {
    const { t } = this.props;

    const { handleSubmit } = this.props;
    const { save, login, logout } = this.props;

    return (
      <>
        <div className='header'>
          <div style={{ float: 'right' }}>
            {this.token() &&
              <Row>
                <Col md="auto">
                  <i>{t('def_hello') + this.token().user.name}</i>
                  <p onClick={() => this.onLogout(logout)}
                    style={{ cursor: 'pointer' }} >
                    {t('def_signout')}
                  </p>
                </Col>
              </Row>
            }
            {!this.token() &&
              <>
                {/* ///////////////////////////////////////////////////////////////////////////////// */}
                <form onSubmit={handleSubmit((fields) => this.onLogin(fields, login))}>
                  <Row>
                    <Col md="auto">
                      <Field id='login_email_id'
                        className='form-control'
                        placeholder={t('user_email')}
                        component='input'
                        type='text'
                        name='email'
                        required={true}
                      />
                    </Col>
                    <Col md="auto">
                      <Field id='login_password_id'
                        className='form-control'
                        placeholder={t('user_password')}
                        component='input'
                        type='password'
                        name='password'
                        required={true}
                      />
                    </Col>
                    <Col md="auto">
                      <Btn type='submit'>{t('def_signin')}</Btn>
                    </Col>
                  </Row>
                </form>
                {/* ///////////////////////////////////////////////////////////////////////////////// */}

                <Col md="auto" style={{ float: 'right', cursor: 'pointer' }}>
                  <i onClick={(e) => this.op.toggle(e)}>{t('def_signup')}</i>
                </Col>

                {/* ///////////////////////////////////////////////////////////////////////////////// */}
                <OverlayPanel ref={(el) => this.op = el}>
                  <form onSubmit={handleSubmit((fields) => this.onSave(fields, save, login))}>
                    <Row>
                      <Col md="auto">
                        <label htmlFor='name_id'>{t('user_name')}</label>
                        <Field id='name_id'
                          className='form-control'
                          placeholder={t('user_name')}
                          component='input'
                          type='text'
                          name='name'
                          required={true}
                        />
                      </Col>
                    </Row>
                    <Row>
                      <Col md="auto">
                        <label htmlFor='email_id'>{t('user_email')}</label>
                        <Field id='email_id'
                          className='form-control'
                          placeholder={t('user_email')}
                          component='input'
                          type='text'
                          name='email'
                          required={true}
                        />
                      </Col>
                    </Row>
                    <Row>
                      <Col md="auto">
                        <label htmlFor='password_id'>{t('user_password')}</label>
                        <Field id='password_id'
                          className='form-control'
                          placeholder={t('user_password')}
                          component='input'
                          type='password'
                          name='password'
                          required={true}
                        />
                      </Col>
                    </Row>
                    <Row>
                      <Col md="auto">
                        <Btn type='submit'>{t('def_signup')}</Btn>
                      </Col>
                    </Row>
                  </form>
                </OverlayPanel>
                {/* ///////////////////////////////////////////////////////////////////////////////// */}
              </>
            }
          </div>
        </div>
      </>
    );
  }
}

/////////////////////////////////////////////////////////////////////////////////
export function mapStateToProps(state) {
  const { authReducer, lastReducer } = state;
  return { authReducer, lastReducer };
}

const mapDispatchToProps = (dispatch) => {
  return {
    login: (data) => { return authActions.login(data)(dispatch) },
    logout: () => { return authActions.logout()(dispatch) },
    save: (data) => { return userActions.save(data)(dispatch) },
  }
};
/////////////////////////////////////////////////////////////////////////////////

const HeaderForm = reduxForm({ form: 'formUser' })(Header);
const connectedHeader = connect(mapStateToProps, mapDispatchToProps);
let exportHeader = (connectedHeader)(HeaderForm);
exportHeader = withTranslation()(exportHeader);
export { exportHeader as Header };