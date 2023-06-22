import { Routes, Route, useLocation } from 'react-router-dom';
import GlobalStyle from './styles/GlobalStyle';
import Questions from './pages/Questions';
import AskQuestion from './pages/AskQuestion';
import QuestionDetail from './pages/QuestionDetail';
import UpdateQuestion from './pages/UpdateQuestion';
import UpdateAnswer from './pages/UpdateAnswer';
import SignUp from './pages/SignUp';
import Login from './pages/Login';
import Users from './pages/Users';
import Header from './components/Header';
import Nav from './components/Nav';

function App() {
  let location = useLocation();
  console.log(location);

  return (
    <div>
      <GlobalStyle />
      <Header />
      <Nav />
      <Routes>
        <Route path="/" element={<Questions />} />
        {/* <Route index element={<Questions />} /> */}
        <Route path="users">
          <Route path="sign-up" element={<SignUp />} />
          <Route path="sign-in" element={<Login />} />
          <Route path="mypage" element={<Users />} />
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
