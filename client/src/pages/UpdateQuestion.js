import { styled } from 'styled-components';
import StyledButton, {
  StyledButtonLink,
  StyledInputText,
  StyledTextarea,
} from '../styles/StyledButton';
import { useNavigate, useParams } from 'react-router-dom';
import { useSelector } from 'react-redux';
import {
  AWS_URL_PATH,
  TEMP_ACCESS_TOKEN,
  selectAllTags,
  selectQuestion,
} from '../slices/questionSlice';
import Header from '../components/Header';
import Footer from '../components/Footer';
import Aside from '../components/Aside';
import { useState } from 'react';
import axios from 'axios';

const UpdateQuestionPageContainer = styled.div`
  .nav-main-container {
    display: flex;
    width: 100%;
  }
  .aside {
    margin: 10px;
  }
`;

const UpdateQuestionContainer = styled.form`
  display: flex;
  flex-direction: column;
  margin: 2em;
  gap: 1.2em;
  width: 80em;

  label {
    display: block;
    font-size: 1.1em;
    font-weight: 600;
    margin-bottom: 0.4em;
  }

  p {
    margin-bottom: 0.5em;
  }

  button {
    margin-right: 0.5em;
  }

  .button-container {
    margin-top: 1em;
  }
`;

const UpdateQuestion = () => {
  const params = useParams();
  console.log(params);
  const navigate = useNavigate();

  const question = useSelector(selectQuestion);
  const tags = useSelector(selectAllTags);

  const [inputText, setInputText] = useState({
    title: question.title,
    body: question.content,
    tags: tags.map((tag) => tag.name).join(' '),
    summary: '',
  });

  const handleInputChange = (e) => {
    if (e.target.id === 'title') {
      setInputText({ ...inputText, title: e.target.value });
    } else if (e.target.id === 'body') {
      setInputText({ ...inputText, body: e.target.value });
    } else if (e.target.id === 'tags') {
      setInputText({ ...inputText, tags: e.target.value });
    } else if (e.target.id === 'summary') {
      setInputText({ ...inputText, summary: e.target.value });
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const data = {
      title: inputText.title.trim(),
      content: inputText.body.trim(),
      // tags 배열로 보내져야됨
      // "tags": ["java", "spring", "임정민"]
      tags: inputText.tags.trim(),
      // summary: inputText.summary.trim(),
    };
    // http POST 요청 보내야함, 성공하면 해당 /quetions/:questionId 로 리다이렉트
    try {
      const response = await axios.patch(
        `${AWS_URL_PATH}/questions/${params.questionId}`,
        data,
        {
          headers: {
            Authorization: TEMP_ACCESS_TOKEN,
          },
        }
      );
      console.log(response);
      navigate(`/questions/${params.questionId}`);
    } catch (err) {
      console.log(err.message);
    }
  };

  return (
    <UpdateQuestionPageContainer>
      <Header />
      <div className="nav-main-container">
        <UpdateQuestionContainer onSubmit={handleSubmit}>
          <div>
            <label htmlFor="title">Title</label>
            <p>
              Be specific and imagine you’re asking a question to another
              person. Minimum 15 characters.
            </p>
            <StyledInputText
              id="title"
              type="text"
              placeholder="e.g. Is there an R function for finding the index of an element in a vector?"
              value={inputText.title}
              minLength="15"
              maxLength="150"
              required
              onChange={handleInputChange}
            />
          </div>
          <div>
            <label htmlFor="body">Body</label>
            <p>
              The body of your question contains your problem details and
              results. Minimum 220 characters.
            </p>
            <StyledTextarea
              id="body"
              height="12em"
              value={inputText.body}
              onChange={handleInputChange}
              minLength="220"
              maxLength="500"
            />
          </div>
          <div>
            <label htmlFor="tags">Tags</label>
            <p>
              Add up to 5 tags to describe what your question is about. Start
              typing to see suggestions.
            </p>
            <StyledInputText
              id="tags"
              type="text"
              placeholder="e.g. (django mongodb javascript)"
              value={inputText.tags}
              required
              onChange={handleInputChange}
            />
          </div>
          <div>
            <label htmlFor="summary">Edit Summary</label>
            <StyledInputText
              id="summary"
              type="text"
              placeholder="briefly explain your changes (corrected spelling, fixed grammar, improved formatting)"
              value={inputText.summary}
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
        </UpdateQuestionContainer>
        <Aside className="aside" />
      </div>
      <Footer />
    </UpdateQuestionPageContainer>
  );
};

export default UpdateQuestion;
