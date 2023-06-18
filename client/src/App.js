import { Routes, Route, useLocation } from 'react-router-dom';
import GlobalStyle from './styles/GlobalStyle';
import AskQuestion from './pages/AskQuestion';
import QuestionDetail from './pages/QuestionDetail';

function App() {
  let location = useLocation();
  console.log(location);

  return (
    <div>
      <GlobalStyle />
      <Routes>
        <Route path="questions">
          <Route path="ask" element={<AskQuestion />} />
          <Route path=":questionId" element={<QuestionDetail />} />
        </Route>
      </Routes>
    </div>
  );
}

export default App;
