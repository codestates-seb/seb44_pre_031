import { createSlice, createAsyncThunk } from '@reduxjs/toolkit';
import axios from 'axios';

export const AWS_URL_PATH =
  'http://ec2-52-79-240-48.ap-northeast-2.compute.amazonaws.com:8080/api';

// const MOCK_UP_API = 'http://localhost:3500';

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

export const fetchUsers = createAsyncThunk(
  'users/mypage/get',
  async (userId) => {
    const response = await axios.get(`${AWS_URL_PATH}/users/${userId}`);
    console.log(response);
    return response.data.result.data;
  }
);
export const fetchUpdateUsers = createAsyncThunk(
  'users/mypage/patch',
  async (params) => {
    const { data, userId } = params;

    const response = await axios.patch(
      `${AWS_URL_PATH}/users/${userId}`,
      {
        displayName: data.displayName,
        location: data.location,
        title: data.title,
        aboutMe: data.aboutMe,
        webLink: data.webLink,
        twitterLink: data.twitterLink,
        githubLink: data.githubLink,
        fullName: data.fullName,
      },
      {
        headers: {
          Authorization: localStorage.getItem('Token'),
        },
      }
    );
    console.log(response);
    return response.data.result.data;
  }
);
export const userSlice = createSlice({
  name: 'user',
  initialState,
  reducers: {},
  extraReducers(builder) {
    builder
      .addCase(fetchUsers.pending, (state) => {
        state.status = 'loading';
      })
      .addCase(fetchUsers.fulfilled, (state, action) => {
        state.profile = action.payload;
        state.status = 'good';
      })
      .addCase(fetchUsers.rejected, (state, action) => {
        state.status = 'failed';
        state.error = action.error.message;
      })
      .addCase(fetchUpdateUsers.pending, (state) => {
        state.status = 'loading';
      })
      .addCase(fetchUpdateUsers.fulfilled, (state, action) => {
        state.profile = action.payload;
        state.status = 'good';
      })
      .addCase(fetchUpdateUsers.rejected, (state, action) => {
        state.status = 'failed';
        state.error = action.error.message;
      });
  },
});
export default userSlice.reducer;
