<template>
    <div>
        <!--头部-->
    <Aheader />
    
    <!--主体-->

  <div style="display: flex">
  <!--侧边栏 -->
  <Aside></Aside>
  <!--内容区域，即router显示-->
  <router-view style="flex:1" />
  </div>
    </div>
</template>

<script>
import Aheader from '@/components/Aheader'
import Aside from '@/components/Aside'
export default {
  name: 'Layout',
  components:{
    Aheader,
    Aside
  }
}
</script>