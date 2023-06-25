import { createSlice } from '@reduxjs/toolkit';

let initialState = {
  tags: [],
  user: '',
  answerCount: 0,
};

const filterSlice = createSlice({
  name: 'filter',
  initialState,
  reducers: {
    customfilter: (state, action) => {
      state.unanswered = action.payload.unanswered;
      state.tags = action.payload.tags;
    },
    searchBarfilter: (state, action) => {
      if (action.payload.tags) {
        state.tags = [action.payload.tags];
      } else if (action.payload.user) {
        state.user = action.payload.user;
      } else {
        state.answerCount = Number(action.payload.answerCount);
      }
    },
  },
});

export const { searchBarfilter, customfilter } = filterSlice.actions;
export default filterSlice.reducer;
