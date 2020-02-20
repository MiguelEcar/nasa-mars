import { authConstants } from '../model.auth.constants';

export function authReducer(state = {}, action) {

  switch (action.type) {
    ///////////////////////////////////////////
    case authConstants.LOGIN_REQUEST:
      return {
        loggingIn: true,
        user: action.user
      };
    ///////////////////////////////////////////
    case authConstants.LOGIN_SUCCESS:
      return {
        loggedIn: true,
        user: action.user
      };
    ///////////////////////////////////////////
    case authConstants.LOGIN_FAILURE:
      return {};
    ///////////////////////////////////////////
    case authConstants.LOGOUT:
      return {};
    ///////////////////////////////////////////
    default:
      return state
  }

}