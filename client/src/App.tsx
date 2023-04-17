import React from 'react';
import logo from './logo.svg';
import './App.css';

function App() {
  return (
    
    <div className="App">
      <header className="App-header">
        <button onClick={addOne}>Click me!</button>
      </header>
    </div>
  );
}

async function addOne(){
  const peopleAPI = 'http://localhost:8080/api/player1';
  const response = await fetch(peopleAPI);
  const data = await response.json();
  console.log(data);
}

export default App;
