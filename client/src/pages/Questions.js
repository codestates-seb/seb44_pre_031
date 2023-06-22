import { styled } from 'styled-components';
import { BasicBlueButton } from '../styles/Buttons';
import QuestionList from '../components/QuestionList';
import Aside from '../components/Aside';
import Header from '../components/Header';
import Nav from '../components/Nav';
import axios from 'axios';
import { useEffect, useState } from 'react';
const WrapContainer = styled.div`
  .wrap {
    margin: 0 auto;
    display: flex;

    justify-content: center;
  }
`;
const QuestionContainer = styled.div`
  width: 720px;
`;

const PageHeader = styled.div`
  display: flex;
  justify-content: space-between;
  margin-bottom: 12px;
  margin-left: 20px;
  margin-top: 34px;
  flex-wrap: wrap;
  .bluebutton {
    margin-right: 14px;
  }
  h1 {
    font-size: 2rem;
    margin-right: 12px;
  }
`;
const QuestionsH2 = styled.div`
  display: flex;

  margin-bottom: 12px;
  margin-left: 20px;
  align-items: center;
  justify-content: space-between;
  .data {
    font-size: 1.30769231rem;
    margin-right: 12px;
    flex: 1 auto;
  }
`;
const SortNavBox = styled.div`
  display: flex;

  align-items: center;
  justify-content: space-between;
`;
const SortNav = styled.div`
  display: flex;
  font-size: 100%;
  flex-flow: row no;
  margin-bottom: 1px;
  margin-right: 16px;

  flex-flow: row nowrap;
`;
const SortNavBtn = styled.a`
  font-size: 15px;
  border: 1px solid black;
  border-radius: ${(props) => (props.middle ? '0' : '3px')};
  border-top-left-radius: ${(props) => (props.end ? '0' : null)};
  border-top-right-radius: ${(props) => (props.start ? '0' : null)};
  border-bottom-right-radius: ${(props) => (props.start ? '0' : null)};
  border-bottom-left-radius: ${(props) => (props.end ? '0' : null)};
  margin-right: ${(props) => (props.end ? '0' : '-1px')};
  margin-bottom: -1px;
  z-index: 25;
  box-shadow: none;
  background-color: ${(props) =>
    props.selected ? 'hsl(210,8%,90%)' : 'transparent'};
  color: ${(props) => (props.selected ? 'hsl(210,8%,25%)' : 'hsl(210,8%,45%)')};
  padding: 10px;
  cursor: pointer;
  outline: none;
  text-align: center;
  text-decoration: none;
`;
const QuestionsContent = styled.div``;
export default function Questions() {
  const [allquestions, setallquesitons] = useState([
    {
      questionId: 1,
      title: 'How can I count repeating intervals in a graph?',
      content:
        'enter image description here Through the image, you can see five repetitive sections. I want to write an algorithm that counts this number, but I cant think of it. I tried to draw a regression curve ...',
      voteCount: 0,
      answerCount: 2,
      countView: 0,
      selectedAnswer: false,
      questionCreatedAt: '2023-06-17T17:06:03',
      displayName: 'Yeahhun Jeon',
      reputation: 0,
    },
  ]);

  const authHandler = () => {
    axios
      .get(
        'http://ec2-52-79-240-48.ap-northeast-2.compute.amazonaws.com:8080/api/questions'
      )
      .then((res) => {
        const data = res.data.result.data.content;
        if (Array.isArray(data)) {
          setallquesitons(data);
        } else {
          console.error('Data is not an array:', data);
        }
      })
      .catch((err) => {
        console.log(err);
      });
  };
  useEffect(() => {
    authHandler();
  }, []);

  return (
    <>
      <Header />
      <WrapContainer className="wrap">
        <Nav />
        <QuestionContainer>
          <PageHeader>
            <h1>All Questions</h1>
            <div className="bluebutton">
              <BasicBlueButton>Ask Question</BasicBlueButton>
            </div>
          </PageHeader>

          <div>
            <QuestionsH2 className="data">
              <div>23,752,022 questions</div>
              <div>
                <SortNavBox>
                  <SortNav>
                    <SortNavBtn start selected>
                      <div>View</div>
                    </SortNavBtn>
                    <SortNavBtn middle>
                      <div>Vote</div>
                    </SortNavBtn>
                    <SortNavBtn end>
                      <div>Score</div>
                    </SortNavBtn>
                  </SortNav>
                </SortNavBox>
              </div>
            </QuestionsH2>
          </div>

          <QuestionsContent>
            {allquestions.map((el) => {
              return (
                <QuestionList
                  key={el.id}
                  title={el.title}
                  content={el.content}
                  questionCreatedAt={el.questionCreatedAt}
                  voteCount={el.voteCount}
                  countView={el.countView}
                  answerCount={el.answerCount}
                  displayName={el.displayName}
                  reputation={el.reputation}
                />
              );
            })}
          </QuestionsContent>
        </QuestionContainer>
        <Aside />
      </WrapContainer>
    </>
  );
}
