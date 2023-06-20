import { Routes, Route, useLocation } from 'react-router-dom';
import GlobalStyle from './styles/GlobalStyle';
import AskQuestion from './pages/AskQuestion';
import QuestionDetail from './pages/QuestionDetail';
import UpdateQuestion from './pages/UpdateQuestion';
import UpdateAnswer from './pages/UpdateAnswer';
import SignUp from './pages/SignUp';

function App() {
  let location = useLocation();
  console.log(location);

  return (
    <div>
      <GlobalStyle />
      <SignUp />
      <Routes>
        <Route path="questions">
          <Route path="ask" element={<AskQuestion />} />
          <Route path=":questionId" element={<QuestionDetail />} />
          <Route path=":questionId/edit" element={<UpdateQuestion />} />
        </Route>
        <Route path="answers">
          <Route path=":answerId/edit" element={<UpdateAnswer />} />
        </Route>
      </Routes>
    </div>
  );
}

export default App;
