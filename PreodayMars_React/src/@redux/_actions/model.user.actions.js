import { userConstants } from '../model.user.constants';
import { httpService } from '@http';
import { PATH } from '@http';

export const userActions = {
    save,
    init,
};

let path = {
    base: PATH,
    path: 'user'
}

function init() {

    return dispatch => {
        dispatch(request());
    }

    function request() { return { type: userConstants.USER_INIT } }
}

function save(user) {

    return dispatch => {
        dispatch(request());

        return httpService.post(path, user)
            .then(
                user => {
                    dispatch(success(user));
                },
                error => {
                    dispatch(failure(error.toString()));
                }
            );
    };

    function request() { return { type: userConstants.USER_SAVE_REQUEST } }
    function success(user) { return { type: userConstants.USER_SAVE_SUCCESS, user } }
    function failure(error) { return { type: userConstants.USER_SAVE_FAILURE, error } }
}
