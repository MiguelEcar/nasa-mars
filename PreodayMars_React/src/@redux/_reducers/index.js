import { combineReducers } from 'redux';

import { authReducer } from './model.auth.reducer';
import { solReducer } from './model.sol.reducer';
import { userReducer } from './model.user.reducer';
import { lastReducer } from './model.last.reducer';

import { reducer as formReducer } from 'redux-form'

const localReducer = combineReducers({
    form: formReducer,
    authReducer,
    solReducer,
    userReducer,
    lastReducer,
});

export default localReducer;