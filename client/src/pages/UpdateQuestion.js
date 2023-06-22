import { styled } from 'styled-components';
import StyledButton, {
  StyledButtonLink,
  StyledInputText,
  StyledTextarea,
} from '../styles/StyledButton';
import { useParams } from 'react-router-dom';
import { useSelector } from 'react-redux';
import { selectQuestion } from '../slices/questionSlice';

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
  const params = useParams();
  console.log(params);
  const question = useSelector(selectQuestion);

  const handleSubmit = (e) => {
    e.preventDefault;
    // http POST 요청 보내야함
  };

  return (
    <UpdateQuestionContainer onSubmit={handleSubmit}>
      <div>
        <label htmlFor="title">Title</label>
        <StyledInputText
          id="title"
          type="text"
          placeholder="e.g. Is there an R function for finding the index of an element in a vector?"
          value={question.title}
        />
      </div>
      <div>
        <label htmlFor="body">Body</label>
        <StyledTextarea id="body" height="12em" value={question.content} />
      </div>
      <div>
        <label htmlFor="tags">Tags</label>
        <StyledInputText
          id="tags"
          type="text"
          placeholder="e.g. (django mongodb javascript)"
          // value={question.tags}
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
        <StyledButtonLink to={`/questions/${params.questionId}`} fontSize="1em">
          Cancel
        </StyledButtonLink>
      </div>
    </UpdateQuestionContainer>
  );
};

export default UpdateQuestion;
