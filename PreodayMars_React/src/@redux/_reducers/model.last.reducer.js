import { lastConstants } from '../model.last.constants';

export function lastReducer(state = {}, action) {

  switch (action.type) {
    case lastConstants.LAST_REQUEST:
      state = {
        ...state,
        loading: true
      };
      break;
    case lastConstants.LAST_SUCCESS:
      state = {
        ...state,
        last: action.last,
        success: true
      };
      break;
    case lastConstants.LAST_FAILURE:
      state = {
        ...state,
        error: action.error
      };
      break;
    ///////////////////////////////////////////
    default:
      return state;
  }
  return state;
}