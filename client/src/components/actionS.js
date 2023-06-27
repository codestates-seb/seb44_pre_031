import { createAsyncThunk } from '@reduxjs/toolkit';
import axios from 'axios';

// eslint-disable-next-line no-unused-vars
export const actionS = createAsyncThunk('user/join', async (data, thunkAPI) => {
  const result = await axios.post(
    'http://ec2-52-79-240-48.ap-northeast-2.compute.amazonaws.com:8080/api/users/sign-up',
    {
      displayName: data.displayName,
      email: data.email,
      code: data.emailAuth,
      password: data.password,
    }
  );

  if (result.data.success === true) {
    console.log('박지훈');
    const success = result.data.success;
    return { success };
  }
  console.log(result);
});
