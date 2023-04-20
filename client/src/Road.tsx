import React from 'react';

function Road(coordinates: { x: string; y: string; }) {
    const style = {
        left: coordinates.x + 'px',
        top: coordinates.y + 'px',
        color: 'red',
        width: '20px',
        height: '100px'
    };
  
    return (
      <div className="road" style={style}></div>
    );
  }

  export default Road;