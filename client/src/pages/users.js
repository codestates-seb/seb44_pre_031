import { styled } from 'styled-components';

const Container = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
  background-color: #f1f2f3;
  font-size: 0.8rem;
`;

const ProfileCard = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 0.8rem;
  background-color: white;
  box-shadow: 0 10px 24px rgba(0, 0, 0, 0.05), 0 20px 48px rgba(0, 0, 0, 0.05),
    0 1px 4px rgba(0, 0, 0, 0.1);
  border: 1px solid black;
  border-radius: 1em;
  padding: 2em;
`;
const ProfileInfo = styled.div`
  display: flex;
  height: 17em;
  flex-direction: column;
  justify-content: space-around;
  align-items: center;
  font-size: 0.8rem;
  box-shadow: 0 10px 24px rgba(0, 0, 0, 0.05), 0 20px 48px rgba(0, 0, 0, 0.05),
    0 1px 4px rgba(0, 0, 0, 0.1);
  border-radius: 1em;
`;
const StatCard = styled.div`
  margin-top: 2.5em;
  margin-left: 30em;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  background-color: white;
  font-size: 0.8rem;
  border: 1px solid black;
  border-radius: 0.5em;
  padding: 1em;
  box-shadow: 0 10px 24px rgba(0, 0, 0, 0.05), 0 20px 48px rgba(0, 0, 0, 0.05),
    0 1px 4px rgba(0, 0, 0, 0.1);
`;
const StatInfo = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  margin-top: 0.1em;
  box-shadow: 0 10px 24px rgba(0, 0, 0, 0.05), 0 20px 48px rgba(0, 0, 0, 0.05),
    0 1px 4px rgba(0, 0, 0, 0.1);
  font-size: 0.8rem;
  padding: 2em;
  border-radius: 0.5em;
  div {
    margin: 0.5em;
    font-size: 1.3em;
  }
`;

const Users = () => {
  return (
    <Container>
      <ProfileCard>
        <div>
          <img src="/profile.png" alt="userImage"></img>
        </div>
        <ProfileInfo>
          <div></div>
          <h2>코딩방범창</h2>
          <div>^^ Member for 14 years, 9 months ^^ Last seen this week</div>
          <div>깃허브 트위터 링크인 티스토리 한국</div>
        </ProfileInfo>
      </ProfileCard>
      <StatCard>
        <h2>Stats</h2>
        <StatInfo>
          <div>
            <div>1,000,000</div>
            <div>reputation</div>
          </div>
          <div>
            <div>15,234</div>
            <div>answer</div>
          </div>
          <div>
            <div>89</div>
            <div>question</div>
          </div>
        </StatInfo>
      </StatCard>
    </Container>
  );
};

export default Users;
