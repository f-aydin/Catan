import { HexGrid, Hexagon, Layout, Pattern, Text } from "react-hexgrid";
import { useEffect, useState } from "react";

export function Board() {
  const [roadVisible, setRoadVisible] = useState(false);
  const [playerResources, setPlayerResources] = useState([]);
  const [dice, setDice] = useState(Number);

  async function addByDiceRoll() {
    const diceRoll =
      Math.floor(Math.random() * 6) + 1 + (Math.floor(Math.random() * 6) + 1);
    await fetch("http://localhost:8080/api/addOneByDice", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(diceRoll),
    });
    setDice(diceRoll);
    getRequest();
  }

  function handleClick() {
    setRoadVisible(true);
  }

  async function getRequest() {
    const response = await fetch("http://localhost:8080/api/playerResources");
    const data = await response.json();
    setPlayerResources(data);
  }

  function writePlayerResources(playerResources: any) {
    const listResources = playerResources.map(
      (item: {
        brick: any;
        wool: any;
        grain: any;
        lumber: any;
        ore: any;
        playerID: any;
      }) => (
        <div className="playerResources">
          <li key={item.playerID}>
            <p>
              <b>Player: {item.playerID + " > "} </b>
              Wool: {item.wool + " | "}
              Ore: {item.ore + " | "}
              Brick: {item.brick + " | "}
              Lumber: {item.lumber + " | "}
              Grain: {item.grain}
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

  function changeOpacity() {
    const button = document.getElementById("12");
    if (button != null) {
      button.style.setProperty("opacity", "1");
      button.style.setProperty("background-color", "red");
    }
  }

  return (
    <div className="App-header">
      <button
        onClick={() => {
          addByDiceRoll();
        }}
      >
        Roll Dice
      </button>

      {dice}

      {writePlayerResources(playerResources)}

      <HexGrid width={900} height={800} viewBox="-50 -50 100 100">
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

      <button className="row1" style={{ ["--fromright" as any]: "310px" }} />
      <button className="row1" style={{ ["--fromright" as any]: "510px" }} />
      <button className="row1" style={{ ["--fromright" as any]: "710px" }} />

      <button className="row2" style={{ ["--fromright" as any]: "350px" }} />
      <button className="row2" style={{ ["--fromright" as any]: "550px" }} />
      <button className="row2" style={{ ["--fromright" as any]: "750px" }} />
      <button className="row2" style={{ ["--fromright" as any]: "950px" }} />

      <button className="row3" style={{ ["--fromright" as any]: "510px" }} />
      <button className="row3" style={{ ["--fromright" as any]: "710px" }} />
      <button className="row3" style={{ ["--fromright" as any]: "910px" }} />
      <button className="row3" style={{ ["--fromright" as any]: "1110px" }} />

      <button className="row4" style={{ ["--fromright" as any]: "600px" }} />
      <button
        className="row4"
        id="12"
        style={{ ["--fromright" as any]: "790px" }}
        onClick={changeOpacity}
      />
      <button className="row4" style={{ ["--fromright" as any]: "990px" }} />
      <button className="row4" style={{ ["--fromright" as any]: "1190px" }} />
      <button className="row4" style={{ ["--fromright" as any]: "1390px" }} />

      <button className="row5" style={{ ["--fromright" as any]: "800px" }} />
      <button className="row5" style={{ ["--fromright" as any]: "990px" }} />
      <button className="row5" style={{ ["--fromright" as any]: "1190px" }} />
      <button className="row5" style={{ ["--fromright" as any]: "1390px" }} />
      <button className="row5" style={{ ["--fromright" as any]: "1590px" }} />

      <button className="row6" style={{ ["--fromright" as any]: "910px" }} />
      <button className="row6" style={{ ["--fromright" as any]: "1110px" }} />
      <button className="row6" style={{ ["--fromright" as any]: "1310px" }} />
      <button className="row6" style={{ ["--fromright" as any]: "1510px" }} />
      <button className="row6" style={{ ["--fromright" as any]: "1710px" }} />

      {/* {roadVisible && <div className="rectangle" />} */}
    </div>
  );
}
