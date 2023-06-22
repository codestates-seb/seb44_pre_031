import { createSlice, createAsyncThunk } from '@reduxjs/toolkit';
import axios from 'axios';

export const AWS_URL_PATH =
  'http://ec2-52-79-240-48.ap-northeast-2.compute.amazonaws.com:8080/api';

// const MOCK_UP_API = 'http://localhost:3500';

const initialState = {
  status: 'idle', // 'idle' | 'loading' | 'succeeded' | 'failed'
  error: null,
  question: {
    questionId: 1,
    title: 'dlawjdad02fasdfasdfasdfasdf1ddd',
    content: 'locationlocatiasdfasdfasdfasdfasdfsdon',
    likeCount: 0,
    viewCount: 2,
    selectedAnswer: false,
    answerCount: 1,
    questionCreatedAt: '2023-06-21T15:47:20',
    questionUpdatedAt: '2023-06-21T15:48:13',
    displayName: 'Tom',
    reputation: 0,
  },
  answers: [
    {
      answerId: 1,
      content: 'locatafsdfasdfdasfasdfasdfasdfsdon',
      selected: false,
      answerCreatedAt: '2023-06-21T15:48:13',
      answerUpdatedAt: '2023-06-21T15:48:13',
      displayName: 'Jerry',
      reputation: 0,
    },
  ],
};
// ,{ header: 'token' }

export const fetchQuestionDetail = createAsyncThunk(
  'question/fetchQuestionDetail',
  async (questionId) => {
    const response = await axios.get(`${AWS_URL_PATH}/questions/${questionId}`);
    console.log(response);
    return response.data.result.data;
  }
);

// JSON-serve 용으로 만든거
// export const fetchQuestionDetail = createAsyncThunk(
//   'question/fetchQuestionDetail',
//   async (questionId) => {
//     const response = await axios.get(
//       `${MOCK_UP_API}/questions:${questionId}`
//       // ,{ header: 'token' }
//     );
//     // console.log(response.data.result.data);
//     return response.data.result.data;
//   }
// );

export const questionSlice = createSlice({
  name: 'question',
  initialState,
  reducers: {
    increment: (state) => {
      state.value += 1;
    },
    incrementByAmount: (state, action) => {
      state.value += action.payload;
    },
  },
  extraReducers(builder) {
    builder
      .addCase(fetchQuestionDetail.pending, (state) => {
        state.status = 'loading';
      })
      .addCase(fetchQuestionDetail.fulfilled, (state, action) => {
        // const actionPayload = action.payload;
        // console.log(action.payload);
        state.question = action.payload.question;
        state.answers = action.payload.answers.content;
      })
      .addCase(fetchQuestionDetail.rejected, (state, action) => {
        state.status = 'failed';
        state.error = action.error.message;
      });
  },
});

export const selectQuestion = (state) => state.question.question;
export const selectAllAnswers = (state) => state.quetions.answers.content;

// Action creators are generated for each case reducer function
export const { increment, incrementByAmount } = questionSlice.actions;

export default questionSlice.reducer;
