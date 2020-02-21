import { authConstants } from '../model.auth.constants';
import { httpAuthService } from '@http';
import { history } from '@theme';
import { PATH } from '@http';

export const authActions = {
    login,
    logout
};


let path = {
    base: PATH,
    path: ''
}

function login(data) {

    return dispatch => {
        dispatch(request({ user: data.email }));

        return httpAuthService.login(path, data)
            .then(
                user => {
                    dispatch(success(user.access_token));
                    history.push('/');
                },
                error => {
                    dispatch(failure(error.toString()));
                }
            );
    };

    function request(user) { return { type: authConstants.LOGIN_REQUEST, user } }
    function success(user) { return { type: authConstants.LOGIN_SUCCESS, user } }
    function failure(error) { return { type: authConstants.LOGIN_FAILURE, error } }
}

function logout() {
    return dispatch => {
        httpAuthService.logout();
        dispatch(request());
    }

    function request() { return { type: authConstants.LOGOUT } }
}
