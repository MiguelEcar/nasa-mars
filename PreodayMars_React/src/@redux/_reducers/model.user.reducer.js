import { userConstants } from '../model.user.constants';

export function userReducer(state = {}, action) {

  switch (action.type) {
    case userConstants.USER_SAVE_REQUEST:
      state = {
        ...state,
        loading: true
      };
      break;
    case userConstants.USER_SAVE_SUCCESS:
      state = {
        ...state,
        user: action.user,
        success: true
      };
      break;
    case userConstants.USER_SAVE_FAILURE:
      state = {
        ...state,
        error: action.error
      };
      break;
    case userConstants.USER_INIT:
      state = {};
      break;
    ///////////////////////////////////////////
    default:
      return state;
  }
  return state;
}