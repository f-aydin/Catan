interface ButtonProps {
    id: string
    location: {
        right: String,
        bottom: String;
    }
    events: () => void
}

export default function Button({id, location, events}: ButtonProps) {
  return (
    <button
      className="vertices"
      id = {id}
      style={{
        ["--fromright" as any]: location.right,
        ["--frombottom" as any]: location.bottom,
      }}
      onClick={events}
    />
  );
}


