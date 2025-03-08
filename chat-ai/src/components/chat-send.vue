<template>
  <div class="chat-send">
    <div style="display: flex;justify-content: center;align-content: center;align-items: center">
    <input type="textarea" v-model="textarea" class="send-input" placeholder="  请输入您的问题 按 enter 即发送" />
      <span @click="sendMessages">
        <img src="./../asserts/image/send.png" width="48" height="48">
      </span>
    </div>
  </div>
</template>

<script>
export default {
name: "chat-send",
  data(){
  return{
    textarea:"",
  }
  },
  methods:{
    sendMessages(){
      if(this.textarea==''||this.textarea==null){
        return;
      }
      if(this.textarea.length>2000){
        open("问题过长,请将您的问题多次会话描述")
        return;
      }
      this.$emit("sendMsg",this.textarea,false)
    },
    echoQuestion(){
      this.textarea="";
    }
  }
}
</script>

<style scoped>

.chat-send{
  text-align: center;
  border-radius: 10px;
  left: 56%;
  top: 92%;
  position: absolute;
  transform: translate(-50%,-50%);
}
.chat-send input{
  height: 6vh;
  width: 40vw;
  border-radius: 10px;
  background: rgba(254,255,253,0.5)
}
.chat-send button{
  height: 3vh;
  width: 3vw;
  border-radius: 10px;
}
</style>