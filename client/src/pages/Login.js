import { Link, useNavigate } from 'react-router-dom';
import { styled } from 'styled-components';
import StyledButton from '../styles/StyledButton';
import { useCallback, useState } from 'react';
import { actionL } from '../components/actionL';
import { useDispatch } from 'react-redux';
// import { userInfo } from '../slices/tokenSlice';
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
const LoginForm = styled.div`
  display: flex;
  flex-direction: column;
  background-color: white;
  box-shadow: 0 10px 24px rgba(0, 0, 0, 0.05), 0 20px 48px rgba(0, 0, 0, 0.05),
    0 1px 4px rgba(0, 0, 0, 0.1);
  border-radius: 10px;
  padding: 24px;
  margin-bottom: 24px;
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

const Login = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const dispatch = useDispatch();
  const onChangeEamil = useCallback((e) => {
    setEmail(e.target.value);
  });
  const onChangePassword = useCallback((e) => {
    setPassword(e.target.value);
  });
  const navigate = useNavigate();
  const onSubmitJoin = useCallback(
    (e) => {
      e.preventDefault();

      console.log([email, password]);
      dispatch(actionL({ email, password }))
        .then((resultAction) => {
          const { accessToken, userId, userMemberId } = resultAction.payload;

          // 토큰과 사용자 ID를 저장
          localStorage.setItem('Id', userId);
          //디스 플레이네임
          localStorage.setItem('Token', accessToken);
          //토큰
          localStorage.setItem('MemberId', userMemberId);
          //userMemberId = 벡에서 원하는 유저 아이디(숫자)

          navigate('/');

          // })
        })
        // eslint-disable-next-line no-unused-vars

        .catch((error) => {
          console.log('로그인 에러:', error);
          console.log(
            '로그인에 실패했습니다. 이메일과 비밀번호를 다시 확인해주세요.'
          );
        });
    },
    [dispatch, email, password]
  );

  return (
    <Container>
      <Contents>
        <Logo>
          <img src="/images/logo-simple.png" alt="logo stackflow"></img>
        </Logo>
        <Ouths>
          <Outh id="Google">
            <OuthContents>
              <img src="/images/google.png" alt="googlr logo"></img>
              <span>Log in with Google</span>
            </OuthContents>
          </Outh>
          <Outh id="GitHub">
            <OuthContents>
              <img src="/images/github.png" alt="googlr logo"></img>
              <span>Log in with GitHub</span>
            </OuthContents>
          </Outh>
          <Outh id="Facebook">
            <OuthContents>
              <img src="/images/facebook.png" alt="googlr logo"></img>
              <span>Log in with Facebook</span>
            </OuthContents>
          </Outh>
        </Ouths>
        <form onSubmit={onSubmitJoin}>
          <LoginForm>
            <InputEmail>
              <div>Email</div>
              <input
                type="email"
                id="loginEamil"
                placeholder="이메일"
                value={email}
                onChange={onChangeEamil}
              ></input>
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
              <StyledButton>로그인</StyledButton>
            </div>
          </LoginForm>
        </form>
        <Message>
          <div>
            Don’t have an account? <Link to="">Sign up</Link>
          </div>

          <div className="areyou">
            Are you an employer? <Link to="">Sign up on Talent </Link>
          </div>
        </Message>
      </Contents>
    </Container>
  );
};

export default Login;
