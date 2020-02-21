import React from 'react';
import { withTranslation } from 'react-i18next';
import { withRouter } from 'react-router-dom';
import { connect } from 'react-redux';

import { solActions } from '@redux';

import { Carousel } from 'primereact/carousel';

import Card from 'react-bootstrap/card';
import Container from 'react-bootstrap/container';
import Row from 'react-bootstrap/row';
import Col from 'react-bootstrap/col';

import { history } from '@theme';


class HomePage extends React.Component {

    /////////////////////////////////////////////////////////////////////////////////
    async componentDidMount() {
        await this.props.getAll();
    }
    /////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////////////////////////////////////////////////////////
    onClick = async (sol) => {
        await this.props.getById(sol.sol);
        history.push("/details");
    }
    /////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////////////////////////////////////////////////////////
    card = (t, sol) => {
        return (
            <Card onClick={(e) => this.onClick(sol)} style={{ cursor: 'pointer' }} >
                <Card.Header>{sol.sol}</Card.Header>
                <Card.Body>
                    <Card.Text>{t('def_average')}</Card.Text>
                    <Card.Text>
                        {sol.average.celsius + ' °C'} | {sol.average.fahrenheit + ' °F'}
                    </Card.Text>
                </Card.Body>
                <Card.Footer>
                    <i style={{ float: 'right' }} >{sol.dateLast.clean}</i>
                </Card.Footer>
            </Card>
        )
    }
    /////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////////////////////////////////////////////////////////
    render() {
        const { t } = this.props;
        const { solReducer } = this.props;
        const { list } = solReducer;

        return (
            <>
                {solReducer.error && <i className='badge badge-danger'>{t('access_server_error')}</i>}
                {list &&
                    <Container>
                        <Row>
                            <label>{t('def_last')}</label>
                            <Col md="auto">
                                {this.card(t, list[0])}
                            </Col>
                        </Row>
                        <hr />
                        <Row>
                            <label>{t('def_history')}</label>
                            <Col>
                                <Carousel value={list} numVisible={5} numScroll={1}
                                    itemTemplate={(sol) => this.card(t, sol)}
                                />
                            </Col>
                        </Row>
                    </Container>
                }
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

const connectedHomePage = connect(mapStateToProps, mapDispatchToProps);
let exportHomePage = (connectedHomePage)(HomePage);
exportHomePage = withTranslation()(exportHomePage);
exportHomePage = withRouter(exportHomePage);
export { exportHomePage as HomePage };