<template>
  <div>
    <button @click="up">前一页</button>
    <button @click="down">后一页</button>
    <p>{{ current }} / {{ pageTotal }}</p>
    <input
      type="number"
      min="1"
      name="dest"
      v-model="jump"
      placeholder="跳至"
    />
    <button @click="to">跳转</button>
  </div>
</template>

<script>
import axios from "axios";
axios.defaults.baseURL = "/api";
axios.defaults.withCredentials = true;

export default {
  name: "PageBack2",
  emits: ["pageDone"],
  props: {
    pageUrl: String,
    pageSize: Number,
    params: Object,
  },
  data() {
    return {
      current: 1,
      dest: 1,
      pageTotal: 1,
      jump: 1,
    };
  },
  methods: {
    to() {
      if (this.jump < 1) return;

      this.dest = this.jump;
      this.page();
    },
    page() {     
          if(this.pageUrl==undefined)return;
          console.log("call page..");
      
      axios
        .get(this.pageUrl+"?pageIndex="+this.dest+"&pageSize="+this.pageSize, {
          params: this.params,
        })
        .then((res) => {
          if (res.status == 200) {
            if (this.dest > res.data.pageIndex) {
              this.dest = this.current;
            } else {
              this.current = this.dest;
            }
            this.$emit("pageDone", res.data);
            this.pageTotal = res.data.pageTotal;
          } else {
            alert(res.statusText);
          }
        })
        .catch((res) => {
          alert(res.data);
        });
    },
    up() {
      if (this.dest === 1) return;

      this.dest -= 1;
      this.page();
    },
    down() {
      this.dest += 1;
      this.page();
    },
  },
  mounted() {
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

button:hover {
  color: blue;
  border: 1px solid blue;
}

input {
  margin: 10px;
  width: 50px;
  text-align: center;
}
</style>