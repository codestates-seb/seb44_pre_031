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
    displayName: '',
    location: '',
    title: '',
    aboutMe: '',
    webLink: '',
    twitterLink: '',
    githubLink: '',
    fullName: '',
  },
};

export const fetchUsers = createAsyncThunk(
  'users/mypage/get',
  async (userId) => {
    const response = await axios.get(`${AWS_URL_PATH}/users/${userId}`);
    return response.data.result.data;
  }
);
export const fetchUpdateUsers = createAsyncThunk(
  'users/mypage/patch',
  async ({ inputText, userId }) => {
    const response = await axios.patch(
      `${AWS_URL_PATH}/users/${userId}`,
      {
        displayName: inputText.displayName,
        location: inputText.location,
        title: inputText.title,
        aboutMe: inputText.aboutMe,
        webLink: inputText.webLink,
        twitterLink: inputText.twitterLink,
        githubLink: inputText.githubLink,
        fullName: inputText.fullName,
      },
      {
        headers: {
          Authorization: localStorage.getItem('Token'),
        },
      }
    );
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
