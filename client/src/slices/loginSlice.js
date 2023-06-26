import { createSlice } from '@reduxjs/toolkit';
import { actionL } from '../components/actionL';

const initialState = {
  isLoggedIn: false,
  token: '',
  id: '',
  userId: '',
  loginRejectReason: '',
};

export const loginSlice = createSlice({
  name: 'login',
  initialState,
  reducers: {},
  extraReducers: (builder) => {
    builder
      .addCase(actionL.pending, (state) => {
        state.isLoggedIn = false; // 로그인 상태 초기화
        state.token = ''; // 토큰 초기화
        state.id = ''; //초기화
        state.userId = '';
        console.log('리듀서');
        state.loginRejectReason = ''; // 거부 이유 초기화
      })
      .addCase(actionL.fulfilled, (state, action) => {
        console.log('슬라이스 통과');
        state.isLoggedIn = true; // 로그인 상태 변경

        state.token = action.payload.accessToken; // 토큰 설정
        state.id = action.payload.userId;
        state.userId = action.payload.userMemberId;
      })
      .addCase(actionL.rejected, (state, action) => {
        state.isLoggedIn = false; // 로그인 상태 초기화
        state.token = ''; // 토큰 초기화
        state.id = ''; //초기화
        state.loginRejectReason = action.error.message; // 거부 이유 설정
      });
  },
});

export default loginSlice.reducer;
