import { createSlice } from '@reduxjs/toolkit';
import { actionS } from '../components/actionS';

const initialState = {
  isJoined: false,
  isJoining: false,
  joinUser: null,
  joinRejectReason: '',
};

export const signupSlice = createSlice({
  name: 'signupUser',
  initialState,
  reducers: {},
  extraReducers: (builder) => {
    builder
      // eslint-disable-next-line no-unused-vars
      .addCase(actionS.pending, (state, action) => {
        state.isJoining = true;
      })
      .addCase(actionS.fulfilled, (state, action) => {
        state.isJoined = true;
        state.isJoining = false;
        state.joinUser = action.payload.data;
        state.joinRejectReason = '';
      })
      .addCase(actionS.rejected, (state, action) => {
        state.isJoining = false;
        console.log('실패');
        state.joinErrorReasion = action.error;
        console.log(state.joinErrorReasion);
      });
  },
});

export default signupSlice.reducer;
