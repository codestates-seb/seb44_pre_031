import { styled } from 'styled-components';
import { Link } from 'react-router-dom';

const FooterContainer = styled.footer`
  width: 100%;
  background-color: #232629;
  color: #9099a1;
  li {
    list-style-type: none;
  }
  .footer-container {
    display: flex;
    justify-content: space-between;
    margin: 0 0 0 32px;
    padding: 32px 0px 12px;
    width: 100%;
    max-width: 1350px;
    margin: 0 auto;
    .logobox {
      flex: 0 0 300px;
    }
    .foot-logo {
      height: 150px;
      text-align: right;
      margin-right: 30px;
      a {
        color: #9099a1;
      }
      ::marker {
        color: #232629;
      }
    }
    .bot-menu-container {
      display: flex;
      flex: 2 1 auto;
      > ul {
        flex: 1 0 auto;
        padding: 0 12px 24px 0;
        > h5 {
          margin: 0 0 18px;
          color: #babfc4;
        }
        > li {
          line-height: 2;
          font-size: 13px;
          ::marker {
            color: #232629;
          }
        }
      }
    }

    .sns-copyright {
      display: flex;
      flex-direction: column;
      flex: 1 1 150px;
      font-size: 11px;
      .sns-container {
        > ul {
          padding: 0;
          display: flex;
          > li {
            margin-left: 12px;
            padding: 4px 0;
            &:first-child {
              margin: 0;
            }
          }
        }
      }
      .copyright-container {
        margin: auto 0 24px;
      }
    }
    div {
      display: flex;
      flex-direction: column;
      justify-content: space-between;
      > ul {
        display: flex;
        justify-content: space-evenly;
      }
    }
  }
`;

function Footer() {
  return (
    <FooterContainer>
      <ul className="footer-container">
        <logobox className="logobox">
          <li className="foot-logo">
            <Link to="/">
              <img src="/images/logo-simple.png" alt="logo"></img>
              <span className="foot-logo-image hide"></span>
            </Link>
          </li>
        </logobox>
        <li className="bot-menu-container">
          <ul>
            <h5>STACK OVERFLOW</h5>

            <li>Question</li>

            <li>Help</li>
          </ul>
          <ul>
            <h5>PRODUCTS</h5>
            <li>Teams</li>
            <li>Advertising</li>
            <li>Collectives</li>
            <li>Talent</li>
          </ul>
          <ul>
            <h5>Company</h5>
            <li>Press</li>
            <li>Work Here</li>
            <li>Legal</li>
            <li>Privacy Policy</li>
            <li>Terms of Service</li>
            <li>Contact Us</li>
            <li>Cookie Settings</li>
            <li>Cookie Policy</li>
          </ul>
          <ul>
            <h5>STACK EXCHANGE NETWORK</h5>
            <li>Technology</li>
            <li>Culture & recreation</li>
            <li>Life & arts</li>
            <li>Science</li>
            <li>Professional</li>
            <li>API</li>
            <li>Data</li>
          </ul>
          <div>
            <ul>
              <li>Blog</li>
              <li>Newsletter</li>
              <li>Twitter</li>
              <li>LinkedIn</li>
              <li>Instagram</li>
            </ul>
            <p>
              Site design / logo © 2023 Stack Exchange Inc; user contributions
              licensed under{' '}
            </p>
          </div>
        </li>
      </ul>
    </FooterContainer>
  );
}

export default Footer;
