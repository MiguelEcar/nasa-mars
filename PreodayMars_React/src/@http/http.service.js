import axios from "axios";
import qs from 'qs'

import { httpAuthService } from './';

export const httpService = {
    post,
    get
};

function post(path, fields) {

    const body = {
        name: fields.name,
        email: fields.email,
        password: fields.password,
    }

    return axios({
        headers: { 'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8' },
        method: 'post',
        url: path.base + path.path,
        data: qs.stringify(body)
    });
}

function get(path) {
    return req('get', path)
        .then(handleResponse)
        .then(data => {
            return data;
        });
}

function req(method, path, data) {
    var res = httpAuthService.tokenByRefresh(path).then(response => {
        httpAuthService.addTokenHeader(response.data.access_token);

        let url = path.args === undefined ? path.base + path.path : path.base + path.path + path.args;
        return axios({
            method: method,
            url: url,
            data: data
        });
    });
    return res;
}

function handleResponse(response) {

    if (response.status === 200
        || response.status === 201
        || response.status === 204) {
        return response.data;
    } else {
        if (response.status === 401) {
            window.location.reload(true);
        }

        const error = (response.data && response.data.message) || response.statusText;
        return Promise.reject(error);
    }
}