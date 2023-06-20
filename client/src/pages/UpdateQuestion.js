import { styled } from 'styled-components';
import StyledButton, {
  StyledButtonLink,
  StyledInputText,
  StyledTextarea,
} from '../styles/StyledButton';

const UpdateQuestionContainer = styled.form`
  display: flex;
  flex-direction: column;
  margin: 2em;
  gap: 1em;

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
      <div>
        <label htmlFor="title">Title</label>
        {/* <input id="title" type="text" /> */}
        <StyledInputText
          id="title"
          type="text"
          placeholder="e.g. Is there an R function for finding the index of an element in a vector?"
        />
      </div>
      <div>
        <label htmlFor="body">Body</label>
        {/* <textarea id="body" /> */}
        <StyledTextarea id="body" height="12em" />
      </div>
      <div>
        <label htmlFor="tags">Tags</label>
        <StyledInputText
          id="tags"
          type="text"
          placeholder="e.g. (django mongodb javascript)"
        />
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
