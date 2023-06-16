import { styled } from 'styled-components';

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
    /* height: 3.5em; */
    padding: 1em;
    font-size: 1em;
    /* border-radius: 5px; */
    text-align: ${(props) => (props.title === 'top' ? 'start' : 'left')};

    &:focus {
      border: 0.1em solid hsl(206, 85%, 57.5%);
      box-shadow: 0px 0px 2px 5px hsl(206, 93%, 83.5%);
    }
  }

  button {
    display: ${(props) => (props.isButtonVisible ? 'block' : 'none')};
    font-size: 1.3em;
    color: white;
    background-color: rgb(10, 149, 255);
    border: none;
    border-radius: 5px;
    width: 3.5em;
    height: 2.5em;
    cursor: pointer;

    &:hover {
      background-color: hsl(206, 100%, 40%);
    }
    &:active {
      background-color: hsl(209, 100%, 37.5%);
      box-shadow: 0px 0px 2px 5px hsl(206, 93%, 83.5%);
    }
  }
`;

const AskQuestionInput = ({
  // eslint-disable-next-line react/prop-types
  title,
  // eslint-disable-next-line react/prop-types
  content,
  // eslint-disable-next-line react/prop-types
  placeholder,
  // eslint-disable-next-line react/prop-types
  inputLabel,
  // eslint-disable-next-line react/prop-types
  inputValue,
  // eslint-disable-next-line react/prop-types
  isButtonVisible,
  // eslint-disable-next-line react/prop-types
  buttonTitle,
  // eslint-disable-next-line react/prop-types
  handleButtonClick,
  // eslint-disable-next-line react/prop-types
  handleInputChange,
  // eslint-disable-next-line react/prop-types
  height,
}) => {
  return (
    <PostInputBox isButtonVisible={isButtonVisible} height={height}>
      <label htmlFor={inputLabel}>
        <h5>{title}</h5>
        <p>{content}</p>
      </label>
      <input
        type="text"
        id={inputLabel}
        placeholder={placeholder}
        value={inputValue}
        onChange={handleInputChange}
      ></input>
      <button type="button" onClick={handleButtonClick}>
        {buttonTitle}
      </button>
    </PostInputBox>
  );
};

export default AskQuestionInput;
