import { userConstants } from '../model.user.constants';
import { httpService } from '@http';
import { PATH } from '@http';

export const userActions = {
    save,
};

let path = {
    base: PATH,
    path: 'user'
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

    function request() { return { type: userConstants.SAVE_REQUEST } }
    function success(user) { return { type: userConstants.SAVE_SUCCESS, user } }
    function failure(error) { return { type: userConstants.SAVE_FAILURE, error } }
}
