import axios from "axios";

import qs from 'qs'

export const httpAuthService = {
    login,
    tokenByRefresh,
    logout,
    addTokenHeader,
    deleteTokenHeader
};

function addTokenHeader(token) {
    axios.defaults.headers.common['Authorization'] = 'Bearer ' + token;
};

function deleteTokenHeader() {
    delete axios.defaults.headers.common['Authorization'];
};

function req(path, headers, body) {
    axios.defaults.withCredentials = true;
    return axios.request({
        withCredentials: true,
        url: '/oauth/token',
        method: 'POST',
        baseURL: path.base,
        timeout: 1000,
        headers: headers,
        data: qs.stringify(body),
    }).then(response => {
        return response;
    });
};

function login(path, fields) {

    const headers = {
        'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8',
        'Authorization': 'Basic cmVhY3QtY2xpOkBwcmVvZGF5LW1hcnM='
    };
    const body = {
        client: 'react-cli',
        username: fields.email,
        password: fields.password,
        grant_type: 'password',
    }

    // store user details and jwt token in local storage to keep user logged in between page refreshes
    var data = req(path, headers, body)
        .then(handleResponse)
        .then(data => {
            localStorage.setItem('mars-token', data.access_token);
            return data;
        });
    return data;
}

async function tokenByRefresh(path) {

    let user = localStorage.getItem('mars-token');

    if (user) {
        const headers = {
            'Content-Type': 'application/x-www-form-urlencoded',
            'Authorization': 'Basic cmVhY3QtY2xpOkBwcmVvZGF5LW1hcnM=',
        };
        const body = {
            client: 'react-cli',
            grant_type: 'refresh_token'
        }

        const response = await req(path, headers, body);
        localStorage.setItem('mars-token', response.data.access_token);

        return response;
    }

};


function logout() {
    // remove user from local storage to log user out
    localStorage.removeItem('mars-token');
}

function handleResponse(response) {

    if (response.status === 200) {
        return response.data;
    } else {
        if (response.status === 401) {
            // auto logout if 401 response returned from api
            logout();
            window.location.reload(true);
        }

        const error = (response.data && response.data.message) || response.statusText;
        return Promise.reject(error);
    }
}