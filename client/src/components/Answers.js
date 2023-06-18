import PostLayout from './PostLayout';

const AnswersHeader = () => {
  return (
    <div>
      <div>2 Answers</div>
      <div>answer filter</div>
    </div>
  );
};

const AnswerList = () => {
  return (
    <div>
      AnswersList
      {/* map 돌려야함 */}
      <PostLayout />
      <hr />
    </div>
  );
};

const AnswerForm = () => {
  return (
    <div>
      <div>answer form header</div>
      <textarea />
      <button>submit button</button>
    </div>
  );
};

const AnswerNotice = () => {
  return <div>Answer Notice</div>;
};

const Answers = () => {
  return (
    <div>
      <hr />
      <AnswersHeader />
      <AnswerList />
      <AnswerForm />
      <AnswerNotice />
    </div>
  );
};

export default Answers;
