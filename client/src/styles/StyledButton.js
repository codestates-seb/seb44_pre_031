import { styled } from 'styled-components';
import { Link } from 'react-router-dom';

export const StyledButton = styled.button`
  font-size: ${(props) => props.fontSize};
  color: ${(props) => (props.isCancel ? 'hsl(358,62%,47%)' : 'white')};
  background-color: ${(props) =>
    props.isCancel ? 'rgb(248, 249, 249)' : 'rgb(10, 149, 255)'};
  border: none;
  border-radius: 5px;
  width: ${(props) => props.width};
  height: 2.5em;
  box-shadow: rgba(255, 255, 255, 0.4) 0px 2px 0px 0px inset;
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

export const StyledButtonLink = styled(Link)`
  color: hsl(205, 47%, 42%);
  font-size: ${(props) => props.fontSize};
  padding: 10px 10px;
  /* padding-left: 6px;
  padding-right: 6px; */
  /* height: 2.5em; */

  background-color: white;
  border: none;
  border-radius: 3px;
  cursor: pointer;
  text-decoration: none;
  text-align: center;

  &:visited {
    text-decoration: none;
  }

  &:hover {
    background-color: hsl(205, 46%, 92%);
  }
`;

export const StyledTagLink = styled(Link)`
  color: hsl(205, 47%, 42%);
  font-size: 14px;
  padding-left: 6px;
  padding-right: 6px;

  background-color: hsl(205, 46%, 92%);
  border: none;
  border-radius: 3px;
  height: 21px;
  cursor: pointer;
  text-decoration: none;
  text-align: center;

  &:visited {
    text-decoration: none;
  }

  &:hover {
    background-color: hsl(205, 53%, 88%);
  }
`;

export const StyledInputText = styled.input`
  height: ${(props) => props.height || '3.5em'};
  padding: 1em;
  font-size: 1em;
  width: 100%;
  /* text-align: ${(props) => (props.title === 'top' ? 'start' : 'left')}; */

  &:focus {
    border: 0.1em solid hsl(206, 85%, 57.5%);
    box-shadow: 0px 0px 2px 5px hsl(206, 93%, 83.5%);
  }
`;

export const StyledTextarea = styled.textarea`
  padding: 1em;
  font-size: 1em;
  width: 100%;
  height: ${(props) => props.height || '4em'};
  /* text-align: ${(props) => (props.title === 'top' ? 'start' : 'left')}; */

  &:focus {
    border: 0.1em solid hsl(206, 85%, 57.5%);
    box-shadow: 0px 0px 2px 5px hsl(206, 93%, 83.5%);
  }
`;

export default StyledButton;
