import { Routes, Route } from 'react-router-dom';
import GlobalStyle from './styles/GlobalStyle';
import Questions from './pages/Questions';
import AskQuestion from './pages/AskQuestion';
import QuestionDetail from './pages/QuestionDetail';
import UpdateQuestion from './pages/UpdateQuestion';
import UpdateAnswer from './pages/UpdateAnswer';
import SignUp from './pages/SignUp';
import Login from './pages/Login';
import Users from './pages/Users';
import SearchUsers from './pages/SearchUsers';
import { useEffect } from 'react';
import { useDispatch } from 'react-redux';
import { updateLoginState } from './slices/loginSlice';

function App() {
  const dispatch = useDispatch();

  useEffect(() => {
    dispatch(updateLoginState());
  }, []);

  return (
    <div>
      <GlobalStyle />

      <Routes>
        <Route path="/" element={<Questions />} />
        <Route path="users">
          <Route path="sign-up" element={<SignUp />} />
          <Route path="sign-in" element={<Login />} />
          <Route path="mypage" element={<Users />} />
          <Route path="anotherUser" element={<SearchUsers />} />
        </Route>
        <Route path="questions">
          <Route path="ask" element={<AskQuestion />} />
          <Route path=":questionId" element={<QuestionDetail />} />
          <Route path=":questionId/edit" element={<UpdateQuestion />} />
          <Route path=":questionId/:answerId/edit" element={<UpdateAnswer />} />
        </Route>
      </Routes>
    </div>
  );
}

export default App;
