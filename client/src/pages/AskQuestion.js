import { useState } from 'react';
import { styled } from 'styled-components';
import { TfiPencil } from 'react-icons/tfi';
import AskQuestionInput from '../components/AskQuestionInput';
// import { useNavigate } from 'react-router-dom';

const AskContainer = styled.div`
  background-color: rgb(248, 249, 249);
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  padding: 2em 2em 5em 2em;
  width: 100%;
`;

// 질문 작성 안내문
const WriteQuestionNotice = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  gap: 3em;
  width: 100%;
`;

const WriteGoodQuestionNotice = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  width: 100%;

  border: 0.1em solid rgb(178, 213, 239);
  border-radius: 5px;
  background-color: rgb(235, 244, 251);
  padding: 2em 3em;

  h2 {
    font-weight: 400;
    margin-bottom: 0.5em;
  }
  h5 {
    margin-top: 1em;
    font-weight: 500;
    font-size: 1.1em;
  }
  ul {
    margin-top: 0.5em;
    margin-left: 30px;
  }
`;

// 질문 등록 form
const QuestionPostForm = styled.form`
  display: flex;
  flex-direction: column;
  margin-top: 2em;
  gap: 2em;
  width: 100%;

  .main-buttons {
    display: flex;
    gap: 1em;
  }
`;

const WriteGoodTitleNotice = styled.div`
  display: flex;
  flex-direction: column;
  border: 0.1em solid rgb(216, 219, 222);
  border-radius: 5px;
  box-shadow: 2px 2px 5px 2px rgba(0, 0, 0, 0.1);

  .title-notice-title {
    background-color: rgb(248, 249, 249);
    border-bottom: solid rgb(216, 219, 222);
    padding: 0.8em;
    font-weight: 500;
    font-size: 1.3em;
  }
  .title-notice-body {
    background-color: white;
    display: flex;
    gap: 1em;
    padding: 1em;
  }
  .title-notice-icon {
    padding: 1em;
  }
  .title-notice-icon svg {
    width: 2em;
    height: 2em;
  }
  .title-notice-description {
    display: flex;
    flex-direction: column;
    gap: 1em;
  }
`;

const StyledButton = styled.button`
  font-size: 1.3em;
  color: ${(props) => (props.isCancel ? 'hsl(358,62%,47%)' : 'white')};
  /* color: white; */
  background-color: ${(props) =>
    props.isCancel ? 'rgb(248, 249, 249)' : 'rgb(10, 149, 255)'};
  border: none;
  border-radius: 5px;
  width: ${(props) => props.width};
  height: 2.5em;
  cursor: pointer;

  &:hover {
    background-color: ${(props) =>
      props.isCancel ? 'hsl(358,75%,97%)' : 'hsl(206, 100%, 40%)'};
  }
  &:active {
    background-color: ${(props) =>
      props.isCancel ? 'hsl(358,76%,90%)' : 'hsl(209, 100%, 37.5%)'};
    box-shadow: 0px 0px 2px 5px
      ${(props) =>
        props.isCancel ? 'hsl(358,74%,83%)' : 'hsl(206, 93%, 83.5%)'};
  }

  &:disabled {
    background-color: lightskyblue;
    color: rgb(227, 241, 252);
    cursor: pointer;
  }
`;

