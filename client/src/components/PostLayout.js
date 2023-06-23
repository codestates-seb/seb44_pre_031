/* eslint-disable react/prop-types */
import { styled } from 'styled-components';
import { BiUpArrow, BiDownArrow } from 'react-icons/bi';
import { FcBookmark } from 'react-icons/fc';
import { CiBookmark } from 'react-icons/ci';
import { RxCountdownTimer } from 'react-icons/rx';
import { Link, useParams } from 'react-router-dom';
import { FaCheck } from 'react-icons/fa';
import { StyledTagLink } from '../styles/StyledButton';
import UserProfile from './UserProfile';
import { useDispatch, useSelector } from 'react-redux';
import {
  AWS_URL_PATH,
  TEMP_ACCESS_TOKEN,
  postDownVoteQeustion,
  postUpVoteQeustion,
  selectAllTags,
  selectQuestion,
} from '../slices/questionSlice';
import axios from 'axios';
import { useState } from 'react';

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
    height: 1.3em;
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
  /* background-color: white; */
  background-color: ${(props) =>
    props.isVoted ? 'hsl(27, 95%, 55%);' : 'white'};
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
  justify-content: space-between;
  width: 100%;

  .user-profile-container {
    flex-grow: 1;
    display: flex;
    gap: 1em;
    /* max-width: 450px; */
    justify-content: flex-end;
    /* margin-right: 0px; */
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
  const question = useSelector(selectQuestion);
  const tags = useSelector(selectAllTags);
  const params = useParams();
  const dispatch = useDispatch();

  // 북마크상태 임시로 로컬에 저장해놓음
  const [isQuestionBookmarked, setIsQuestionBookmarked] = useState(false);

  // 투표수 true인지 false 인지 상태 저장
  const [questionVoteStatus, setQuestionVoteStatus] = useState({
    upVote: false,
    downVote: false,
  });

  const handleQuestionUpVoteClick = () => {
    if (questionVoteStatus.upVote === false) {
      setQuestionVoteStatus({ ...questionVoteStatus, upVote: true });
      dispatch(postUpVoteQeustion(params.questionId));
    } else if (questionVoteStatus.upVote === true) {
      setQuestionVoteStatus({
        ...questionVoteStatus,
        upVote: false,
      });
      dispatch(postDownVoteQeustion(params.questionId));
    }
  };
  const handleQuestionDownVoteClick = () => {
    if (questionVoteStatus.downVote === false) {
      setQuestionVoteStatus({ ...questionVoteStatus, downVote: true });
      dispatch(postUpVoteQeustion(params.questionId));
    } else if (questionVoteStatus.downVote === true) {
      setQuestionVoteStatus({
        ...questionVoteStatus,
        downVote: false,
      });
      dispatch(postDownVoteQeustion(params.questionId));
    }
  };

  const handleBookmarkClick = async () => {
    setIsQuestionBookmarked(!isQuestionBookmarked);
    try {
      const response = await axios.post(
        `${AWS_URL_PATH}/users/${params.questionId}`,
        null,
        {
          headers: {
            Authorization: TEMP_ACCESS_TOKEN,
          },
        }
      );
      console.log(response);
    } catch (err) {
      console.log(err.message);
    }
  };

  return (
    <PostLayoutContainer>
      <VoteCellContainer>
        <StyledVoteButton
          onClick={handleQuestionUpVoteClick}
          isVoted={questionVoteStatus.upVote}
        >
          <BiUpArrow className="upvote-icon" />
        </StyledVoteButton>
        <p className="votes-count">{question.likeCount}</p>
        <StyledVoteButton
          onClick={handleQuestionDownVoteClick}
          isVoted={questionVoteStatus.downVote}
        >
          <BiDownArrow className="downvote-icon" />
        </StyledVoteButton>
        {/* 북마크 안돼있으면 첫번째꺼, 돼있으면 두번째꺼 렌더 */}
        {isQuestionBookmarked ? (
          <div>
            <FcBookmark
              className="bookmarked-icon"
              onClick={handleBookmarkClick}
            />
          </div>
        ) : (
          <div>
            <CiBookmark
              className="bookmark-icon"
              onClick={handleBookmarkClick}
            />
          </div>
        )}

        <div>
          <RxCountdownTimer />
        </div>
      </VoteCellContainer>
      <PostCellContainer>
        <PostContent>{question.content}</PostContent>
        <TagsContainer>
          {/* 태그링크에 to 속성 추가해야함 */}
          {tags.map((tag) => (
            <StyledTagLink key={tag}>{tag}</StyledTagLink>
          ))}
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
