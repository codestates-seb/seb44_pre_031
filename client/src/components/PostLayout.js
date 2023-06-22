/* eslint-disable react/prop-types */
import { styled } from 'styled-components';
import { BiUpArrow, BiDownArrow } from 'react-icons/bi';
// import { FcBookmark } from 'react-icons/fc';
import { CiBookmark } from 'react-icons/ci';
import { RxCountdownTimer } from 'react-icons/rx';
import { Link } from 'react-router-dom';
import { FaCheck } from 'react-icons/fa';
import { StyledTagLink } from '../styles/StyledButton';
import UserProfile from './UserProfile';
import { useSelector } from 'react-redux';
// import { useState } from 'react';
// import { useState } from 'react';

const PostLayoutContainer = styled.div`
  display: flex;
  gap: 1.2em;
  padding-top: 0.5em;
  padding-bottom: 2em;
  margin-top: 1em;
  width: 100%;
`;

const VoteCellContainer = styled.div`
  display: flex;
  flex-direction: column;
  text-align: center;
  gap: 0.5em;

  div {
    cursor: pointer;
  }

  .upvote-icon {
    margin-top: 0.3em;
    width: 1.3em;
    height: 1.3em;
  }
  .downvote-icon {
    margin-top: 0.3em;
    width: 1.3em;
    height: 1.3em;
  }

  .bookmark-icon {
    stroke-width: 0.1em;
    width: 1.1em;
    height: 1.3em;
    stroke: gray;
  }
  .bookmarked-icon {
    width: 1.5em;
    height: 1.2em;
  }

  .checkmark {
    svg {
      width: 1.8em;
      height: 1.8em;
    }
  }

  .votes-count {
    font-size: 1.3em;
    font-weight: 500;
  }
`;

const StyledVoteButton = styled.button`
  width: 40px;
  height: 40px;
  background-color: white;
  border-radius: 30px;
  border: solid lightgray 0.1em;
  cursor: pointer;
  text-align: center;

  &:hover {
    background-color: hsl(27, 95%, 90%);
  }

  &:active {
    background-color: hsl(27, 95%, 90%);
    box-shadow: 0px 0px 2px 2px hsl(206, 93%, 83.5%);
  }
`;

const PostCellContainer = styled.div`
  display: flex;
  flex-direction: column;
  gap: 2em;
  width: 1000px;
`;
const PostContent = styled.div`
  /* height: 8em; */
`;

const TagsContainer = styled.div`
  display: flex;
  gap: 0.5em;
`;

const PostFooterContainer = styled.div`
  display: flex;

  .user-profile-container {
    flex-grow: 1;
    display: flex;
  }
`;

const FooterFeatContainer = styled.div`
  flex-grow: 2;
  display: flex;
  gap: 1em;
  align-items: center;

  button,
  a {
    font-size: 0.9em;
    font-weight: 500;
    border: none;
    background-color: transparent;
    color: hsl(210, 8%, 45%);
    cursor: pointer;
    text-decoration: none;

    &:hover {
      color: hsl(210, 8%, 55%);
    }
  }
`;

