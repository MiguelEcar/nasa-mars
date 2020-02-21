import { solConstants } from '../model.sol.constants';

export function solReducer(state = {}, action) {

  switch (action.type) {
    case solConstants.SOL_LIST_REQUEST:
      state = {
        ...state,
        loading: true
      };
      break;
    case solConstants.SOL_LIST_SUCCESS:
      state = {
        ...state,
        list: action.list,
        success: true
      };
      break;
    case solConstants.SOL_LIST_FAILURE:
      state = {
        ...state,
        error: action.error
      };
      break;
    ///////////////////////////////////////////
    case solConstants.SOL_BY_ID_REQUEST:
      state = {
        ...state,
        loading: true
      };
      break;
    case solConstants.SOL_BY_ID_SUCCESS:
      state = {
        ...state,
        sol: action.sol,
        success: true
      };
      break;
    case solConstants.SOL_BY_ID_FAILURE:
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