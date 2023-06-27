import { Link, useNavigate } from 'react-router-dom';
import { styled } from 'styled-components';
import StyledButton from '../styles/StyledButton';

import { useCallback, useState } from 'react';
import { useDispatch } from 'react-redux';
import { actionS } from '../components/actionS';
import axios from 'axios';

const Container = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
  background-color: #f1f2f3;
  font-size: 0.8rem;
`;
const Contents = styled.div`
  display: flex;
  flex-direction: column;
  width: 278px;
`;
const Logo = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-bottom: 24px;
  #logo {
    width: 32px;
    height: 37px;
  }
`;
const Outh = styled.button`
  margin: 4px 0 4px;
  border: 1px solid;
  padding: 10.4px;
  border-radius: 5px;

  &:hover {
    cursor: pointer;
  }

  &#Google {
    background-color: white;
    border-color: #d6d9dc;
    &:hover {
      background-color: #f8f9f9;
    }
    &:active {
      background-color: #f1f2f3;
    }
    &img {
      width: 25px;
      height: 25px;
    }
  }

  &#GitHub {
    background-color: #2f3337;
    color: white;
    border-color: transparent;
    &:hover {
      background-color: #232629;
    }
    &:active {
      background-color: black;
    }
  }

  &#Facebook {
    background-color: #385499;
    color: white;
    border-color: transparent;
    &:hover {
      background-color: #314a86;
    }
    &:active {
      background-color: #2a4074;
    }
  }
`;

const Ouths = styled.div`
  display: flex;
  flex-direction: column;
  margin: -4px 0 16px;

  > a {
    display: flex;
    flex-direction: column;
    text-decoration: none;
  }
`;
const OuthContents = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  span {
    margin-left: 10px;
  }
`;
const SignUpForm = styled.div`
  display: flex;
  flex-direction: column;
  background-color: white;
  box-shadow: 0 10px 24px rgba(0, 0, 0, 0.05), 0 20px 48px rgba(0, 0, 0, 0.05),
    0 1px 4px rgba(0, 0, 0, 0.1);
  border-radius: 10px;
  padding: 24px;
  margin-bottom: 24px;
`;
const InputDisplayName = styled.div`
  display: flex;
  flex-direction: column;
  margin: 6px 0 6px;

  > label {
    text-align: left;
    margin: 2px 0 2px;
    padding: 0 2px;
    font-size: 1rem;
    font-weight: bold;
  }

  > input {
    margin: 2px 0 2px;
    border: 1px solid #babfc4;
    border-radius: 3px;
    padding: 0.6em 0.7em;
    color: #0c0d0e;
  }
`;

const InputEmail = styled.div`
  display: flex;
  flex-direction: column;
  margin: 6px 0 6px;

  > label {
    text-align: left;
    margin: 2px 0 2px;
    padding: 0 2px;
    font-size: 1rem;
    font-weight: bold;
  }

  > input {
    margin: 2px 0 2px;
    border: 1px solid #babfc4;
    border-radius: 3px;
    padding: 0.6em 0.7em;
    color: #0c0d0e;
  }
`;

const InputPW = styled.div`
  display: flex;
  flex-direction: column;
  margin: 6px 0 6px;

  > div.input-password-label {
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    align-items: center;

    > div {
      text-align: left;
      margin: 2px 0 2px;
      padding: 0 2px;
      font-size: 1rem;
      font-weight: 800;
    }
  }

  > input {
    margin: 2px 0 2px;
    border: 1px solid #babfc4;
    border-radius: 3px;
    padding: 0.6em 0.7em;
    color: #0c0d0e;
  }
`;
const JustMarginTop = styled.div`
  height: 6rem;
  display: flex;
  flex-direction: column;
  justify-content: space-around;
`;
const Message = styled.div`
  padding: 16px;
  margin-bottom: 24px;
  display: flex;
  flex-direction: column;
  align-items: center;

  div.areyou {
    margin-top: 12px;
  }
`;
const EmailAuthForm = styled.div`
  display: flex;
