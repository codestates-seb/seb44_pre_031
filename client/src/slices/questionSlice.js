import { createSlice, createAsyncThunk } from '@reduxjs/toolkit';
import axios from 'axios';

export const AWS_URL_PATH =
  'http://ec2-52-79-240-48.ap-northeast-2.compute.amazonaws.com:8080/api';

// const MOCK_UP_API = 'http://localhost:3500';

// export const TEMP_ACCESS_TOKEN = store.getState().login.token;

// export const TEMP_ACCESS_TOKEN =
//   'Bearer eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJVU0VSIl0sIm1lbWJlcklkIjoxLCJzdWIiOiIyMHRha2tpQGdtYWlsLmNvbSIsImlhdCI6MTY4Nzc0MjYyNCwiZXhwIjoxNjg3NzQ1MDI0fQ.49dG9d17C9z0IsPnv4i4jde0al883TBWVPWYeP3H24Q';

export const TEMP_ACCESS_TOKEN = localStorage.getItem('Token');

const initialState = {
  status: 'idle', // 'idle' | 'loading' | 'succeeded' | 'failed'
  error: null,
  question: {
    memberId: 2,
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
      memberId: 3,
      questionId: 33,
      answerId: 1,
      voteCount: 1,
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
  async ({ questionId, token }) => {
    const response = await axios.post(
      `${AWS_URL_PATH}/questions/${questionId}/1/like`,
      null,
      {
        headers: {
          Authorization: token,
        },
      }
    );
    console.log(response);
    return response.data.success;
  }
);
export const postDownVoteQeustion = createAsyncThunk(
  'question/postDownVoteQeustion',
  async ({ questionId, token }) => {
    const response = await axios.post(
      `${AWS_URL_PATH}/questions/${questionId}/2/like`,
      null,
      {
        headers: {
          Authorization: token,
        },
      }
    );
    console.log(response);
    return response.data.success;
  }
);

export const postUpVoteAnswer = createAsyncThunk(
  'question/postUpVoteAnswer',
  async ({ answerId, token }) => {
    const response = await axios.post(
      `${AWS_URL_PATH}/answers/${answerId}/like`,
      null,
      {
        headers: {
          Authorization: token,
        },
      }
    );
    console.log(response.data);
    console.log(answerId);
    return { voteCount: response.data.result.data, answerId };
  }
);
export const postDownVoteAnswer = createAsyncThunk(
  'question/postDownVoteAnswer',
  async ({ answerId, token }) => {
    const response = await axios.post(
      `${AWS_URL_PATH}/answers/${answerId}/dislike`,
      null,
      {
        headers: {
          Authorization: token,
        },
      }
    );
    console.log(response.data);
    console.log(answerId);
    return { voteCount: response.data.result.data, answerId };
  }
);

export const postSelectAnswer = createAsyncThunk(
  'question/postSelectAnswer',
  async ({ questionId, answerId, token }) => {
    console.log(questionId, answerId);
    const response = await axios.post(
      `${AWS_URL_PATH}/answers/${questionId}/${answerId}/select`,
      null,
      {
        headers: {
          Authorization: token,
        },
      }
    );
    console.log(response);
    return { response: response.data, answerId };
  }
);

export const questionSlice = createSlice({
  name: 'question',
  initialState,
  reducers: {
    sortByHighestScore: (state) => {
      const sortedAnswers = state.answers.sort(
        (a, b) => b.voteCount - a.voteCount
      );
      console.log(sortedAnswers);
      state.answers = sortedAnswers;
    },
    sortByModifiedNewest: (state) => {
      const sortedAnswers = state.answers.sort(
        (a, b) => Date.parse(b.answerUpdatedAt) - Date.parse(a.answerUpdatedAt)
      );
      // console.log(sortedAnswers);
      state.answers = sortedAnswers;
    },
    sortByCreatedOldest: (state) => {
      const sortedAnswers = state.answers.sort(
        (a, b) => Date.parse(a.answerCreatedAt) - Date.parse(b.answerCreatedAt)
      );
      // console.log(sortedAnswers);
      state.answers = sortedAnswers;
    },
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
      })
      .addCase(postUpVoteAnswer.fulfilled, (state, action) => {
        state.status = 'succeeded';
        const selectedAnswer = state.answers.find(
          (answer) => answer.answerId === action.payload.answerId
        );
        if (action.payload) {
          selectedAnswer.voteCount = action.payload.voteCount;
        }
      })
      .addCase(postDownVoteAnswer.fulfilled, (state, action) => {
        state.status = 'succeeded';
        const selectedAnswer = state.answers.find(
          (answer) => answer.answerId === action.payload.answerId
        );
        if (action.payload) {
          selectedAnswer.voteCount = action.payload.voteCount;
        }
      })
      .addCase(postSelectAnswer.fulfilled, (state, action) => {
        const selectedAnswer = state.answers.find(
          (answer) => answer.answerId === action.payload.answerId
        );

        state.status = 'succeeded';
        selectedAnswer.selected = !selectedAnswer.selected;
      });
  },
});

export const selectQuestion = (state) => state.question.question;
export const selectAllAnswers = (state) => state.question.answers;
export const selectAllTags = (state) => state.question.tags;

// Action creators are generated for each case reducer function
export const {
  increment,
  incrementByAmount,
  sortByHighestScore,
  sortByModifiedNewest,
  sortByCreatedOldest,
} = questionSlice.actions;

export default questionSlice.reducer;
