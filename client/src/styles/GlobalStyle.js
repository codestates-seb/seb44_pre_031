import { createGlobalStyle } from 'styled-components';

const GlobalStyle = createGlobalStyle`
  * {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
    font-family: 'Noto Sans', 'Noto Sans KR', sans-serif;
    }
.wrap{
  position: relative;
    margin: 0 auto;
    display: flex;
    justify-content:center;
  
}
`;

export default GlobalStyle;
