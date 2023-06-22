import { createAsyncThunk } from '@reduxjs/toolkit';
import axios from 'axios';

// eslint-disable-next-line no-unused-vars
export const actionL = createAsyncThunk('user/join', async (data, thunkAPI) => {
  const result = await axios.post(
    'http://ec2-52-79-240-48.ap-northeast-2.compute.amazonaws.com:8080/api/users/sign-in',
    {
      email: data.email,

      password: data.password,
    }
  );

  return result;
});
