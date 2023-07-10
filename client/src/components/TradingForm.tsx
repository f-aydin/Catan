import { useState } from 'react';
import './TradingForm.css'
import TradingButton from './TradingButton';

interface TradingFormProps{
    playerTurn: Number
}

export default function TradingForm({playerTurn}: TradingFormProps) {
    const [giveWool, setGiveWool] = useState(Number);
    const [giveOre, setGiveOre] = useState(Number);
    const [giveBrick, setGiveBrick] = useState(Number);
    const [giveLumber, setGiveLumber] = useState(Number);
    const [giveGrain, setGiveGrain] = useState(Number);
    const [getWool, setGetWool] = useState(Number);
    const [getOre, setGetOre] = useState(Number);
    const [getBrick, setGetBrick] = useState(Number);
    const [getLumber, setGetLumber] = useState(Number);
    const [getGrain, setGetGrain] = useState(Number);



    async function tradeWithBank() {
        const response = await fetch("http://localhost:8080/api/tradeWithBank", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({
                playerTurn: playerTurn,
                giveWool: giveWool,
                giveOre: giveOre,
                giveBrick: giveBrick,
                giveLumber: giveLumber,
                giveGrain: giveGrain,
                getWool: getWool,
                getOre: getOre,
                getBrick: getBrick,
                getLumber: getLumber,
                getGrain: getGrain
            })
        })
        const data = response.json();
        console.log(data);

    }


  return (
    <div id="tradingplatform">
        <div>
            Player1: <TradingButton resourceType='wool'/> <TradingButton resourceType='ore'/> <TradingButton resourceType='brick'/> <TradingButton resourceType='wood'/> <TradingButton resourceType='grain'/> <br/>
            Player2: <TradingButton resourceType='wool'/> <TradingButton resourceType='ore'/> <TradingButton resourceType='brick'/> <TradingButton resourceType='wood'/> <TradingButton resourceType='grain'/><br/>
            Player3: <TradingButton resourceType='wool'/> <TradingButton resourceType='ore'/> <TradingButton resourceType='brick'/> <TradingButton resourceType='wood'/> <TradingButton resourceType='grain'/><br/>
            Player4: <TradingButton resourceType='wool'/> <TradingButton resourceType='ore'/> <TradingButton resourceType='brick'/> <TradingButton resourceType='wood'/> <TradingButton resourceType='grain'/>

        </div>
        
        <button  onClick={() => tradeWithBank()}>Submit</button>
    </div>
  );
}
