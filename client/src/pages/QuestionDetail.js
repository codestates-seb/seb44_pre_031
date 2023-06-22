// import { useParams } from 'react-router-dom';
import Question from '../components/Question';
import Answers from '../components/Answers';
import { styled } from 'styled-components';
// import { useState } from 'react';
import { useDispatch } from 'react-redux';
import { useEffect } from 'react';
import { fetchQuestionDetail } from '../slices/questionSlice';
// import axios from 'axios';

const QuestionDetailContainer = styled.div`
  display: flex;
  flex-direction: column;
  padding: 2em;
  /* 보기좋으라고 일단 설정해놨음 */
  /* max-width: 1250px; */

  hr {
    border: 0.1px solid lightgray;
  }
`;

const QuestionDetail = () => {
  // let { questionId } = useParams();

  const dispatch = useDispatch();

  useEffect(() => {
    dispatch(fetchQuestionDetail(1));

    // axios
    //   .get(
    //     'http://ec2-3-35-10-128.ap-northeast-2.compute.amazonaws.com:8080/api/questions/1'
    //   )
    //   .then(function (response) {
    //     // 성공한 경우 실행
    //     console.log(response);
    //   })
    //   .catch(function (error) {
    //     // 에러인 경우 실행
    //     console.log(error);
    //   });
  }, []);

  return (
    <QuestionDetailContainer>
      {/* <p>question Id: {questionId}</p> */}
      <Question />
      <hr />
      <Answers />
    </QuestionDetailContainer>
  );
};

export default QuestionDetail;
