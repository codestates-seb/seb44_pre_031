import { createAsyncThunk } from '@reduxjs/toolkit';
import axios from 'axios';
import jwt_decode from 'jwt-decode';
// eslint-disable-next-line import/no-unresolved
// import jwt_decode from 'jsonwebtoken';
// eslint-disable-next-line no-unused-vars
export const actionL = createAsyncThunk('user/join', async (data, thunkAPI) => {
  const response = await axios.post(
    'http://ec2-52-79-240-48.ap-northeast-2.compute.amazonaws.com:8080/api/users/sign-in',
    {
      email: data.email,
      password: data.password,
    }
  );
  // const result = response.data;
  const accessToken = response.headers.authorization.slice(); // jwt 토큰 추출
  // const decodeAccessToken = jwt_decode.verify(
  //   accessToken,
  //   'kevin12312421512312431@!kfjiejwf'
  // );
  // console.log(decodeAccessToken);
  const userId = jwt_decode(accessToken).username;
  const userMemberId = jwt_decode(accessToken).memberId;
  // 추출한 토큰과 사용자 ID를 객체로 반환
  return { accessToken, userId, userMemberId };
});
