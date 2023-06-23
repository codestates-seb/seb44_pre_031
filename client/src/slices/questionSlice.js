import { createSlice, createAsyncThunk } from '@reduxjs/toolkit';
import axios from 'axios';

export const AWS_URL_PATH =
  'http://ec2-52-79-240-48.ap-northeast-2.compute.amazonaws.com:8080/api';

// const MOCK_UP_API = 'http://localhost:3500';

export const TEMP_ACCESS_TOKEN =
  'Bearer eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJVU0VSIl0sIm1lbWJlcklkIjoxLCJ1c2VybmFtZSI6ImRsYXdqZGFsczAyMThAZ21haWwuY29tIiwic3ViIjoiZGxhd2pkYWxzMDIxOEBnbWFpbC5jb20iLCJpYXQiOjE2ODc1MTExMDksImV4cCI6MTY4NzUxMzUwOX0.DiRQMcKsQVmlV2padcERWa4FzYKkzeHoJDw64mo_pA4';

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
      questionId: 33,
      answerId: 1,
      content: 'locatafsdfasdfdasfasdfasdfasdfsdon',
      selected: false,
      answerCreatedAt: '2023-06-21T15:48:13',
      answerUpdatedAt: '2023-06-21T15:48:13',
      displayName: 'Jerry',
      reputation: 0,
    },
  ],
  tags: ['java', 'react', 'javascript'],
};
// ,{ header: 'token' }

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

export const fetchQuestionDetail = createAsyncThunk(
  'question/fetchQuestionDetail',
  async (questionId) => {
    const response = await axios.get(`${AWS_URL_PATH}/questions/${questionId}`);
    console.log(response);
    return response.data.result.data;
  }
);

export const postUpVoteQeustion = createAsyncThunk(
  'question/postUpVoteQeustion',
  async (questionId) => {
    const response = await axios.post(
      `${AWS_URL_PATH}/questions/${questionId}/1/like`,
      null,
      {
        headers: {
          Authorization: TEMP_ACCESS_TOKEN,
        },
      }
    );
    console.log(response);
    return response.data.success;
  }
);
export const postDownVoteQeustion = createAsyncThunk(
  'question/postDownVoteQeustion',
  async (questionId) => {
    const response = await axios.post(
      `${AWS_URL_PATH}/questions/${questionId}/2/like`,
      null,
      {
        headers: {
          Authorization: TEMP_ACCESS_TOKEN,
        },
      }
    );
    console.log(response);
    return response.data.success;
  }
);

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
        console.log(action.payload);
        state.status = 'succeeded';
        state.question = action.payload.question;
        state.answers = action.payload.answers.content;
        state.tags = action.payload.tags;
      })
      .addCase(fetchQuestionDetail.rejected, (state, action) => {
        state.status = 'failed';
        state.error = action.error.message;
      })
      .addCase(postUpVoteQeustion.fulfilled, (state, action) => {
        state.status = 'succeeded';
        if (action.payload) {
          state.question.likeCount++;
        }
      })
      .addCase(postDownVoteQeustion.fulfilled, (state, action) => {
        state.status = 'succeeded';
        if (action.payload) {
          state.question.likeCount--;
        }
      });
  },
});

export const selectQuestion = (state) => state.question.question;
export const selectAllAnswers = (state) => state.question.answers;
export const selectAllTags = (state) => state.question.tags;

// Action creators are generated for each case reducer function
export const { increment, incrementByAmount } = questionSlice.actions;

export default questionSlice.reducer;
