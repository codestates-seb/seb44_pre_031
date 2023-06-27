import { configureStore } from '@reduxjs/toolkit';
import loginReducer from '../slices/loginSlice';
import signupReducer from '../slices/signupSlice';
import questionReducer from '../slices/questionSlice';
import userReduser from '../slices/userSlice';
import serchUsersReducer from '../slices/searchUsersSlice';
// import tokenSlice from '../slices/tokenSlice';

import paginationReducer from '../slices/paginationSlice';
import filterquestionReducer from '../slices/filterquestionSlice';
const store = configureStore({
  reducer: {
    question: questionReducer,
    login: loginReducer,
    signup: signupReducer,
    mypage: userReduser,
    searchUsers: serchUsersReducer,
    // userId: tokenSlice,
    pages: paginationReducer,
    filter: filterquestionReducer,
  },
});

export default store;
