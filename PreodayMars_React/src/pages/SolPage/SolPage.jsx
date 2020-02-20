import React from 'react';
import { withTranslation } from 'react-i18next';
import { withRouter } from 'react-router-dom';
import { connect } from 'react-redux';

import { solActions } from '@redux';

import { Link } from "react-router-dom";
import Card from 'react-bootstrap/card';

import Btn from 'react-bootstrap/button';

class SolPage extends React.Component {

    /////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////////////////////////////////////////////////////////
    render() {
        const { t } = this.props;
        const { sol } = this.props.solReducer;

        return (
            <>
                {sol &&

                    <Card>
                        <Card.Header>{sol.sol}</Card.Header>
                        <Card.Body>
                            <Card.Text>
                                {t('def_average')} {sol.sensor.average.celsius + ' °C'} | {sol.sensor.average.fahrenheit + ' °F'}
                            </Card.Text>
                            <hr />
                            <Card.Text>
                                {t('def_minimum')} {sol.sensor.minimum.celsius + ' °C'} | {sol.sensor.minimum.fahrenheit + ' °F'}
                            </Card.Text>
                            <Card.Text>
                                {t('def_maximum')} {sol.sensor.maximum.celsius + ' °C'} | {sol.sensor.maximum.fahrenheit + ' °F'}
                            </Card.Text>
                        </Card.Body>
                        <Card.Footer>
                            <i style={{ float: 'right' }} >{t('def_first')}{sol.dateFirst.clean}</i>
                            <br />
                            <i style={{ float: 'right' }} >{t('def_last')}{sol.dateLast.clean}</i>
                        </Card.Footer>
                    </Card>
                }
                <Link to="/">
                    <Btn>{t('def_return')}</Btn>
                </Link>

            </>
        );
    }
}

/////////////////////////////////////////////////////////////////////////////////
export function mapStateToProps(state) {
    const { authReducer, solReducer } = state;
    return { authReducer, solReducer };
}

export const mapDispatchToProps = { ...solActions }
/////////////////////////////////////////////////////////////////////////////////

const connectedSolPage = connect(mapStateToProps, mapDispatchToProps);
let exportSolPage = (connectedSolPage)(SolPage);
exportSolPage = withTranslation()(exportSolPage);
exportSolPage = withRouter(exportSolPage);
export { exportSolPage as SolPage };