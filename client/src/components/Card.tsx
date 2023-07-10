import { useState } from 'react';
import './Card.css'

interface CardProps {
    playerTurn: Number,
    cardType: string
}

export default function Card({playerTurn, cardType}: CardProps){
    const [showCard, setShowCard] = useState(false);
    const [cardPlayed, setCardPlayed] = useState(false);

    function getPath(name : String){
        if(!showCard && !cardPlayed){
            return("./icons/cards/DevelopmentCard.png");
        }
        return("./icons/cards/" + name + ".png");
    }

    function playability() {
        setCardPlayed(true);
    }

    async function playCard(name : any) {
        playability();
        switch(name){
            case "YEAROFPLENTY": playYearOfPlentyCard(); break;
            case "KNIGHT": playKnightCard(); break;
            case "MONOPOLY": playMonopolyCard(); break;
        }
    }

    return(
        <input
            type='image'
            id='card'
            disabled={cardPlayed}
            src = {getPath(cardType)}
            width={50}
            height={72}
            onMouseEnter={() => setShowCard(true)}
            onMouseLeave={() => setShowCard(false)}
            onClick={() => playCard(cardType)}
            />
    )

    async function playYearOfPlentyCard(){
        const resource = prompt("choose the resource to get from the bank: ");
        const resourceUpperCase = resource?.toUpperCase();
        const response = await fetch("http://localhost:8080/api/useYearOfPlenty", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({
              playerID: Number(playerTurn),
              type: String(resourceUpperCase)
            })
          }) 
    }

    async function playKnightCard() {
        let robberPlace = Number(prompt("Choose a tile number between 1 and 19: "))
        while(robberPlace < 1 && robberPlace > 19){
          robberPlace = Number(prompt("Choose a tile number between 1 and 19: "))
        }
        const response = await fetch("http://localhost:8080/api/placeRobberOnTile ", {
          method: "POST",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify(robberPlace - 1)
        });
    }
    
    async function playMonopolyCard() {
        const resource = prompt("choose the resource to steal from players: ");
                const resourceUpperCase = resource?.toUpperCase();
                const response = await fetch("http://localhost:8080/api/useMonopoly", {
                    method: "POST",
                    headers: { "Content-Type": "application/json" },
                    body: JSON.stringify({
                      playerID: Number(playerTurn),
                      type: String(resourceUpperCase)
                    })
                  }) 
    }
}




