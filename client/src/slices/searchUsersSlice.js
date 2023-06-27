import { createSlice, createAsyncThunk } from '@reduxjs/toolkit';
import axios from 'axios';

export const AWS_URL_PATH =
  'http://ec2-52-79-240-48.ap-northeast-2.compute.amazonaws.com:8080/api';

const initialState = {
  status: 'good',
  error: null,
  profile: {
    id: 1,
    displayName: '지훈',
    location: 'locationlocatiasdfasdfasdfasdfasdfsdon',
    title: 'hello',
    aboutMe: 'hello evreyone my name is jihoon',
    webLink: '',
    twitterLink: '2023-06-21T15:48:13',
    githubLink: '2023-06-21T15:48:13',
    fullName: '2023-06-21T15:48:13',
  },
};
// ,{ header: 'token' }

export const fetchUsers = createAsyncThunk(
  'users/anotherUser',
  async (userId) => {
    const response = await axios.get(`${AWS_URL_PATH}/users/${userId}`);
    console.log(response);
    return response.data.result.data;
  }
);

export const serchUsersSlice = createSlice({
  name: 'user',
  initialState,
  reducers: {},
  extraReducers(builder) {
    builder
      .addCase(fetchUsers.pending, (state) => {
        state.status = 'loading';
      })
      .addCase(fetchUsers.fulfilled, (state, action) => {
        // const actionPayload = action.payload;
        // console.log(action.payload);
        state.profile = action.payload;
        console.log(state.profile);
      })
      .addCase(fetchUsers.rejected, (state, action) => {
        state.status = 'failed';
        state.error = action.error.message;
      });
  },
});
export default serchUsersSlice.reducer;
