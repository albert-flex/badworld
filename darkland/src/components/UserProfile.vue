<template>
  <div class="user">
    <div class="tap"></div>
    <div id="profile">
      <div class="firstline">
        <img src="../assets/backpaper.png" />
        <button>修改资料</button>
      </div>
      <div class="secondline">
        <p class="user-name">夏文纯一 (100000)</p>
        <p class="user-email">Natsufumij@163.com</p>
      </div>
      <div class="thirdline">
        <p>@工作</p>
        <p>@公司</p>
        <p>@官网</p>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "UserProfile",
};
</script>

<style scoped>
.user {
  background-image: url(../assets/backpaper.png);
  width: 100%;
  height: 400px;
  background-size: cover;
  border-radius: 20px;
}

.tap {
  width: 100%;
  height: 45%;
}

.firstline {
  width: 100%;
  padding: 10px;
  display: flex;
}

.firstline img {
  margin-bottom: 0px;
  width: 100px;
  height: 100px;
  margin-right: auto;
}

.firstline button {
  margin-right: 20px;
  padding: 12px;
  align-self: center;
  border-radius: 20%;
}

.user-name {
  font-size: 1.2em;
  color: white;
  margin: 0px;
  margin-left: 1em;
}

.user-email {
  color: darkgray;
  margin: 0px;
  margin-top: 0.5em;
  margin-left: 1em;
}

.thirdline {
  display: flex;
}

.thirdline p {
  width: 33%;
  text-align: center;
  color: whitesmoke;
}
</style>