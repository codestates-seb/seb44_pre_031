import { Routes, Route, useLocation } from 'react-router-dom';
import GlobalStyle from './styles/GlobalStyle';
import Questions from './pages/Questions';
import AskQuestion from './pages/AskQuestion';
import QuestionDetail from './pages/QuestionDetail';
import UpdateQuestion from './pages/UpdateQuestion';
import UpdateAnswer from './pages/UpdateAnswer';

function App() {
  let location = useLocation();
  console.log(location);

  return (
    <div>
      <GlobalStyle />

      <Routes>
        <Route path="questions">
          <Route index element={<Questions />} />
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
