import React, { useState } from 'react';
import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button';
import { Grid } from '@mui/material';
import lOGIN from './../images/Login.jpeg';
import Alert from '@mui/material/Alert';
function LoginPage() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [errorMessage, setErrorMessage] = useState('');

  const handleUsernameChange = (event) => {
    setUsername(event.target.value);
  };

  const handlePasswordChange = (event) => {
    setPassword(event.target.value);
  };

  const handleSubmit = (event) => {
    event.preventDefault(); // Prevent default form submission behavior

    // Simulate login logic (replace with your actual authentication logic)
    if (username === 'admin' && password === 'password') {
      console.log('Login successful!');
      // Redirect to a different page or perform other actions on successful login
    } else {
      setErrorMessage('Invalid username or password');
    }
  };

  return (
    <Grid container flexDirection={'row'} sx={{marginLeft:'80px'}} >  
        <Grid item >
<img src={lOGIN} width={'800px'} />
        </Grid> 
<Grid  item sx={{marginTop:'300px',marginLeft:'200px'}}> 
      <div className="login-page" > 
      <h1>Login</h1>
      {errorMessage && <Alert severity="error">{errorMessage}</Alert>}
      <form onSubmit={handleSubmit}>
        <TextField
          label="Username"
          variant="outlined"
          required
          fullWidth
          value={username}
          onChange={handleUsernameChange}
          margin="normal"
        />
        <TextField
          label="Password"
          variant="outlined"
          type="password"
          required
          fullWidth
          value={password}
          onChange={handlePasswordChange}
          margin="normal"
        />
        <Button variant="contained" type="submit" fullWidth>
          Login
        </Button>
      </form>
    </div>
   </Grid>
    </Grid>
   
       
   
   
  );
}

export default LoginPage;
