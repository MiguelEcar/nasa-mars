import React from "react";
import { Header } from "./";


class Theme extends React.Component {

  render() {

    return (
      <>
        <div className="wrapper">
          {/* %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% */}
          <Header />
          {/* %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% */}
          <div className='content'>
            {/* %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% */}
            {this.props.children}
            {/* %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% */}
          </div>
        </div>
      </>
    );
  }
}

export { Theme };