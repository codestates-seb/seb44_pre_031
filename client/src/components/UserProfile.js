/* eslint-disable react/prop-types */
import { styled } from 'styled-components';
import { Link } from 'react-router-dom';
import { PropTypes } from 'prop-types';

const UserProfileContainer = styled.div`
  display: flex;
  flex-direction: column;
  padding: 0.5em;
  border-radius: 0.3em;
  background-color: ${(props) =>
    props.type === 'asked' ? 'hsl(205,53%,88%)' : 'white'};
  /* width: 100%; */

  .date {
    font-size: 0.8em;
    color: ${(props) =>
      props.type === 'asked' ? 'gray' : ' hsl(206, 100%, 40%)'};
    text-decoration: none;

    &:visited {
      color: ${(props) =>
        props.type === 'asked' ? 'gray' : ' hsl(206, 100%, 40%)'};
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
    gap: 0.3em;
    span {
      font-size: 0.9em;
      color: hsl(210, 8%, 55%);
      margin-right: 0.1em;
    }
    .reputation {
      font-size: 0.9em;
      font-weight: 550;
      color: hsl(210, 8%, 45%);
    }
  }
`;

const ColorCircleSpan = styled.span`
  display: inline-block;
  background-color: ${(props) => props.color};
  border-radius: 20px;
  width: 8px;
  height: 8px;
  font-size: 100%;
  text-align: center;
`;

const UserProfile = ({
  type,
  createdDate,
  updatedDate,
  username,
  reputation,
}) => {
  const randomNumber1 = Math.floor(Math.random() * 200) + 1;
  const randomNumber2 = Math.floor(Math.random() * 200) + 1;
  const randomNumber3 = Math.floor(Math.random() * 200) + 1;

  return (
    <UserProfileContainer type={type}>
      {type === 'asked' && <p className="date">{`asked ${createdDate}`}</p>}
      {type === 'edited' && (
        <Link className="date">{`edited ${updatedDate}`}</Link>
      )}
      <div className="user-info-container">
        <img src="/images/test-image.png" alt="" />
        <div className="user-name-reputation-contaienr">
          <div>
            <Link className="username">{username}</Link>
          </div>
          <div className="user-reputation-container">
            <span className="reputation">{reputation}</span>
            <div>
              <ColorCircleSpan color="hsl(47.95,100%,50.2%)"></ColorCircleSpan>
              <span>{randomNumber1}</span>
            </div>
            <div>
              <ColorCircleSpan color="hsl(210,5.63%,72.16%)"></ColorCircleSpan>
              <span>{randomNumber2}</span>
            </div>
            <div>
              <ColorCircleSpan color="hsl(26.49,44.51%,66.08%)"></ColorCircleSpan>
              <span>{randomNumber3}</span>
            </div>
          </div>
        </div>
      </div>
    </UserProfileContainer>
  );
};

export default UserProfile;

UserProfile.propTypes = {
  type: PropTypes.string,
};
