import { styled } from 'styled-components';
import Header from '../components/Header';
import Nav from '../components/Nav';
import { AiFillGithub, AiFillTwitterCircle } from 'react-icons/ai';
import { FiClock } from 'react-icons/fi';
import { FaLink } from 'react-icons/fa';
import { MdCake, MdWhereToVote } from 'react-icons/md';
import Footer from '../components/Footer';

const WrapContainer = styled.div`
  display: flex;
  justify-content: center;
`;
const Container = styled.div`
  width: 1100px;
  display: flex;
  flex-direction: column;
  align-items: baseline;
  min-height: 50vh;
  background-color: white;
  font-size: 0.8rem;
`;

const ProfileCard = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  margin-right: 8em;
  font-size: 0.8rem;
  background-color: white;
  padding: 2em;
`;
const ProfileInfo = styled.div`
  display: flex;
  height: 14em;
  margin-left: 2em;
  flex-direction: column;
  justify-content: space-evenly;
  align-items: baseline;
  font-size: 1.4em;
`;
const StatAbout = styled.div`
  display: flex;
  align-items: baseline;
  div > p {
    font-size: 1.5em;
  }
`;
const StatCard = styled.div`
  margin-top: 1em;
  display: flex;
  flex-direction: column;
  justify-content: left;
  align-items: baseline;
  background-color: white;
  font-size: 0.9rem;
  border-radius: 0.5em;
  padding: 1em;
  div {
    font-size: 0.6em;
  }
`;
const StatInfo = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  margin-top: 0.1em;
  font-size: 1rem;
  border: solid 1px #babfc4;
  border-radius: 0.5em;
  div {
    margin: 0.5em;
    font-size: 1.5em;
  }
  div > span {
    color: #babfc4;
  }
`;
const Fakemypage = styled.div`
  width: 1100px;
  height: 800px;
  background-image: url('/images/fakemypage.png');
  background-repeat: no-repeat;
`;
const Users = () => {
  return (
    <>
      <Header />
      <WrapContainer>
        <Nav />
        <Container>
          <ProfileCard>
            <div>
              <img src="/profile.png" alt="userImage"></img>
            </div>
            <ProfileInfo>
              <h2>코딩방범창</h2>
              <h4>coding window</h4>
              <div>
                <MdCake /> Member for 14 years, 9 months <FiClock /> Last seen
                this week
              </div>
              <div>
                <AiFillGithub />
                <AiFillTwitterCircle />
                <FaLink /> jihoon <MdWhereToVote />
                한국
              </div>
            </ProfileInfo>
          </ProfileCard>
          <StatAbout>
            <StatCard>
              <h2>Stats</h2>
              <StatInfo>
                <div>
                  <div>1,000,000</div>
                  <div>
                    <span>reputation</span>
                  </div>
                </div>
                <div>
                  <div>15,234</div>
                  <div>
                    <span>answer</span>
                  </div>
                </div>
                <div>
                  <div>89</div>
                  <div>
                    <span>question</span>
                  </div>
                </div>
              </StatInfo>
            </StatCard>
            <div>
              <h2>About</h2>
              <p>안녕하세요 어바웃 입니다</p>
            </div>
          </StatAbout>
          <Fakemypage />
        </Container>
      </WrapContainer>
      <Footer />
    </>
  );
};

export default Users;
