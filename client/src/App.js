import GlobalStyle from './styles/GlobalStyle';
import Header from './components/Header';

import Questions from './pages/Questions';
import Nav from './components/Nav';
import Aside from './components/Aside';

function App() {
  return (
    <div>
      <GlobalStyle />
      <Header />
      <div className="wrap">
        <Nav />
        <Questions />
        <Aside />
      </div>
    </div>
  );
}

export default App;
