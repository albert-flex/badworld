<template>
  <div>
    <button @click="$emit('pageUp')">前一页</button>
    <button @click="$emit('pageDown')">后一页</button>
    <p>当前第{{ current }}页</p>
  </div>
</template>

<script>
export default {
  name: "PageBack2",
  emits: ["pageUp", "pageDown"],
  props: {
    current: Number,
    pageSize: Number,
  },
};
</script>

<style scoped>
div {
  display: flex;
  justify-content: center;
}

button {
  margin: 5px;
  border: 1px solid grey;
  color: black;
  cursor: pointer;
}

p {
  color: gray;
}

button :cover {
  color: blue;
  border: 1px solid blue;
}
</style>