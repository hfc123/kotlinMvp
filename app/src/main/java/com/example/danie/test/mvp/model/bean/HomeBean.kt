package com.example.danie.test.mvp.model.bean

import com.chad.library.adapter.base.entity.MultiItemEntity

class HomeBean {

    private var nextPublishTime: Long = 0
    private var dialog: String? = null
    private var newestIssueType: String? = null
    private var nextPageUrl: String? = null
    private var issueList: List<IssueListEntity>? = null

    fun setNextPublishTime(nextPublishTime: Long) {
        this.nextPublishTime = nextPublishTime
    }

    fun setDialog(dialog: String) {
        this.dialog = dialog
    }

    fun setNewestIssueType(newestIssueType: String) {
        this.newestIssueType = newestIssueType
    }

    fun setNextPageUrl(nextPageUrl: String) {
        this.nextPageUrl = nextPageUrl
    }

    fun setIssueList(issueList: List<IssueListEntity>) {
        this.issueList = issueList
    }

    fun getNextPublishTime(): Long {
        return nextPublishTime
    }

    fun getDialog(): String? {
        return dialog
    }

    fun getNewestIssueType(): String? {
        return newestIssueType
    }

    fun getNextPageUrl(): String? {
        return nextPageUrl
    }

    fun getIssueList(): List<IssueListEntity>? {
        return issueList
    }

    class IssueListEntity {
        var date: Long = 0
        var publishTime: Long = 0
        var releaseTime: Long = 0
        var count: Int = 0
        var itemList: List<ItemListEntity>? = null
        var type: String? = null

        class ItemListEntity : MultiItemEntity {
            override fun getItemType(): Int {
                return 3;
            }

            /**
             * data : {"dataType":"TextHeader","text":"- Apr. 09, Brunch -","adTrack":null,"font":"lobster"}
             * adIndex : -1
             * tag : null
             * id : 0
             * type : textHeader
             */
            var data: DataEntity? = null
            var adIndex: Int = 0
            var tag: String? = null
            var id: Int = 0
            var type: String? = null

            class DataEntity {
                /**
                 * dataType : TextHeader
                 * text : - Apr. 09, Brunch -
                 * adTrack : null
                 * font : lobster
                 */
                var dataType: String? = null
                var text: String? = null
                var adTrack: String? = null
                var font: String? = null
            }
        }
    }

}