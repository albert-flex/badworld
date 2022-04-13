<template>
  <div
    class="mask"
    :style="showMask ? 'display:block' : 'display:none;zIndex=5'"
  ></div>
</template>

<script>
export default {
    name: 'MaskBack',
    props:{
      showMask: Boolean,
    }
}
</script>

<style scoped>
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