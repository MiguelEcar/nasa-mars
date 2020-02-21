import { combineReducers } from 'redux';

import { authReducer } from './model.auth.reducer';
import { solReducer } from './model.sol.reducer';
import { userReducer } from './model.user.reducer';

import { reducer as formReducer } from 'redux-form'

const localReducer = combineReducers({
    form: formReducer,
    authReducer,
    solReducer,
    userReducer,
});

export default localReducer;