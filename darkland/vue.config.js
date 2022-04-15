const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  devServer:{
    proxy:{
      "/api":{
        target: "http://192.168.1.6:9001",
        changOrigin: true,
        pathRewrite:{
          "^/api":""
        }
      }
    }
  }
})
