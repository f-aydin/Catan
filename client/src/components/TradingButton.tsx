import { useState } from "react";

interface TradingButtonProps{
    resourceType: string
}

export default function TradingButton({resourceType}: TradingButtonProps){
    const [amount, setAmount] = useState(0);

    function getPath(resourceType : String){
        return("./icons/resources/" + resourceType + ".png");
    }

    function decreaseAmount() {
        if(amount > 0){
            setAmount(amount - 1);
        }
    }

    return (
        <>
            <input
                type="image"
                src={getPath(resourceType)}
                width={50}
                onClick={() => setAmount(amount + 1)}
                onContextMenu={(e) => {
                    decreaseAmount()
                    e.preventDefault()
                }} />
            {amount}
        </> 
    )
}