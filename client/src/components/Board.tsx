import {HexGrid, Hexagon, Layout, Pattern } from "react-hexgrid";
import { Dispatch, SetStateAction, useState } from "react";
import { log } from "console";

export function Board() {

    const [roadVisible, setRoadVisible] = useState(false);
    const [playerResources, setPlayerResources] = useState([]);

    function handleClick() {
        setRoadVisible(true)
      }

    async function getRequest(){
        const response = await fetch("http://localhost:8080/api/playerResources");
        const data = await response.json();
        writePlayerResources(data);
    }

    function writePlayerResources(playerResource : any){
        playerResource.map((item: {
            brick: any;
            wool: any;
            grain: any;
            lumber: any;
            ore: any; playerID: any; 
        }) => console.log(item.playerID, item.ore, item.lumber, item.grain, item.wool, item.brick))
    }

    
    getRequest()

    return (
    <div className='App-header'>
        <HexGrid width={900} height={800} viewBox="-50 -50 100 100">
        <Layout size={{ x: 12, y: 10.5 }} flat={false} spacing={0.97} origin={{ x: 0, y: 0 }}>
            <Hexagon key={1} q={0} r={-2} s={2} fill='ore'/>
            <Hexagon key={2} q={1} r={-2} s={1} fill='wool'/>
            <Hexagon key={3} q={2} r={-2} s={0} fill='lumber'/>
            <Hexagon key={4} q={-1} r={-1} s={2} fill='grain'/>
            <Hexagon key={5} q={0} r={-1} s={1} fill='brick'/>
            <Hexagon key={6} q={1} r={-1} s={0} fill='wool'/>
            <Hexagon key={7} q={2} r={-1} s={-1} fill='brick'/>
            <Hexagon key={8} q={-2} r={0} s={2} fill='grain'/>
            <Hexagon key={9} q={-1} r={0} s={1} fill='lumber'/>
            <Hexagon key={10} onClick={handleClick} q={0} r={0} s={0} fill='desert'/>
            <Hexagon key={11} q={1} r={0} s={-1} fill='lumber'/>
            <Hexagon key={12} q={2} r={0} s={-2} fill='ore'/>
            <Hexagon key={13} q={-2} r={1} s={1} fill='lumber'/>
            <Hexagon key={14} q={-1} r={1} s={0} fill='ore'/>
            <Hexagon key={15} q={0} r={1} s={-1} fill='grain'/>
            <Hexagon key={16} q={1} r={1} s={-2} fill='wool'/>
            <Hexagon key={17} q={-2} r={2} s={0} fill='brick'/>
            <Hexagon key={18} q={-1} r={2} s={-1} fill='grain'/>
            <Hexagon key={19} q={0} r={2} s={-2} fill='wool'/>
        <Pattern id="grain" link="https://colonist.io/dist/images/tile_grain.svg" />
        <Pattern id="lumber" link="https://colonist.io/dist/images/tile_lumber.svg" />
        <Pattern id="ore" link="https://colonist.io/dist/images/tile_ore.svg" />
        <Pattern id="wool" link="https://colonist.io/dist/images/tile_wool.svg" />
        <Pattern id="brick" link="https://colonist.io/dist/images/tile_brick.svg" />
        <Pattern id="desert" link="https://colonist.io/dist/images/tile_desert.svg" />
        </Layout>
        </HexGrid>

                <img className='resourceIcon' src={require('../icons/ore.png')} alt='ore'/>
                <img className='resourceIcon' src={require('../icons/wool.png')} alt='wool'/>
                <img className='resourceIcon' src={require('../icons/brick.png')} alt='brick'/>
                <img className='resourceIcon' src={require('../icons/wood.png')} alt='wood'/>
                <img className='resourceIcon' src={require('../icons/grain.png')} alt='grain'/>
                
                <div className="resourceAmount">2</div>
                <div className="resourceAmount">1</div>
                <div className="resourceAmount">4</div>
                <div className="resourceAmount">5</div>
                <div className="resourceAmount">6</div>

        {roadVisible && <div className="rectangle"/>}        
    </div>  );
    
}