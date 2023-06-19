import { Link } from 'react-router-dom';
import PostLayout from './PostLayout';
import { styled } from 'styled-components';

const QuestionContaier = styled.div`
  display: flex;
  flex-direction: column;
  gap: 1em;
`;

const QuestionHeaderContainer = styled.div`
  display: flex;
  flex-direction: column;
  gap: 0.8em;

  .question-header {
    display: flex;
    justify-content: space-between;
  }

  .question-title {
    text-decoration: none;
    color: hsl(210, 8%, 25%);
    font-size: 1.6em;
    font-weight: 500;
    :visited {
      color: hsl(210, 8%, 25%);
    }
  }
`;

const AskQuestionLink = styled(Link)`
  font-size: 0.8em;
  color: white;
  background-color: rgb(10, 149, 255);
  border: none;
  border-radius: 2px;
  width: ${(props) => props.width};
  height: 2.7em;
  box-shadow: rgba(255, 255, 255, 0.4) 0px 2px 0px 0px inset;
  cursor: pointer;
  text-decoration: none;
  padding: 0.6em;

  &:visited {
    color: white;
    text-decoration: none;
  }

  &:hover {
    background-color: hsl(206, 100%, 40%);
  }
  &:active {
    background-color: hsl(209, 100%, 37.5%);
    box-shadow: 0px 0px 2px 5px hsl(206, 93%, 83.5%);
  }

  &:disabled {
    background-color: lightskyblue;
    color: rgb(227, 241, 252);
    cursor: pointer;
  }
`;

const QuestionInfoContainer = styled.div`
  display: flex;
  gap: 1.7em;
  .question-info {
    display: flex;
    gap: 0.5em;
  }

  span {
    font-size: 0.8em;
  }
  span:first-child {
    color: gray;
  }
`;

const QuestionHeader = () => {
  return (
    <QuestionHeaderContainer>
      <div className="question-header">
        <Link className="question-title">
          How to correctly change/focusOut event
        </Link>
        <AskQuestionLink to="/questions/ask">Ask Question</AskQuestionLink>
      </div>
      <QuestionInfoContainer>
        <div className="question-info">
          <span>Asked</span>
          <span>7 years ago</span>
        </div>
        <div className="question-info">
          <span>Modified</span>
          <span>3 months ago</span>
        </div>
        <div className="question-info">
          <span>Viewed</span>
          <span>261k times</span>
        </div>
      </QuestionInfoContainer>
    </QuestionHeaderContainer>
  );
};

const Question = () => {
  return (
    <QuestionContaier>
      <QuestionHeader />
      <hr />
      <PostLayout />
    </QuestionContaier>
  );
};

export default Question;
