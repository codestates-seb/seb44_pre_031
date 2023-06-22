import { styled } from 'styled-components';

const QLiContainer = styled.div`
  border-top: 1px solid hsl(210, 8%, 90%);
  border-bottom: 1px solid hsl(210, 8%, 90%);
  padding: 16px;
  display: flex;
  position: relative;
`;
const PostSummaryStats = styled.div`
  align-items: center;
  color: hsl(210, 8%, 45%);
  flex-direction: column;
  display: flex;
  flex-shrink: 0;
  flex-wrap: wrap;
  font-size: 14px;
  gap: 6px;
  margin-bottom: 5px;
  margin-right: 15px;
  > div:nth-child(1) {
    //
    color: hsl(210, 8%, 5%);
  }
  .has-answer {
    background-color: unset;
    border: 1px solid hsl(140, 41%, 31%);
    color: hsl(140, 41%, 31%);
    border-radius: 3px;
    padding: 2px 4px;
  }
  /* 만약 조회수가 1k 가 넘으면 시간남으면 구현 
     view 많은순 정렬 했을때 만 보이게끔 
    > div:nth-child(3) {
    color: hsl(47, 84%, 28%);
  } */
  div {
    align-items: center;
    border: 1px solid transparent;
    display: inline-flex;
    gap: 0.3em;
    justify-content: center;
    white-space: nowrap;
  }
`;
const PostSummaryContent = styled.div`
  flex-grow: 1;
  max-width: 100%;
  h3 {
    font-weight: 400;
    word-break: break-all;
    overflow-wrap: break-word;
    font-size: 19px;
    margin: 0 0 1.5px;
    padding-right: 20px;
  }
  a {
    color: hsl(209, 100%, 37.5%);
    cursor: pointer;
    text-decoration: none;
  }
  .Post-summary-content {
    overflow: hidden;
    color: hsl(210, 8%, 25%);
    margin-bottom: 8px;
  }
  .Post-summary-meta {
    align-items: center;
    column-gap: 5px;
    display: flex;
    justify-content: space-between;
    row-gap: 5px;

    div {
      display: flex;
      float: left;
      gap: 5px;
      line-height: 15px;
    }
    ul {
      display: inline;
      list-style: none;
      margin-bottom: 1em;
    }
    li {
      display: inline;
    }
  }
`;
const Tag = styled.div`
  font-size: 13px;
  color: hsl(205, 47%, 42%);
  background-color: hsl(205, 46%, 92%);
  border-color: transparent;
  display: inline-block;
  padding: 0.4em 0.4em;
  margin: 2px;
  line-height: 1;
  text-decoration: none;
  text-align: center;
  border-width: 1px;
  border-radius: 3px;
  cursor: pointer;
`;
const UserCardContainer = styled.div`
  display: flex;
  font-size: 14px;
  flex-wrap: wrap;
  justify-content: flex-end;
  margin-left: auto;
  .namecolor {
    color: hsl(205, 47%, 42%);
  }
`;

export default function QuestionList({
  // eslint-disable-next-line react/prop-types
  title,
  // eslint-disable-next-line react/prop-types
  content,
  // eslint-disable-next-line react/prop-types
  voteCount,
  // eslint-disable-next-line react/prop-types
  answerCount,
  // eslint-disable-next-line react/prop-types
  countView,
  // eslint-disable-next-line react/prop-types
  displayName,
  // eslint-disable-next-line react/prop-types
  reputation,
  // eslint-disable-next-line react/prop-types
  questionCreatedAt,
}) {
  let tag = [
    {
      title: 'javascript',
    },
    {
      title: '코딩방범대',
    },
    {
      title: 'python',
    },
  ];

  return (
    <QLiContainer>
      <PostSummaryStats>
        <div>
          <span>{voteCount} votes</span>
        </div>
        <div className={answerCount !== '0' ? 'has-answer' : 'null'}>
          <span>{answerCount} answers</span>
        </div>
        <div>
          <span>{countView} views</span>
        </div>
      </PostSummaryStats>
      <PostSummaryContent>
        <h3 className="Post-summary-title">
          <a href="/">{title}</a>
        </h3>
        <div className="Post-summary-content">{content}</div>
        <div className="Post-summary-meta">
          <div>
            <ul>
              {tag.map((el) => {
                return (
                  <li key={el.id}>
                    <Tag>{el.title}</Tag>
                  </li>
                );
              })}
            </ul>
          </div>
          <UserCardContainer>
            <div className="namecolor">{displayName}</div>
            <div>{reputation}</div>
            <div>{questionCreatedAt}</div>
          </UserCardContainer>
        </div>
      </PostSummaryContent>
    </QLiContainer>
  );
}
