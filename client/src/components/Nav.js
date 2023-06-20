import { styled } from 'styled-components';
import { IoEarth } from 'react-icons/io5';
const NavContainer = styled.div`
  display: block;
  position: sticky;
  flex-direction: column;
  justify-content: center;
  left: 0;
  top: 0;
  bottom: 0;
  right: 0;
  height: 100%;
  z-index: 1000;
  width: 160px;
  flex-shrink: 0;

  box-shadow: 0 0 0 hsl(210deg 8% 5% / 5%);
  transform: translateZ(0);
  text-align: left;
`;
const MainMenu = styled.div`
  list-style: none;
  color: hsl(210, 8%, 35%);
  margin: 0 0 12px;
  vertical-align: baseline;
  > li {
    position: relative;
  }
  > li {
    display: list-item;
    line-height: 2;
    font-size: 14px;
    position: relative;
    padding-bottom: 1px;
  }
  > li:nth-child(1) {
    text-transform: uppercase;
    margin: 16px 0 0px 8px;
    color: hsl(210, 8%, 45%);
    font-size: 12px;
  }
  > li:nth-child(2) > a {
    display: flex;
    padding: 8px 6px 8px 8px;
  }
  > li:nth-child(3) > a {
    padding: 4px;
    padding-left: 30px;
    color: hsl(210, 8%, 45%);
  }
  > li:nth-child(4) > a {
    padding: 4px;
    padding-left: 30px;
    color: hsl(210, 8%, 45%);
  }

  .youarehere > a {
    font-weight: bold;
    background: hsl(210, 8%, 95%);
    color: hsl(210, 8%, 5%);
    border-right: 3px solid hsl(27, 90%, 55%);
  }
  a {
    cursor: pointer;
    user-select: auto;
    text-decoration: none;
    display: block;
  }

  > img {
    width: 164px;
    padding-top: 1px;
    cursor: pointer;
  }
`;
const StyledIoEarth = styled(IoEarth)`
  font-size: 18px;
  margin-top: 2px;
  margin-right: 3px;
`;
export default function Nav() {
  return (
    <>
      <NavContainer>
        <div>
          <MainMenu>
            <li>Public</li>
            <li className="youarehere">
              <a href="/">
                <StyledIoEarth />
                <span>Questions</span>
              </a>
            </li>
            <li>
              <a href="/">Tags</a>
            </li>
            <li>
              <a href="/">Users</a>
            </li>
            <img src="/images/nav-img.png" alt="nav-img" />
          </MainMenu>
        </div>
      </NavContainer>
    </>
  );
}
