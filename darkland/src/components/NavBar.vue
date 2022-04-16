<template>
    <ul class="main-nav">
      <li class="push sitetitle"><button @click="$emit('toHome')">Bad World</button></li>
      <li><button @click="$emit('toAboutSite')">关于网站</button></li>
      <li>
        <button @click="openLoginForm"><img src="../assets/user.svg" /></button>
      </li>
    </ul>

  <a-mask :showMask="showLogin" />
  <form class="login-box" id="login-box" :style="showLogin?'display:block':'display:none'">
      <p>
        <label for="username">用户名:</label>
        <input type="text" name="username" id="username" v-model="loginData.userName"/>
      </p>
      <p>
        <label for="password">密 码:</label>
        <input type="password" name="password" id="password" v-model="loginData.password"/>
      </p>
      <p>
        <ul class="tool">
          <li><button @click.stop.prevent="toRegister">注册</button></li>
          <li><button @click.stop.prevent="login">确认登录</button></li>
          <li><button @click.stop.prevent="closeLoginForm">关闭</button></li>
        </ul>
      </p>
  </form>

  <b-mask :showMask="showRegister" />
  <form class="register-box" :style="showRegister?'display:block':'display:none'">
    <p>
      <label for="username2">用户名:</label>
      <input type="text" name="username2" id="username2" v-model="registerData.userName">
    </p>
    <p>
      <label for="password2">密 码:</label>
      <input type="text" name="password2" id="password2" v-model="registerData.password">
    </p>
    <p>
      <label for="email">邮 箱:</label>
      <input type="email" name="email" id="email" v-model="registerData.profile.email">
    </p>
    <p>
      <ul class="tool">
        <li><button @click.stop.prevent="toLogin">登录</button></li>
        <li><button @click.stop.prevent="register">确认注册</button></li>
        <li><button @click.stop.prevent="closeRegisterForm">关闭</button></li>
      </ul>
    </p>
  </form>
</template>

<script>
import MaskBack from "./MaskBack.vue";
import axios from "axios";
axios.defaults.withCredentials=true;
axios.defaults.baseURL="/api";

export default {
  name: "NavBar",
  emits: ["toHome", "toAboutSite","logined"],
  data() {
    return {
      //TODO: Login
      showLogin: false,
      showRegister: false,
      loginData: {
        userName:"",
        password:"",
      },
      registerData: {
        userName:"",
        password:"",
        profile: {
          email:"",
        },
      },
    };
  },
  components: {
    AMask: MaskBack,
    BMask: MaskBack,
  },
  methods: {
    openLoginForm() {
      this.showRegister = false;
      this.showLogin = true;
    },
    openRegisterForm() {
      this.showLogin = false;
      this.showRegister = true;
    },
    closeLoginForm() {
      this.showLogin = false;
    },
    closeRegisterForm() {
      this.showRegister = false;
    },
    login() {
      let params=new FormData();
      params.append("username",this.loginData.userName);
      params.append("password",this.loginData.password);
      let config ={
        headers: {"Content-Type":"application/x-www-form-urlencoded"}
      };
      let url="login";
      axios.post(url,params,config)
      .then(response=> {
        if(response.status==200) {
          alert("登录成功!");
          this.isLogined=true;
          this.$emit("logined");
        }else{
          alert("登录失败");
        }
      }).catch(error=>{
        alert(error);
      })
    },
    register() {
        let url="/user/register";
        axios.post(url,this.registerData)
        .then(response=>{
        if(response.status==200){
          alert("查询成功!");
        }else{
          alert("查询失败");
        }
      }).catch(error=>{
        alert(error);
      })
    },
    toRegister() {
      this.openRegisterForm();
    },
    toLogin() {
      this.openLoginForm();
    },
  },
};
</script>

<style scoped>
.main-nav {
  display: flex;
  list-style: none;
  background-color: #2c3e50;
  color: aliceblue;
  margin: 0px;
  padding-left: 5px;
  padding-right: 5px;
  height: 80px;
  position: sticky;
  top: 0px;
  z-index: 2;
}

.sitetitle {
  font-size: 2em;
}

.main-nav li {
  align-self: center;
  text-align: center;
}

.push {
  margin-right: auto;
}

.main-nav button {
  font-size: 1.2em;
  margin-right: 20px;
  background: none;
  border: none;
  color: white;
  cursor: pointer;
}

.main-nav img {
  width: 50px;
  height: 50px;
  cursor: pointer;
}

.login-box,
.register-box {
  position: fixed;
  padding: 20px;
  margin: 20px;
  width: 400px;
  height: 200px;
  left: 50%;
  margin-left: -200px;
  margin-top: 100px;
  background-color: #2c3e50;
  border-radius: 20px;
  color: white;
  z-index: 20;
}

.register-box {
  height: 300px;
}

.login-box p,
.register-box p {
  display: flex;
}

.login-box label,
.register-box label {
  height: 30%;
  padding: 10px;
  width: 30%;
  margin-left: 20px;
}

.login-box input,
.register-box input {
  height: 30%;
  padding: 10px;
  width: 60%;
  margin-right: 20px;
  border-radius: 10px;
  border-width: 0px;
}

.tool {
  list-style: none;
  display: flex;
  align-items: center;
}

.tool button {
  border-radius: 10px;
  width: 100px;
  height: 40px;
  font-size: 1.1em;
}

.tool li {
  margin: 10px;
}

.mask {
  display: block;
  position: fixed;
  top: 0px;
  left: 0px;
  width: 100%;
  height: 240%;
  background-color: #111e;
  z-index: 5;
}
</style>