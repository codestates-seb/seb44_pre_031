import { createSlice } from '@reduxjs/toolkit';

let initialState = {
  currentpage: 1,
  pagesize: 5,
  totalpage: 30,
  totalposts: 1,
  questionsTitle: 'All Questions',
};

const pageSlice = createSlice({
  name: 'page',
  initialState,
  reducers: {
    selectPagesize: (state, action) => {
      state.pagesize = action.payload;
      state.totalpage = Math.ceil(state.totalposts / action.payload);
      state.currentpage = 1;
    },
    selectPage: (state, action) => {
      state.currentpage = action.payload;
    },
    gotoNext: (state) => {
      state.currentpage += 1;
    },
    gotoPrev: (state) => {
      state.currentpage -= 1;
    },
    setTotalposts: (state, action) => {
      console.log(action.payload);
      state.totalposts = action.payload.questionsLength;
      state.totalpage = Math.ceil(
        action.payload.questionsLength / state.pagesize
      );
      state.currentpage = 1;
      state.questionsTitle = action.payload.questionsTitle;
    },
  },
});

export const { selectPage, selectPagesize, gotoNext, gotoPrev, setTotalposts } =
  pageSlice.actions;
export default pageSlice.reducer;