`;
const SignUp = () => {
  const [email, setEmail] = useState('');
  const [emailAuth, setEmailAuth] = useState('');
  const [password, setPassword] = useState('');
  const [displayName, setdisplayName] = useState('');

  const dispatch = useDispatch();
  const navigate = useNavigate();
  const onChangeEamil = useCallback((e) => {
    setEmail(e.target.value);
  });
  const onChangeEamilAuth = useCallback((e) => {
    setEmailAuth(e.target.value);
  });
  const onChangePassword = useCallback((e) => {
    setPassword(e.target.value);
  });
  const onChangeDisplay = useCallback((e) => {
    setdisplayName(e.target.value);
  });
  // const goEmail =
  const onSubmitJoin = useCallback(
    (e) => {
      e.preventDefault();

      console.log([displayName, email, emailAuth, password]);
      dispatch(actionS({ displayName, email, emailAuth, password })).then(
        (resultAction) => {
          const { success } = resultAction.payload;
          if (success === true) {
            alert('회원가입 성공');
            navigate('/users/sign-in');
          } else {
            alert('비밀번호 아이디 이메일 인증을 모두 수행하쇼');
          }
        }
      );
    },
    [displayName, email, emailAuth, password]
  );
  const goEmail = useCallback(
    (event) => {
      event.preventDefault();
      axios
        .get(
          `http://ec2-52-79-240-48.ap-northeast-2.compute.amazonaws.com:8080/api/users/emails?email=${email}`
        )
        .then((response) => {
          // 이메일 인증에 대한 로직을 추가해주세요
          console.log(response);
        })
        .catch((error) => {
          console.error(error);
        });
    },
    [email]
  );
  return (
    <Container>
      <Contents>
        <Logo>logo</Logo>
        <Ouths>
          <Outh id="Google">
            <OuthContents>
              <img src="/images/google.png" alt="googlr logo"></img>
              <span>Sign up with Google</span>
            </OuthContents>
          </Outh>
          <Outh id="GitHub">
            <OuthContents>
              <img src="/images/github.png" alt="googlr logo"></img>
              <span>Sign up with GitHub</span>
            </OuthContents>
          </Outh>
          <Outh id="Facebook">
            <OuthContents>
              <img src="/images/facebook.png" alt="googlr logo"></img>
              <span>Sign up with Facebook</span>
            </OuthContents>
          </Outh>
        </Ouths>
        <form onSubmit={onSubmitJoin}>
          <SignUpForm>
            <InputDisplayName>
              <div>Display name</div>
              <input
                type="name"
                id="signupDisplayName"
                placeholder="보여질 이름입력"
                value={displayName}
                onChange={onChangeDisplay}
              ></input>
            </InputDisplayName>
            <InputEmail>
              <div>Email</div>
              <input
                type="email"
                id="loginEamil"
                placeholder="이메일"
                value={email}
                onChange={onChangeEamil}
              ></input>
              {/* <div onClick={goEmail}>이메일 인증</div> */}
            </InputEmail>
            <InputEmail>
              <div>Email 인증</div>
              <EmailAuthForm>
                <input
                  type="text"
                  id="emailAuth"
                  placeholder="인증번호 입력"
                  value={emailAuth}
                  onChange={onChangeEamilAuth}
                ></input>
                <StyledButton onClick={goEmail}>인증번호 발급</StyledButton>
              </EmailAuthForm>

              {/* <div onClick={goEmail}>보내기</div> */}
            </InputEmail>
            <InputPW>
              <div>Password</div>
              <input
                type="password"
                id="loginPassword"
                placeholder="비밀번호"
                value={password}
                onChange={onChangePassword}
              ></input>
            </InputPW>
            <div>
              <StyledButton>회원가입</StyledButton>
            </div>
            <JustMarginTop>
              <hr></hr>
              <p>
                By clicking “Sign up”, you agree to our terms of service and
                acknowledge that you have read and understand our privacy policy
                and code of conduct.
              </p>
            </JustMarginTop>
          </SignUpForm>
        </form>
        <Message>
          <div>
            Already have an account? <Link to="">Log in </Link>
          </div>

          <div className="areyou">
            Are you an employer? <Link to="">Sign up on Talent </Link>
          </div>
        </Message>
      </Contents>
    </Container>
  );
};

export default SignUp;
