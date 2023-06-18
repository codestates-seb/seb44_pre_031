// import { useParams } from 'react-router-dom';
import Question from '../components/Question';
import Answers from '../components/Answers';
import { styled } from 'styled-components';

const QuestionDetailContainer = styled.div`
  display: flex;
  flex-direction: column;
  padding: 2em;
`;

const QuestionDetail = () => {
  // let { questionId } = useParams();

  return (
    <QuestionDetailContainer>
      {/* <p>question Id: {questionId}</p> */}
      <Question />
      <Answers />
    </QuestionDetailContainer>
  );
};

export default QuestionDetail;
