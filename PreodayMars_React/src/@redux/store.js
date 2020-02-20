import { createStore, applyMiddleware } from 'redux';
import thunkMiddleware from 'redux-thunk';
import { createLogger } from 'redux-logger';
import localReducer from './_reducers';
import ReduxThunk from 'redux-thunk'

const loggerMiddleware = createLogger();

export const store = createStore(
    localReducer,
    {},
    applyMiddleware(
        ReduxThunk,
        thunkMiddleware,
        loggerMiddleware
    )
);