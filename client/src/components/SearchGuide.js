import { styled } from 'styled-components';
import { BasicBlueButton } from '../styles/Buttons';

const SearchGuideBox = styled.div`
  position: absolute;
  padding-top: 30px;
  top: 45px;
  left: 0;
  right: 0;
  width: 650px;
  height: 150.88px;
  padding: 0;
  margin: 0px;
  background-color: white;
  border: 1px solid hsl(210, 8%, 85%);
  box-shadow: 0 1px 3px hsla(0, 0%, 0%, 0.06), 0 2px 6px hsla(0, 0%, 0%, 0.06),
    0 3px 8px hsla(0, 0%, 0%, 0.09);
  display: block;
  border-radius: 5px;
  color: hsl(210, 8%, 5%);
  font-size: 15px; /* 수정: 글씨 크기 조정 */
  z-index: 2000;
`;

const SearchHint = styled.div`
  padding: 12px;
  display: flex;
  color: hsl(210, 8%, 25%);
  justify-content: space-between;
`;

const Hintele = styled.div`
  margin-bottom: ${(props) => (props.end ? '0' : '12px')};
  font-size: 15px; /* 수정: 글씨 크기 조정 */
  font-family: ui-monospace, 'Cascadia Mono', 'Segoe UI Mono', 'Liberation Mono',
    Menlo, Monaco, Consolas, monospace;
  > span:nth-child(1) {
    color: black;
  }
  > span:nth-child(2) {
    color: hsl(210, 8%, 45%);
    margin-left: 8px; /* 수정: 공백 더 띄어줌 */
  }
`;
const AskBox = styled.div`
  display: flex;
  margin-top: 10px;
  padding-top: 10px;
  align-items: center;

  justify-content: space-between;
  border-top: 1px solid hsl(210, 8%, 90%);

  > span {
    margin-right: 8px;
    color: #0074cc;
  }
`;

export default function SearchGuide() {
  return (
    <SearchGuideBox>
      <SearchHint>
        <div>
          <Hintele>
            <span>[tag]</span>
            <span end={1}>search within a tag</span>
          </Hintele>
          <Hintele>
            <span>user:1234</span>
            <span end={1}>unanswered questions</span>
          </Hintele>
        </div>
        <div>
          <Hintele>
            <span>answers: 0</span> {/* 수정: 공백 추가 */}
            <span>unanswered questions</span>
          </Hintele>
          <Hintele>
            <span>title :</span>
            <span>search within a title</span>
          </Hintele>
        </div>
      </SearchHint>
      <AskBox>
        <BasicBlueButton skyblue>ask question</BasicBlueButton>
        <span>search help</span>
      </AskBox>
    </SearchGuideBox>
  );
}
