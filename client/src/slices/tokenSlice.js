// import { createSlice } from '@reduxjs/toolkit';
// import { actionL } from '../components/actionL';

// const tokenSlice = createSlice({
//   name: 'userId',
//   initialState: {
//     userAccess: null,
//   },
//   reducers: {
//     userInfo: (state, action) => {
//       state.userAccess = action.payload;
//     },
//   },
//   extraReducers: (builder) => {
//     builder.addCase(actionL.fulfilled, (state, action) => {
//       // eslint-disable-next-line no-unused-vars
//       const { result, accessToken, userId } = action.payload;

//       // 토큰과 사용자 ID를 상태에 저장
//       localStorage.setItem('Id', userId);
//       localStorage.setItem('Token', accessToken);

//       // 사용자 정보 업데이트
//       state.userAccess = { userId, accessToken };
//     });
//   },
// });

// export const { userInfo } = tokenSlice.actions;

// export default tokenSlice.reducer;
