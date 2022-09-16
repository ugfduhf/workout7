package com.example.minuteworkout7

import android.media.Image

class exerciseModel(
    private var id: Int,
    private var name: String,
    private var image: Int,
    private var isCompleted:Boolean,
    private var isSelected:Boolean
) {
    fun getId():Int{
        return id
    }
    fun setId(idS: Int){
        this.id = idS
    }
    fun getName():String{
        return name
    }
    fun setName(nameS :String){
        this.name = nameS
    }
    fun getImage():Int{
        return image
    }
    fun setImage(imageS: Int){
        this.image = imageS
    }
    fun getIsCompleted():Boolean{
        return isCompleted
    }
    fun setIsCompleted(isCompleted: Boolean){
        this.isCompleted = isCompleted
    }
    fun isSlected():Boolean{
        return isSelected
    }
    fun setImage(isSelected: Boolean){
        this.isSelected = isSelected
    }
}