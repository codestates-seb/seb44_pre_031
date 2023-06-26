// import { createSlice } from '@reduxjs/toolkit';
// import { actionL } from '../components/actionL';

// const initialState = {
//   isLogin: false,
//   token: '',
//   joinRejectReason: '',
// };

// // export const userSlice = createSlice({
// //   name: 'user',
// //   initialState: {
// //     email: '',
// //     password: '',
// //   },
// //   reducers: {
// //     login: (state, action) => {
// //       state.email = action.payload;
// //       state.password = action.payload;
// //     },
// //     logout: (state) => {
// //       state.user = null;
// //     },
// //     userSearch: (state, action) => {
// //       state.name = action.payload;
// //       state.number = action.payload;
// //     },
// //   },
// // });

// export const loginSlice = createSlice({
//   name: 'join',
//   initialState,
//   reducers: {},
//   extraReducers: (builder) => {
//     builder
//       // eslint-disable-next-line no-unused-vars
//       .addCase(actionL.pending, (state, action) => {
//         state.isJoining = true;
//         console.log('진행중');
//       })
//       .addCase(actionL.fulfilled, (state, action) => {
//         state.isLogin = true;
//         state.token = '';
//         state.joinUser = action.payload.config.data;
//         state.joinRejectReason = '';
//       })
//       .addCase(actionL.rejected, (state, action) => {
//         state.isJoining = false;
//         console.log('실패');

//         state.joinErrorReasion = action.error;
//       });
//   },
// });

// export default loginSlice.reducer;
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
  reducers: {
    updateLoginState: {
      reducer: (state, action) => {
        state.isLoggedIn = action.payload.isLoggedIn;
        state.token = action.payload.token;
        state.id = action.payload.id;
        state.userId = action.payload.userId;
        state.loginRejectReason = action.payload.loginRejectReason;
        // state = action.payload;
        console.log(action.payload);
      },
      prepare: () => {
        // 로그인된 유저인지 어떻게 알지?
        const isLoggedIn = localStorage.getItem('Token') ? true : false;
        const token = localStorage.getItem('Token');
        const id = localStorage.getItem('Id');
        const userId = localStorage.getItem('MemberId');
        const loginRejectReason = '';
        return {
          payload: { isLoggedIn, token, id, userId, loginRejectReason },
        };
      },
    },
  },
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

export const { updateLoginState } = loginSlice.actions;
