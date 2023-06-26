/* eslint-disable react/prop-types */
import { Link } from 'react-router-dom';
import { styled } from 'styled-components';
import { IoIosSearch } from 'react-icons/io';
import { BasicBlueButton } from '../styles/Buttons';
import { useState } from 'react';
import SearchGuide from './SearchGuide';
import { setTotalposts } from '../slices/paginationSlice';
import { useDispatch, useSelector } from 'react-redux';
import axios from 'axios';

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

export default function Header({ setAllQuestions }) {
  const [isFocus, setIsFocus] = useState(false);
  const [searchInputValue, setSearchInputValue] = useState('');

  const handleOnKeyDown = async (e) => {
    if (e.key === 'Enter') {
      if (
        searchInputValue.trim().slice(0, 1) === '[' &&
        searchInputValue.trim().slice(-1) === ']'
      ) {
        try {
          // '['  => 태그검색한다는뜻  => tag 요청 http를 보낸다
          const tag = searchInputValue.trim().slice(1, -1);
          const response = await axios.get(
            `http://ec2-52-79-240-48.ap-northeast-2.compute.amazonaws.com:8080/api/questions/search-tag/${tag}`
          );
          const questions = response.data.result.data.content;
          setAllQuestions(questions);
          dispatch(
            setTotalposts({
              questionsLength: questions.length,
              questionsTitle: `Questions tagged [${tag}]`,
            })
          );
        } catch (error) {
          console.log('Error fetching questions:', error);
        }
      } else if (searchInputValue.trim().slice(0, 5) === 'user:') {
        try {
          // 'user:'  => 유저이름으로 검색한다는뜻  => username을 요청 http를 보낸다
          const username = searchInputValue.trim().slice(5);
          const response = await axios.get(
            `http://ec2-52-79-240-48.ap-northeast-2.compute.amazonaws.com:8080/api/questions/search-author/${username}`
          );
          const questions = response.data.result.data.content;
          setAllQuestions(questions);
          dispatch(
            setTotalposts({
              questionsLength: questions.length,
              questionsTitle: `Search by Author: ${username}`,
            })
          );
        } catch (error) {
          console.log('Error fetching questions:', error);
        }
      } else if (searchInputValue.trim() === 'answers:0') {
        try {
          // 'user:'  => 유저이름으로 검색한다는뜻  => username을 요청 http를 보낸다
          const response = await axios.get(
            `http://ec2-52-79-240-48.ap-northeast-2.compute.amazonaws.com:8080/api/questions/search-unanswered`
          );
          const questions = response.data.result.data.content;
          setAllQuestions(questions);
          dispatch(
            setTotalposts({
              questionsLength: questions.length,
              questionsTitle: 'Unanswered Questions',
            })
          );
        } catch (error) {
          console.log('Error fetching questions:', error);
        }
      } else {
        try {
          // 위에 아무것도 안걸린 디폴트값  => 타이틀 키워드 검색 요청 http를 보낸다
          const keyword = searchInputValue.trim();
          const response = await axios.get(
            `http://ec2-52-79-240-48.ap-northeast-2.compute.amazonaws.com:8080/api/questions/search-keyword/${keyword}`
          );
          const questions = response.data.result.data.content;
          setAllQuestions(questions);
          dispatch(
            setTotalposts({
              questionsLength: questions.length,
              questionsTitle: `Search by Title Keyword: ${keyword}`,
            })
          );
        } catch (error) {
          console.log('Error fetching questions:', error);
        }
      }
    }
  };

  const dispatch = useDispatch();
  const isLoggedIn = useSelector((state) => state.login.isLoggedIn);

  const focusHandler = () => {
    setIsFocus(!isFocus);
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
            onKeyDown={handleOnKeyDown}
            onFocus={focusHandler}
          />
          <StyledIoIosSearch />
          {isFocus && <SearchGuide />}
        </SearchBox>
        {isLoggedIn ? (
          <BasicBlueButton skyblue>
            <Link to="/users/mypage">My Page</Link>
          </BasicBlueButton>
        ) : (
          <>
            <BasicBlueButton skyblue>
              <Link to="/users/sign-in">Log in</Link>
            </BasicBlueButton>
            <BasicBlueButton>
              <Link to="/users/sign-up">Sign up</Link>
            </BasicBlueButton>
          </>
        )}
      </Headercontainer>
    </>
  );
}
