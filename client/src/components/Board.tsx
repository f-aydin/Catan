import { HexGrid, Hexagon, Layout, Pattern, Text } from "react-hexgrid";
import { useEffect, useState, MouseEvent } from "react";
import board from '../icons/catan_tile.png'
import Card from './Card'
import TradingForm from './TradingForm'
import Button from './Button'

export function Board() {
  const [playerResources, setPlayerResources] = useState([]);
  const [dice, setDice] = useState(0);
  const [tile1, setTile1] = useState(Number);
  const [tile2, setTile2] = useState(Number);
  const [tile3, setTile3] = useState(Number);
  const [buildingType, setBuildingType] = useState("SETTLEMENT");
  const [playerTurn, setPlayerTurn] = useState(1);
  const [diceRolled, setDiceRolled] = useState(false);
  const [tradingPopupVisible, setTradingPopupVisible] = useState(true);
  

  async function addByDiceRoll() {
    const diceRoll = 
      Math.floor(Math.random() * 6) + 1 + (Math.floor(Math.random() * 6) + 1);
    const response = await fetch("http://localhost:8080/api/addOneByDice", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(diceRoll)
    });
    const data = await response.json();
    setDice(diceRoll);
    getRequest();
    if(diceRoll === 7){
      moveRobber(); 
    } 
  }

  async function moveRobber() {
    let robberPlace = Number(prompt("Choose a tile number between 1 and 19: "))
    while(robberPlace < 1 && robberPlace > 19){
      robberPlace = Number(prompt("Choose a tile number between 1 and 19: "))
    }
    const response = await fetch("http://localhost:8080/api/placeRobberOnTile ", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(robberPlace - 1)
    });

    const robberDiv = document.getElementById("robber");
      if(robberDiv != null && robberPlace > 0 && robberPlace < 20){
        robberDiv.style.setProperty("right", String(robberLocations[robberPlace - 1][1]));
        robberDiv.style.setProperty("bottom", String(robberLocations[robberPlace - 1][2]));
      }
  }

  async function getRequest() {
    const response = await fetch("http://localhost:8080/api/playerResources");
    const data = await response.json();
    setPlayerResources(data);
  }

  function writePlayerResources(playerResources: any) {
    const listResources = playerResources.map(
      (player: {
        brick: any;
        wool: any;
        grain: any;
        lumber: any;
        ore: any;
        playerID: any;
        devCards: any;
      }) => (
        <div className="playerResources">
          <li key={player.playerID}>
            <p>
              <b>Player: {player.playerID + " > "} </b>
              Wool: {player.wool + " | "}
              Ore: {player.ore + " | "}
              Brick: {player.brick + " | "}
              Lumber: {player.lumber + " | "}
              Grain: {player.grain + " | "}
              DevCards: {player.devCards.map((card : any) =>  <Card cardType={card.type} playerTurn={playerTurn} />)}
            </p>
          </li>
        </div>
      )
    );
    return <ul>{listResources}</ul>;
  }

  useEffect(() => {
    getRequest();
  }, []);

  function changeOpacity(e: MouseEvent<HTMLElement>) {
    const button = document.getElementById(e.currentTarget.id);
    if (button != null) {
      if(buildingType == "CITY"){
        button.style.setProperty("border-radius", "0%")
      }
      button.style.setProperty("opacity", "1");
      switch(playerTurn){
        case 1:
          button.style.setProperty("background-color", "red");
          break;
        case 2:
          button.style.setProperty("background-color", "blue");
          break;
        case 3:
          button.style.setProperty("background-color", "green");
          break;
        case 4:
          button.style.setProperty("background-color", "yellow");
          break;
      }
    }
  }

  async function buildSettlement() {
    const response = await fetch("http://localhost:8080/api/buildSettlement", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({
        playerID: playerTurn,
        tile1: tile1 - 1,
        tile2: tile2 - 1,
        tile3: tile3 - 1,
        type: buildingType
      })
    });
  }

  function changeButtonDisabilityByClicking() {
    const diceRollButton = document.getElementById("diceRoll") as HTMLButtonElement;
    const switchTurnButton = document.getElementById("switchTurn") as HTMLButtonElement;
    const buildButton = document.getElementById("buildButton") as HTMLButtonElement;
    if(diceRollButton != null && switchTurnButton != null && buildButton != null){
      if(diceRolled && switchTurnButton && buildButton){
        diceRollButton.disabled = false;
        switchTurnButton.disabled = true;
        buildButton.disabled = true;
      } else {
        diceRollButton.disabled = true;
        switchTurnButton.disabled = false;
        buildButton.disabled = false;
      }
    }
  }

  async function changeTurn() {
    const response = await fetch("http://localhost:8080/api/switchTurn")
    const data = await response.json();
    setPlayerTurn(data);
  }

    const robberLocations = [
      [1, "590px", "720px"],
      [2, "430px", "720px"],
      [3, "270px", "720px"],
      [4, "670px", "600px"],
      [5, "510px", "600px"],
      [6, "350px", "600px"],
      [7, "190px", "600px"],
      [8, "750px", "480px"],
      [9, "590px", "480px"],
      [10, "430px", "480px"],
      [11, "270px", "480px"],
      [12, "110px", "480px"],
      [13, "670px", "360px"],
      [14, "510px", "360px"],
      [15, "350px", "360px"],
      [16, "190px", "360px"],
      [17, "590px", "240px"],
      [18, "430px", "240px"],
      [19, "270px", "240px"]
    ]

  async function buyDevelopmentCard(){
    const response = await fetch("http://localhost:8080/api/buyDevCard", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: String(playerTurn)
    })
  }

  function handleTrade() {
    setTradingPopupVisible(!tradingPopupVisible);
  }

  return (
    <div className="App-header">
      <button id="diceRoll"
        onClick={() => {
          setDiceRolled(true); addByDiceRoll(); changeButtonDisabilityByClicking()
        }}
      >
        Roll Dice
      </button>
      {dice}
      
      <div id="playerturn">
        Player turn: {playerTurn}
        <button id="switchTurn" onClick={() => {changeTurn(); setDiceRolled(false); changeButtonDisabilityByClicking()}}>End Turn</button>
      </div>
       
      <div>
        Tile 1:
        <input size={1} placeholder={"Tile 1"} value={tile1} onChange={(e) => setTile1(Number(e.target.value))} />
        Tile 2:
        <input size={1} placeholder={"Tile 2"} value={tile2} onChange={(e) => setTile2(Number(e.target.value))}/>
        Tile 3:
        <input size={1} placeholder={"Tile 3"} value={tile3} onChange={(e) => setTile3(Number(e.target.value))}/>
        <label htmlFor="type">Type: </label>
          <select size={1} id="type" name="type" onChange={(e) => setBuildingType(String(e.target.value))}>
            <option value="SETTLEMENT" selected>Settlement</option>
            <option value="CITY">City</option>
          </select>
        <button
          id="buildButton"
          onClick={() => {
            buildSettlement();
          }}
        >
          Build!
        </button>
      </div>
      {writePlayerResources(playerResources)}
      <button onClick={buyDevelopmentCard}>Buy Development Card</button>
      <div id="hover">View Board: </div>
      <img id="catantiles" src={board}></img>

      <div id="trade">
        <button onClick={() => handleTrade()}>Trade</button>
        {tradingPopupVisible && <TradingForm playerTurn = {playerTurn}/>}
      </div>
      

<div style={{position: "relative", width: 900, height: 900}}>
      <HexGrid width={900} height={800} viewBox="-50 -50 100 100" style={{position: "absolute", top:0,left:0}}>
        <Layout
          size={{ x: 12, y: 10.5 }}
          flat={false}
          spacing={0.97}
          origin={{ x: 0, y: 0 }}
        >
          <Hexagon key={1} q={0} r={-2} s={2} data={10} fill="ore">
            <Text>10</Text>
          </Hexagon>
          <Hexagon key={2} q={1} r={-2} s={1} data={2} fill="wool">
            <Text>2</Text>
          </Hexagon>
          <Hexagon key={3} q={2} r={-2} s={0} data={9} fill="lumber">
            <Text>9</Text>
          </Hexagon>
          <Hexagon key={4} q={-1} r={-1} s={2} data={12} fill="grain">
            <Text>12</Text>
          </Hexagon>
          <Hexagon key={5} q={0} r={-1} s={1} data={6} fill="brick">
            <Text>6</Text>
          </Hexagon>
          <Hexagon key={6} q={1} r={-1} s={0} data={4} fill="wool">
            <Text>4</Text>
          </Hexagon>
          <Hexagon key={7} q={2} r={-1} s={-1} data={10} fill="brick">
            <Text>10</Text>
          </Hexagon>
          <Hexagon key={8} q={-2} r={0} s={2} data={9} fill="grain">
            <Text>9</Text>
          </Hexagon>
          <Hexagon key={9} q={-1} r={0} s={1} data={11} fill="lumber">
            <Text>11</Text>
          </Hexagon>
          <Hexagon key={10} q={0} r={0} s={0} data={7} fill="desert">
            <Text>7</Text>
          </Hexagon>
          <Hexagon key={11} q={1} r={0} s={-1} data={3} fill="lumber">
            <Text>3</Text>
          </Hexagon>
          <Hexagon key={12} q={2} r={0} s={-2} data={8} fill="ore">
            <Text>8</Text>
          </Hexagon>
          <Hexagon key={13} q={-2} r={1} s={1} data={8} fill="lumber">
            <Text>8</Text>
          </Hexagon>
          <Hexagon key={14} q={-1} r={1} s={0} data={3} fill="ore">
            <Text>3</Text>
          </Hexagon>
          <Hexagon key={15} q={0} r={1} s={-1} data={4} fill="grain">
            <Text>4</Text>
          </Hexagon>
          <Hexagon key={16} q={1} r={1} s={-2} data={5} fill="wool">
            <Text>5</Text>
          </Hexagon>
          <Hexagon key={17} q={-2} r={2} s={0} data={5} fill="brick">
            <Text>5</Text>
          </Hexagon>
          <Hexagon key={18} q={-1} r={2} s={-1} data={6} fill="grain">
            <Text>6</Text>
          </Hexagon>
          <Hexagon key={19} q={0} r={2} s={-2} data={11} fill="wool">
            <Text>11</Text>
          </Hexagon>
          <Pattern
            id="grain"
            link="https://colonist.io/dist/images/tile_grain.svg"
          />
          <Pattern
            id="lumber"
            link="https://colonist.io/dist/images/tile_lumber.svg"
          />
          <Pattern
            id="ore"
            link="https://colonist.io/dist/images/tile_ore.svg"
          />
          <Pattern
            id="wool"
            link="https://colonist.io/dist/images/tile_wool.svg"
          />
          <Pattern
            id="brick"
            link="https://colonist.io/dist/images/tile_brick.svg"
          />
          <Pattern
            id="desert"
            link="https://colonist.io/dist/images/tile_desert.svg"
          />
        </Layout>
      </HexGrid>

      <div id="robber" style={{ ["--fromright" as any]: "430px",  ["--frombottom" as any]: "480px"  }}/>

      <button id="1" className="vertices" style={{ ["--fromright" as any]: "590px" , ["--frombottom" as any]: "800px"}} onClick={changeOpacity}/>
      <button id="2" className="vertices" style={{ ["--fromright" as any]: "430px", ["--frombottom" as any]: "800px" }} onClick={changeOpacity}/>
      <button id="3" className="vertices" style={{ ["--fromright" as any]: "270px", ["--frombottom" as any]: "800px" }} onClick={changeOpacity}/>

      <button id="4" className="vertices" style={{ ["--fromright" as any]: "670px", ["--frombottom" as any]: "760px" }}  onClick={changeOpacity}/>
      <button id="5" className="vertices" style={{ ["--fromright" as any]: "510px", ["--frombottom" as any]: "760px" }}  onClick={changeOpacity}/>
      <button id="6" className="vertices" style={{ ["--fromright" as any]: "350px", ["--frombottom" as any]: "760px" }}  onClick={changeOpacity}/>
      <button id="7" className="vertices" style={{ ["--fromright" as any]: "190px", ["--frombottom" as any]: "760px" }}  onClick={changeOpacity}/>

      <button id="8" className="vertices" style={{ ["--fromright" as any]: "670px", ["--frombottom" as any]: "680px" }} onClick={changeOpacity}/>
      <button id="9" className="vertices" style={{ ["--fromright" as any]: "510px", ["--frombottom" as any]: "680px" }} onClick={changeOpacity}/>
      <button id="10" className="vertices" style={{ ["--fromright" as any]: "350px", ["--frombottom" as any]: "680px" }} onClick={changeOpacity}/>
      <button id="11" className="vertices" style={{ ["--fromright" as any]: "190px", ["--frombottom" as any]: "680px" }} onClick={changeOpacity}/>

      <button id="12" className="vertices" style={{ ["--fromright" as any]: "750px",  ["--frombottom" as any]: "640px" }} onClick={changeOpacity}/>
      <button
        className="vertices"
        id="13"
        style={{ ["--fromright" as any]: "590px",  ["--frombottom" as any]: "640px"  }}
        onClick={changeOpacity}
      />
      <button id="14" className="vertices" style={{ ["--fromright" as any]: "430px",  ["--frombottom" as any]: "640px"  }} onClick={changeOpacity}/>
      <button id="15" className="vertices" style={{ ["--fromright" as any]: "270px",  ["--frombottom" as any]: "640px"  }} onClick={changeOpacity}/>
      <button id="16" className="vertices" style={{ ["--fromright" as any]: "110px",  ["--frombottom" as any]: "640px"  }} onClick={changeOpacity}/>

      <button id="17" className="vertices" style={{ ["--fromright" as any]: "750px",  ["--frombottom" as any]: "560px"  }} onClick={changeOpacity}/>
      <button id="18" className="vertices" style={{ ["--fromright" as any]: "590px",  ["--frombottom" as any]: "560px" }} onClick={changeOpacity}/>
      <button id="19" className="vertices" style={{ ["--fromright" as any]: "430px",  ["--frombottom" as any]: "560px" }} onClick={changeOpacity}/>
      <button id="20" className="vertices" style={{ ["--fromright" as any]: "270px",  ["--frombottom" as any]: "560px" }} onClick={changeOpacity}/>
      <button id="21" className="vertices" style={{ ["--fromright" as any]: "110px",  ["--frombottom" as any]: "560px" }} onClick={changeOpacity}/>

      <button id="22" className="vertices" style={{ ["--fromright" as any]: "830px",  ["--frombottom" as any]: "520px" }} onClick={changeOpacity}/>
      <button id="23" className="vertices" style={{ ["--fromright" as any]: "670px",  ["--frombottom" as any]: "520px" }} onClick={changeOpacity}/>
      <button id="24" className="vertices" style={{ ["--fromright" as any]: "510px",  ["--frombottom" as any]: "520px" }} onClick={changeOpacity}/>
      <button id="25" className="vertices" style={{ ["--fromright" as any]: "350px",  ["--frombottom" as any]: "520px" }} onClick={changeOpacity}/>
      <button id="26" className="vertices" style={{ ["--fromright" as any]: "190px",  ["--frombottom" as any]: "520px" }} onClick={changeOpacity}/>
      <button id="27" className="vertices" style={{ ["--fromright" as any]: "30px",  ["--frombottom" as any]: "520px" }} onClick={changeOpacity}/>

      <button id="28" className="vertices" style={{ ["--fromright" as any]: "830px",  ["--frombottom" as any]: "440px" }} onClick={changeOpacity}/>
      <button id="29" className="vertices" style={{ ["--fromright" as any]: "670px",  ["--frombottom" as any]: "440px" }} onClick={changeOpacity}/>
      <button id="30" className="vertices" style={{ ["--fromright" as any]: "510px",  ["--frombottom" as any]: "440px" }} onClick={changeOpacity}/>
      <button id="31" className="vertices" style={{ ["--fromright" as any]: "350px",  ["--frombottom" as any]: "440px" }} onClick={changeOpacity}/>
      <button id="32" className="vertices" style={{ ["--fromright" as any]: "190px",  ["--frombottom" as any]: "440px" }} onClick={changeOpacity}/>
      <button id="33" className="vertices" style={{ ["--fromright" as any]: "30px",  ["--frombottom" as any]: "440px" }} onClick={changeOpacity}/>

      <button id="34" className="vertices" style={{ ["--fromright" as any]: "750px",  ["--frombottom" as any]: "400px"  }} onClick={changeOpacity}/>
      <button id="35" className="vertices" style={{ ["--fromright" as any]: "590px",  ["--frombottom" as any]: "400px" }} onClick={changeOpacity}/>
      <button id="36" className="vertices" style={{ ["--fromright" as any]: "430px",  ["--frombottom" as any]: "400px" }} onClick={changeOpacity}/>
      <button id="37" className="vertices" style={{ ["--fromright" as any]: "270px",  ["--frombottom" as any]: "400px" }} onClick={changeOpacity}/>
      <button id="38" className="vertices" style={{ ["--fromright" as any]: "110px",  ["--frombottom" as any]: "400px" }} onClick={changeOpacity}/>

      <button id="39" className="vertices" style={{ ["--fromright" as any]: "750px",  ["--frombottom" as any]: "320px"  }} onClick={changeOpacity}/>
      <button id="40" className="vertices" style={{ ["--fromright" as any]: "590px",  ["--frombottom" as any]: "320px" }} onClick={changeOpacity}/>
      <button id="41" className="vertices" style={{ ["--fromright" as any]: "430px",  ["--frombottom" as any]: "320px" }} onClick={changeOpacity}/>
      <button id="42" className="vertices" style={{ ["--fromright" as any]: "270px",  ["--frombottom" as any]: "320px" }} onClick={changeOpacity}/>
      <button id="43" className="vertices" style={{ ["--fromright" as any]: "110px",  ["--frombottom" as any]: "320px" }} onClick={changeOpacity}/>

      <button id="44" className="vertices" style={{ ["--fromright" as any]: "670px", ["--frombottom" as any]: "280px" }} onClick={changeOpacity}/>
      <button id="45" className="vertices" style={{ ["--fromright" as any]: "510px", ["--frombottom" as any]: "280px" }} onClick={changeOpacity}/>
      <button id="46" className="vertices" style={{ ["--fromright" as any]: "350px", ["--frombottom" as any]: "280px" }} onClick={changeOpacity}/>
      <button id="47" className="vertices" style={{ ["--fromright" as any]: "190px", ["--frombottom" as any]: "280px" }} onClick={changeOpacity}/>

      <button id="48" className="vertices" style={{ ["--fromright" as any]: "670px", ["--frombottom" as any]: "200px" }} onClick={changeOpacity}/>
      <button id="49" className="vertices" style={{ ["--fromright" as any]: "510px", ["--frombottom" as any]: "200px" }} onClick={changeOpacity}/>
      <button id="50" className="vertices" style={{ ["--fromright" as any]: "350px", ["--frombottom" as any]: "200px" }} onClick={changeOpacity}/>
      <button id="51" className="vertices" style={{ ["--fromright" as any]: "190px", ["--frombottom" as any]: "200px" }} onClick={changeOpacity}/>

      <button id="52" className="vertices" style={{ ["--fromright" as any]: "590px" , ["--frombottom" as any]: "160px"}} onClick={changeOpacity}/>
      <button id="53" className="vertices" style={{ ["--fromright" as any]: "430px", ["--frombottom" as any]: "160px" }} onClick={changeOpacity}/>
      <button id="54" className="vertices" style={{ ["--fromright" as any]: "270px", ["--frombottom" as any]: "160px" }} onClick={changeOpacity}/>

      </div>
    </div>
  );
}