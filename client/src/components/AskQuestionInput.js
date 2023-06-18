import { styled } from 'styled-components';
import { PropTypes } from 'prop-types';

export const PostInputBox = styled.div`
  display: flex;
  flex-direction: column;
  gap: 1em;
  padding: 1.8em;
  border: 0.1em solid rgb(216, 219, 222);
  border-radius: 5px;
  background-color: white;

  label h5 {
    font-weight: 600;
    font-size: 1.4em;
  }

  input {
    height: ${(props) => props.height || '3.5em'};
    padding: 1em;
    font-size: 1em;
    text-align: ${(props) => (props.title === 'top' ? 'start' : 'left')};

    &:focus {
      border: 0.1em solid hsl(206, 85%, 57.5%);
      box-shadow: 0px 0px 2px 5px hsl(206, 93%, 83.5%);
    }
  }

  textarea {
    padding: 1em;
    font-size: 1em;
    text-align: ${(props) => (props.title === 'top' ? 'start' : 'left')};

    &:focus {
      border: 0.1em solid hsl(206, 85%, 57.5%);
      box-shadow: 0px 0px 2px 5px hsl(206, 93%, 83.5%);
    }
  }

  button {
    display: ${(props) => (props.isButtonVisible ? 'block' : 'none')};
    font-size: 1.2em;
    color: white;
    background-color: rgb(10, 149, 255);
    border: none;
    border-radius: 5px;
    width: 3.5em;
    height: 2.5em;
    box-shadow: rgba(255, 255, 255, 0.4) 0px 2px 0px 0px inset;
    cursor: pointer;

    &:hover {
      background-color: hsl(206, 100%, 40%);
    }
    &:active {
      background-color: hsl(209, 100%, 37.5%);
      box-shadow: 0px 0px 2px 5px hsl(206, 93%, 83.5%);
    }
  }

  .validation-notice {
    color: red;
  }
`;

const AskQuestionInput = ({
  title,
  name,
  content,
  placeholder,
  inputLabel,
  inputValue,
  isButtonVisible,
  buttonTitle,
  handleButtonClick,
  handleInputChange,
  height,
  validationNotice,
  isValid,
  minlength,
  maxlength,
  handleBlur,
}) => {
  return (
    <PostInputBox isButtonVisible={isButtonVisible} height={height}>
      <label htmlFor={inputLabel}>
        <h5>{title}</h5>
        <p>{content}</p>
      </label>
      {name === 'body' ? (
        <textarea
          name={name}
          id={inputLabel}
          placeholder={placeholder}
          value={inputValue}
          minLength={minlength}
          maxLength={maxlength}
          onChange={handleInputChange}
          rows="12"
          cols="50"
          onBlur={handleBlur}
        />
      ) : (
        <input
          type="text"
          name={name}
          id={inputLabel}
          placeholder={placeholder}
          value={inputValue}
          minLength={minlength}
          maxLength={maxlength}
          onChange={handleInputChange}
          onBlur={handleBlur}
        />
      )}
      {!isValid && <p className="validation-notice">{validationNotice}</p>}
      <button type="button" onClick={handleButtonClick}>
        {buttonTitle}
      </button>
    </PostInputBox>
  );
};

AskQuestionInput.propTypes = {
  title: PropTypes.string,
  name: PropTypes.string,
  content: PropTypes.string,
  placeholder: PropTypes.string,
  inputLabel: PropTypes.string,
  inputValue: PropTypes.string,
  isButtonVisible: PropTypes.bool,
  buttonTitle: PropTypes.string,
  handleButtonClick: PropTypes.func,
  height: PropTypes.string,
  handleInputChange: PropTypes.func,
  validationNotice: PropTypes.string,
  isValid: PropTypes.bool,
  minlength: PropTypes.string,
  maxlength: PropTypes.string,
  handleBlur: PropTypes.func,
};

export default AskQuestionInput;
