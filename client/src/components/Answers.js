import { styled } from 'styled-components';
import { AnswerLayout } from './PostLayout';
import { useState } from 'react';
// eslint-disable-next-line import/no-named-as-default
import StyledButton, { StyledTagLink } from '../styles/StyledButton';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { useSelector } from 'react-redux';
import axios from 'axios';
import {
  AWS_URL_PATH,
  TEMP_ACCESS_TOKEN,
  selectAllTags,
} from '../slices/questionSlice';

const AnswersContainer = styled.div`
  display: flex;
  flex-direction: column;
  /* padding: 1em; */
  margin-top: 2em;
`;

const AnswersHeaderContainer = styled.div`
  .answers-header {
    display: flex;
    justify-content: space-between;
  }

  .number-of-answers {
    font-size: 1.2em;
  }

  .answers-pagination button {
    margin-right: 0.5em;
  }

  .answers-filter-container {
    display: flex;
    gap: 0.3em;
  }

  .answers-filter-title {
    display: flex;
    flex-direction: column;
    align-items: flex-end;
    font-size: 0.8em;
  }
  .reset-filter {
    color: hsl(206, 100%, 40%);
    text-decoration: none;
    cursor: pointer;

    &:hover {
      color: hsl(206, 100%, 52%);
    }
    /* 
    &:visited {
      color: hsl(209, 100%, 37.5%);
    } */
  }
`;

const AnswersHeader = () => {
  const answerCount = useSelector(
    (state) => state.question.question.answerCount
  );

  const handleSelectChange = (e) => {
    console.log(e.target.value);
  };

  return (
    <AnswersHeaderContainer>
      <div className="answers-header">
        <div className="number-of-answers">{`${answerCount} Answers`}</div>
        <div className="answers-filter-container">
          <div className="answers-filter-title">
            <p>Sorted by:</p>
            <Link className="reset-filter">Reset to default</Link>
          </div>
          <select onChange={handleSelectChange} defaultValue="highest-score">
            <option value="highest-score">Highest Score (default)</option>
            <option value="date-modified">Date modified(newest first)</option>
            <option value="date-created">Date created (oldest first)</option>
          </select>
        </div>
      </div>
      {/* <div className="answers-pagination">
        <button>1</button>
        <button>2</button>
        <button>3</button>
        <button>Next</button>
      </div> */}
    </AnswersHeaderContainer>
  );
};

const AnswerList = () => {
  const answers = useSelector((state) => state.question.answers);

  return (
    <div>
      {/* 데이터 받아서 answer map 돌려야함 */}
      {answers.map((answer) => {
        return (
          <>
            <AnswerLayout key={answer.answerId} answer={answer} />
            <hr />
          </>
        );
      })}
      {/* <AnswerLayout /> */}
      {/* <hr /> */}
    </div>
  );
};

const AnswerFormContainer = styled.form`
  display: flex;
  flex-direction: column;
  margin: 1em 0em;
  gap: 1em;

  .your-answer {
    font-size: 1.3em;
  }

  textarea {
    height: 12em;
    padding: 1em;
    font-size: 1em;
    text-align: 'left';

    &:focus {
      border: 0.1em solid hsl(206, 85%, 57.5%);
      box-shadow: 0px 0px 2px 5px hsl(206, 93%, 83.5%);
    }
  }

  button {
    margin-right: 0.8em;
  }

  .validation-notice {
    font-size: 0.8em;
    color: red;
  }
`;

const AnswerForm = () => {
  const [inputText, setInputText] = useState('');
  const [isValid, setIsValid] = useState(false);
  const [validationNotice, setValidationNotice] = useState('');
  const params = useParams();
  const navigate = useNavigate();

  const handleInputText = (e) => {
    setInputText(e.target.value);
  };

  const validateInput = () => {
    let inputTextLength = inputText?.trim().length;
    if (inputTextLength >= 30 && inputTextLength <= 500) {
      setIsValid(true);
    }
    if (inputTextLength < 30) {
      setIsValid(false);
      setValidationNotice(`Body must be at least 30 characters; you entered ${inputTextLength}.
      `);
    }
    if (inputTextLength > 500) {
      setIsValid(false);
      setValidationNotice(
        `Title cannot be longer than 500 characters. you entered ${inputTextLength}`
      );
    }
  };

  const handleSumbitClick = async (e) => {
    e.preventDefault();
    validateInput();

    // POST 요청 보내야됨
    // console.log(isValid);
    if (isValid) {
      // console.log('submit success');
      try {
        const response = await axios.post(
          `${AWS_URL_PATH}/answers/${params.questionId}`,
          { content: inputText.trim() },
          {
            headers: {
              Authorization: TEMP_ACCESS_TOKEN,
              // 'Content-Type': 'application/json',
            },
          }
        );
        // const response = await axios('https://swapi.dev/api/');
        console.log(response);

        // 성공하면 해당 질문 상세페이지로 다시 redirect
        // navigate(`/questions/${params.questionId}`);
        navigate(0);
      } catch (error) {
        console.log(error);
      }
    } else if (!isValid) {
      // console.log('submit failed');
    }
  };

  const handleSubmitBlur = () => {
    validateInput();
  };

  const handleDiscardButtonClick = () => {
    //reset state value
    setInputText('');
    setIsValid(false);
    setValidationNotice('');
  };

  return (
    <AnswerFormContainer>
      <label htmlFor="answer" className="your-answer">
        Your Answer
      </label>
      <textarea
        id="answer"
        name="answer"
        rows="12"
        cols="50"
        placeholder=""
        minLength={30}
        maxLength={500}
        value={inputText}
        onChange={handleInputText}
        onBlur={handleSubmitBlur}
      />
      {!isValid ? (
        <p className="validation-notice">{validationNotice}</p>
      ) : null}
      <div className="button-container">
        <StyledButton
          type="submit"
          fontSize="1em"
          width="10em"
          onClick={handleSumbitClick}
        >
          Post Your Answer
        </StyledButton>
        <StyledButton
          type="button"
          isCancel={true}
          fontSize="1em"
          width="8em"
          onClick={handleDiscardButtonClick}
        >
          Discard
        </StyledButton>
      </div>
    </AnswerFormContainer>
  );
};

const AnswerBottomNoticeContainer = styled.h2`
  font-size: 1.2em;
  font-weight: 400;
  p,
  ul,
  div,
  a {
    display: inline;
  }

  .tags-container a {
    margin-left: 0.2em;
    margin-right: 0.2em;
  }

  .ask-your-own-questipn {
    text-decoration: none;
    color: hsl(206, 100%, 40%);
    &:hover {
      color: hsl(206, 100%, 52%);
    }

    &:visited {
      color: hsl(209, 100%, 37.5%);
    }
  }
`;

const AnswerBottomNotice = () => {
  const tags = useSelector(selectAllTags);

  return (
    <AnswerBottomNoticeContainer>
      <p>Not the answer you are looking for? Browse other questions tagged</p>
      <div className="tags-container">
        {tags.map((tag) => (
          <StyledTagLink key={tag}>{tag}</StyledTagLink>
        ))}
      </div>
      <p> or </p>
      <Link className="ask-your-own-questipn" to="../ask">
        ask your own question
      </Link>
    </AnswerBottomNoticeContainer>
  );
};

const Answers = () => {
  return (
    <AnswersContainer>
      <AnswersHeader />
      <AnswerList />
      <AnswerForm />
      <AnswerBottomNotice />
    </AnswersContainer>
  );
};

export default Answers;
