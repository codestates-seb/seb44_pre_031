/* eslint-disable import/no-named-as-default */
import { styled } from 'styled-components';
import StyledButton, {
  StyledButtonLink,
  StyledInputText,
  StyledTextarea,
} from '../styles/StyledButton';
import { Link, useNavigate, useParams } from 'react-router-dom';
// import { useSelector } from 'react-redux';
// import { selectAllAnswers } from '../slices/questionSlice';
import Footer from '../components/Footer';
import Header from '../components/Header';
import Aside from '../components/Aside';
import Nav from '../components/Nav';
import { useState } from 'react';
import { useSelector } from 'react-redux';
import { AWS_URL_PATH, selectAllAnswers } from '../slices/questionSlice';
import axios from 'axios';

const UpdateAnswerPageContainer = styled.div`
  .nav-main-container {
    display: flex;
    width: 100%;
  }
  .aside {
    margin: 10px;
  }
`;

const UpdateAnswerContainer = styled.form`
  display: flex;
  flex-direction: column;
  margin: 2em;
  gap: 1em;
  width: 80em;

  .title {
    text-decoration: none;
    color: hsl(209, 100%, 37.5%);
    font-size: 1.3em;

    &:visited {
      color: hsl(209, 100%, 37.5%);
    }
  }

  label {
    display: block;
    font-size: 1.1em;
    font-weight: 600;
    margin-bottom: 0.4em;
  }

  button {
    margin-right: 0.5em;
  }

  .button-container {
    margin-top: 1em;
  }
`;

const UpdateQuestion = () => {
  const navigate = useNavigate();
  const params = useParams();
  const answers = useSelector(selectAllAnswers);
  const selectedAnswer = answers.find(
    (answer) => Number(params.answerId) === answer.answerId
  );
  const token = useSelector((state) => state.login.token);

  const [inputText, setInputText] = useState({
    body: selectedAnswer.content,
    summary: '',
  });

  const handleInputChange = (e) => {
    if (e.target.id === 'body') {
      setInputText({ ...inputText, body: e.target.value });
    } else if (e.target.id === 'summary') {
      setInputText({ ...inputText, summary: e.target.value });
    }
  };
  const handleSubmit = async (e) => {
    e.preventDefault();
    // http POST 요청 보내야함
    try {
      const response = await axios.patch(
        `${AWS_URL_PATH}/answers/${params.questionId}/${params.answerId}`,
        { content: inputText.body },
        {
          headers: {
            Authorization: token,
          },
        }
      );
      console.log(response);
      navigate(`/questions/${params.questionId}`);
    } catch (err) {
      console.log(err.message);
      confirm(err.message);
    }
  };

  return (
    <UpdateAnswerPageContainer>
      <Header />
      <div className="nav-main-container">
        <Nav />
        <UpdateAnswerContainer onSubmit={handleSubmit}>
          <Link className="title">How Can I submit a POST?</Link>
          <div>
            <label htmlFor="body">Body</label>
            {/* <textarea id="body" /> */}
            <StyledTextarea
              id="body"
              height="12em"
              value={inputText.body}
              onChange={handleInputChange}
            />
          </div>
          <div>
            <label htmlFor="summary">Edit Summary</label>
            <StyledInputText
              id="summary"
              type="text"
              placeholder="briefly explain your changes (corrected spelling, fixed grammar, improved formatting)"
              onChange={handleInputChange}
            />
          </div>
          <div className="button-container">
            <StyledButton type="submit" fontSize="1em" width="6em">
              Save edits
            </StyledButton>
            <StyledButtonLink
              to={`/questions/${params.questionId}`}
              fontSize="1em"
            >
              Cancel
            </StyledButtonLink>
          </div>
        </UpdateAnswerContainer>
        <Aside />
      </div>
      <Footer />
    </UpdateAnswerPageContainer>
  );
};

export default UpdateQuestion;
