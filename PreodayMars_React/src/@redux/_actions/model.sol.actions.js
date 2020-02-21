import { solConstants } from '../model.sol.constants';
import { httpService } from '@http';
import { PATH } from '@http';

export const solActions = {
    getAll,
    getById,
};

let path = {
    base: PATH,
    path: 'nasa'
}

function getAll() {
    path = {
        ...path,
        args: '/all'
    }
    return dispatch => {
        dispatch(request());

        httpService.get(path)
            .then(
                list => dispatch(success(list)),
                error => dispatch(failure(error.toString()))
            );
    };

    function request() { return { type: solConstants.SOL_LIST_REQUEST } }
    function success(list) { return { type: solConstants.SOL_LIST_SUCCESS, list } }
    function failure(error) { return { type: solConstants.SOL_LIST_FAILURE, error } }
}


function getById(id) {
    path = {
        ...path,
        args: '?sol=' + id
    }
    return dispatch => {
        dispatch(request());

        httpService.get(path)
            .then(
                sol => dispatch(success(sol)),
                error => dispatch(failure(error.toString()))
            );
    };

    function request() { return { type: solConstants.SOL_BY_ID_REQUEST } }
    function success(sol) { return { type: solConstants.SOL_BY_ID_SUCCESS, sol } }
    function failure(error) { return { type: solConstants.SOL_BY_ID_FAILURE, error } }
}