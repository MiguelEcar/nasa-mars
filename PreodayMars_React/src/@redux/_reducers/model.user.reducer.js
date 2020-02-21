import { userConstants } from '../model.user.constants';

export function userReducer(state = {}, action) {

  switch (action.type) {
    case userConstants.SAVE_REQUEST:
      state = {
        ...state,
        loading: true
      };
      break;
    case userConstants.SAVE_SUCCESS:
      state = {
        ...state,
        user: action.user,
        success: true
      };
      break;
    case userConstants.SAVE_FAILURE:
      state = {
        ...state,
        error: action.error
      };
      break;
    ///////////////////////////////////////////
    default:
      state = {}
  }
  return state;
}