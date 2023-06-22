import { styled } from 'styled-components';
import { Link } from 'react-router-dom';
export const BasicBlueButton = styled(Link)`
  margin-left: 9px;
  cursor: pointer;
  padding: 8px 0.8em;
  box-shadow: inset 0 1px 0 0 hsla(0, 0%, 100%, 0.7);
  border: 1px solid transparent;
  border-radius: 3px;
  font-size: 14px;
  text-decoration: none;
  white-space: nowrap;
  pointer-events: auto;
  color: ${(props) => (props.skyblue ? 'hsl(205,47%,42%)' : 'white')};
  background-color: ${(props) =>
    props.skyblue ? 'hsl(205,46%,92%)' : 'hsl(206,100%,52%)'};
  &:hover {
    background-color: ${(props) =>
      props.skyblue ? 'hsl(205,57%,81%)' : 'hsl(206,100%,40%)'};
  }
  &:focus {
    border-color: hsl(206, 90%, 69.5%);
    box-shadow: 0 0 0 4px hsla(206, 100%, 40%, 0.15);
  }
`;
