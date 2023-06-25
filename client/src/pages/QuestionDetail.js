import { useParams } from 'react-router-dom';
import Question from '../components/Question';
import Answers from '../components/Answers';
import { styled } from 'styled-components';
import { useDispatch } from 'react-redux';
import { useEffect } from 'react';
import { fetchQuestionDetail } from '../slices/questionSlice';
import Header from '../components/Header';
import Footer from '../components/Footer';
import Nav from '../components/Nav';
import Aside from '../components/Aside';

const QuestionDetailPageContainer = styled.div`
  .nav-main-container {
    display: flex;
  }
`;

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
  let { questionId } = useParams();

  const dispatch = useDispatch();

  useEffect(() => {
    dispatch(fetchQuestionDetail(questionId));
  }, []);

  return (
    <QuestionDetailPageContainer>
      <Header />
      <div className="nav-main-container">
        <Nav />
        <QuestionDetailContainer>
          <Question />
          <hr />
          <Answers />
        </QuestionDetailContainer>
        <Aside />
      </div>
      <Footer />
    </QuestionDetailPageContainer>
  );
};

export default QuestionDetail;