export const QuestionLayout = () => {
  const question = useSelector((state) => state.question.question);

  return (
    <PostLayoutContainer>
      <VoteCellContainer>
        <StyledVoteButton>
          <BiUpArrow className="upvote-icon" />
        </StyledVoteButton>
        <p className="votes-count">{question.likeCount}</p>
        <StyledVoteButton>
          <BiDownArrow className="downvote-icon" />
        </StyledVoteButton>
        {/* 북마크 안돼있으면 첫번째꺼, 돼있으면 두번째꺼 렌더 */}
        <div>
          <CiBookmark className="bookmark-icon" />
        </div>
        {/* <div>
          <FcBookmark className="bookmarked-icon" />
        </div> */}
        <div>
          <RxCountdownTimer />
        </div>
      </VoteCellContainer>
      <PostCellContainer>
        <PostContent>{question.content}</PostContent>
        {/* 나중에 태그 구현되면 추가해야됨 */}
        <TagsContainer>
          <StyledTagLink>javascript</StyledTagLink>
          <StyledTagLink>react</StyledTagLink>
          <StyledTagLink>springboot</StyledTagLink>
          <StyledTagLink>aws</StyledTagLink>
        </TagsContainer>
        <PostFooterContainer>
          <FooterFeatContainer>
            <Link>Share</Link>
            <Link to={`../${question.questionId}/edit`}>Edit</Link>
            {/* 본인이면 Delete 아니면 Follow */}
            <button>Follow</button>
            <button>Delete</button>
          </FooterFeatContainer>
          <div className="user-profile-container">
            {/* edited 기록이 있으면 edit 렌더 아니면 asked 작성자만 렌더 */}
            {question.questionCreatedAt !== question.questionUpdatedAt && (
              <UserProfile
                type="edited"
                updatedDate={question.questionUpdatedAt}
                username={question.displayName}
                reputation={question.reputation}
              />
            )}
            <UserProfile
              type="asked"
              createdDate={question.questionCreatedAt}
              username={question.displayName}
              reputation={question.reputation}
            />
          </div>
        </PostFooterContainer>
      </PostCellContainer>
    </PostLayoutContainer>
  );
};

export const AnswerLayout = ({ answer }) => {
  return (
    <PostLayoutContainer>
      <VoteCellContainer>
        <StyledVoteButton>
          <BiUpArrow className="upvote-icon" />
        </StyledVoteButton>
        {/* 나중에 투표수 추가되면 바꿔야함 */}
        <p className="votes-count">{1}</p>
        <StyledVoteButton>
          <BiDownArrow className="downvote-icon" />
        </StyledVoteButton>
        {/* 북마크 안돼있으면 첫번째꺼, 돼있으면 두번째꺼 렌더 */}
        <div>
          <CiBookmark className="bookmark-icon" />
        </div>
        {/* <div>
          <FcBookmark className="bookmarked-icon" />
        </div> */}
        {/* 채택된 답변이면 green 체크마크 렌더 */}
        {answer.selected === true ? (
          <div className="checkmark">
            <FaCheck fill="rgb(46,112,68)" />
          </div>
        ) : (
          <div className="checkmark">
            <FaCheck fill="rgb(186,191,196)" />
          </div>
        )}
        <div>
          <RxCountdownTimer />
        </div>
      </VoteCellContainer>
      <PostCellContainer>
        <PostContent>{answer.content}</PostContent>
        {/* 나중에 태그 구현되면 추가해야됨 */}
        <TagsContainer>
          <StyledTagLink>javascript</StyledTagLink>
          <StyledTagLink>react</StyledTagLink>
          <StyledTagLink>springboot</StyledTagLink>
          <StyledTagLink>aws</StyledTagLink>
        </TagsContainer>
        <PostFooterContainer>
          <FooterFeatContainer>
            <Link>Share</Link>
            <Link to={`../${answer.questionId}/${answer.answerId}/edit`}>
              Edit
            </Link>
            {/* 본인이면 Delete 아니면 Follow */}
            <button>Follow</button>
            <button>Delete</button>
          </FooterFeatContainer>
          <div className="user-profile-container">
            {/* edited 기록이 있으면 edit 렌더 아니면 asked 작성자만 렌더 */}
            {answer.answerCreatedAt !== answer.answerUpdatedAt && (
              <UserProfile
                type="edited"
                updatedDate={answer.answerUpdatedAt}
                username={answer.displayName}
                reputation={answer.reputation}
              />
            )}
            <UserProfile
              type="asked"
              createdDate={answer.answerCreatedAt}
              username={answer.displayName}
              reputation={answer.reputation}
            />
          </div>
        </PostFooterContainer>
      </PostCellContainer>
    </PostLayoutContainer>
  );
};
