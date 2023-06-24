import { createSlice } from '@reduxjs/toolkit';

let initialState = {
  currentpage: 1,
  pagesize: 10,
  totalpage: 30,
  totalposts: 0,
};

const pageSlice = createSlice({
  name: 'page',
  initialState,
  reducers: {
    selectPagesize: (state, action) => {
      state.pagesize = action.payload;
      state.totalpage = Math.floor(state.totalposts / action.payload);
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
      state.totalposts = action.payload;
      state.totalpage = action.payload / state.pagesize;
    },
  },
});

export const { selectPage, selectPagesize, gotoNext, gotoPrev, setTotalposts } =
  pageSlice.actions;
export default pageSlice.reducer;
