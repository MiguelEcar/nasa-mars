import React from 'react';
import { render } from 'react-dom';
import { Provider } from 'react-redux';

import { store } from '@redux';
import { App } from './App';

import { I18nextProvider } from "react-i18next";
import i18n from "./i18n";

render(
    <Provider store={store}>
        <I18nextProvider i18n={i18n}>
            <App />
        </I18nextProvider>
    </Provider>,
    document.getElementById('app')
);