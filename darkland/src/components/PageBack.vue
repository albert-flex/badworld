<template>
  <nav aria-label="pagination">
    <ul class="pagination">
      <li>
        <a
          ><span aria-hidden="true">«</span
          ><span class="visuallyhidden">previous set of pages</span></a
        >
      </li>
      <li>
        <a><span class="visuallyhidden">page </span>1</a>
      </li>
      <li>
        <a aria-current=""><span class="visuallyhidden">page </span>2</a>
      </li>
      <li>
        <a><span class="visuallyhidden">page </span>3</a>
      </li>
      <li>
        <a
          ><span class="visuallyhidden">next set of pages</span
          ><span aria-hidden="true">»</span></a
        >
      </li>
    </ul>
  </nav>
</template>

<script>
export default {
    name: 'PageBack',
}
</script>

<style scoped>
    /*
    PAGINATION CSS
 */

.visuallyhidden {
  border: 0;
  clip: rect(0 0 0 0);
  height: auto;
  margin: 0;
  overflow: hidden;
  padding: 0;
  position: absolute;
  width: 1px;
  white-space: nowrap;
}

nav {
  display: flex;
  justify-content: center;
}

.pagination {
  list-style: none;
  margin: 0;
  padding: 0;
  display: flex;
}

.pagination li {
  margin: 0 1px;
}

.pagination a {
  display: block;
  padding: 0.5em 1em;
  border: 1px solid #999;
  border-radius: 0.2em;
  text-decoration: none;
  cursor: pointer;
}

.pagination a[aria-current="page"] {
  background-color: #2c3e50;
  color: #fff;
}
</style>