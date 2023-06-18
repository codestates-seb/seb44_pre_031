import { styled } from 'styled-components';
import { BiUpArrow, BiDownArrow } from 'react-icons/bi';
import { FcBookmark } from 'react-icons/fc';
import { CiBookmark } from 'react-icons/ci';
import { RxCountdownTimer } from 'react-icons/rx';
import { Link } from 'react-router-dom';

const PostLayoutContainer = styled.div`
  display: flex;
  gap: 1.1em;
  padding: 1em;
`;

const VoteCellContainer = styled.div`
  display: flex;
  flex-direction: column;
  text-align: center;
  gap: 0.3em;
  /* 
  .bookmarked {
    height: ;
  } */
  div {
    cursor: pointer;
  }

  .upvote-icon {
    stroke-width: 0.1em;
  }
  .downvote-icon {
    stroke-width: 0.1em;
    margin-top: 0.3em;
  }

  .bookmark-icon {
    stroke-width: 0.1em;
    width: 1em;
    height: 1.2em;
    stroke: gray;
  }
  .bookmarked-icon {
    width: 1.2em;
    height: 1.1em;
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
`;
const PostContent = styled.div`
  /* height: 8em; */
`;

const TagsContainer = styled.div`
  display: flex;
  gap: 0.5em;
`;
const StyledTagLink = styled(Link)`
  font-size: 0.7em;
  color: hsl(205, 47%, 42%);
  background-color: hsl(205, 46%, 92%);
  border: none;
  border-radius: 3px;
  width: 6em;
  /* width: ${(props) => props.width}; */
  height: 2.5em;
  cursor: pointer;
  text-decoration: none;
  text-align: center;
  padding-top: 0.5em;

  &:visited {
    /* color: hsl(205, 46%, 92%); */
    /* background-color: hsl(209, 100%, 37.5%); */
    text-decoration: none;
  }

  &:hover {
    background-color: hsl(205, 53%, 88%);
  }
  &:active {
    /* background-color: hsl(209, 100%, 37.5%); */
    /* box-shadow: 0px 0px 2px 2px hsl(206, 93%, 83.5%); */
  }
`;

const PostFooterContainer = styled.div`
  display: flex;
  justify-content: space-between;

  .user-profile-container {
    display: flex;
    gap: 1em;
  }
`;

const FooterFeatContainer = styled.div`
  display: flex;
  gap: 1em;

  button {
    font-size: 0.9em;
    border: none;
    background-color: transparent;
    color: hsl(210, 8%, 45%);
    cursor: pointer;

    &:hover {
      color: hsl(210, 8%, 55%);
    }
  }
`;

const UserProfileContainer = styled.div`
  display: flex;
  flex-direction: column;
  padding: 0.5em;
  border-radius: 0.3em;
  background-color: ${(props) => (props.asked ? 'hsl(205,53%,88%)' : 'white')};
  width: 12em;

  .date {
    font-size: 0.8em;
    color: ${(props) => (props.asked ? 'gray' : ' hsl(206, 100%, 40%)')};
    text-decoration: none;

    &:visited {
      color: ${(props) => (props.asked ? 'gray' : ' hsl(206, 100%, 40%)')};
    }

    &:hover {
      color: hsl(206, 100%, 52%);
    }
  }

  .user-info-container {
    display: flex;
    gap: 0.4em;
  }

  .username {
    font-size: 0.9em;
    color: hsl(206, 100%, 40%);
    text-decoration: none;

    &:visited {
      color: hsl(209, 100%, 37.5%);
    }

    &:hover {
      color: hsl(206, 100%, 52%);
    }
  }
  img {
    height: 2.5em;
  }

  .user-name-reputation-contaienr {
    display: flex;
    flex-direction: column;
  }

  .user-reputation-container {
    display: flex;
    justify-content: space-between;
    align-items: center;
    span {
      font-size: 0.9em;
      color: hsl(210, 8%, 55%);
      margin-right: 0.5em;
    }
    .reputation {
      font-size: 1em;
      font-weight: 550;
      color: hsl(210, 8%, 45%);
    }
  }
`;

const PostLayout = () => {
  return (
    <PostLayoutContainer>
      <VoteCellContainer>
        <StyledVoteButton>
          <BiUpArrow className="upvote-icon" />
        </StyledVoteButton>
        <p className="votes-count">12</p>
        <StyledVoteButton>
          <BiDownArrow className="downvote-icon" />
        </StyledVoteButton>
        <div>
          <CiBookmark className="bookmark-icon" />
        </div>
        <div>
          <FcBookmark className="bookmarked-icon" />
        </div>
        <div>
          <RxCountdownTimer />
        </div>
      </VoteCellContainer>
      <PostCellContainer>
        <PostContent>
          I am trying to do something like the following in React JSX (where
          ObjectRow is a separate component):
          <br />I am trying to do something like the following in React JSX
          (where ObjectRow is a separate component):
          <br />I am trying to do something like the following in React JSX
          (where ObjectRow is a separate component):
        </PostContent>
        <TagsContainer>
          <StyledTagLink>javascript</StyledTagLink>
          <StyledTagLink>reactjs</StyledTagLink>
          <StyledTagLink>jsx</StyledTagLink>
        </TagsContainer>
        <PostFooterContainer>
          <FooterFeatContainer>
            <button>Share</button>
            <button>Edit</button>
            <button>Follow</button>
          </FooterFeatContainer>
          <div className="user-profile-container">
            <UserProfileContainer>
              <Link className="date">edited Feb 14, 2021 at 15:36</Link>
              <div className="user-info-container">
                <img src="/images/test-image.png" alt="dd" />
                <div className="user-name-reputation-contaienr">
                  <Link className="username">Peter Mortensen</Link>
                  <div className="user-reputation-container">
                    <span className="reputation">31k</span>
                    <span>21</span>
                    <span>105</span>
                    <span>131</span>
                  </div>
                </div>
              </div>
            </UserProfileContainer>
            <UserProfileContainer asked={true}>
              <Link className="date">asked Apr 5, 2014 at 5:29</Link>
              <div className="user-info-container">
                <img src="/images/test-image.png" alt="dd" />
                <div className="user-name-reputation-contaienr">
                  <Link className="username">B Robster</Link>
                  <div className="user-reputation-container">
                    <span className="reputation">40.2k</span>
                    <span>21</span>
                    <span>89</span>
                    <span>122</span>
                  </div>
                </div>
              </div>
            </UserProfileContainer>
          </div>
        </PostFooterContainer>
      </PostCellContainer>
    </PostLayoutContainer>
  );
};

export default PostLayout;
