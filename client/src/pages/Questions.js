/* eslint-disable import/default */
/* eslint-disable import/namespace */
import { styled } from 'styled-components';
import { BasicBlueButton } from '../styles/Buttons';
import QuestionList from '../components/QuestionList';
import Aside from '../components/Aside';
import Header from '../components/Header';
import Nav from '../components/Nav';
import axios from 'axios';
import { useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { setTotalposts } from '../slices/paginationSlice';
// eslint-disable-next-line import/no-unresolved
import PaginationLeft from '../components/PaginationLeft';
import PaginationRight from '../components/PaginationRight';
import { Link } from 'react-router-dom';
import Footer from '../components/Footer';

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

const SortNavBtn = styled.button`
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

const PageContainer = styled.div`
  margin-left: 200px;
  margin-right: 200px;
  margin-bottom: 100px;
`;

export default function Questions() {
  const [allquestions, setAllQuestions] = useState([]);
  const pages = useSelector((state) => state.pages);
  const dispatch = useDispatch();

  const fetchQuestions = async () => {
    try {
      const response = await axios.get(
        'http://ec2-52-79-240-48.ap-northeast-2.compute.amazonaws.com:8080/api/questions'
      );
      const questions = response.data.result.data.content;
      setAllQuestions(questions);
      dispatch(setTotalposts(questions.length));
    } catch (error) {
      console.error('Error fetching questions:', error);
    }
  };

  useEffect(() => {
    fetchQuestions();
  }, []);

  const start = (pages.currentpage - 1) * pages.pagesize;
  const end = start + pages.pagesize;
  const onepage = allquestions.slice(start, end);

  const handleViewFilterBtnClick = () => {
    const sortedResult = [...allquestions].sort(
      (a, b) => parseInt(b.viewCount) - parseInt(a.viewCount)
    );
    console.log(sortedResult);
    setAllQuestions(sortedResult);
  };

  return (
    <>
      <Header />
      <WrapContainer className="wrap">
        <Nav />
        <QuestionContainer>
          <PageHeader>
            <h1>All Questions</h1>
            <div className="bluebutton">
              <BasicBlueButton>
                <Link to="/questions/ask" style={{ color: 'white' }}>
                  Ask Question
                </Link>
              </BasicBlueButton>
            </div>
          </PageHeader>
          <div>
            <QuestionsH2 className="data">
              <div>{allquestions.length} questions</div>
              <div>
                <SortNavBox>
                  <SortNav>
                    <SortNavBtn
                      start
                      selected
                      onClick={handleViewFilterBtnClick}
                    >
                      <div>View</div>
                    </SortNavBtn>
                    <SortNavBtn middle>
                      <div>Vote</div>
                    </SortNavBtn>
                    <SortNavBtn end>
                      <div>Answer</div>
                    </SortNavBtn>
                  </SortNav>
                </SortNavBox>
              </div>
            </QuestionsH2>
          </div>
          <QuestionsContent>
            {onepage.map((el) => (
              <QuestionList key={el.questionId} question={el} />
            ))}
          </QuestionsContent>
        </QuestionContainer>
        <Aside />
      </WrapContainer>
      <PageContainer>
        <PaginationLeft />
        <PaginationRight />
      </PageContainer>
      <Footer />
    </>
  );
}
