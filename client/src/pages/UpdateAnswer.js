import { styled } from 'styled-components';
import StyledButton, {
  StyledButtonLink,
  StyledInputText,
  StyledTextarea,
} from '../styles/StyledButton';
import { Link } from 'react-router-dom';

const UpdateQuestionContainer = styled.form`
  display: flex;
  flex-direction: column;
  margin: 2em;
  gap: 1em;

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
  const handleSubmit = (e) => {
    e.preventDefault;
    // http POST 요청 보내야함
  };

  return (
    <UpdateQuestionContainer onSubmit={handleSubmit}>
      <Link className="title">How Can I submit a POST?</Link>
      <div>
        <label htmlFor="body">Body</label>
        {/* <textarea id="body" /> */}
        <StyledTextarea id="body" height="12em" />
      </div>
      <div>
        <label htmlFor="summary">Edit Summary</label>
        <StyledInputText
          id="summary"
          type="text"
          placeholder="briefly explain your changes (corrected spelling, fixed grammar, improved formatting)"
        />
      </div>
      <div className="button-container">
        <StyledButton type="submit" fontSize="1em" width="6em">
          Save edits
        </StyledButton>
        <StyledButtonLink to="/questions/1" fontSize="1em">
          Cancel
        </StyledButtonLink>
      </div>
    </UpdateQuestionContainer>
  );
};

export default UpdateQuestion;
