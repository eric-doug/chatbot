<template>
  <div class="container">
    <div class="aside">
      <div class="logo">logo区域</div>
      <div>聊天选择区域</div>
    </div>
    <div class="main-right">
      <div class="header">标题区域</div>
      <chat-main ref="main"></chat-main>
      <chat-send @sendMsg="sendMsg" ref="send"  @keydown.enter.native="sendText($event)"></chat-send>
    </div>
  </div>
</template>

<script>
import EventSource from 'eventsource';
import {setDeviceInfo} from "@/utils/storage";
import ChatMain from "@/components/chat-main";
import ChatSend from "@/components/chat-send";
import {chat} from "@/api/sse";
export default {
name: "index",
  components: {ChatSend, ChatMain},
  data(){
  return{
    deviceType:1, //1pc 2h5
    uid:123456
  }
  },
  mounted() {
    this.clientWidth()
    this.initSSE();
  },
  methods:{
    initSSE(){
      const url = `${process.env.VUE_APP_API_BASE_URL}/createSse`; // 替换为实际的SSE端点URL
      this.eventSource = new EventSource(url,{headers:{uid:this.uid}});
      this.eventSource.onmessage = (event) => {
        // 处理接收到的SSE数据

        let payload;
        try{
          payload =JSON.parse(event.data);
        }catch (error) {
          // console.error("内容报异常")
        }
        if(undefined!=payload){
          this.$refs.main.appendMsg(payload)
          this.$refs.main.toBottom()
        }
      };
      this.eventSource.onerror = (error) => {
        // 处理连接错误
        console.error(error);
      };
    },
    sendMsg(textarea){
      if(null==this.eventSource){
        this.initSSE()
      }
      chat({question:textarea,uid:this.uid}).then(()=>{
        this.$refs.main.appendQ(textarea)
        this.$refs.send.echoQuestion()
      })
    },

    sendText(event){
      if (event.keyCode === 13) {
        this.$refs.send.sendMessages()
        this.$refs.main.toBottom()
        return false
      }
    },
    clientWidth(){
      //监听宽度,判断是否关闭对应的菜单
      let deviceInfo = 'pc';
      this.deviceType = 1
      if(document.body.clientWidth>document.body.clientHeight){
        setDeviceInfo(deviceInfo);
      }else{
        deviceInfo = 'h5'
        this.deviceType = 2
        setDeviceInfo(deviceInfo);
      }
    },
  },


}
</script>

<style scoped>
.container{
  text-align: center;
  display: flex;
  justify-content: flex-start;
  align-items: center;
}
.aside{
  width: 13vw;
  height: calc(100vh);
  background: #f1f0f0;
}
.logo{
  width: 13vw;
  height: 12vh;
}
.main-right{
  width: 87vw;
  height: calc(100vh);
}
.header{
  width: 100%;
  height:7vh;
  background: white;
}


</style>