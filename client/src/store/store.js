import { configureStore } from '@reduxjs/toolkit';
import loginReducer from '../slices/loginSlice';
import signupReducer from '../slices/signupSlice';
import questionReducer from '../slices/questionSlice';

const store = configureStore({
  reducer: {
    question: questionReducer,
    login: loginReducer,
    signup: signupReducer,
  },
});

export default store;
