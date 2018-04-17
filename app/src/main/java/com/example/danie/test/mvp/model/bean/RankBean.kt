package com.example.danie.test.mvp.model.bean

/**
 * Created by Administrator on 2018/4/16 0016.
 */
class RankBean {


  /**
   * tabInfo : {"tabList":[{"id":0,"name":"周排行","apiUrl":"http://baobab.kaiyanapp.com/api/v4/rankList/videos?strategy=weekly"},{"id":1,"name":"月排行","apiUrl":"http://baobab.kaiyanapp.com/api/v4/rankList/videos?strategy=monthly"},{"id":2,"name":"总排行","apiUrl":"http://baobab.kaiyanapp.com/api/v4/rankList/videos?strategy=historical"}],"defaultIdx":0}
   */

  private var tabInfo: TabInfoBean? = null

  fun getTabInfo(): TabInfoBean? {
    return tabInfo
  }

  fun setTabInfo(tabInfo: TabInfoBean) {
    this.tabInfo = tabInfo
  }

  class TabInfoBean {
    /**
     * tabList : [{"id":0,"name":"周排行","apiUrl":"http://baobab.kaiyanapp.com/api/v4/rankList/videos?strategy=weekly"},{"id":1,"name":"月排行","apiUrl":"http://baobab.kaiyanapp.com/api/v4/rankList/videos?strategy=monthly"},{"id":2,"name":"总排行","apiUrl":"http://baobab.kaiyanapp.com/api/v4/rankList/videos?strategy=historical"}]
     * defaultIdx : 0
     */

    var defaultIdx: Int = 0
    var tabList: List<TabListBean>? = null

    class TabListBean {
      /**
       * id : 0
       * name : 周排行
       * apiUrl : http://baobab.kaiyanapp.com/api/v4/rankList/videos?strategy=weekly
       */

      var id: Int = 0
      var name: String? = null
      var apiUrl: String? = null
    }
  }
}
