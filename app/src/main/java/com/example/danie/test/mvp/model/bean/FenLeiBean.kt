package com.example.danie.test.mvp.model.bean

class FenLeiBean {

    /**
     * bgColor :
     * defaultAuthorId : 2160
     * headerImage : http://img.kaiyanapp.com/c9b19c2f0a2a40f4c45564dd8ea766d3.png
     * name : 时尚
     * alias : null
     * description : 优雅地行走在潮流尖端
     * id : 24
     * bgPicture : http://img.kaiyanapp.com/22192a40de238fe853b992ed57f1f098.jpeg
     */
    private var bgColor: String? = null
    private var defaultAuthorId: Int = 0
    private var headerImage: String? = null
    private var name: String? = null
    private var alias: String? = null
    private var description: String? = null
    private var id: Int = 0
    private var bgPicture: String? = null

    fun setBgColor(bgColor: String) {
        this.bgColor = bgColor
    }

    fun setDefaultAuthorId(defaultAuthorId: Int) {
        this.defaultAuthorId = defaultAuthorId
    }

    fun setHeaderImage(headerImage: String) {
        this.headerImage = headerImage
    }

    fun setName(name: String) {
        this.name = name
    }

    fun setAlias(alias: String) {
        this.alias = alias
    }

    fun setDescription(description: String) {
        this.description = description
    }

    fun setId(id: Int) {
        this.id = id
    }

    fun setBgPicture(bgPicture: String) {
        this.bgPicture = bgPicture
    }

    fun getBgColor(): String? {
        return bgColor
    }

    fun getDefaultAuthorId(): Int {
        return defaultAuthorId
    }

    fun getHeaderImage(): String? {
        return headerImage
    }

    fun getName(): String? {
        return name
    }

    fun getAlias(): String? {
        return alias
    }

    fun getDescription(): String? {
        return description
    }

    fun getId(): Int {
        return id
    }

    fun getBgPicture(): String? {
        return bgPicture
    }
}