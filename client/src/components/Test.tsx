import React, { useState } from 'react';
import { Container, Grid, Typography, Button } from '@mui/material';

interface Props {
  availableResources: string[];
}

const ResourceTrading: React.FC<Props> = ({ availableResources }) => {
  const [selectedResource, setSelectedResource] = useState(String);

  const handleSelectResource = (resource: string) => {
    setSelectedResource(resource);
  };

  const handleTrade = () => {
    // Implement the trade logic here
  };

  return (
    <Container>
      <Typography variant="h4">Resource Trading</Typography>
      <Grid container spacing={2}>
        {availableResources.map((resource, index) => (
          <Grid item xs={4} sm={2} md={3} lg={4} key={index}>
            <Typography variant="h6" component="div">
              {resource}
            </Typography>
          </Grid>
        ))}
        <Grid item xs={12} sm={6} md={4} lg={3} justifyContent="center">
          <Button variant="contained" color="primary" onClick={handleTrade}>
            Trade
          </Button>
        </Grid>
      </Grid>
    </Container>
  );
};

export default ResourceTrading;