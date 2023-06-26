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
import { useState } from 'react';
// useEffect
import { fetchUpdateUsers } from '../slices/userSlice';
// fetchUsers
import Nav from '../components/Nav';
import { useNavigate } from 'react-router-dom';

const UpdateQuestionPageContainer = styled.div`
  .nav-main-container {
    display: flex;
    justify-content: center;
    width: 100%;
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
  .sns {
    display: flex;
    padding: 10px;
    div {
      margin: 10px;
    }
  }
`;

const UpdateUserInfo = () => {
  const dispatch = useDispatch();
  const navigator = useNavigate();
  const userId = localStorage.getItem('MemberId');

  const profile = useSelector((state) => state.mypage.profile);

  const [inputText, setInputText] = useState({
    displayName: profile.displayName,
    location: profile.location,
    title: profile.title,
    aboutMe: profile.aboutMe,
    webLink: profile.webLink,
    twitterLink: profile.twitterLink,
    githubLink: profile.githubLink,
    fullName: profile.fullName,
  });

  const handleInputChange = (e) => {
    if (e.target.id === 'displayName') {
      setInputText({ ...inputText, displayName: e.target.value });
    } else if (e.target.id === 'location') {
      setInputText({ ...inputText, location: e.target.value });
    } else if (e.target.id === 'title') {
      setInputText({ ...inputText, title: e.target.value });
    } else if (e.target.id === 'aboutMe') {
      setInputText({ ...inputText, aboutMe: e.target.value });
    } else if (e.target.id === 'webLink') {
      setInputText({ ...inputText, webLink: e.target.value });
    } else if (e.target.id === 'twitterLink') {
      setInputText({ ...inputText, twitterLink: e.target.value });
    } else if (e.target.id === 'githubLink') {
      setInputText({ ...inputText, githubLink: e.target.value });
    } else if (e.target.id === 'fullName') {
      setInputText({ ...inputText, fullName: e.target.value });
    }
  };
  const handleSubmit = async (e) => {
    e.preventDefault();
    dispatch(fetchUpdateUsers({ inputText, userId })).then(() => {
      navigator('/users/mypage');
    });
  };

  return (
    <UpdateQuestionPageContainer>
      <Header />
      <div className="nav-main-container">
        <Nav />

        <UpdateQuestionContainer onSubmit={handleSubmit}>
          <div>
            <label htmlFor="displayName">Display name</label>

            <StyledInputText
              id="displayName"
              type="text"
              placeholder="e.g. Is there an R function for finding the index of an element in a vector?"
              value={inputText.displayName}
              required
              onChange={handleInputChange}
            />
          </div>

          <div>
            <label htmlFor="location">Location</label>

            <StyledInputText
              id="location"
              type="text"
              placeholder="e.g. (django mongodb javascript)"
              value={inputText.location}
              onChange={handleInputChange}
            />
          </div>
          <div>
            <label htmlFor="title">Title</label>

            <StyledInputText
              id="title"
              type="text"
              placeholder="e.g. Is there an R function for finding the index of an element in a vector?"
              value={inputText.title}
              onChange={handleInputChange}
            />
          </div>
          <div>
            <label htmlFor="aboutMe">About Me</label>

            <StyledTextarea
              id="aboutMe"
              height="12em"
              value={inputText.aboutMe}
              onChange={handleInputChange}
            />
          </div>
          <div className="sns">
            <div>
              <label htmlFor="title">webLink</label>

              <StyledInputText
                id="webLink"
                type="text"
                placeholder="e.g. Is there an R function for finding the index of an element in a vector?"
                value={inputText.webLink}
                onChange={handleInputChange}
              />
            </div>
            <div>
              <label htmlFor="title">twitterLink</label>

              <StyledInputText
                id="twitterLink"
                type="text"
                placeholder="e.g. Is there an R function for finding the index of an element in a vector?"
                value={inputText.twitterLink}
                onChange={handleInputChange}
              />
            </div>
            <div>
              <label htmlFor="title">githubLink</label>

              <StyledInputText
                id="githubLink"
                type="text"
                placeholder="e.g. Is there an R function for finding the index of an element in a vector?"
                value={inputText.githubLink}
                onChange={handleInputChange}
              />
            </div>
          </div>
          <div>
            <label htmlFor="fullName">Full Name</label>

            <StyledInputText
              id="fullName"
              type="text"
              placeholder="e.g. Is there an R function for finding the index of an element in a vector?"
              value={inputText.fullName}
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
      </div>
      <Footer />
    </UpdateQuestionPageContainer>
  );
};

export default UpdateUserInfo;
