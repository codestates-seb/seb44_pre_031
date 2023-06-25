/* eslint-disable import/no-unresolved */
import { styled } from 'styled-components';
import { PageBtn } from './PaginationLeft';
import { useSelector, useDispatch } from 'react-redux';
import { selectPagesize } from '../slices/paginationSlice';

const PageSizer = styled.div`
  margin: 20px 0;
  float: right;
  display: flex;
  flex-wrap: wrap;
  gap: 4px;
`;

function PaginationRight() {
  const pages = useSelector((state) => state.pages);
  const dispatch = useDispatch();
  const changePagesizeHandler = (num) => {
    dispatch(selectPagesize(num));
  };
  return (
    <PageSizer>
      <PageBtn
        selected={pages.pagesize === 5}
        onClick={() => {
          changePagesizeHandler(5);
        }}
      >
        5
      </PageBtn>
      <PageBtn
        selected={pages.pagesize === 10}
        onClick={() => {
          changePagesizeHandler(10);
        }}
      >
        10
      </PageBtn>
      <PageBtn
        selected={pages.pagesize === 15}
        onClick={() => {
          changePagesizeHandler(15);
        }}
      >
        15
      </PageBtn>
      <PageBtn clear={1}>per page</PageBtn>
    </PageSizer>
  );
}

export default PaginationRight;
