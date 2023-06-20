import { createAsyncThunk } from '@reduxjs/toolkit';
import axios from 'axios';

// eslint-disable-next-line no-unused-vars
export const actionS = createAsyncThunk('user/join', async (data, thunkAPI) => {
  const result = await axios.post('http://localhost:8080/api/users/sign-up', {
    displayName: data.displayName,
    email: data.email,
    password: data.password,
  });

  console.log(result);
  return result;
});
