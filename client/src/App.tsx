import React, { useState } from 'react';
import logo from './logo.svg';
import './App.css';

function App() {
  const [agePlayer, setAgePlayer] = useState("");
  const [namePlayer, setNamePlayer] = useState("");

  return (
    
    <div className="App">
      <header className="App-header">
        <input type='number' value={agePlayer} onChange={(e) => setAgePlayer(e.target.value)}/>
        <input type='text' value={namePlayer} onChange={(e) => setNamePlayer(e.target.value)}/>
        <button onClick={storeInDB}>Add me!</button>
      </header>
    </div>
  );
  async function consoleLog(){
    const response = await fetch('http://localhost:8080/api');
    const data = await response.json();
    console.log(data);
  }
  
  async function storeInDB(){
    const response = await fetch('http://localhost:8080/api', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({
        age: Number(agePlayer),
        firstname: namePlayer,
      })
    });
    
  }
}



export default App;
