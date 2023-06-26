import { Link } from 'react-router-dom';
import { styled } from 'styled-components';
import { IoIosSearch } from 'react-icons/io';
import { BasicBlueButton } from '../styles/Buttons';
import { useState } from 'react';
import SearchGuide from './SearchGuide';
import { searchBarfilter } from '../slices/filterquestionSlice';
import { selectPage } from '../slices/paginationSlice';
import { useDispatch } from 'react-redux';

const Headercontainer = styled.div`
  position: sticky;
  display: flex;
  top: 0;
  width: 100%;
  height: 70px;
  z-index: 5050;
  justify-content: center;
  align-items: center;
  border-top: 3px solid hsl(27, 90%, 55%);
  background-color: hsl(210, 8%, 97.5%);
  box-shadow: 0 1px 2px hsla(0, 0%, 0%, 0.05), 0 1px 4px hsla(0, 0%, 0%, 0.05),
    0 2px 8px hsla(0, 0%, 0%, 0.05);
  margin: 0 auto;
`;
const Headerlogo = styled.div`
  img {
    display: flex;
    margin-bottom: 10px;
    align-items: center;
    padding: 0 8px;
    width: 200px;
    height: 30px;
    &:hover {
      background-color: hsl(210, 8%, 90%);
      margin-bottom: 10px;
    }
  }
`;
const HeaderTextButtonContainer = styled.div`
  display: flex;
  align-items: center;
`;

const HeaderTextButton = styled.div`
  padding: 6px 12px;
  color: hsl(210, 8%, 35%);
  font-size: 15px;
  border-radius: 20px;
  cursor: pointer;
  &:hover {
    color: black;
    background-color: hsl(210, 8%, 90%);
  }
`;
const SearchBox = styled.div`
  position: relative;
  display: flex;
  align-items: center;
  margin-left: 5px;
`;
const StyledIoIosSearch = styled(IoIosSearch)`
  position: absolute;
  font-size: 20px;

  color: hsl(210, 8%, 60%);
  left: 0.5em;
`;
const SearchInput = styled.input`
  padding-left: 2.5em; /* Adjust the left padding to accommodate the search icon */
  height: 32px;
  width: 650px;
  padding-top: 3px;
  border-radius: 3px;
  font-size: 16px;
  border: 1px solid hsl(210, 8%, 60%);
  outline: none;
  &:focus {
    border-color: hsl(206, 90%, 69.5%);
    box-shadow: 0 0 0 4px hsla(206, 100%, 40%, 0.15);
  }
`;

export default function Header() {
  const [isFocus, setIsFocus] = useState(false);
  const [searchInputValue, setSearchInputValue] = useState('');
  const dispatch = useDispatch();
  const focusHandler = () => {
    setIsFocus(!isFocus);
  };
  const isFollowGuide = (text) => {
    if (text.slice(0, 5) === '[tag]') {
      dispatch(searchBarfilter({ tags: text.slice(5, text.length) }));
    } else if (text.slice(0, 12) === 'displayName:') {
      dispatch(searchBarfilter({ user: text.slice(12, text.length) }));
    } else if (text.slice(0, 7) === 'answer:') {
      dispatch(
        searchBarfilter({ answerCount: parseInt(text.slice(7, text.length)) })
      );
    }
  };

  const searchbarInputHandler = () => {
    isFollowGuide(searchInputValue);
    setSearchInputValue('');
    dispatch(selectPage(1));
  };
  return (
    <>
      <Headercontainer>
        <Headerlogo>
          <Link to="/">
            <img src="/images/logo-stackoverflow.png" alt="logo" />
          </Link>
        </Headerlogo>
        <HeaderTextButtonContainer>
          <HeaderTextButton>About</HeaderTextButton>
          <HeaderTextButton>Product</HeaderTextButton>
          <HeaderTextButton>For Teams</HeaderTextButton>
        </HeaderTextButtonContainer>
        <SearchBox>
          <SearchInput
            type="text"
            maxLength={240}
            value={searchInputValue}
            placeholder="Search..."
            onChange={(e) => {
              setSearchInputValue(e.target.value);
            }}
            onKeyDown={(e) => {
              if (e.key === 'Enter') {
                searchbarInputHandler(e);
              }
            }}
            onFocus={focusHandler}
          />
          <StyledIoIosSearch />
          {isFocus && <SearchGuide />}
        </SearchBox>
        <BasicBlueButton skyblue>
          <Link to="/users/sign-in">Log in</Link>
        </BasicBlueButton>
        <BasicBlueButton>
          <Link to="/users/sign-up">Sign up</Link>
        </BasicBlueButton>
      </Headercontainer>
    </>
  );
}
