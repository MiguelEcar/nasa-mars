import { lastConstants } from '../model.last.constants';
import { httpService } from '@http';
import { PATH } from '@http';

export const lastActions = {
    getLast,
};

let path = {
    base: PATH,
    path: 'last'
}

function getLast() {
    return dispatch => {
        dispatch(request());

        return httpService.get(path)
            .then(
                last => dispatch(success(last)),
                error => dispatch(failure(error.toString()))
            );
    };

    function request() { return { type: lastConstants.LAST_REQUEST } }
    function success(last) { return { type: lastConstants.LAST_SUCCESS, last } }
    function failure(error) { return { type: lastConstants.LAST_FAILURE, error } }
}