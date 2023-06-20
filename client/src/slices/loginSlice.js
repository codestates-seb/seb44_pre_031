import { createSlice } from '@reduxjs/toolkit';
import { actionL } from '../components/actionL';

const initialState = {
  isLogin: false,
  token: '',
  joinRejectReason: '',
};

// export const userSlice = createSlice({
//   name: 'user',
//   initialState: {
//     email: '',
//     password: '',
//   },
//   reducers: {
//     login: (state, action) => {
//       state.email = action.payload;
//       state.password = action.payload;
//     },
//     logout: (state) => {
//       state.user = null;
//     },
//     userSearch: (state, action) => {
//       state.name = action.payload;
//       state.number = action.payload;
//     },
//   },
// });

export const loginSlice = createSlice({
  name: 'join',
  initialState,
  reducers: {},
  extraReducers: (builder) => {
    builder
      // eslint-disable-next-line no-unused-vars
      .addCase(actionL.pending, (state, action) => {
        state.isJoining = true;
      })
      .addCase(actionL.fulfilled, (state, action) => {
        state.isLogin = true;
        state.token = '';
        state.joinUser = action.payload.config.data;
        state.joinRejectReason = '';
      })
      .addCase(actionL.rejected, (state, action) => {
        state.isJoining = false;
        console.log('실패');

        state.joinErrorReasion = action.error;
      });
  },
});

export default loginSlice;
