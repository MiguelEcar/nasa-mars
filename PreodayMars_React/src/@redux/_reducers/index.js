import { combineReducers } from 'redux';

import { authReducer } from './model.auth.reducer';
import { solReducer } from './model.sol.reducer';


const localReducer = combineReducers({
    authReducer,
    solReducer,
});

export default localReducer;