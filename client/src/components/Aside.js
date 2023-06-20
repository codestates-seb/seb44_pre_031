import { styled } from 'styled-components';
import { FaStackOverflow } from 'react-icons/fa';
import { BiMessageAlt } from 'react-icons/bi';
import { HiPencil } from 'react-icons/hi';

const Sidebar = styled.div`
  margin-left: 25px;
  width: 300px;
`;
const SideWidget = styled.div`
  margin-bottom: 15px;
  width: 100%;
  background-color: #fdf8e2;
  border: 1px solid hsl(205, 53%, 88%);
  border-radius: 3px;
  ul {
    list-style: none;
  }
  li {
  }
  .widget-title {
    padding: 12px 15px;
    font-size: 13px;
    font-weight: 700;
    color: hsl(210, 8%, 35%);
    background-color: hsl(47, 83%, 91%);
    border-radius: 3px;
    border-top: 1px solid hsl(210, 8%, 90%);
    border-bottom: 1px solid hsl(210, 8%, 90%);
  }
  .widget-content {
    padding: 5px 16px;
    margin: 12px 0;
    color: hsl(210, 8%, 25%);
    background-color: #fdf8e2;
    display: flex;

    .icon-pencil {
      font-size: 30px;
      margin-right: 5px;
    }
    .icon-stack {
      font-size: 20px;
      margin-right: 5px;
    }
    .icon-message {
      font-size: 20px;
      margin-right: 5px;
    }
  }
`;
export default function Aside() {
  return (
    <Sidebar>
      <SideWidget>
        <ul>
          <li className="widget-title">The Overflow Blog</li>
          <li className="widget-content">
            <HiPencil className="icon-pencil" />
            <div>
              Hype or not? AI’s benefits for developers explored in the 2023
              Developer Survey
            </div>
          </li>
          <li className="widget-content">
            <HiPencil className="icon-pencil" />
            <div>
              Pair programing? We peek under the hood of Duet, Google’s coding
              assistant....
            </div>
          </li>
          <li className="widget-title">Featured on Meta</li>
          <li className="widget-content">
            <BiMessageAlt className="icon-message" />
            <div>Statement from SO: June 5, 2023 Moderator Action</div>
          </li>
          <li className="widget-content">
            <BiMessageAlt className="icon-message" />
            <div>Stack Exchange Network Outage – June 15, 2023</div>
          </li>
          <li className="widget-content">
            <FaStackOverflow className="icon-stack" />
            <div>
              Does the policy change for AI-generated content affect users who
              (want to)...
            </div>
          </li>
          <li className="widget-content">
            <FaStackOverflow className="icon-stack" />
            <div>
              Does the policy change for AI-generated content affect users who
              (want to)...
            </div>
          </li>
          <li className="widget-content">
            <FaStackOverflow className="icon-stack" />
            <div>Temporary policy: Generative AI (e.g., ChatGPT) is banned</div>
          </li>
          <li className="widget-content">
            <FaStackOverflow className="icon-stack" />
            <div>
              We are seeking functional feedback for the formatting assistant
            </div>
          </li>
        </ul>
      </SideWidget>
    </Sidebar>
  );
}
