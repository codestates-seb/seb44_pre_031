export function Filteringposts(posts, filter) {
  const filteredposts = posts.filter((post) => {
    let filtering = true;
    if (filter.unanswered) {
      filtering = post.answerCount === 0;
    }
    if (filter.tags.length) {
      filtering = false;
      for (let i of filter.tags) {
        if (filtering === false) {
          filtering = post.tagList.includes(i);
        }
      }
    }
    if (filter.user.length) {
      filtering = post.user.displayName === filter.user;
    }
    console.log(typeof filter.answerCount);
    if (filter.answerCount !== null) {
      console.log(filter.answerCount);
      filtering = post.answerCount === filter.answerCount;
    }
    return filtering;
  });
  return filteredposts;
}
