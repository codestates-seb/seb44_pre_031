import { useParams } from 'react-router-dom';

const Question = () => {
  let { questionId } = useParams();

  return <p>question Id: {questionId}</p>;
};

export default Question;
