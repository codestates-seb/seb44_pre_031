import { configureStore } from '@reduxjs/toolkit';
import counterReducer from '../slices/initSlice';

const store = configureStore({
  reducer: {
    counter: counterReducer,
  },
});

export default store;
