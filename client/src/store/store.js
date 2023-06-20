import { configureStore } from '@reduxjs/toolkit';
import counterReducer from '../slices/initSlice';
import loginReducer from '../slices/loginSlice';
import signupReducer from '../slices/signupSlice';

const store = configureStore({
  reducer: {
    counter: counterReducer,
    login: loginReducer,
    signup: signupReducer,
  },
});

export default store;
