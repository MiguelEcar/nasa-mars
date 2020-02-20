import { authConstants } from '@redux';
import { httpService } from '@http';
import { history } from '@theme';

export const authActions = {
    login,
    logout
};

function login(path, username, password) {
    return dispatch => {
        dispatch(request({ username }));

        httpService.login(path, username, password)
            .then(
                user => {
                    dispatch(success(user.access_token));
                    history.push('/');
                },
                error => {
                    dispatch(failure(error.toString()));
                    // dispatch(alertActions.error(error.toString()));
                }
            );
    };

    function request(user) { return { type: authConstants.LOGIN_REQUEST, user } }
    function success(user) { return { type: authConstants.LOGIN_SUCCESS, user } }
    function failure(error) { return { type: authConstants.LOGIN_FAILURE, error } }
}

function logout() {
    httpService.logout();
    return { type: authConstants.LOGOUT };
}
