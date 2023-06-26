/* eslint-disable import/no-named-as-default */
import { styled } from 'styled-components';
import StyledButton, {
  StyledButtonLink,
  StyledInputText,
  StyledTextarea,
} from '../styles/StyledButton';
import { useSelector, useDispatch } from 'react-redux';
import Header from '../components/Header';
import Footer from '../components/Footer';
import Aside from '../components/Aside';
import { useState, useEffect } from 'react';
import { fetchUsers, fetchUpdateUsers } from '../slices/userSlice';

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

const UpdateUserInfo = () => {
  const dispatch = useDispatch();

  const userId = localStorage.getItem('MemberId');
  useEffect(() => {
    dispatch(fetchUsers(userId));
  }, []);
  const [inputText, setInputText] = useState({});
  const profile = useSelector((state) => state.mypage.profile);

  // const [inputText, setInputText] = useState(
  //   {
  //     displayName: profile.displayName,
  //     location: profile.location,
  //     title: profile.title,
  //     aboutMe: profile.aboutMe,
  //     webLink: profile.webLink,
  //     twitterLink: profile.twitterLink,
  //     githubLink: profile.githubLink,
  //     fullName: profile.fullName,
  //   },
  //   [profile]
  // );
  useEffect(() => {
    setInputText({
      displayName: profile.displayName,
      location: profile.location,
      title: profile.title,
      aboutMe: profile.aboutMe,
      webLink: profile.webLink,
      twitterLink: profile.twitterLink,
      githubLink: profile.githubLink,
      fullName: profile.fullName,
    });
  }, [profile]);
  const handleInputChange = (e) => {
    if (e.target.id === 'displayName') {
      setInputText({ ...inputText, title: e.target.value });
    } else if (e.target.id === 'location') {
      setInputText({ ...inputText, body: e.target.value });
    } else if (e.target.id === 'title') {
      setInputText({ ...inputText, tags: e.target.value });
    } else if (e.target.id === 'aboutMe') {
      setInputText({ ...inputText, summary: e.target.value });
    } else if (e.target.id === 'webLink') {
      setInputText({ ...inputText, summary: e.target.value });
    } else if (e.target.id === 'twitterLink') {
      setInputText({ ...inputText, summary: e.target.value });
    } else if (e.target.id === 'githubLink') {
      setInputText({ ...inputText, summary: e.target.value });
    } else if (e.target.id === 'fullName') {
      setInputText({ ...inputText, summary: e.target.value });
    }
  };
  const handleSubmit = async (e) => {
    e.preventDefault();
    // http POST 요청 보내야함
    dispatch(fetchUpdateUsers({ ...inputText, userId }));
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
              value={profile.title}
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
              value={profile.body}
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
              value={profile.tags}
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
              value={profile.summary}
              onChange={handleInputChange}
            />
          </div>
          <div className="button-container">
            <StyledButton type="submit" fontSize="1em" width="6em">
              Save edits
            </StyledButton>
            <StyledButtonLink to={'/'} fontSize="1em">
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

export default UpdateUserInfo;
