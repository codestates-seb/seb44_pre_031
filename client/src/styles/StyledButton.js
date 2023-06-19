import { styled } from 'styled-components';
import { Link } from 'react-router-dom';

const StyledButton = styled.button`
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

export const StyledLink = styled(Link)`
  font-size: 1.2em;
  color: white;
  background-color: rgb(10, 149, 255);
  border: none;
  border-radius: 5px;
  width: ${(props) => props.width};
  height: 2.5em;
  box-shadow: rgba(255, 255, 255, 0.4) 0px 2px 0px 0px inset;
  cursor: pointer;
  text-decoration: none;

  &:visited {
    color: white;
    text-decoration: none;
  }

  &:hover {
    background-color: hsl(206, 100%, 40%);
  }
  &:active {
    background-color: hsl(209, 100%, 37.5%);
    box-shadow: 0px 0px 2px 5px hsl(206, 93%, 83.5%);
  }

  &:disabled {
    background-color: lightskyblue;
    color: rgb(227, 241, 252);
    cursor: pointer;
  }
`;

export default StyledButton;
