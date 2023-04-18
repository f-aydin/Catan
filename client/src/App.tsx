import { HexGrid, Layout, Hexagon, Text, Pattern, Path, Hex } from 'react-hexgrid';
import './App.css';
import { Component } from 'react';

class App extends Component {
  render() {
    return (
      <div>
        <HexGrid width={900} height={800} viewBox="-50 -50 100 100">
          {/* Grid with manually inserted hexagons */}
          <Layout size={{ x: 12, y: 10.5 }} flat={false} spacing={1.01} origin={{ x: 0, y: 0 }}>
            <Hexagon q={0} r={0} s={0} fill='desert'/>
            {/* Using pattern (defined below) to fill the hexagon */}
            <Hexagon q={0} r={-1} s={1} fill='brick'/>
            <Hexagon q={0} r={-2} s={2} fill='ore'/>
            <Hexagon q={0} r={1} s={-1} fill='grain'/>
            <Hexagon q={0} r={2} s={-2} fill='wool'/>

            <Hexagon q={1} r={0} s={-1} fill='lumber'/>
            <Hexagon q={2} r={0} s={-2} fill='ore'/>
            <Hexagon q={-1} r={0} s={1} fill='lumber'/>
            <Hexagon q={-2} r={0} s={2} fill='grain'/>

            <Hexagon q={1} r={-1} s={0} fill='wool'/>
            <Hexagon q={2} r={-2} s={0} fill='lumber'/>
            <Hexagon q={-1} r={1} s={0} fill='ore'/>
            <Hexagon q={-2} r={2} s={0} fill='brick'/>

            <Hexagon q={1} r={-2} s={1} fill='wool'/>
            <Hexagon q={2} r={-1} s={-1} fill='brick'/>
            <Hexagon q={1} r={1} s={-2} fill='wool'/>
            <Hexagon q={-1} r={2} s={-1} fill='grain'/>
            <Hexagon q={-2} r={1} s={1} fill='lumber'/>
            <Hexagon q={-1} r={-1} s={2} fill='grain'/>

          <Pattern id="grain" link="https://colonist.io/dist/images/tile_grain.svg" />
          <Pattern id="lumber" link="https://colonist.io/dist/images/tile_lumber.svg" />
          <Pattern id="ore" link="https://colonist.io/dist/images/tile_ore.svg" />
          <Pattern id="wool" link="https://colonist.io/dist/images/tile_wool.svg" />
          <Pattern id="brick" link="https://colonist.io/dist/images/tile_brick.svg" />
          <Pattern id="desert" link="https://colonist.io/dist/images/tile_desert.svg" />

          <Path start={new Hex(0, 0, 0)} end={new Hex(-2, 0, 1)} />
          </Layout>
        </HexGrid>
      </div>
    );
  }
}


export default App;
