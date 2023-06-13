import './Card.css'

interface CardProps {
    cardType: string
}

function getPath(name : String){
    return(
        "./icons/cards/" +
        name +
        ".png"
    );
}

export default function Card({cardType}: CardProps){
    return(
        <img
            id='card'
            src = {getPath(cardType)}
            width={50}
            height={72}

            />
    )
}