// 페이지 컴포넌트
const AskQuestion = () => {
  // const navigate = useNavigate();

  const [isButtonVisible, setIsButtonVisible] = useState({
    title: true,
    body: false,
    tags: false,
    discard: false,
  });
  const [isPostDisabled, setIsPostDisabled] = useState(true);
  const [inputText, setInputText] = useState({
    title: '',
    body: '',
    tags: '',
  });
  const [validationNotice, setValidationNotice] = useState({
    title: '',
    body: '',
    tags: '',
  });
  const [isValid, setIsValid] = useState({
    title: false,
    body: false,
    tags: false,
  });

  //validation
  const handleTitleButtonClick = (e) => {
    e.preventDefault();
    if (inputText.title.length < 15) {
      setValidationNotice({
        ...validationNotice,
        title: `Title must be at least 15 characters; you entered ${inputText.title.length}`,
      });
    }
    if (inputText.title.length > 30) {
      setValidationNotice({
        ...validationNotice,
        title: `Title cannot be longer than 30 characters. you entered ${inputText.title.length}`,
      });
    }
    if (inputText.title.length >= 15 && inputText.title.length <= 30) {
      setIsValid({ ...isValid, title: true });
      setIsButtonVisible({ ...isButtonVisible, title: false, body: true });
    }
  };

  const handleTitleInputBlur = () => {
    if (inputText.title.length < 15) {
      setIsValid({ ...isValid, title: false });
      setValidationNotice({
        ...validationNotice,
        title: `Title must be at least 15 characters; you entered ${inputText.title.length}`,
      });
    }
    if (inputText.title.length > 30) {
      setIsValid({ ...isValid, title: false });
      setValidationNotice({
        ...validationNotice,
        title: `Title cannot be longer than 30 characters. you entered ${inputText.title.length}`,
      });
    }
    if (inputText.title.length >= 15 && inputText.title.length <= 30) {
      setIsValid({ ...isValid, title: true });
    }
  };

  const handleBodyButtonClick = (e) => {
    e.preventDefault();
    if (inputText.body.length < 220) {
      setValidationNotice({
        ...validationNotice,
        body: `Body must be at least 220 characters; you entered ${inputText.body.length}`,
      });
    }
    if (inputText.body.length > 500) {
      setValidationNotice({
        ...validationNotice,
        body: `Body cannot be longer than 250 characters; you entered ${inputText.body.length}`,
      });
    }
    if (inputText.body.length >= 220 && inputText.body.length <= 500) {
      setIsValid({ ...isValid, body: true });
      setIsButtonVisible({ ...isButtonVisible, body: false, tags: true });
    }
  };
  const handleBodyInputBlur = () => {
    if (inputText.body.length < 220) {
      setIsValid({ ...isValid, body: false });
      setValidationNotice({
        ...validationNotice,
        body: `Body must be at least 220 characters; you entered ${inputText.body.length}`,
      });
    }
    if (inputText.body.length > 500) {
      setIsValid({ ...isValid, body: false });
      setValidationNotice({
        ...validationNotice,
        body: `Body cannot be longer than 250 characters; you entered ${inputText.body.length}`,
      });
    }
    if (inputText.body.length >= 220 && inputText.body.length <= 500) {
      setIsValid({ ...isValid, body: true });
    }
  };

  const handleTagsButtonClick = (e) => {
    e.preventDefault();

    if (inputText.tags.split(' ').length < 1 || inputText.tags.length === 0) {
      setValidationNotice({
        ...validationNotice,
        tags: 'Please enter at least one tag; see a list of popular tags.',
      });
      return;
    }
    if (inputText.tags.split(' ').length > 5) {
      setValidationNotice({
        ...validationNotice,
        tags: 'Please enter no more than 5 tags.',
      });
    }
    if (
      inputText.tags.split(' ').length >= 1 &&
      inputText.tags.split(' ').length < 6
    ) {
      setIsValid({ ...isValid, tags: true });
      setIsButtonVisible((prev) => ({ ...prev, tags: false, discard: true }));
      setIsPostDisabled(false);
    }
  };

  const handleTagsInputBlur = () => {
    if (inputText.tags.split(' ').length < 1 || inputText.tags.length === 0) {
      setIsValid({ ...isValid, tags: false });
      setValidationNotice({
        ...validationNotice,
        tags: 'Please enter at least one tag; see a list of popular tags.',
      });
      return;
    }
    if (inputText.tags.split(' ').length > 5) {
      setIsValid({ ...isValid, tags: false });
      setValidationNotice({
        ...validationNotice,
        tags: 'Please enter no more than 5 tags.',
      });
    }
    if (
      inputText.tags.split(' ').length >= 1 &&
      inputText.tags.split(' ').length < 6
    ) {
      setIsValid({ ...isValid, tags: true });
      setIsPostDisabled(false);
    }
  };

  const handleInputChange = (e) => {
    if (e.target.id === 'title') {
      setInputText({ ...inputText, title: e.target.value });
    } else if (e.target.id === 'body') {
      setInputText({ ...inputText, body: e.target.value });
    } else if (e.target.id === 'tags') {
      setInputText({ ...inputText, tags: e.target.value });
    }
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    if (
      inputText.title.length >= 15 &&
      inputText.title.length <= 30 &&
      inputText.body.length >= 220 &&
      inputText.body.length <= 500 &&
      inputText.tags.split(' ').length >= 1 &&
      inputText.tags.split(' ').length < 6 &&
      inputText.tags.trim().length !== 0
    ) {
      console.log(inputText.title);
      // 성공하면 navegate('/questions/{생성된questionId}') 로 리다이렉트하게 만들어야함
    }
  };

  const handleDiscardButtonClick = () => {
    // programically reload 하게 만들어서 상태값을 초기화시켜야되는데
    setIsButtonVisible({
      title: true,
      body: false,
      tags: false,
      discard: false,
    });
    setIsPostDisabled(true);
    setInputText({
      title: '',
      body: '',
      tags: '',
    });
    setValidationNotice({
      title: '',
      body: '',
      tags: '',
    });
    setIsValid({ title: false, body: false, tags: false });
  };

  return (
    <AskContainer>
      <WriteQuestionNotice>
        <h1>Ask a public question</h1>
        <WriteGoodQuestionNotice>
          <h2>Wrting a good question</h2>
          <p>
            You are ready to ask a programming-related question and this form
            will help guide you through the process. Looking to ask a
            non-programming question? See the topics here to find a relevant
            site.
          </p>
          <h5>Steps</h5>
          <ul>
            <li>Summarize your problem in a one-line title.</li>
            <li>Describe your problem in more detail.</li>
            <li>Describe what you tried and what you expected to happen.</li>
            <li>
              Add “tags” which help surface your question to members of the
              community.
            </li>
            <li>Review your question and post it to the site.</li>
          </ul>
        </WriteGoodQuestionNotice>
      </WriteQuestionNotice>
      <QuestionPostForm onSubmit={handleSubmit}>
        <WriteGoodTitleNotice>
          <h5 className="title-notice-title">Wrting a good title</h5>
          <div className="title-notice-body">
            <div className="title-notice-icon">
              <TfiPencil />
            </div>
            <div className="title-notice-description">
              <p>Your title should summarize the problem.</p>
              <p>
                You might find that you have a better idea of your title after
                writing out the rest of the question.
              </p>
            </div>
          </div>
        </WriteGoodTitleNotice>
        <AskQuestionInput
          title="Title"
          name="title"
          content=" Be specific and imagine you’re asking a question to another
          person."
          placeholder="e.g. Is there an R function for finding the index of an element in a vector?"
          inputLabel="title"
          inputValue={inputText.title}
          isButtonVisible={isButtonVisible.title}
          buttonTitle="Next"
          handleButtonClick={handleTitleButtonClick}
          handleInputChange={handleInputChange}
          minlength="15"
          maxlength="30"
          validationNotice={validationNotice.title}
          isValid={isValid.title}
          handleBlur={handleTitleInputBlur}
        />
        <AskQuestionInput
          title="Body"
          name="body"
          content="The body of your question contains your problem details and results. Minimum 220 characters."
          placeholder=""
          inputLabel="body"
          inputValue={inputText.body}
          isButtonVisible={isButtonVisible.body}
          buttonTitle="Next"
          handleButtonClick={handleBodyButtonClick}
          handleInputChange={handleInputChange}
          // height="10em"
          minlength="220"
          maxlength="250"
          validationNotice={validationNotice.body}
          isValid={isValid.body}
          handleBlur={handleBodyInputBlur}
        />
        <AskQuestionInput
          title="Tags"
          name="tags"
          content="Add up to 5 tags to describe what your question is about. Start typing to see suggestions."
          placeholder="e.g. (jquery windows pandas)"
          inputLabel="tags"
          inputValue={inputText.tags}
          isButtonVisible={isButtonVisible.tags}
          buttonTitle="Next"
          handleButtonClick={handleTagsButtonClick}
          handleInputChange={handleInputChange}
          validationNotice={validationNotice.tags}
          isValid={isValid.tags}
          handleBlur={handleTagsInputBlur}
        />
        <div className="main-buttons">
          <StyledButton type="submit" width="10em" disabled={isPostDisabled}>
            Post your question
          </StyledButton>
          {isButtonVisible.discard ? (
            <StyledButton
              type="button"
              width="8em"
              disabled={false}
              isCancel={true}
              onClick={handleDiscardButtonClick}
            >
              Discard draft
            </StyledButton>
          ) : null}
        </div>
      </QuestionPostForm>
    </AskContainer>
  );
};

export default AskQuestion;
